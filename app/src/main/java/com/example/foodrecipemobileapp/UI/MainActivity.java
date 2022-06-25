package com.example.foodrecipemobileapp.UI;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.foodrecipemobileapp.Adapters.RandomRecipeAdapter;

import com.example.foodrecipemobileapp.Datas.Repositories.RecipeRepository;
import com.example.foodrecipemobileapp.Listeners.RandomRecipeResponseListener;
import com.example.foodrecipemobileapp.Listeners.RecipeClickListener;
import com.example.foodrecipemobileapp.Models.Intermediates.RecipeWithExtendedIngredientsAndInstructions;
import com.example.foodrecipemobileapp.Models.Recipe;
import com.example.foodrecipemobileapp.Models.Responses.RandomRecipeApiResponse;
import com.example.foodrecipemobileapp.R;
import com.example.foodrecipemobileapp.Datas.Remotes.RequestManager;
import com.example.foodrecipemobileapp.Utils.EndlessRecyclerOnScrollListener;
import com.example.foodrecipemobileapp.Utils.InternetUtils;
import com.example.foodrecipemobileapp.databinding.ActivityMainBinding;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    public ActivityMainBinding binding;

    public RecyclerView recyclerView;
    public SearchView searchView;

    public Spinner spinner;
    public RequestManager manager;

    public RandomRecipeAdapter randomRecipeAdapter;
    public List<String> tags = new ArrayList<>();

    public List<Recipe> recipes;

    public RecipeRepository recipeRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        recipeRepository = RecipeRepository.getInstance(getApplication());
        if(InternetUtils.isInternetConnected(getApplicationContext())){
            deteleAllRecipe();
        }
        else{
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_LONG).show();
            populateData("", 10);
        }
        recipes = new ArrayList<>();
        manager = new RequestManager(this);

        findViews();
        initializeRecyclerViewAdapter();
        initializeSearchView();
        initializeDropdown();
        setRecyclerViewOnScrollListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        deteleAllRecipe();
    }

    private void deteleAllRecipe(){
        Completable.fromAction(() -> recipeRepository.deteleAll())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    private void findViews(){
        searchView = binding.searchViewHome;
        spinner = binding.spinnerTags;
        recyclerView = binding.recyclerRandom;
        recyclerView.setHasFixedSize(true);
    }

    private void setRecyclerViewOnScrollListener(){
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                tags.clear();
                tags.add(binding.spinnerTags.getSelectedItem().toString());
                manager.getRandomRecipes(moreRandomRecipeResponseListener, tags, 10);
            }
        });
    }

    private void initializeRecyclerViewAdapter(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        randomRecipeAdapter = new RandomRecipeAdapter(MainActivity.this, recipes, recipeClickListener);
        recyclerView.setAdapter(randomRecipeAdapter);
    }

    private void initializeSearchView(){
        // Add search functions
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                tags.clear();
                tags.add(s);
                manager.getRandomRecipes(randomRecipeResponseListener, tags, 10);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    private void initializeDropdown(){
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.tags,
                R.layout.spinner_text
        );
        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(spinnerSelectedListener);
    }

    private void populateData(String tag, int amount){
        recipeRepository.getRecipesByTag(tag, amount)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new MaybeObserver<List<RecipeWithExtendedIngredientsAndInstructions>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onSuccess(@NonNull List<RecipeWithExtendedIngredientsAndInstructions> recipeList)
                    {
                        Log.d("SUCCESS", "Success loading recipes");
                        recipes.clear();
                        recipeList.forEach(rec -> recipes.add(rec.recipe));
                        randomRecipeAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(MainActivity.this, "ERROR populating recipes", Toast.LENGTH_LONG).show();
                        Log.d("ERROR", "Error loading recipes");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("COMPLETE", "Complete loading recipes");
                    }
                });
    }

    //Get data from api
    private final RandomRecipeResponseListener randomRecipeResponseListener = new RandomRecipeResponseListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void didFetch(RandomRecipeApiResponse response, String message) {
            saveToLocal(response);
            populateData("", 10);
        }

        @Override
        public void didError(String message) {
        }
    };

    //Get more data on scroll
    private final RandomRecipeResponseListener moreRandomRecipeResponseListener = new RandomRecipeResponseListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void didFetch(RandomRecipeApiResponse response, String message) {
            recipes.addAll(response.recipes);
            recipeRepository.insertRecipes(response.recipes);
            randomRecipeAdapter.notifyDataSetChanged();
        }

        @Override
        public void didError(String message) {

        }
    };

    // Event select item in spinner(tags in api)
    private final AdapterView.OnItemSelectedListener spinnerSelectedListener = new AdapterView.OnItemSelectedListener() {

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            tags.clear();
            tags.add(adapterView.getSelectedItem().toString());
            manager.getRandomRecipes(randomRecipeResponseListener, tags, 10);
            setRecyclerViewOnScrollListener();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    // Event click item in recyclerview
    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            Intent intent = new Intent(MainActivity.this, RecipeDetailsActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtra("id", Long.parseLong(id));
            startActivity(intent);
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void saveToLocal(RandomRecipeApiResponse response){
        recipeRepository.insertRecipes(response.recipes);
//        recipeRepository.populateDatas(response.recipes);
    }
}
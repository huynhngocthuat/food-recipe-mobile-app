package com.example.foodrecipemobileapp.UI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.foodrecipemobileapp.Adapters.RandomRecipeAdapter;
import com.example.foodrecipemobileapp.Datas.Repositories.RecipeRepository;
import com.example.foodrecipemobileapp.Listeners.RandomRecipeResponseListener;
import com.example.foodrecipemobileapp.Listeners.RecipeClickListener;
import com.example.foodrecipemobileapp.Models.Recipe;
import com.example.foodrecipemobileapp.Models.Responses.RandomRecipeApiResponse;
import com.example.foodrecipemobileapp.R;
import com.example.foodrecipemobileapp.Datas.Remotes.RequestManager;
import com.example.foodrecipemobileapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public ActivityMainBinding binding;

    public RecyclerView recyclerView;
    public SearchView searchView;

    public Spinner spinner;
    public ProgressDialog dialog;
    public RequestManager manager;

    public RandomRecipeAdapter randomRecipeAdapter;
    public List<String> tags = new ArrayList<>();

    public RecipeRepository recipeRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        recipeRepository = RecipeRepository.getInstance(getApplication());

        findViews();
        dialogLoading();

        searchViewInitialize();
        dropDownInitialize();

        manager = new RequestManager(this);
//        manager.getRandomRecipes(randomRecipeResponseListener);
//        dialog.show();

    }

    private void findViews(){
        searchView = binding.searchViewHome;
        spinner = binding.spinnerTags;
        recyclerView = binding.recyclerRandom;
        recyclerView.setHasFixedSize(true);
    }

    private void dialogLoading(){
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading......");
    }

    private void searchViewInitialize(){
        // Add search functions
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                tags.clear();
                tags.add(s);
                manager.getRandomRecipes(randomRecipeResponseListener, tags);
                dialog.show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    private void dropDownInitialize(){
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.tags,
                R.layout.spinner_text
        );
        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(spinnerSelectedListener);
    }

//    private void populateData(){
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 1);
//        recyclerView.setLayoutManager(gridLayoutManager);
//        randomRecipeAdapter = new RandomRecipeAdapter(MainActivity.this, response.recipes, recipeClickListener);
//        recyclerView.setAdapter(randomRecipeAdapter);
//    }

    //Get data from api
    private final RandomRecipeResponseListener randomRecipeResponseListener = new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiResponse response, String message) {
            dialog.dismiss();
            recipeRepository.insertRecipes(response.recipes);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 1);
            recyclerView.setLayoutManager(gridLayoutManager);
            randomRecipeAdapter = new RandomRecipeAdapter(MainActivity.this, response.recipes, recipeClickListener);
            recyclerView.setAdapter(randomRecipeAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    // Event select item in spinner(tags in api)
    private final AdapterView.OnItemSelectedListener spinnerSelectedListener = new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            tags.clear();
            tags.add(adapterView.getSelectedItem().toString());
            manager.getRandomRecipes(randomRecipeResponseListener, tags);
            dialog.show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    // Event click item in recyclerview
    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            startActivity(new Intent(
                    MainActivity.this,
                    RecipeDetailsActivity.class).putExtra("id", id));
        }
    };
}
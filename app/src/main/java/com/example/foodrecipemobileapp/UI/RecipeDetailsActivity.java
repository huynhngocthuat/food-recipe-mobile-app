package com.example.foodrecipemobileapp.UI;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.IntentCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodrecipemobileapp.Adapters.ExtendedIngredientsAdapter;
import com.example.foodrecipemobileapp.Adapters.InstructionsAdapter;
import com.example.foodrecipemobileapp.Adapters.SimilarRecipeAdapter;
import com.example.foodrecipemobileapp.Datas.Repositories.RecipeRepository;
import com.example.foodrecipemobileapp.Listeners.RecipeByIdResponseListener;
import com.example.foodrecipemobileapp.Listeners.RecipeClickListener;
import com.example.foodrecipemobileapp.Listeners.SimilarRecipesListener;
import com.example.foodrecipemobileapp.Models.Intermediates.RecipeWithExtendedIngredientsAndInstructions;
import com.example.foodrecipemobileapp.Models.Recipe;
import com.example.foodrecipemobileapp.Models.Responses.SimilarRecipeResponse;
import com.example.foodrecipemobileapp.Datas.Remotes.RequestManager;
import com.example.foodrecipemobileapp.databinding.ActivityRecipeDetailsBinding;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RecipeDetailsActivity extends AppCompatActivity {

    long idRecipe;
    TextView textViewMealName, textViewMealSource, textViewMealSummary;
    ImageView imageViewMealImage;
    ImageView homeButton;
    RecyclerView recyclerMealingredients, recyclerMealSimilar, recyclerMealInstructions;

    RequestManager manager;
    ProgressDialog dialog;
    ExtendedIngredientsAdapter extendedIngredientsAdapter;
    SimilarRecipeAdapter similarRecipeAdapter;
    InstructionsAdapter instructionsAdapter;
    ActivityRecipeDetailsBinding binding;

    RecipeRepository recipeRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecipeDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        findViews();
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecipeDetailsActivity.this.finish();
            }
        });

        recipeRepository = RecipeRepository.getInstance(getApplication());

        idRecipe = getIntent().getLongExtra("id",0);
        manager = new RequestManager(this);
        recipeRepository.getRecipeById(idRecipe)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new MaybeObserver<RecipeWithExtendedIngredientsAndInstructions>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }
                    @Override
                    public void onSuccess(@NonNull RecipeWithExtendedIngredientsAndInstructions recipeFull) {
                        // Set recipe general informations
                        textViewMealName.setText(recipeFull.recipe.title);
                        textViewMealSource.setText(recipeFull.recipe.sourceName);
                        textViewMealSummary.setText(android.text.Html.fromHtml(recipeFull.recipe.summary));
                        Picasso.get().load(recipeFull.recipe.image).into(imageViewMealImage);

                        // Extended ingredients
                        recyclerMealingredients.setHasFixedSize(true);
                        recyclerMealingredients.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        extendedIngredientsAdapter = new ExtendedIngredientsAdapter(RecipeDetailsActivity.this, recipeFull.extendedIngredients);
                        recyclerMealingredients.setAdapter(extendedIngredientsAdapter);

                        // Instructions
                        recyclerMealInstructions.setHasFixedSize(true);
                        recyclerMealInstructions.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this, LinearLayoutManager.VERTICAL, false));
                        instructionsAdapter = new InstructionsAdapter(RecipeDetailsActivity.this, recipeFull.analyzedInstructions);
                        recyclerMealInstructions.setAdapter(instructionsAdapter);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("ERR", "Fail to load recipe");
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        manager.getSimilarRecipes(similarRecipesListener, (int) idRecipe);
    }

    private void findViews() {
        textViewMealName = binding.textViewMealName;
        textViewMealSource = binding.textViewMealSource;
        textViewMealSummary = binding.textViewMealSummary;
        imageViewMealImage = binding.imageViewMealImage;
        recyclerMealingredients = binding.recyclerMealIngredients;
        recyclerMealSimilar = binding.recyclerMealSimilar;
        recyclerMealInstructions = binding.recyclerMealInstructions;
        homeButton = binding.homeBtn;
    }

    // Display similar recipes
    private final SimilarRecipesListener  similarRecipesListener = new SimilarRecipesListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void didFetch(List<SimilarRecipeResponse> responses, String message) {
            recyclerMealSimilar.setHasFixedSize(true);
            recyclerMealSimilar.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));
            similarRecipeAdapter = new SimilarRecipeAdapter(RecipeDetailsActivity.this, responses, recipeClickListener);
            recyclerMealSimilar.setAdapter(similarRecipeAdapter);
            responses.forEach(recipe -> {
                manager.getRecipeById(recipeByIdResponseListener, (int)recipe.id);
            });
        }

        @Override
        public void didError(String message) {
        }
    };

    private final RecipeByIdResponseListener recipeByIdResponseListener = new RecipeByIdResponseListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void didFetch(Recipe response, String message) {
            List<Recipe> recipes = new ArrayList<>();
            recipes.add(response);
            recipeRepository.insertRecipes(recipes);
        }

        @Override
        public void didError(String message) {

        }
    };

    // Set event click similar recipes
    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            int idRecipe = (int) Float.parseFloat(id);

            Intent intent = new Intent(RecipeDetailsActivity.this, RecipeDetailsActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("id", Long.parseLong(String.valueOf(idRecipe)));
            startActivity(intent);
        }
    };
}










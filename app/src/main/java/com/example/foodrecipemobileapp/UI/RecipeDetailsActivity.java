package com.example.foodrecipemobileapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodrecipemobileapp.Adapters.IngredientsAdapter;
import com.example.foodrecipemobileapp.Adapters.InstructionsAdapter;
import com.example.foodrecipemobileapp.Adapters.SimilarRecipeAdapter;
import com.example.foodrecipemobileapp.Listeners.InstructionsListener;
import com.example.foodrecipemobileapp.Listeners.RecipeClickListener;
import com.example.foodrecipemobileapp.Listeners.RecipeDetailsListener;
import com.example.foodrecipemobileapp.Listeners.SimilarRecipesListener;
import com.example.foodrecipemobileapp.Models.Responses.InstructionsResponse;
import com.example.foodrecipemobileapp.Models.Responses.RecipeDetailsResponse;
import com.example.foodrecipemobileapp.Models.Responses.SimilarRecipeResponse;
import com.example.foodrecipemobileapp.Datas.Remotes.RequestManager;
import com.example.foodrecipemobileapp.databinding.ActivityRecipeDetailsBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeDetailsActivity extends AppCompatActivity {

    int idRecipe;
    TextView textViewMealName, textViewMealSource, textViewMealSummary;
    ImageView imageViewMealImage;
    RecyclerView recyclerMealingredients, recyclerMealSimilar, recyclerMealInstructions;

    RequestManager manager;
    ProgressDialog dialog;
    IngredientsAdapter ingredientsAdapter;
    SimilarRecipeAdapter similarRecipeAdapter;
    InstructionsAdapter instructionsAdapter;
    ActivityRecipeDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecipeDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        findViews();

        idRecipe = Integer.parseInt(getIntent().getStringExtra("id"));
        manager = new RequestManager(this);
        manager.getRecipeDetails(recipeDetailsListener, idRecipe);
        manager.getSimilarRecipes(similarRecipesListener, idRecipe);
        manager.getInstructions(instructionsListener, idRecipe);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading Details....");
        dialog.show();
    }

    private void findViews() {
        textViewMealName = binding.textViewMealName;
        textViewMealSource = binding.textViewMealSource;
        textViewMealSummary = binding.textViewMealSummary;
        imageViewMealImage = binding.imageViewMealImage;
        recyclerMealingredients = binding.recyclerMealIngredients;
        recyclerMealSimilar = binding.recyclerMealSimilar;
        recyclerMealInstructions = binding.recyclerMealInstructions;
    }

    // set data on view
    private final RecipeDetailsListener recipeDetailsListener = new RecipeDetailsListener() {
        @Override
        public void didFetch(RecipeDetailsResponse response, String message) {
            dialog.dismiss();
            textViewMealName.setText(response.title);
            textViewMealSource.setText(response.sourceName);
            textViewMealSummary.setText(android.text.Html.fromHtml(response.summary));
            Picasso.get().load(response.image).into(imageViewMealImage);

            recyclerMealingredients.setHasFixedSize(true);
            recyclerMealingredients.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));
            ingredientsAdapter = new IngredientsAdapter(RecipeDetailsActivity.this, response.extendedIngredients);
            recyclerMealingredients.setAdapter(ingredientsAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    // Display similar recipes
    private final SimilarRecipesListener  similarRecipesListener = new SimilarRecipesListener() {
        @Override
        public void didFetch(List<SimilarRecipeResponse> responses, String message) {
            recyclerMealSimilar.setHasFixedSize(true);
            recyclerMealSimilar.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));
            similarRecipeAdapter = new SimilarRecipeAdapter(RecipeDetailsActivity.this, responses, recipeClickListener);
            recyclerMealSimilar.setAdapter(similarRecipeAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    // Set event click similar recipes
    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            int idRecipe = (int)Float.parseFloat(id);
//            Toast.makeText(RecipeDetailsActivity.this, id, Toast.LENGTH_SHORT).show();
            System.out.println(idRecipe);
            startActivity(new Intent(RecipeDetailsActivity.this, RecipeDetailsActivity.class)
            .putExtra("id", Integer.toString(idRecipe)));

        }
    };

    // Display instructions step by step
    private final InstructionsListener instructionsListener = new InstructionsListener() {
        @Override
        public void didFetch(List<InstructionsResponse> response, String message) {
            recyclerMealInstructions.setHasFixedSize(true);
            recyclerMealInstructions.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this, LinearLayoutManager.VERTICAL, false));
            instructionsAdapter = new InstructionsAdapter(RecipeDetailsActivity.this, response);
            recyclerMealInstructions.setAdapter(instructionsAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };
}










package com.example.foodrecipemobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodrecipemobileapp.Adapters.IngredientsAdapter;
import com.example.foodrecipemobileapp.Listeners.RecipeDetailsListener;
import com.example.foodrecipemobileapp.Models.RecipeDetailsResponse;
import com.squareup.picasso.Picasso;

public class RecipeDetailsActivity extends AppCompatActivity {

    int id;
    TextView textViewMealName, textViewMealSource, textViewMealSummary;
    ImageView imageViewMealImage;
    RecyclerView recyclerMealingredients;
    RequestManager manager;
    ProgressDialog dialog;
    IngredientsAdapter ingredientsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        findViews();

        id = Integer.parseInt(getIntent().getStringExtra("id"));
        manager = new RequestManager(this);
        manager.getRecipeDetails(recipeDetailsListener, id);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading Details....");
        dialog.show();
    }

    private void findViews() {
        textViewMealName = findViewById(R.id.textView_meal_name);
        textViewMealSource = findViewById(R.id.textView_meal_source);
        textViewMealSummary = findViewById(R.id.textView_meal_summary);
        imageViewMealImage = findViewById(R.id.imageView_meal_image);
        recyclerMealingredients = findViewById(R.id.recycler_meal_ingredients);
    }

    private final RecipeDetailsListener recipeDetailsListener = new RecipeDetailsListener() {
        @Override
        public void didFetch(RecipeDetailsResponse response, String message) {
            dialog.dismiss();
            textViewMealName.setText(response.title);
            textViewMealSource.setText(response.sourceName);
            textViewMealSummary.setText(response.summary);
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
}
package com.example.foodrecipemobileapp.Datas.Locals;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.foodrecipemobileapp.Models.AnalyzedInstruction;
import com.example.foodrecipemobileapp.Models.Equipment;
import com.example.foodrecipemobileapp.Models.ExtendedIngredient;
import com.example.foodrecipemobileapp.Models.Ingredient;
import com.example.foodrecipemobileapp.Models.Intermediates.RecipeWithExtendedIngredientsAndInstructions;
import com.example.foodrecipemobileapp.Models.Length;
import com.example.foodrecipemobileapp.Models.Measures;
import com.example.foodrecipemobileapp.Models.Metric;
import com.example.foodrecipemobileapp.Models.Recipe;
import com.example.foodrecipemobileapp.Models.Step;
import com.example.foodrecipemobileapp.Models.Us;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;

@Dao
public abstract class RecipeDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertRecipe(Recipe recipe);
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertRecipes(List<Recipe> recipes);

    // Insert recipe's properties
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract List<Long> insertExtendedIngredients(List<ExtendedIngredient> ingredients);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract List<Long> insertInstructions(List<AnalyzedInstruction> instructions);

    // Insert steps and their properties
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract List<Long> insertSteps(List<Step> steps);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertIngredients(List<Ingredient> ingredients);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertEquipments(List<Equipment> equipments);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertLength(Length length);

    // Insert Measures and their properties
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract List<Long> insertMeasures(List<Measures> measures);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertUsMeasure(List<Us> us);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertMetricMeasure(List<Metric> metric);

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Transaction
    public void insertAll(List<Recipe> recipes) {
        //
        List<ExtendedIngredient> extendedIngredients;
        List<AnalyzedInstruction> analyzedInstructions;

        insertRecipes(recipes);
        // Set recipe foreign key id for it's instructions and extended ingredients
        recipes.forEach(recipe -> {
            Log.d("RECIPE_ID", String.format("%d", recipe.idRecipe));
            recipe.extendedIngredients.forEach(extendedIngredient -> extendedIngredient.idFkRecipe = recipe.idRecipe);
            // Insert extended ingredients
            List<Long> extIngredientIds = insertExtendedIngredients(recipe.extendedIngredients);
            // Set measure's extended ingredient foreign key
            List<Measures> measures = new ArrayList<>();
            for (int index = 0; index < extIngredientIds.size(); index++) {
                recipe.extendedIngredients
                        .get(index)
                        .measures.idFkExtendedIngredient = extIngredientIds.get(index);
                measures.add(recipe.extendedIngredients.get(index).measures);
            }
            // Insert measures
            List<Long> measuresIds = insertMeasures(measures);

            // Set metric and us's measure foreign key
            List<Us> usMeasures = new ArrayList<>();
            List<Metric> metricMeasures = new ArrayList<>();
            for(int index = 0; index < measuresIds.size(); index++){
                measures.get(index).us.idFkMeasures = measuresIds.get(index);
                usMeasures.add(measures.get(index).us);
                measures.get(index).metric.idFkMeasures = measuresIds.get(index);
                metricMeasures.add(measures.get(index).metric);
            }
            insertUsMeasure(usMeasures);
            insertMetricMeasure(metricMeasures);

            // Insert analyzed instructions
            recipe.analyzedInstructions
                    .forEach(analyzedInstruction -> analyzedInstruction.idFkRecipe = recipe.idRecipe);
            List<Long> instructionIds = insertInstructions(recipe.analyzedInstructions);
            instructionIds.forEach(ins -> Log.d("IDS", String.format("%d", ins)));
            // Get list of step id from step insertion
            List<List<Long>> instructionStepIds = new ArrayList<>();
            for(int index = 0; index < instructionIds.size(); index++){
                int finalIndex = index;
                recipe.analyzedInstructions
                        .forEach(analyzedInstruction -> {
                            analyzedInstruction.steps
                                    .forEach(step -> step.idFkInstruction = instructionIds.get(finalIndex));
                            instructionStepIds.add(insertSteps(analyzedInstruction.steps));
                        });
            }
            Log.d("LENGTH", String.format("%d", instructionStepIds.size()));
            // Set id step (return from insertion) for insert step's ingredients and equipments
            for(int istrStepIndex = 0; istrStepIndex < instructionStepIds.size(); istrStepIndex++){
                for(int stepIndex = 0; stepIndex < instructionStepIds.get(istrStepIndex).size(); stepIndex++){
                    recipe
                            .analyzedInstructions.get(istrStepIndex)
                            .steps.get(stepIndex)
                            .idStep = instructionStepIds.get(istrStepIndex).get(stepIndex);
                }
            }
            // Set step foreign id for ingredients and equipments then
            recipe.analyzedInstructions
                    .forEach(analyzedInstruction -> {
                        analyzedInstruction.steps
                                .forEach(step -> {
                                    step.ingredients
                                            .forEach(ingredient -> ingredient.idFkStep = step.idStep);
                                    insertIngredients(step.ingredients);
                                    step.equipments
                                            .forEach(equipment -> equipment.idFkStep = step.idStep);
                                    insertEquipments(step.equipments);
                                    if(step.length != null){
                                        step.length.idFkStep = step.idStep;
                                        insertLength(step.length);
                                    }
                                });
                    });

        });
    }

    @Query("DELETE FROM us")
    public abstract void deleteUsMeasure();
    @Query("DELETE FROM metric")
    public abstract void deleteMetricMeasure();
    @Query("DELETE FROM measures")
    public abstract void deleteMeasures();
    @Query("DELETE FROM ingredient")
    public abstract void deleteIngredient();
    @Query("DELETE FROM equipment")
    public abstract void deleteEquipment();
    @Query("DELETE FROM step")
    public abstract void deleteStep();
    @Query("DELETE FROM instruction")
    public abstract void deleteInstruction();
    @Query("DELETE FROM extended_ingredient")
    public abstract void deleteExtendedIngredient();

    @Query("DELETE FROM recipe")
    public abstract void deleteAllRecipe();

    @Transaction
    public void deleteAll(){
        deleteUsMeasure();
        deleteMetricMeasure();
        deleteMeasures();
        deleteIngredient();
        deleteEquipment();
        deleteStep();
        deleteInstruction();
        deleteExtendedIngredient();
        deleteAllRecipe();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Transaction
    public void deleteAndInsert(List<Recipe> recipes){
        deleteAll();
        insertAll(recipes);
    }

    @Transaction
    @Query("SELECT * FROM recipe")
    public abstract Maybe<List<RecipeWithExtendedIngredientsAndInstructions>> getRecipeWithIngredientsAndInstructions();

}

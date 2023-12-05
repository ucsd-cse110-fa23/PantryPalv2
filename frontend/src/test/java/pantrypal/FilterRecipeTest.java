package pantrypal;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class FilterRecipeTest {
    @Test
    void testFilterBreakfast() {
        // Given a list of recipes
        ArrayList<RecipeData> recipes = new ArrayList<RecipeData>();
        ArrayList<String> mealTypes = new ArrayList<String>();
        mealTypes.add("Breakfast");

        String[] ingredients = { "Chicken" };
        recipes.add(new RecipeData("Fried Chicken", ingredients, "Deep fry it!", "Lunch"));
        recipes.add(new RecipeData("Chicken Soup", ingredients, "Boil it!", "Breakfast"));
        recipes.add(new RecipeData("Chicken Salad", ingredients, "Mix it!", "Dinner"));
        recipes.add(new RecipeData("Chicken Omelette", ingredients, "Fold it!", "Breakfast"));

        // When the user clicks on the “sort alphabetically” button
        ArrayList<RecipeData> filteredRecipes = FilterRecipes.filterRecipeByMeal(recipes, mealTypes);

        // Then the recipes should be sorted alphabetically
        assertEquals("Chicken Soup", filteredRecipes.get(0).title);
        assertEquals("Chicken Omelette", filteredRecipes.get(1).title);
    }

    @Test
    void testFilterLunch() {
        // Given a list of recipes
        ArrayList<RecipeData> recipes = new ArrayList<RecipeData>();
        ArrayList<String> mealTypes = new ArrayList<String>();
        mealTypes.add("Lunch");

        String[] ingredients = { "Chicken" };
        recipes.add(new RecipeData("Fried Chicken", ingredients, "Deep fry it!", "Lunch"));
        recipes.add(new RecipeData("Chicken Soup", ingredients, "Boil it!", "Breakfast"));
        recipes.add(new RecipeData("Chicken Salad", ingredients, "Mix it!", "Dinner"));
        recipes.add(new RecipeData("Chicken Omelette", ingredients, "Fold it!", "Breakfast"));

        // When the user clicks on the “sort alphabetically” button
        ArrayList<RecipeData> filteredRecipes = FilterRecipes.filterRecipeByMeal(recipes, mealTypes);

        // Then the recipes should be sorted alphabetically
        assertEquals("Fried Chicken", filteredRecipes.get(0).title);
    }

    @Test
    void testFilterDinner() {
        // Given a list of recipes
        ArrayList<RecipeData> recipes = new ArrayList<RecipeData>();
        ArrayList<String> mealTypes = new ArrayList<String>();
        mealTypes.add("Dinner");

        String[] ingredients = { "Chicken" };
        recipes.add(new RecipeData("Fried Chicken", ingredients, "Deep fry it!", "Lunch"));
        recipes.add(new RecipeData("Chicken Soup", ingredients, "Boil it!", "Breakfast"));
        recipes.add(new RecipeData("Chicken Salad", ingredients, "Mix it!", "Dinner"));
        recipes.add(new RecipeData("Chicken Omelette", ingredients, "Fold it!", "Breakfast"));

        // When the user clicks on the “sort alphabetically” button
        ArrayList<RecipeData> filteredRecipes = FilterRecipes.filterRecipeByMeal(recipes, mealTypes);

        // Then the recipes should be sorted alphabetically
        assertEquals("Chicken Salad", filteredRecipes.get(0).title);
    }

}

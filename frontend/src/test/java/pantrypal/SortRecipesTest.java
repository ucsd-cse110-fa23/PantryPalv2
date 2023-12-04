package pantrypal;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class SortRecipesTest {

    @Test
    void testSortAlphabetically() {
        // Given a list of recipes
        ArrayList<RecipeData> recipes = new ArrayList<RecipeData>();
        String[] ingredients = { "Chicken" };
        recipes.add(new RecipeData("Fried Chicken", ingredients, "Deep fry it!"));
        recipes.add(new RecipeData("Chicken Soup", ingredients, "Boil it!"));
        recipes.add(new RecipeData("Chicken Salad", ingredients, "Mix it!"));

        // When the user clicks on the “sort alphabetically” button
        ArrayList<RecipeData> sortedRecipes = SortRecipes.sortAlphabetically(recipes, false);

        // Then the recipes should be sorted alphabetically
        assertEquals("Chicken Salad", sortedRecipes.get(0).title);
        assertEquals("Chicken Soup", sortedRecipes.get(1).title);
        assertEquals("Fried Chicken", sortedRecipes.get(2).title);
    }

    @Test
    void testSortAlphabeticallyReverse() {
        // Given a list of recipes
        ArrayList<RecipeData> recipes = new ArrayList<RecipeData>();
        String[] ingredients = { "Chicken" };
        recipes.add(new RecipeData("Fried Chicken", ingredients, "Deep fry it!"));
        recipes.add(new RecipeData("Chicken Soup", ingredients, "Boil it!"));
        recipes.add(new RecipeData("Chicken Salad", ingredients, "Mix it!"));

        // When the user clicks on the “sort alphabetically” button
        ArrayList<RecipeData> sortedRecipes = SortRecipes.sortAlphabetically(recipes, true);

        // Then the recipes should be sorted alphabetically
        assertEquals("Fried Chicken", sortedRecipes.get(0).title);
        assertEquals("Chicken Soup", sortedRecipes.get(1).title);
        assertEquals("Chicken Salad", sortedRecipes.get(2).title);
    }

    @Test
    void testSortByTime() {
        // Given a list of recipes
        ArrayList<RecipeData> recipes = new ArrayList<RecipeData>();
        String[] ingredients = { "Chicken" };
        recipes.add(new RecipeData("Fried Chicken", ingredients, "Deep fry it!"));
        recipes.add(new RecipeData("Chicken Soup", ingredients, "Boil it!"));
        recipes.add(new RecipeData("Chicken Salad", ingredients, "Mix it!"));

        // When the user clicks on the “sort by time” button
        ArrayList<RecipeData> sortedRecipes = SortRecipes.sortByTime(recipes, false);

        assertEquals("Fried Chicken", sortedRecipes.get(0).title);
        assertEquals("Chicken Soup", sortedRecipes.get(1).title);
        assertEquals("Chicken Salad", sortedRecipes.get(2).title);
    }

    @Test
    void testSortByTimeReverse() {
        // Given a list of recipes
        ArrayList<RecipeData> recipes = new ArrayList<RecipeData>();
        String[] ingredients = { "Chicken" };
        recipes.add(new RecipeData("Fried Chicken", ingredients, "Deep fry it!"));
        recipes.add(new RecipeData("Chicken Soup", ingredients, "Boil it!"));
        recipes.add(new RecipeData("Chicken Salad", ingredients, "Mix it!"));

        // When the user clicks on the “sort by time” button
        ArrayList<RecipeData> sortedRecipes = SortRecipes.sortByTime(recipes, true);

        assertEquals("Fried Chicken", sortedRecipes.get(2).title);
        assertEquals("Chicken Soup", sortedRecipes.get(1).title);
        assertEquals("Chicken Salad", sortedRecipes.get(0).title);
    }
}

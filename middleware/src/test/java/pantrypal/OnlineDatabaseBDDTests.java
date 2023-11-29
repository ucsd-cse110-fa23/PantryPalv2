package pantrypal;

import static org.junit.jupiter.api.Assertions.*;


import java.io.IOException;

import org.junit.jupiter.api.Test;

public class OnlineDatabaseBDDTests {
    @Test
    void testBDDSaveRecipe() {
        // Given that the user has generated a recipe with the title “Miso Soup”, the
        // ingredients “Miso Paste, Tofu”, and instructions “Put all ingredients into a
        // pot of water and boil”, and an image of a bowl of soup with it
        // When the user logs out of their account and logs onto their account from
        // another computer, and clicks on the “View Recipe List” button,
        // Then the user should see a recipe with the title “Miso Soup”, the ingredients
        // “Miso Paste, Tofu”, and instructions “Put all ingredients into a pot of water
        // and boil”, and an image of a bowl of soup with it.
        RecipeData recipe = new RecipeData("Miso Soup", new String[] { "Miso Paste", "Tofu" },
                "Put all ingredients into a pot of water and boil", "Lunch", 0.0);
        RecipeService recipeService = new RecipeService("Ben", true);
        try {
            recipeService.createRecipe(recipe);
            assertTrue(recipeService.recipeExists(recipe.title));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // When the user logs out of their account and logs onto their account from
        // another computer, and clicks on the “View Recipe List” button,

        RecipeService recipeService2 = new RecipeService("Ben", true);
        // Then the user should see a recipe with the title “Miso Soup”, the ingredients
        // “Miso Paste, Tofu”, and instructions “Put all ingredients into a pot of water
        // and boil”, and an image of a bowl of soup with it.
        try {
            assertTrue(recipeService2.recipeExists(recipe.title));
            assertTrue(recipeService2.readRecipes().get(0).title.equals(recipe.title));
            assertTrue(recipeService2.readRecipes().get(0).instructions.equals(recipe.instructions));
            assertTrue(recipeService2.readRecipes().get(0).ingredients[0].equals(recipe.ingredients[0]));
            assertTrue(recipeService2.readRecipes().get(0).ingredients[1].equals(recipe.ingredients[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void testBDDDeleteRecipe() {
        // Given that the user has generated a recipe with the title “Miso Soup”, 
        // the ingredients “Miso Paste, Tofu”, and instructions “Put all ingredients 
        // into a pot of water and boil”, and an image of a bowl of soup with it

        // When the user deletes that recipe and logs out of their account and 
        // logs onto their account from another computer and clicks on the “View Recipe List” button, 

        // Then the user should see an empty list of recipes

        // creating the recipe with Miso Soup as part of the recipelist
        RecipeData recipe = new RecipeData("Miso Soup", new String[] { "Miso Paste", "Tofu" },
                "Put all ingredients into a pot of water and boil", "Lunch", 0.0);
        RecipeService recipeService = new RecipeService("Ben", true);
        try {
            recipeService.createRecipe(recipe);
            assertTrue(recipeService.recipeExists(recipe.title));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // When the user deletes that recipe and logs out of their account and 
        // logs onto their account from another computer and clicks on the “View Recipe List” button, 


        RecipeService recipeService2 = new RecipeService("Ben", true);
        // Then the user should see a recipe with the title “Miso Soup”, the ingredients
        // “Miso Paste, Tofu”, and instructions “Put all ingredients into a pot of water
        // and boil”, and an image of a bowl of soup with it.
        try {
            assertTrue(recipeService2.recipeExists(recipe.title));
            recipeService.deleteRecipe("Miso Soup"); 
            assertFalse(recipeService2.recipeExists(recipe.title));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

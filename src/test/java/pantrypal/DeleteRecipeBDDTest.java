package pantrypal;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.beans.binding.When;

public class DeleteRecipeBDDTest { // Feature 7: Recipe Deletion [L]
    RecipeData savedRecipe;
    // Scenario 1: Deleting a Recipe from the Database

    private void resetRecipeFile() {
        CRUDRecipes.setFilePath("test.json");
        Path path = Paths.get(CRUDRecipes.FILE_PATH);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void setUp() {
        resetRecipeFile();
    }

    @AfterEach
    void tearDown() {
        resetRecipeFile();
    }

    @Test
    void testBDDViewRecipe() throws IOException {
        // Given the user completely generates a recipe
        // And the user is on the detail page
        // And the “delete” button is accessible
        String title = "potato mush";
        String[] ingredients = { "potato", "black papper", "butter" };
        String instructions = "Mash the cooked potatoes and fry the black pepper in butter, then stir all the ingredients together.";
        savedRecipe = new RecipeData(title, ingredients, instructions);

        // When the user click on the “delete” button
        // Then the recipe should be deleted from the database
        // And the recipe should be deleted from the saved recipe list
        // Then the app should jump back to the “Recipes List” page
        try {
            CRUDRecipes.createRecipe(savedRecipe);

            // Ensure recipe has been added
            assertTrue(CRUDRecipes.recipeExists(title));

            CRUDRecipes.deleteRecipe(title);

            assertFalse(CRUDRecipes.recipeExists(title));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

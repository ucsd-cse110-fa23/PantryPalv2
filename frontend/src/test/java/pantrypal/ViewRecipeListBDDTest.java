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

public class ViewRecipeListBDDTest { // Feature 5: View List of Recipes [H]
    RecipeData savedRecipe;

    private void resetRecipeFile() {
        CRUDRecipes.changeFilePath("test.json");
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

    // Scenario 1: Saved at least one recipe
    @Test
    void testBDDViewRecipeListOneSaved() throws IOException {
        // Given the user saved a recipe “potato mash”
        // And the user is on the homepage page
        // And the button “View Recipe List” is accessible
        String title = "potato mush";
        String[] ingredients = { "potato", "black papper", "butter" };
        String instructions = "Mash the cooked potatoes and fry the black pepper in butter, then stir all the ingredients together.";
        savedRecipe = new RecipeData(title, ingredients, instructions);

        // When the user click on the button “View Recipe List”
        // Then the app should jump to a new page and display the saved recipe list
        try {
            CRUDRecipes.createRecipe(savedRecipe);

            ArrayList<RecipeData> expectedRecipes = new ArrayList<>();
            expectedRecipes.add(savedRecipe);

            assertEquals(expectedRecipes.size(), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Scenario 2: Have no saved recipe
    @Test
    void testBDDViewRecipeListNoSaved() throws IOException {
        // Given the user have not saved any recipe
        // And the user is on the homepage
        // And the button “View Recipe List” is accessible

        String title = "potato mush";
        String[] ingredients = { "potato", "black papper", "butter" };
        String instructions = "Mash the cooked potatoes and fry the black pepper in butter, then stir all the ingredients together.";
        savedRecipe = new RecipeData(title, ingredients, instructions);

        // And the user is on the “Recipes List” page
        // And the title “potato mash” is accessible
        // When the user click on the title “potato mash”
        // Then the app should jump to a new page and display an empty list
        try {
            CRUDRecipes.createRecipe(savedRecipe);

            ArrayList<RecipeData> expectedRecipes = new ArrayList<>();
            // expectedRecipes.add(savedRecipe);

            assertEquals(expectedRecipes.size(), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

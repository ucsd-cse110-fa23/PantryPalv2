package pantrypal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EditRecipeBDDTest {
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

    // Scenario: Save a modification
    @Test
    void testBDDViewRecipeListOneSaved() throws IOException {
        // Given the user saved a recipe “potato mash”
        String title = "potato mush";
        String[] ingredients = { "potato", "black papper", "butter" };
        String instructions = "Mash the cooked potatoes and fry the black pepper in butter, then stir all the ingredients together.";
        savedRecipe = new RecipeData(title, ingredients, instructions);

        // When the user click on the button “View Recipe List”
        // Then the app should jump to a new page and display the saved recipe list
        try {
            CRUDRecipes.createRecipe(savedRecipe);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // the and the user edit some details in current recipe
        // And the user is on edit page
        // And “save” and “cancel” button are accessible
        RecipeData data = CRUDRecipes.getRecipe(title);
        data.instructions = "Mash the cooked potatoes and fry the black pepper in butter, then stir all the ingredients together. Add some salt.";
        // When the user click on the “save” button
        // Then the recipe should be updated and saved into the database
        CRUDRecipes.updateRecipe(data);
        assertEquals(CRUDRecipes.getRecipe(title).instructions, data.instructions);
        // And the recipe should be updated to the saved recipe list
        // And the app should jump back to the recipe’s details page

    }
}

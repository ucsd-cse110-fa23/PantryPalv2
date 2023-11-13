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

public class SaveRecipeBDDTest {
    RecipeData unsavedRecipe;

    // Scenario 1: Save a  recipe
    @Test
    void testBDDSaveRecipe() {
        // Given the user completely generates a recipe
        String[] ingredients = {"Chicken"};
        unsavedRecipe = new RecipeData("Fried Chicken", ingredients, "Deep fry it!");
        
        // And the “save” button is accessible
        // When the user click on the “save” button
        // Then the recipe should be saved into the database
        try {
            CRUDRecipes.createRecipe(unsavedRecipe);

        // Then the recipe should be saved into the database
            assertTrue(CRUDRecipes.recipeExists(unsavedRecipe.title));
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}

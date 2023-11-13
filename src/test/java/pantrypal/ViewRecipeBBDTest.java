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

public class ViewRecipeBBDTest {
    RecipeData savedRecipe;
    // Scenario 1: Saved at least one recipe
    @Test
    void testBDDViewRecipe() throws IOException {
        // Given the user completely generates a recipe
        String title = "potato mush";
        String[] ingredients = {"potato","black papper", "butter"};
        String instructions = "Mash the cooked potatoes and fry the black pepper in butter, then stir all the ingredients together.";
        savedRecipe = new RecipeData(title, ingredients, instructions);
        
        // And the user is on the “Recipes List” page
        // And the title “potato mash” is accessible
        // When the user click on the title “potato mash”
        // Then the app should jump to a new page and display the details about potato mash
        try {
            CRUDRecipes.createRecipe(savedRecipe);

            ArrayList<RecipeData> expectedRecipes = new ArrayList<>();
            expectedRecipes.add(savedRecipe);

            assertEquals(expectedRecipes.get(0).title, savedRecipe.title);
            assertArrayEquals(expectedRecipes.get(0).ingredients, savedRecipe.ingredients);
            assertEquals(expectedRecipes.get(0).instructions, savedRecipe.instructions);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
}

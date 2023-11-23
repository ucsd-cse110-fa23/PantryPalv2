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

public class CreateRecipeBDDTest { // Feature 1: Recipe Creation [H] & Feature 2: Meal Type Selection [M]

    private void resetRecipeFile() {
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

    // Scenario 1: Creating a New Recipe
    @Test
    void testBDDCreateRecipe() {
        // Given the user is using the recipe app
        // When the user provides voice input for available ingredients, saying "Um,
        // chicken and rice"
        String prompt = "Um, chicken and rice";

        MockModel model = new MockModel();
        ChatGPT gpt = new ChatGPT(model);

        String[] result = gpt.generateRecipe(prompt, "Breakfast");

        // Then the app should recognize the ingredients
        assertEquals("Chicken", result[1]);
        assertEquals("Rice", result[2]);

        // And the app should suggest recipes based on the provided ingredients
        assertEquals("Proper recipe here", result[0]);

    }

    //
    // Scenario 2: Providing a Nonexistent Meal Type
    @Test
    void testBDDCreateRecipeInvalidMealType() {
        // Given the user is using the recipe app
        // When the user provides voice input for available ingredients, saying "I want
        // to have brunch

        String prompt = "I want to have brunch";

        MockModel model = new MockModel();
        ChatGPT gpt = new ChatGPT(model);

        // Then the app should recognize the voice input
        String test = gpt.generateMealType(prompt);

        // And the app should return an error message “Wrong meal type!”
        String output = "Wrong meal type!";

        assertEquals(test, output);

    }
}

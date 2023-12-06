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

public class CreateRecipeBDDTest { // Feature 1: Recipe Creation [H] &
    // Feature 2:
    // Meal Type Selection[M]

    private MockGPT mockGPT;
    
    @BeforeEach
    public void setUp() {
        // Initialize the mockGPT object before each test execution
        this.mockGPT = new MockGPT();
    }

    // Scenario 1: Creating a New Recipe
    @Test
    void testBDDCreateRecipe() {
        // Given the user is using the recipe app
        // When the user provides voice input for available ingredients, saying "Um,
        // chicken and rice"
        String prompt = "Um, chicken and rice";

        String result = mockGPT.generateRecipe(prompt, "Breakfast");

        // Then the app should recognize the ingredients
        // And the app should suggest recipes based on the provided ingredients
        String exceptOutput = "Chicken Fried Rice\nBreakfast\nChicken;Rice;\ncook it!";

        assertEquals(result, exceptOutput);

    }

    //
    // Scenario 2: Providing a Nonexistent Meal Type
    @Test
    void testBDDCreateRecipeInvalidMealType() {
        // Given the user is using the recipe app
        // When the user provides voice input for available ingredients, saying "I
        // want
        // to have brunch

        String prompt = "I want to have brunch";

        // Then the app should recognize the voice input
        String test = mockGPT.generateMealType(prompt);

        // And the app should return an error message “Wrong meal type!”
        String output = "Wrong meal type!";

        assertEquals(test, output);

    }
}

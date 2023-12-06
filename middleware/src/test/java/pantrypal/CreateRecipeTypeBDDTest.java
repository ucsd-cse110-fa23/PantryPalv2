package pantrypal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateRecipeTypeBDDTest { //More in depth tests for Feature 2: Meal Type Selection [M]
    private MockGPT mockGPT;
    
    @BeforeEach
    public void setUp() {
        // Initialize the mockGPT object before each test execution
        this.mockGPT = new MockGPT();
    }

    @Test
    void testBDDCreateRecipeBreakfast() {

        // Given the user is using the recipe app
        // When the user provides voice input for meal type saying, "Um breakfast"
        String mealPrompt = "Um breakfast";

        // The app recogizes that the user wants breakfast, and prompts the user for
        // ingredients
        String mealType = mockGPT.generateMealType(mealPrompt);
        assertTrue(mealType.toLowerCase().contains("breakfast"));

        // When the user provides voice input for ingredients saying, "Um, chicken and
        // rice"
        String prompt = "Um, chicken and rice";

        String result = mockGPT.generateRecipe(prompt, mealType);

        // Then the app should recognize the ingredients
        // And the app should suggest recipes based on the provided ingredients
        String exceptOutput = "Chicken Fried Rice\nBreakfast\nChicken;Rice;\ncook it!";

        assertEquals(result, exceptOutput);
    }

    @Test
    void testBDDCreateRecipeLunch() {

        // Given the user is using the recipe app
        // When the user provides voice input for meal type saying, "Um lunch"
        String mealPrompt = "Um lunch";

        // The app recogizes that the user wants breakfast, and prompts the user for
        // ingredients
        String mealType = mockGPT.generateMealType(mealPrompt);
        assertTrue(mealType.toLowerCase().contains("lunch"));

        // When the user provides voice input for ingredients saying, "Um, chicken and
        // rice"
        String prompt = "Um, chicken and rice";

        String result = mockGPT.generateRecipe(prompt, mealType);

        // Then the app should recognize the ingredients
        // And the app should suggest recipes based on the provided ingredients
        String exceptOutput = "Chicken Fried Rice\nLunch\nChicken;Rice;\ncook it!";

        assertEquals(result, exceptOutput);
    }

    @Test
    void testBDDCreateRecipeDinner() {
        // Given the user is using the recipe app
        // When the user provides voice input for meal type saying, "Um dinner"
        String mealPrompt = "Um dinner";

        // The app recogizes that the user wants breakfast, and prompts the user for
        // ingredients
        String mealType = mockGPT.generateMealType(mealPrompt);
        assertTrue(mealType.toLowerCase().contains("dinner"));

        // When the user provides voice input for ingredients saying, "Um, chicken and
        // rice"
        String prompt = "Um, chicken and rice";

        String result = mockGPT.generateRecipe(prompt, mealType);

        // Then the app should recognize the ingredients
        // And the app should suggest recipes based on the provided ingredients
        String exceptOutput = "Chicken Fried Rice\nDinner\nChicken;Rice;\ncook it!";

        assertEquals(result, exceptOutput);
    }

    void testBDDCreateRecipeInvalidMealType() {
        // Given the user is using the recipe app
        // When the user provides voice input for available ingredients, saying "I want
        // to have brunch

        String prompt = "I uhh don't know what I want";

        // Then the app should recognize the voice input
        String test = mockGPT.generateMealType(prompt);

        // And the app should return an error message “Wrong meal type!”
        String output = "Wrong meal type!";

        assertEquals(test, output);

    }
}

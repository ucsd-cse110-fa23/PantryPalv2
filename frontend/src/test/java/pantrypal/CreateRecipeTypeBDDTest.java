package pantrypal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CreateRecipeTypeBDDTest { //More in depth tests for Feature 2: Meal Type Selection [M]
    @Test
    void testBDDCreateRecipeBreakfast() {
        MockModelMealType model = new MockModelMealType();
        ChatGPT gpt = new ChatGPT(model);

        // Given the user is using the recipe app
        // When the user provides voice input for meal type saying, "Um breakfast"
        String mealPrompt = "Um breakfast";

        // The app recogizes that the user wants breakfast, and prompts the user for
        // ingredients
        String mealType = gpt.generateMealType(mealPrompt);
        assertTrue(mealType.toLowerCase().contains("breakfast"));

        // When the user provides voice input for ingredients saying, "Um, chicken and
        // rice"
        String prompt = "Um, chicken and rice";

        String[] result = gpt.generateRecipe(prompt, mealType);

        // Then the app should recognize the ingredients
        assertEquals("Chicken", result[1]);
        assertEquals("Rice", result[2]);

        // And the app should suggest recipes based on the provided ingredients
        assertEquals("Breakfast recipe here", result[0]);
    }

    @Test
    void testBDDCreateRecipeLunch() {
        MockModelMealType model = new MockModelMealType();
        ChatGPT gpt = new ChatGPT(model);

        // Given the user is using the recipe app
        // When the user provides voice input for meal type saying, "Um lunch"
        String mealPrompt = "Um lunch";

        // The app recogizes that the user wants breakfast, and prompts the user for
        // ingredients
        String mealType = gpt.generateMealType(mealPrompt);
        assertTrue(mealType.toLowerCase().contains("lunch"));

        // When the user provides voice input for ingredients saying, "Um, chicken and
        // rice"
        String prompt = "Um, chicken and rice";

        String[] result = gpt.generateRecipe(prompt, mealType);

        // Then the app should recognize the ingredients
        assertEquals("Chicken", result[1]);
        assertEquals("Rice", result[2]);

        // And the app should suggest recipes based on the provided ingredients
        assertEquals("Lunch recipe here", result[0]);
    }

    @Test
    void testBDDCreateRecipeDinner() {
        MockModelMealType model = new MockModelMealType();
        ChatGPT gpt = new ChatGPT(model);

        // Given the user is using the recipe app
        // When the user provides voice input for meal type saying, "Um dinner"
        String mealPrompt = "Um dinner";

        // The app recogizes that the user wants breakfast, and prompts the user for
        // ingredients
        String mealType = gpt.generateMealType(mealPrompt);
        assertTrue(mealType.toLowerCase().contains("dinner"));

        // When the user provides voice input for ingredients saying, "Um, chicken and
        // rice"
        String prompt = "Um, chicken and rice";

        String[] result = gpt.generateRecipe(prompt, mealType);

        // Then the app should recognize the ingredients
        assertEquals("Chicken", result[1]);
        assertEquals("Rice", result[2]);

        // And the app should suggest recipes based on the provided ingredients
        assertEquals("Dinner recipe here", result[0]);
    }

    void testBDDCreateRecipeInvalidMealType() {
        // Given the user is using the recipe app
        // When the user provides voice input for available ingredients, saying "I want
        // to have brunch

        String prompt = "I uhh don't know what I want";

        MockModelMealType model = new MockModelMealType();
        ChatGPT gpt = new ChatGPT(model);

        // Then the app should recognize the voice input
        String test = gpt.generateMealType(prompt);

        // And the app should return an error message “Wrong meal type!”
        String output = "Wrong meal type!";

        assertEquals(test, output);

    }
}

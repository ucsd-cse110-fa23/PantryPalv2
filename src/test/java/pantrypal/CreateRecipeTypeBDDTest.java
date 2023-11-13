package pantrypal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CreateRecipeTypeBDDTest {
    @Test
    void testBDDCreateRecipeBreakfast() {
        // Given the user is using the recipe app
        // When the user provides voice input for available ingredients, saying "Um,
        // chicken and rice"
        String prompt = "Um, chicken and rice";

        MockModelMealType model = new MockModelMealType();
        ChatGPT gpt = new ChatGPT(model);

        String[] result = gpt.generateRecipe(prompt, "Breakfast");

        // Then the app should recognize the ingredients
        assertEquals("Chicken", result[1]);
        assertEquals("Rice", result[2]);

        // And the app should suggest recipes based on the provided ingredients
        assertEquals("Breakfast recipe here", result[0]);
    }
}

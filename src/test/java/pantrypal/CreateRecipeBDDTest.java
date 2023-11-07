package pantrypal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CreateRecipeBDDTest {
    @Test
    void testBDDCreateRecipe() {
        // Given the user is using the recipe app
        // When the user provides voice input for available ingredients, saying "Um,
        // chicken and rice"
        String prompt = "Um, chicken and rice";

        MockModel model = new MockModel();
        ChatGPT gpt = new ChatGPT(model);

        String[] result = gpt.generateRecipe(prompt);

        // Then the app should recognize the ingredients
        assertEquals("Chicken", result[1]);
        assertEquals("Rice", result[2]);

        // And the app should suggest recipes based on the provided ingredients
        assertEquals("Proper recipe here", result[0]);

    }
}

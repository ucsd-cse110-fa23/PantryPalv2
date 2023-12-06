package pantrypal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChatGPTTest {

    // Testing – 25 points
    // 9’ – Tests with non-trivial testing-class coverage: all testable non-trivial
    // methods tested, e.g., can exclude field getter/setter methods
    // 6’ E2E/Integration Tests: Integration test(s) that tests all the features
    // that are part of the milestone, note that APIs such as Whisper and ChatGPT,
    // and Voice Recorder needs to be mocked in these integration tests
    // 5' – Local testing: All tests automated, tied into JUnit (run and show
    // pass/fail)
    // 5' – Continuous Integration: All tests run on GitHub Actions

    @Test
    void testGenerateRecipe() {
        String transcription = "I have Chicken and Rice.";

        String mealType1 = "Breakfast";
        String mealType2 = "Lunch";
        String mealType3 = "Dinner";

        MockGPT gpt = new MockGPT();
        String recipe = gpt.generateRecipe(transcription, mealType1);
        
        String exceptOutput = "Chicken Fried Rice\nBreakfast\nChicken;Rice;\ncook it!";

        assertEquals(recipe, exceptOutput);

    }

    @Test
    void testCreateRecipe() {
        String prompt = "Given the following ingredients:\n";
        String[] ingredients1 = { "Eggs", "Sausage", "Onions" };
        String[] ingredients2 = { "Salami", "Bread", "Lettuce", "Butter" };
        String[] ingredients3 = { "Steak", "Sea Salt", "Pepper", "Garlic", "Olive Oil" };
        String mealType1 = "Breakfast";
        String mealType2 = "Lunch";
        String mealType3 = "Dinner";

        MockGPT gpt = new MockGPT();
        String test1 = gpt.createRecipe(ingredients1, mealType1);
        String test2 = gpt.createRecipe(ingredients2, mealType2);
        String test3 = gpt.createRecipe(ingredients3, mealType3);
        String output = "Proper recipe here";

        assertEquals(test1, output);
        assertEquals(test2, output);
        assertEquals(test3, output);

    }

    @Test
    void testParseModelResponse() {
        String prompt1 = "{\"ingredients\": [\"Chicken\",\"Rice\"]}";
        String prompt2 = "{\"ingredients\": [\"Steak\",\"Sea Salt\",\"Pepper\",\"Garlic\",\"Olive Oil\"]}";

        ChatGPT gpt = new ChatGPT();

        String[] test1 = gpt.parseModelResponse(prompt1);
        String[] test2 = gpt.parseModelResponse(prompt2);

        String[] output1 = { "Chicken", "Rice" };
        String[] output2 = { "Steak", "Sea Salt", "Pepper", "Garlic", "Olive Oil" };

        assertEquals(test1[0], output1[0]);
        assertEquals(test1[1], output1[1]);

        assertEquals(test2[0], output2[0]);
        assertEquals(test2[1], output2[1]);
        assertEquals(test2[2], output2[2]);
        assertEquals(test2[3], output2[3]);
        assertEquals(test2[4], output2[4]);
    }

    @Test
    void testExtractIngredients() {
        String prompt = "{\"ingredients\": [\"Chicken\",\"Rice\"]}";

        MockGPT gpt = new MockGPT();

        String[] test = gpt.extractIngredients(prompt);

        String[] output = { "Chicken", "Rice" };

        assertEquals(test[0], output[0]);
        assertEquals(test[1], output[1]);
    }

    @Test
    void testGenerateMealType() {
        String prompt = "I want to have breakfast";

        MockGPT gpt = new MockGPT();

        String test = gpt.generateMealType(prompt);

        String output = "Breakfast";

        assertEquals(test, output);
    }
    

}

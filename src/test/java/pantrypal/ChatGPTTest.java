package pantrypal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChatGPTTest {

    @Test
    void testExtractIngredients() {
        String prompt = "{\"ingredients\": [\"Chicken\",\"Rice\"]}";
        String transcription = """
        {
            "ingredients": [
                "Chicken",
                "Rice"
            ]
        }
        """;
        MockModel model = new MockModel();
        ChatGPT gpt = new ChatGPT(model);
        String[] test = gpt.extractIngredients(prompt);
        String[] output = {"Chicken", "Rice"};
        assertEquals(test[0], output[0]);
    }

    @Test
    void testParseModelResponse1() {
        String prompt = "{\"ingredients\": [\"Chicken\",\"Rice\"]}";

        MockModel model = new MockModel();
        ChatGPT gpt = new ChatGPT(model);
        String[] test = gpt.parseModelResponse(prompt);
        String[] output = {"Chicken", "Rice"};
        assertEquals(test[0], output[0]);       
    }

    @Test
    void testParseModelResponse2() {
        String prompt = "{\"ingredients\": [\"Chicken\",\"Rice\"]}";

        MockModel model = new MockModel();
        ChatGPT gpt = new ChatGPT(model);
        String[] test = gpt.parseModelResponse(prompt);
        String[] output = {"Chicken", "Rice"};
        assertEquals(test[1], output[1]);       
    }

}

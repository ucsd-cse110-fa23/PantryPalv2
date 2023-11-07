package pantrypal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChatGPTTest {

    @Test
    void testParseModelResponse1() {
        String prompt = "{\"ingredients\": [\"Chicken\",\"Rice\"]}";

        MockModel model = new MockModel();
        ChatGPT gpt = new ChatGPT(model);
        String[] test = gpt.parseModelResponse(prompt);
        String[] output = { "Chicken", "Rice" };
        assertEquals(test[0], output[0]);
        assertEquals(test[1], test[1]);
    }

    @Test
    void testParseModelResponse2() {
        String prompt = "{\"ingredients\": [\"Steak\",\"Sea Salt\",\"Pepper\",\"Garlic\",\"Olive Oil\"]}";
        ChatGPT gpt = new ChatGPT(new MockModel());
        String[] test = gpt.parseModelResponse(prompt);
        String[] output = { "Steak", "Sea Salt", "Pepper", "Garlic", "Olive Oil" };
        assertEquals(test[0], output[0]);
        assertEquals(test[1], output[1]);
        assertEquals(test[2], output[2]);
        assertEquals(test[3], output[3]);
        assertEquals(test[4], output[4]);

    }

}

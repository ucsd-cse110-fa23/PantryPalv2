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

}

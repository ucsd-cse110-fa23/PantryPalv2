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




    // private MyQueue queue;
    
    // @BeforeEach
    // void setUp() {
    //     queue = new MyQueue(5);
    // }
    
    // @Test
    // void testEnqueue() {
    //     queue.enqueue(1);
    //     queue.enqueue(2);
    //     queue.enqueue(3);
    //     assertEquals(3, queue.size());
    // }
    
    // @Test
    // void testDequeue() {
    //     queue.enqueue(1);
    //     queue.enqueue(2);
    //     queue.enqueue(3);
    //     assertEquals(1, queue.dequeue());
    //     assertEquals(2, queue.dequeue());
    //     assertEquals(1, queue.size());
    // }
    
    // @Test
    // void testPeek() {
    //     queue.enqueue(1);
    //     queue.enqueue(2);
    //     queue.enqueue(3);
    //     assertEquals(1, queue.peek());
    //     assertEquals(3, queue.size());
    // }
    
    // @Test
    // void testIsEmpty() {
    //     assertTrue(queue.isEmpty());
    //     queue.enqueue(1);
    //     assertFalse(queue.isEmpty());
    // }
    
    // @Test
    // void testIsFull() {
    //     assertFalse(queue.isFull());
    //     queue.enqueue(1);
    //     queue.enqueue(2);
    //     queue.enqueue(3);
    //     queue.enqueue(4);
    //     queue.enqueue(5);
    //     assertTrue(queue.isFull());
    // }
    
    // @Test
    // void testEnqueueWhenFull() {
    //     queue.enqueue(1);
    //     queue.enqueue(2);
    //     queue.enqueue(3);
    //     queue.enqueue(4);
    //     queue.enqueue(5);
    //     assertThrows(IllegalStateException.class, () -> queue.enqueue(6));
    // }
    
    // @Test
    // void testDequeueWhenEmpty() {
    //     assertThrows(IllegalStateException.class, () -> queue.dequeue());
    // }
}

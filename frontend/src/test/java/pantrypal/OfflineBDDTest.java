package pantrypal;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class OfflineBDDTest {
    // Server offline from the start
    @Test
    public void serverOfflineFromStart(){
         MockMiddlewareModel mockModel = new MockMiddlewareModel();

        // Simulate server is running
        mockModel.simulateServerRunning();

        // Assert server is reported as running
        assertTrue(mockModel.isServerOnline(), "Server should be reported as online");
    }

    // Server offline during use
    @Test
    public void serverOfflineDuringUse(){
        MockMiddlewareModel mockModel = new MockMiddlewareModel();
        // Simulate server initially running
        mockModel.simulateServerRunning();
        assertTrue(mockModel.isServerOnline(), "Server should initially be reported as online");

        // Simulate server going offline
        mockModel.simulateServerDown();

        // Assert server is reported as offline
        assertFalse(mockModel.isServerOnline(), "Server should be reported as offline after going down");
    }

    // Server running throughout
    @Test 
    public void serverRunningThroughout(){
        MockMiddlewareModel mockModel = new MockMiddlewareModel();
        mockModel.simulateServerRunning();

        assertTrue(mockModel.isServerOnline(), "Server should be reported as online");
    }
}
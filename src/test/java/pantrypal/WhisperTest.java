package pantrypal;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.BeforeEach;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.io.IOException;
// import java.net.URISyntaxException;

// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class WhisperTest {
    private Whisper whisper;

    @BeforeEach
    void setUp() {
        whisper = new Whisper();
    }

    @Test
    void testGetTranscript() {
        try {
            String filePath = "./my_audio.m4a"; 
            String transcript = whisper.getTranscript(filePath);

            
            assertNotNull(transcript);
            assertTrue(transcript.length() > 0);

        } catch (IOException | URISyntaxException e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

}

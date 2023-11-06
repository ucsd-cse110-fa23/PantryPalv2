package pantrypal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WhisperTest {

    @Test
    void testHumanVoice() throws IOException, URISyntaxException {
        String transcript = Whisper.getTranscript("./src/test/java/resources/my_audio.m4a");
        assertEquals("I love computer science. It is great.", transcript);
    }

    @Test
    void testHello() throws IOException, URISyntaxException {
        String transcript = Whisper.getTranscript("./src/test/java/resources/hello.mp3");
        assertEquals("Hello, world.", transcript);
    }

}

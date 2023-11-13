package pantrypal;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;

public class AudioRecorderTest {
    
    private void resetRecordingFile() {
        Path path = Paths.get(AudioRecorder.path); 
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void setUp() {
        resetRecordingFile();
    }

    @AfterEach
    void tearDown() {
        resetRecordingFile();
    }

    @Test
    void testAudioRecorder() {
        AudioRecorder ar = new AudioRecorder();

        ar.startRecording();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ar.stopRecording();

        File file = new File(AudioRecorder.path);

        assertTrue(file.exists());
    }
}

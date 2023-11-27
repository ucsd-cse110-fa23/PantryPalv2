package pantrypal;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileService {

    // Directory where the uploaded files will be saved
    private final static Path location = Paths.get("src/resources");
    private final static String filename = "recording.wav";

    public static boolean isWavFile(MultipartFile file) {
        String contentType = file.getContentType();
        // TODO: check if we get Wav file
        return true; //contentType != null && contentType.equals("audio/wav");
    }

    public static void saveFile(MultipartFile file) throws IOException {
        // Ensure the directory exists
        Files.createDirectories(location);

        // Resolve the file path based on file's original name
        Path destinationFile = location.resolve(Paths.get(filename))
                .normalize().toAbsolutePath();

        // Copy the file to the target location, replacing the existing file if it exists
        Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);
    }

    public static String getFilePath() {
        // TODO: make this a variable, when copying File to store locally in saveFile save to specific path with specific name
        return location.toString() + "/" + filename;
    }
}

package pantrypal;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileService {

    public static boolean isWavFile(MultipartFile file) {
        String contentType = file.getContentType();
        // TODO: check if we get Wav file
        return true; //contentType != null && contentType.equals("audio/wav");
    }

    public static void saveFile(MultipartFile file, Path location) throws IOException {
        // Ensure the directory exists
        Files.createDirectories(location);

        // Resolve the file path based on file's original name
        Path destinationFile = location.resolve(Paths.get(file.getOriginalFilename()))
                .normalize().toAbsolutePath();

        // Copy the file to the target location, replacing the existing file if it exists
        Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);
    }
}

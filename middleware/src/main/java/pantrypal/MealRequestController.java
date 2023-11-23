package pantrypal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
public class MealRequestController {

    // Directory where the uploaded files will be saved
    private final Path rootLocation = Paths.get("src/resources");

    @PostMapping("/mealtype")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty() || !FileService.isWavFile(file)) {
            return ResponseEntity.badRequest().body("Invalid file (type=" + file.getContentType() +"). Please upload a WAV file.");
        }

        try {
            FileService.saveFile(file, rootLocation);
            return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Could not upload the file: " + file.getOriginalFilename());
        }
    }

}

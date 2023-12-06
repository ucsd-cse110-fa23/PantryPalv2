package pantrypal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OfflineController {

    /**
     * Endpoint to check the server status.
     * @return ResponseEntity with a message indicating the server is up.
     */
    @GetMapping("/api/status")
    public ResponseEntity<String> checkServerStatus() {
        return ResponseEntity.ok("Server is up and running");
    }
}
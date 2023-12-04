package pantrypal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountApiController {

    @PostMapping("/login")
    public ResponseEntity<String> accountLogin(@RequestBody Account acc) {
        try {
            boolean authenticated = AuthenticationService.authenticate(acc.getUsername(), acc.getPassword());
            if(authenticated) {
                return ResponseEntity.ok(acc.getUsername());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid username or password.");
            }
        } catch(Exception e) {
            return ResponseEntity.internalServerError().body("Error with authentication");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> accountCreation(@RequestBody Account acc) {
        try {
            boolean created = AuthenticationService.createAccount(acc.getUsername(), acc.getPassword());
            if(created) {
                return ResponseEntity.ok(acc.getUsername());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid username");
            }
        } catch(Exception e) {
            return ResponseEntity.internalServerError().body("Error with authentication");
        }
    }

}

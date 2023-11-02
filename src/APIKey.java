import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class APIKey {
    public static String getAPIKey() {
        try (BufferedReader reader = new BufferedReader(new FileReader("keys.txt"))) {
            return reader.readLine(); // Read and return the first line
        } catch (Exception e) {
            System.out.println("Error reading API key from keys.txt");
            System.out.println(e);
            return "";
        }
    }
}

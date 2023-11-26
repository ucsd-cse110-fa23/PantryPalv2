package pantrypal;

import java.io.BufferedReader;
import java.io.FileReader;

public class MongoKey {
    public static String getAPIKey() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/resources/mongokey.txt"))) {
            return reader.readLine(); // Read and return the first line
        } catch (Exception e) {
            System.out.println("Error reading Mongo key from mongokey.txt");
            System.out.println(e);
            return "";
        }
    }
}

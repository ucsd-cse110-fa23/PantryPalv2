package pantrypal;

import java.io.BufferedReader;
import java.io.FileReader;

public class GetIP {
    public static String getIP() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/resources/ip.txt"))) {
            return reader.readLine(); // Read and return the first line
        } catch (Exception e) {
            System.out.println("Error reading ip from ip.txt");
            System.out.println(e);
            return "";
        }
    }
}

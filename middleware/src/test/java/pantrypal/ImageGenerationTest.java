package pantrypal;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.regex.Pattern;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.net.URI;
import java.net.URISyntaxException;

public class ImageGenerationTest {
    private DallE dalle;
    private Pattern urlPattern;

    @BeforeEach
    public void setUp() {
        // Initialize the DallE object before each test execution
        this.dalle = new DallE();
        // Define the basic structure of the URL, excluding dynamically generated parts
        this.urlPattern = Pattern.compile("https://oaidalleapiprodscus.blob.core.windows.net/private/org-.*/user-.*/img-.*\\.png.*");
    
    }

    @Test
    public void generateImageUrlNotNull() throws IOException, InterruptedException, URISyntaxException {
        String recipeTitle = "Chicken fried rice";
        String imageUrl = dalle.generateImageURL(recipeTitle);
        assertNotNull(imageUrl, "Generated image URL should not be null");
    }
    
    @Test
    public void generateImageUrlValidFormat() throws IOException, InterruptedException, URISyntaxException {
        String recipeTitle = "Chicken fried rice";
        String imageUrl = dalle.generateImageURL(recipeTitle);
        assertTrue(urlPattern.matcher(imageUrl).matches(), "Invalid image URL format");
    }
}

package pantrypal;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.regex.Pattern;


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
    public void generateImageUrlNotNull() {
        // Tests whether the generated image URL is empty
        String recipeTitle = "Chicken fried rice";
        String imageUrl = dalle.generateImageUrl(recipeTitle);
        assertNotNull(imageUrl, "Generated image URL should not be null");
    }

    @Test
    public void generateImageUrlValidFormat() {
        // Test whether the format of the generated image URL is valid
        String recipeTitle = "Delicious Recipe";
        String imageUrl = dalle.generateImageUrl(recipeTitle);
        assertTrue(urlPattern.matcher(imageUrl).matches(), "Invalid image URL format");
    }

    
}

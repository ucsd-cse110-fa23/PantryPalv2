package pantrypal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
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
        this.dalle = mock(DallE.class);
        // Define the basic structure of the URL, excluding dynamically generated parts
        this.urlPattern = Pattern.compile("https://oaidalleapiprodscus.blob.core.windows.net/private/org-.*/user-.*/img-.*\\.png.*");
    
    }

    // Test whether the generated image URL is empty
    @Test
    public void generateImageUrlNotNull() throws IOException, InterruptedException, URISyntaxException {
        // Set up behavior for the mocked DallE instance
        when(dalle.generateImageURL(anyString())).thenReturn("https://oaidalleapiprodscus.blob.core.windows.net/private/org-mocked/user-mocked/img-mocked.png?st=mocked&se=mocked&sp=mocked&sv=mocked&sr=mocked&rscd=mocked&rsct=image/png&skoid=mocked&sktid=mocked&skt=mocked&ske=mocked&sks=mocked&skv=mocked&sig=mocked");
        String recipeTitle = "Chicken fried rice";
        String imageUrl = dalle.generateImageURL(recipeTitle);
        assertNotNull(imageUrl, "Generated image URL should not be null");
    }
    
    // Test whether the format of the generated image URL is valid
    @Test
    public void generateImageUrlValidFormat() throws IOException, InterruptedException, URISyntaxException {
        // Set up behavior for the mocked DallE instance
        when(dalle.generateImageURL(anyString())).thenReturn("https://oaidalleapiprodscus.blob.core.windows.net/private/org-mocked/user-mocked/img-mocked.png?st=mocked&se=mocked&sp=mocked&sv=mocked&sr=mocked&rscd=mocked&rsct=image/png&skoid=mocked&sktid=mocked&skt=mocked&ske=mocked&sks=mocked&skv=mocked&sig=mocked");

        String recipeTitle = "Miso Soup";
        String imageUrl = dalle.generateImageURL(recipeTitle);
        assertTrue(urlPattern.matcher(imageUrl).matches(), "Invalid image URL format");
    }
}

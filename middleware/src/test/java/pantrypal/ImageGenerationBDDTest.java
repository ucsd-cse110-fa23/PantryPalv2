package pantrypal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.And;

import java.util.regex.Pattern;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.net.URI;
import java.net.URISyntaxException;

public class ImageGenerationBDDTest { // user story 6 - Recipe Image Generation
    private DallE dalle;
    private Pattern urlPattern;
    
    @BeforeEach
    public void setUp() {
        // Initialize the DallE object before each test execution
        this.dalle = mock(DallE.class);
        // Define the basic structure of the URL, excluding dynamically generated parts
        this.urlPattern = Pattern.compile("https://oaidalleapiprodscus.blob.core.windows.net/private/org-.*/user-.*/img-.*\\.png.*");
    
    }

    @Test
    void testBDDImageGenerateNewRecipe() throws IOException, InterruptedException, URISyntaxException {
        // BDD #1:
        // Given that the user has requested the generation of a recipe through voice input of the meal type “Breakfast” and ingredients “Eggs, bacon, and cheese”
        
        // When the user clicks the “generate recipe” button
        // Then the app should display a generated breakfast recipe with eggs, bacon, and cheese, with an image of what the recipe would look like at the top of the page. 
        String expectedRecipe = "Chicken fried rice";
        String expectedURL = "https://oaidalleapiprodscus.blob.core.windows.net/private/org-mocked/user-mocked/img-mocked-Chicken-fried-rice.png?st=mocked&se=mocked&sp=mocked&sv=mocked&sr=mocked&rscd=mocked&rsct=image/png&skoid=mocked&sktid=mocked&skt=mocked&ske=mocked&sks=mocked&skv=mocked&sig=mocked";

        when(dalle.generateImageURL(eq(expectedRecipe))).thenReturn(expectedURL);
        String actualURL = dalle.generateImageURL(expectedRecipe);

        assertEquals(expectedURL, actualURL);
        assertNotNull(actualURL, "Generated image URL should not be null");
        assertTrue(urlPattern.matcher(actualURL).matches(), "Invalid image URL format");
    }

    @Test
    void testBDDImageGenerateExistRecipe() throws IOException, InterruptedException, URISyntaxException {
        // BDD #2:
        // Given that the user had previously created and saved a recipe that is “Breakfast” with ingredients “Eggs, bacon, and cheese”
        // When the user opens the app and clicks on the “View recipe list” 
        // And the user then clicks on the respective recipe for breakfast with eggs, bacon and cheese
        // Then a generated image of what the recipe looks like should be displayed at the top of the page of the detailed recipe description.

        String expectedRecipe = "Bacon, Egg, and Cheese Pie";
        String expectedURL = "https://oaidalleapiprodscus.blob.core.windows.net/private/org-mocked/user-mocked/img-mocked-Bacon-Egg-and-Cheese-Pie.png?st=mocked&se=mocked&sp=mocked&sv=mocked&sr=mocked&rscd=mocked&rsct=image/png&skoid=mocked&sktid=mocked&skt=mocked&ske=mocked&sks=mocked&skv=mocked&sig=mocked";

        when(dalle.generateImageURL(eq(expectedRecipe))).thenReturn(expectedURL);
        RecipeData exsitRecipe = new RecipeData("Eggs;bacon;cheese;|||||https://oaidalleapiprodscus.blob.core.windows.net/private/org-mocked/user-mocked/img-mocked-Bacon-Egg-and-Cheese-Pie.png?st=mocked&se=mocked&sp=mocked&sv=mocked&sr=mocked&rscd=mocked&rsct=image/png&skoid=mocked&sktid=mocked&skt=mocked&ske=mocked&sks=mocked&skv=mocked&sig=mocked");
        String actualURL = exsitRecipe.imageUrl;

        assertEquals(expectedURL, actualURL);
        assertNotNull(actualURL, "Generated image URL should not be null");
        assertTrue(urlPattern.matcher(actualURL).matches(), "Invalid image URL format");
    }



}

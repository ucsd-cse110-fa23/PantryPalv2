package pantrypal;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import java.util.regex.Pattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class RegenerateRecipeBDDTest { // Feature 14: Recipe regeneration [M]
    private MockGPT mockGPT;
    
    @BeforeEach
    public void setUp() {
        // Initialize the mockGPT object before each test execution
        this.mockGPT = new MockGPT();
    }

    @Test
    void testBDDRegenerateRecipe(){
        // Given A user Mack is unhappy with the breakfast recipe that was generated after saying he has “Potato, balck papper and butter”  
        // When Mack clicks on the regenerate button
        // Then The app generates a new different recipe with the same initial ingredients of potato, balck papper and butter
        String title = "potato mush";
        String[] ingredients = { "potato", "black papper", "butter" };
        String instructions = "Mash the cooked potatoes and fry the black pepper in butter, then stir all the ingredients together.";
        String mealType = "breakfast";
        title+= "\n" + mealType;
        RecipeData recipeData = new RecipeData(title, ingredients, instructions);

        String originalRecipe = recipeData.toString();

        String regenerateRecipe = mockGPT.regenerateRecipe(ingredients, instructions, originalRecipe);
        
        // Check if the regenerated recipe is not null
        assertNotNull(regenerateRecipe, "Regenerated recipe should not be null");
        // Check if the regenerated recipe is different from the original one
        assertNotEquals(originalRecipe, regenerateRecipe, "Regenerated recipe should be different from the original one");
        
    }
}

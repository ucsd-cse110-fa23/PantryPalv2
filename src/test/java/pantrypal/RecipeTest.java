package pantrypal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RecipeTest {
    private Recipe recipe;

    @BeforeEach
    void setUp() {
        recipe = new Recipe();
    }
    
    @Test
    void testSetRecipeIndex() {
        recipe.setRecipeIndex(1);
        assertEquals("1", recipe.index.getText());
    }

    @Test
    void testSetRecipeName() {
        recipe.setRecipeName("Pasta");
        assertEquals("Pasta", recipe.recipeName.getText());
    }

    @Test
    void testSetRecipeDetails() {
        recipe.setRecipeDetails("Ingredients: Flour, Water");
        assertEquals("Ingredients: Flour, Water", recipe.recipeDetails.getText());
    }

}
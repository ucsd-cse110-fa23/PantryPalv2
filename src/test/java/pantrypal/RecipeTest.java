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
    // @Test
    // public void testRecipeCreation() {
    //     Recipe recipe = new Recipe();
    //     assertNotNull(recipe);
    // }

    @Test
    public void testSetRecipeIndex() {
        Recipe recipe = new Recipe();
        recipe.setRecipeIndex(1);
        assertEquals("1", recipe.index.getText());
    }

    @Test
    public void testSetRecipeName() {
        Recipe recipe = new Recipe();
        recipe.setRecipeName("Pasta");
        assertEquals("Pasta", recipe.recipeName.getText());
    }

    @Test
    public void testSetRecipeDetails() {
        Recipe recipe = new Recipe();
        recipe.setRecipeDetails("Ingredients: Flour, Water");
        assertEquals("Ingredients: Flour, Water", recipe.recipeDetails.getText());
    }

}
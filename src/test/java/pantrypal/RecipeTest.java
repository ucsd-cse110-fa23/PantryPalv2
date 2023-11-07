package pantrypal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class RecipeTest {
    private Recipe recipe;

    @BeforeEach
    void setUp() {
        //int in = 1;
        String na = "Pasta";
        String de = "Ingredients: Flour, Water";
        recipe = new Recipe(na,de);
    }
    
    // @Test
    // void testSetRecipeIndex() {
    //     recipe.setRecipeIndex(1);
    //     assertEquals("1", recipe.index.getText());
    // }

    // @Test
    // void testSetRecipe() {
    //     //recipe.setRecipeName("Pasta");
    //     assertEquals("Pasta", recipe.recipeName.getText());
    //     assertEquals("Ingredients: Flour, Water", recipe.recipeDetails.getText());
    // }

    // @Test
    // void testSetRecipeDetails() {
    //     //recipe.setRecipeDetails("Ingredients: Flour, Water");
    //     assertEquals("Ingredients: Flour, Water", recipe.recipeDetails.getText());
    // }

}
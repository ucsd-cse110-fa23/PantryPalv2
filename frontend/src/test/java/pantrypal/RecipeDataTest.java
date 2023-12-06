package pantrypal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RecipeDataTest {
    @Test
    void testRecipeData1() {
        String title = "Title";
        String[] ingredients = {"Ingredient 1", "Ingredient 2", "Ingredient 3"};
        String instructions = "Instructions";

        RecipeData recipe = new RecipeData(title, ingredients, instructions);

        assertEquals(recipe.title, "Title");
        assertEquals(recipe.ingredients[0], "Ingredient 1");
        assertEquals(recipe.ingredients[1], "Ingredient 2");
        assertEquals(recipe.ingredients[2], "Ingredient 3");
        assertEquals(recipe.instructions, "Instructions");
    }

    @Test
    void testRecipeDataWhitespace() {
        String title = "Chicken Chausseur ";
        String[] ingredients = {"Chicken", "Tomatoes", "Potato", "Onion"};
        String instructions = " Cook it";

        RecipeData recipe = new RecipeData(title, ingredients, instructions);
        
        assertEquals(recipe.title, "Chicken Chausseur ");
        assertEquals(recipe.ingredients[0], "Chicken");
        assertEquals(recipe.ingredients[1], "Tomatoes");
        assertEquals(recipe.ingredients[2], "Potato");
        assertEquals(recipe.ingredients[3], "Onion");
        assertEquals(recipe.instructions, " Cook it");

    }
}

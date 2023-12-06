package pantrypal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class RegenerateRecipeTest {
    

    @Test
    void testRegernateRecipe() {
        MockGPT gpt = new MockGPT();

        String title = "potato mush";
        String[] ingredients = { "potato", "black papper", "butter" };
        String instructions = "Mash the cooked potatoes and fry the black pepper in butter, then stir all the ingredients together.";
        RecipeData recipeData = new RecipeData(title, ingredients, instructions);
        String originalRecipe = recipeData.toString();

        String regenerateRecipe = gpt.regenerateRecipe(ingredients, instructions, originalRecipe);
        
        String expectedOutput = "new recipe";

        assertEquals(regenerateRecipe, expectedOutput);


    }

    @Test
    void testRecreateRecipe() {
        //String prompt = "Given the following ingredients:\n";
        String[] ingredients1 = { "Eggs", "Sausage", "Onions" };
        String[] ingredients2 = { "Salami", "Bread", "Lettuce", "Butter" };
        String[] ingredients3 = { "Steak", "Sea Salt", "Pepper", "Garlic", "Olive Oil" };
        String mealType1 = "Breakfast";
        String mealType2 = "Lunch";
        String mealType3 = "Dinner";

        MockGPT gpt = new MockGPT();
        String test1 = gpt.recreateRecipe(ingredients1, mealType1);
        String test2 = gpt.recreateRecipe(ingredients2, mealType2);
        String test3 = gpt.recreateRecipe(ingredients3, mealType3);
        String output = "Proper new recipe here";

        assertEquals(test1, output);
        assertEquals(test2, output);
        assertEquals(test3, output);

    }
}

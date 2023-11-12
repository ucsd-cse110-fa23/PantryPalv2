package pantrypal;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CRUDRecipesTest {

    private void resetRecipeFile() {
        Path path = Paths.get(CRUDRecipes.FILE_PATH); 
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void setUp() {
        resetRecipeFile();
    }

    @AfterEach
    void tearDown() {
        resetRecipeFile();
    }
    
    //General check #1 for create recipe
    @Test
    void testCreateRecipesGeneral() {
        String[] ingredients1 = {"Chicken", "Rice"};
        RecipeData recipe1 = new RecipeData("Chicken and rice", ingredients1, "Cook it together");

        try {
            CRUDRecipes.createRecipe(recipe1);
            assertTrue(CRUDRecipes.recipeExists(recipe1.title));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Test with no ingredients
    @Test
    void testCreateRecipesNoIngredients() {
        String[] ingredients1 = {};
        RecipeData recipe1 = new RecipeData("Nada", ingredients1, "Nadada");

        try {
            CRUDRecipes.createRecipe(recipe1);
            assertTrue(CRUDRecipes.recipeExists(recipe1.title));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCreateRecipesEmptyTitle() {
        String[] ingredients1 = {};
        RecipeData recipe1 = new RecipeData("", ingredients1, "Nadada");

        try {
            CRUDRecipes.createRecipe(recipe1);
            assertTrue(CRUDRecipes.recipeExists(recipe1.title));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testRecipeExists() {
        String[] ingredients1 = {"Chicken"};
        RecipeData recipe1 = new RecipeData("", ingredients1, "Nadada");

        try {
            CRUDRecipes.createRecipe(recipe1);
            assertTrue(CRUDRecipes.recipeExists(recipe1.title));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testRecipeExistsNoIngredients() {
        String[] ingredients1 = {};
        RecipeData recipe1 = new RecipeData("", ingredients1, "Nadada");

        try {
            CRUDRecipes.createRecipe(recipe1);
            assertTrue(CRUDRecipes.recipeExists(recipe1.title));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testRecipeExistsNoInstructions() {
        String[] ingredients1 = {};
        RecipeData recipe1 = new RecipeData("", ingredients1, "");

        try {
            CRUDRecipes.createRecipe(recipe1);
            assertTrue(CRUDRecipes.recipeExists(recipe1.title));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testRecipeExistsForWrongRecipe() {
        String[] ingredients1 = {};
        RecipeData recipe1 = new RecipeData("", ingredients1, "");

        try {
            CRUDRecipes.createRecipe(recipe1);
            assertFalse(CRUDRecipes.recipeExists("NOT A RECIPE"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testUpdateRecipeAddIngredients() {
        String title = "Chicken and Rice";
        String[] ingredientsInitial = {"Chicken", "Rice"};
        String[] ingredientsUpdated = {"Chicken", "Rice", "Onion"};
        RecipeData recipe1 = new RecipeData(title, ingredientsInitial, "Cook it together");

        try {
            CRUDRecipes.createRecipe(recipe1);
            RecipeData updatedRecipe = CRUDRecipes.getRecipe(title);
            updatedRecipe.ingredients = ingredientsUpdated;
            CRUDRecipes.updateRecipe(updatedRecipe);
            assertArrayEquals(CRUDRecipes.getRecipe(title).ingredients, ingredientsUpdated);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testUpdateRecipeDeleteIngredients() {
        String title = "Chicken and Rice";
        String[] ingredientsInitial = {"Chicken", "Rice"};
        String[] ingredientsUpdated = {};
        RecipeData recipe1 = new RecipeData(title, ingredientsInitial, "Cook it together");

        try {
            CRUDRecipes.createRecipe(recipe1);
            RecipeData updatedRecipe = CRUDRecipes.getRecipe(title);
            updatedRecipe.ingredients = ingredientsUpdated;
            CRUDRecipes.updateRecipe(updatedRecipe);
            assertArrayEquals(CRUDRecipes.getRecipe(title).ingredients, ingredientsUpdated);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testUpdateRecipeNoChanges() {
        String title = "Chicken and Rice";
        String[] ingredientsInitial = {"Chicken", "Rice"};
        String[] ingredientsUpdated = {"Chicken", "Rice"};
        RecipeData recipe1 = new RecipeData(title, ingredientsInitial, "Cook it together");

        try {
            CRUDRecipes.createRecipe(recipe1);
            RecipeData updatedRecipe = CRUDRecipes.getRecipe(title);
            updatedRecipe.ingredients = ingredientsUpdated;
            CRUDRecipes.updateRecipe(updatedRecipe);
            assertArrayEquals(CRUDRecipes.getRecipe(title).ingredients, ingredientsUpdated);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testUpdateRecipeInstructions() {
        String title = "Chicken and Rice";
        String[] ingredients = {"Chicken", "Rice"};
        String instructionsInitial = "Cook it up";
        String instructionsUpdated = "Cook the chicken with the rice";
        RecipeData recipe1 = new RecipeData(title, ingredients, instructionsInitial);

        try {
            CRUDRecipes.createRecipe(recipe1);
            RecipeData updatedRecipe = CRUDRecipes.getRecipe(title);
            updatedRecipe.instructions = instructionsUpdated;
            CRUDRecipes.updateRecipe(updatedRecipe);
            assertEquals(CRUDRecipes.getRecipe(title).instructions, instructionsUpdated);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testUpdateRecipeDeleteInstructions() {
        String title = "Chicken and Rice";
        String[] ingredients = {"Chicken", "Rice"};
        String instructionsInitial = "Cook it up";
        String instructionsUpdated = "";
        RecipeData recipe1 = new RecipeData(title, ingredients, instructionsInitial);

        try {
            CRUDRecipes.createRecipe(recipe1);
            RecipeData updatedRecipe = CRUDRecipes.getRecipe(title);
            updatedRecipe.instructions = instructionsUpdated;
            CRUDRecipes.updateRecipe(updatedRecipe);
            assertEquals(CRUDRecipes.getRecipe(title).instructions, instructionsUpdated);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetRecipe() {
        String title = "Chicken and Rice";
        String[] ingredients = {"Chicken", "Rice"};
        String instructions = "Cook it up";
        RecipeData recipe1 = new RecipeData(title, ingredients, instructions);

        try {
            CRUDRecipes.createRecipe(recipe1);

            //Check that title, ingredients, and instructions are the same
            assertEquals(CRUDRecipes.getRecipe(title).title, recipe1.title);
            assertArrayEquals(CRUDRecipes.getRecipe(title).ingredients, recipe1.ingredients);
            assertEquals(CRUDRecipes.getRecipe(title).instructions, recipe1.instructions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetRecipeDifferentRecipes() {
        String title1 = "Chicken and Rice";
        String[] ingredients1 = {"Chicken", "Rice"};
        String instructions1 = "Cook it up";
        RecipeData recipe1 = new RecipeData(title1, ingredients1, instructions1);

        String title2 = "Beef and Rice";
        String[] ingredients2 = {"Beef", "Rice"};
        String instructions2 = "Dont cook it up";
        RecipeData recipe2 = new RecipeData(title2, ingredients2, instructions2);

        try {
            CRUDRecipes.createRecipe(recipe1);
            //Check that title, ingredients, and instructions are the same
            assertNotEquals(CRUDRecipes.getRecipe(title1).title, recipe2.title);
            assertNotEquals(CRUDRecipes.getRecipe(title1).instructions, recipe2.instructions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDeleteRecipe() {
        String title1 = "Chicken and Rice";
        String[] ingredients1 = {"Chicken", "Rice"};
        String instructions1 = "Cook it up";
        RecipeData recipe1 = new RecipeData(title1, ingredients1, instructions1);

        try {
            CRUDRecipes.createRecipe(recipe1);
            
            //Ensure recipe has been added
            assertTrue(CRUDRecipes.recipeExists(title1));

            CRUDRecipes.deleteRecipe(recipe1.title);

            assertFalse(CRUDRecipes.recipeExists(title1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDeleteRecipe2() {
        String title1 = "";
        String[] ingredients1 = {};
        String instructions1 = "";
        RecipeData recipe1 = new RecipeData(title1, ingredients1, instructions1);

        try {
            CRUDRecipes.createRecipe(recipe1);
            
            //Ensure recipe has been added
            assertTrue(CRUDRecipes.recipeExists(title1));

            CRUDRecipes.deleteRecipe(recipe1.title);

            assertFalse(CRUDRecipes.recipeExists(title1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testReadRecipes() {
        String title1 = "Chicken and Rice";
        String[] ingredients1 = {"Chicken", "Rice"};
        String instructions1 = "Cook it up";
        RecipeData recipe1 = new RecipeData(title1, ingredients1, instructions1);

        String title2 = "Beef and Rice";
        String[] ingredients2 = {"Beef", "Rice"};
        String instructions2 = "Dont cook it up";
        RecipeData recipe2 = new RecipeData(title2, ingredients2, instructions2);

        try {
            CRUDRecipes.createRecipe(recipe1);
            CRUDRecipes.createRecipe(recipe2);

            ArrayList<RecipeData> expectedRecipes = new ArrayList<>();
            expectedRecipes.add(recipe1);
            expectedRecipes.add(recipe2);

            ArrayList<RecipeData> resultRecipes = CRUDRecipes.readRecipes();

            assertEquals(expectedRecipes.get(0).title, recipe1.title);
            assertArrayEquals(expectedRecipes.get(0).ingredients, recipe1.ingredients);
            assertEquals(expectedRecipes.get(0).instructions, recipe1.instructions);

            assertEquals(expectedRecipes.get(1).title, recipe2.title);
            assertArrayEquals(expectedRecipes.get(1).ingredients, recipe2.ingredients);
            assertEquals(expectedRecipes.get(1).instructions, recipe2.instructions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package pantrypal;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.*;

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
}

package pantrypal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import org.junit.jupiter.api.Test;

public class CreateAccountBDD {

    // BDD where the user creates an account and creates some recipes
    @Test
    void testCreateAccount() {
        IMiddlewareModel middleware = new MockMiddlewareModel();
        String username = "chef12";
        String password = "coolchefboi";

        String[] ingredients1 = { "Chicken", "Rice" };
        RecipeData recipe1 = new RecipeData("Chicken and rice", ingredients1, "Cook it together");

        try {
            assertTrue(AccountService.accountSignup(username, password, middleware));
            assertTrue(AccountService.accountLogin(username, password, middleware));

            CRUDRecipes.createRecipe(recipe1);
            assertTrue(CRUDRecipes.recipeExists(recipe1.title));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // BDD where the user creates an account and creates some recipes
    @Test
    void testExistingCreateAccount() {
        IMiddlewareModel middleware = new MockMiddlewareModel();
        String username = "chef12";
        String password = "coolchefboi";
        String password2 = "coolchefboi2";

        String[] ingredients1 = { "Chicken", "Rice" };
        RecipeData recipe1 = new RecipeData("Chicken and rice", ingredients1, "Cook it together");

        try {
            AccountService.accountSignup(username, password, middleware);
            assertTrue(AccountService.accountLogin(username, password, middleware));

            AccountService.accountSignup(username, password2, middleware);
            
            assertTrue(AccountService.accountLogin(username, password, middleware));
            assertFalse(AccountService.accountLogin(username, password2, middleware));

            CRUDRecipes.createRecipe(recipe1);
            assertTrue(CRUDRecipes.recipeExists(recipe1.title));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
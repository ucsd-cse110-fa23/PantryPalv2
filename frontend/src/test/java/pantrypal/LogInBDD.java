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

public class LogInBDD {

    // BDD test where the user logs in successfully and creates recipes
    @Test
    void testLogIn() {
        IMiddlewareModel middleware = new MockMiddlewareModel();
        String username = "chef12";
        String password = "coolchefboi";

        String[] ingredients1 = { "Chicken", "Rice" };
        RecipeData recipe1 = new RecipeData("Chicken and rice", ingredients1, "Cook it together");

        try {
            AccountService.accountSignup(username, password, middleware);
            assertTrue(AccountService.accountLogin(username, password, middleware));

            CRUDRecipes.createRecipe(recipe1);
            assertTrue(CRUDRecipes.recipeExists(recipe1.title));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // BDD test where the user first tries to log in with a username that does not
    // exist in the database
    // and then logs in with the correct login credentials that do exist in the
    // database, and then continues
    // to create some recipes
    @Test
    void testLogInNonExistent() {
        IMiddlewareModel middleware = new MockMiddlewareModel();
        String username = "chef3";
        String password = "coolguy";

        String[] ingredients1 = { "Chicken", "Rice" };
        RecipeData recipe1 = new RecipeData("Chicken and rice", ingredients1, "Cook it together");

        String nonexistentUser = "chef4";

        try {
            AccountService.accountSignup(username, password, middleware);
            assertFalse(AccountService.accountLogin(nonexistentUser, password, middleware));
            assertTrue(AccountService.accountLogin(username, password, middleware));

            CRUDRecipes.createRecipe(recipe1);
            assertTrue(CRUDRecipes.recipeExists(recipe1.title));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
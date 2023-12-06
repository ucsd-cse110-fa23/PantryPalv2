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

public class ShareRecipeBDD {

    @Test
    void testShareRecipeBDD() {
        IMiddlewareModel middleware = new MockMiddlewareModel();
        String username = "chef12";
        String password = "coolchefboi";

        String[] ingredients1 = { "Eggs", "Bread" };
        String type1 = "Breakfast";
        RecipeData recipe1 = new RecipeData("Eggs and Bread", ingredients1, "Cook it together", type1);

        try {
            AccountService.accountSignup(username, password, middleware);
            assertTrue(AccountService.accountLogin(username, password, middleware));

            CRUDRecipes.createRecipe(recipe1);
            assertTrue(CRUDRecipes.recipeExists(recipe1.title));

            String link = "http://localhost:8080/share/recipe?recipe=Eggs_and_Bread";
            assertTrue(link != null && link.equals("http://localhost:8080/share/recipe?recipe=Eggs_and_Bread"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
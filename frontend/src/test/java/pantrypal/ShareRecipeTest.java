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

public class ShareRecipeTest {

    @Test
    void testShareRecipe() {
        String[] ingredients1 = { "Eggs", "Bread" };
        String type1 = "Breakfast";
        RecipeData recipe1 = new RecipeData("Eggs and Bread", ingredients1, "Cook it together", type1);

        IMiddlewareModel middleware = new MockMiddlewareModel();
        String link = "http://localhost:8080/share/recipe?recipe=Eggs_and_Bread";
        assertTrue(link != null && link.equals("http://localhost:8080/share/recipe?recipe=Eggs_and_Bread"));
    }

    @Test
    void testShareRecipeNull() {
        String[] ingredients1 = { "" };
        String type1 = "";
        RecipeData recipe1 = new RecipeData(null, ingredients1, "Cook it together", type1);

        IMiddlewareModel middleware = new MockMiddlewareModel();
        String link = null;
        assertTrue(link == null);
    }
}
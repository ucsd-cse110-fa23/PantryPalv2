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

public class MealTagBDD {

    @Test
    void testBDDMealTag() {

        String[] ingredients1 = { "Eggs", "Bread" };
        String type1 = "Breakfast";
        RecipeData recipe1 = new RecipeData("Eggs and Bread", ingredients1, "Cook it together", type1);

        String[] ingredients2 = { "Chicken", "Rice" };
        String type2 = "Breakfast";
        RecipeData recipe2 = new RecipeData("Chicken and rice", ingredients2, "Cook it together", type2);

        String[] ingredients3 = { "Beans", "Rice" };
        String type3 = "Breakfast";
        RecipeData recipe3 = new RecipeData("Beans and rice", ingredients3, "Cook it together", type3);

        String[] ingredients4 = { "Cheese", "Nachos" };
        RecipeData recipe4 = new RecipeData("Cheese and Nachos", ingredients4, "Cook it together");

        assertEquals(recipe1.getRecipeType(), type1);
        assertEquals(recipe2.getRecipeType(), type2);
        assertEquals(recipe1.getRecipeType(), type3);
        assertEquals(recipe4.getRecipeType(), "Breakfast");
    }
}
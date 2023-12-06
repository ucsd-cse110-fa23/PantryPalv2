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

public class MealTagTest {

    @Test
    void testMealTagBreakfast() {

        String[] ingredients1 = { "Eggs", "Bread" };
        String type = "Breakfast";
        RecipeData recipe1 = new RecipeData("Eggs and Bread", ingredients1, "Cook it together", type);

        assertEquals(recipe1.getRecipeType(), type);
    }

    @Test
    void testMealTagLunch() {

        String[] ingredients1 = { "Chicken", "Rice" };
        String type = "Breakfast";
        RecipeData recipe1 = new RecipeData("Chicken and rice", ingredients1, "Cook it together", type);

        assertEquals(recipe1.getRecipeType(), type);
    }

    @Test
    void testMealTagDinner() {

        String[] ingredients1 = { "Beans", "Rice" };
        String type = "Breakfast";
        RecipeData recipe1 = new RecipeData("Beans and rice", ingredients1, "Cook it together", type);

        assertEquals(recipe1.getRecipeType(), type);
    }

    @Test
    void testMealTagNull() {

        String[] ingredients1 = { "Beans", "Rice" };
        RecipeData recipe1 = new RecipeData("Beans and rice", ingredients1, "Cook it together");

        assertEquals(recipe1.getRecipeType(), "Breakfast");
    }
}
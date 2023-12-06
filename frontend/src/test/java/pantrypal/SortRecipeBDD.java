package pantrypal;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class SortRecipeBDD {
    
    @Test
    void testFilterBDD() {
        // Given there exist 0 or more than 1 recipes that are saved in the recipe list and the user is logged in to their account
        // When the user selects the “sort” button
        // Then the user is prompted with a dropdown menu displaying the various types of sorting mechanisms such as “alphabetically”, “reverse alphabetically”, “chronologically”, “reverse chronologically”
        // When the user clicks on the sort type they want to apply to the recipe list from the dropdown menu
        // Then the recipes are sorted in the designated manner based on the selected sort type. 

        //populate the list of recipes - precondition
        // uses the "MockSortFilterUI" class to mock the frontend behavior of the application

        ArrayList<RecipeData> recipes = new ArrayList<RecipeData>();
        recipes.add(new RecipeData("A",null,null,"Lunch"));
        recipes.add(new RecipeData("B",null,null,"Breakfast"));
        recipes.add(new RecipeData("C",null,null,"Lunch"));
        recipes.add(new RecipeData("D",null,null,"Dinner"));

        MockSortFilterLogic mock = new MockSortFilterLogic(recipes);

        // create expected results arrays 
        String[] lunch = {"A","C"};
        String[] dinner = {"D"};
        String[] breakfast = {"B"};
        String[] lunchandbreakfast = {"A","B","C"};
        String[] lunchanddinner = {"A","D","C"};
        String[] breakfastanddinner = {"B","D"};
        String[] all = {"A","B","C","D"};

        // breakfast and dinner 
        mock.toggleTag("Lunch");
        // assert correct order
        int i = 0;
        for (RecipeData r : mock.currentRecipes) {
            assertTrue(Arrays.asList(breakfastanddinner).contains(r.title));
            i++;
        }
        assertEquals(mock.currentRecipes.size(),breakfastanddinner.length);
        //assertFalse(true);
        // just breakfast
        mock.toggleTag("Dinner");
        i = 0;
        for (RecipeData r : mock.currentRecipes) {
            assertTrue(Arrays.asList(breakfast).contains(r.title));
            i++;
        }
        assertEquals(mock.currentRecipes.size(),breakfast.length);

        // just lunch
        mock.toggleTag("Breakfast");
        mock.toggleTag("Lunch");
        i = 0;
        for (RecipeData r : mock.currentRecipes) {
            //System.out.println(Arrays.asList(lunch));
            //System.out.println(r.title);
            assertTrue(Arrays.asList(lunch).contains(r.title));
            i++;
        }
        assertEquals(mock.currentRecipes.size(),lunch.length);

        // dinner and lunch
        mock.toggleTag("Dinner");
        i = 0;
        for (RecipeData r : mock.currentRecipes) {
            //System.out.println(Arrays.asList(lunch));
            //System.out.println(r.title);
            assertTrue(Arrays.asList(lunchanddinner).contains(r.title));
            i++;
        }
        assertEquals(mock.currentRecipes.size(),lunchanddinner.length);

        // just dinner
        mock.toggleTag("Lunch");
        i = 0;
        for (RecipeData r : mock.currentRecipes) {
            //System.out.println(Arrays.asList(lunch));
            //System.out.println(r.title);
            assertTrue(Arrays.asList(dinner).contains(r.title));
            i++;
        }
        assertEquals(mock.currentRecipes.size(),dinner.length);

        // lunch and breakfast
        mock.toggleTag("Lunch");
        mock.toggleTag("Breakfast");
        mock.toggleTag("Dinner");
        i = 0;
        for (RecipeData r : mock.currentRecipes) {
            //System.out.println(Arrays.asList(lunch));
            //System.out.println(r.title);
            assertTrue(Arrays.asList(lunchandbreakfast).contains(r.title));
            i++;
        }
        assertEquals(mock.currentRecipes.size(),lunchandbreakfast.length);

        // test all
        mock.toggleTag("Dinner");
        i = 0;
        for (RecipeData r : mock.currentRecipes) {
            //System.out.println(Arrays.asList(lunch));
            //System.out.println(r.title);
            assertTrue(Arrays.asList(all).contains(r.title));
            i++;
        }
        assertEquals(mock.currentRecipes.size(),all.length);
    }
}


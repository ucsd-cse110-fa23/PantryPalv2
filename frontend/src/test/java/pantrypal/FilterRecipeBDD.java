package pantrypal;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import javafx.beans.binding.When;

public class FilterRecipeBDD {
    
    @Test
    void testFilterBDD() {
        // Given there exist 0 or more than 1 recipes that are saved in the recipe list and the user is logged in to their account and has clicked on the “View Recipe List”
        // When the user selects the “filter” button
        // Then the user is prompted with a dropdown menu displaying the three type of meals “breakfast”, “lunch”, or “dinner”
        // When the user clicks on the meal type they want to apply to the recipe list from the dropdown menu
        // Then the recipes are filtered by the designated meal type to only show the selected meal type  



        ArrayList<RecipeData> recipes = new ArrayList<RecipeData>();
        recipes.add(new RecipeData("A title",null,null));
        recipes.add(new RecipeData("B title",null,null));
        recipes.add(new RecipeData("C title",null,null));
        recipes.add(new RecipeData("D title",null,null));

        // set an order of times 
        recipes.get(2).createdTime = 1;
        recipes.get(3).createdTime = 2;
        recipes.get(0).createdTime = 3;
        recipes.get(1).createdTime = 4;

        MockSortFilterUI mock = new MockSortFilterUI(recipes);

        // create expected results arrays 
        String[] alphabetically = {"A title","B title","C title","D title"};
        String[] reverseZA = {"D title","C title","B title","A title"};
        String[] oldtonew = {"C title","D title","A title","B title"};
        String[] newtoold = {"B title","A title","D title","C title"};

        // sort by alphabetically 
        mock.updateList("Alphabetically");
        // assert correct order
        int i = 0;
        for (RecipeData r : mock.currentRecipes) {
            assertEquals(r.title,alphabetically[i]);
            i++;
        }

        // sort reverse alphabet
        mock.updateList("Reverse Alphabetically");
        // assert correct order
        i = 0;
        for (RecipeData r : mock.currentRecipes) {
            assertEquals(r.title,reverseZA[i]);
            i++;
        }

        // sort old to new 
        mock.updateList("Newest First");
        // assert correct order
        i = 0;
        for (RecipeData r : mock.currentRecipes) {
            assertEquals(r.title,newtoold[i]);
            i++;
        }

        // sort new to old
        mock.updateList("Oldest First");
        // assert correct order
        i = 0;
        for (RecipeData r : mock.currentRecipes) {
            assertEquals(r.title,oldtonew[i]);
            i++;
        }

    }
}


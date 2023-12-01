package pantrypal;

import java.util.ArrayList;
import java.util.Collections;

public class SortRecipes {
    public static ArrayList<RecipeData> sortAlphabetically(ArrayList<RecipeData> recipes, boolean isReverse) {
        ArrayList<RecipeData> sortedRecipes = new ArrayList<RecipeData>(recipes);
        recipes.sort((RecipeData r1, RecipeData r2) -> r1.title.compareTo(r2.title));
        if (isReverse)
            Collections.reverse(sortedRecipes);

        return sortedRecipes;
    }

    public static ArrayList<RecipeData> sortByTime(ArrayList<RecipeData> recipes, boolean isReverse) {
        ArrayList<RecipeData> sortedRecipes = new ArrayList<RecipeData>(recipes);
        recipes.sort((RecipeData r1, RecipeData r2) -> (int) (r1.createdTime - r2.createdTime));
        if (isReverse)
            Collections.reverse(sortedRecipes);

        return sortedRecipes;
    }
}

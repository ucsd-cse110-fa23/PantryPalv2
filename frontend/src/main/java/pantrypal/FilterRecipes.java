package pantrypal;

import java.util.ArrayList;
import java.util.List;

public class FilterRecipes {
    public static ArrayList<RecipeData> filterRecipeByMeal(ArrayList<RecipeData> recipes, List<String> mealTypes) {
        ArrayList<RecipeData> filteredRecipes = new ArrayList<RecipeData>();
        for (RecipeData recipe : recipes) {
            if (mealTypes.contains(recipe.type.trim())) {
                filteredRecipes.add(recipe);
            }
        }
        return filteredRecipes;
    }
}

package pantrypal;

import java.util.ArrayList;

public class FilterRecipes {
    public static ArrayList<RecipeData> filterRecipeByMeal(ArrayList<RecipeData> recipes, String mealType) {
        ArrayList<RecipeData> filteredRecipes = new ArrayList<RecipeData>();
        for (RecipeData recipe : recipes) {
            if (recipe.type.equals(mealType)) {
                filteredRecipes.add(recipe);
            }
        }
        return filteredRecipes;
    }
}

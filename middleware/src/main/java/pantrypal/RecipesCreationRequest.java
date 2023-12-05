package pantrypal;

import java.util.List;

public class RecipesCreationRequest {
    private List<RecipeData> recipes;
    private String username;

    public List<RecipeData> getRecipes() {
        return recipes;
    }

    public String getUsername() {
        return username;
    }

    public void setRecipes(List<RecipeData> rs) {
        recipes = rs;
    }

    public void setUsername(String u) {
        username = u;
    }
}

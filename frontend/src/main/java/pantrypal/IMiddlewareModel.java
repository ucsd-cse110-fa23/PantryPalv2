package pantrypal;

import java.util.List;

public interface IMiddlewareModel {
    public List<RecipeData> getRecipes();
    public void postRecipes(List<RecipeData> recipes);
    public String mealTypeExtraction(String filePath);
    public String generateRecipe(String filePath, String mealType);
    public Boolean postAccountAuthentication(Account acc);
    public Boolean postAccountCreation(Account acc);
}

package pantrypal;

import java.util.List;

public interface IMiddlewareModel {
    public List<RecipeData> getRecipes(Account acc);
    public void postRecipes(List<RecipeData> recipes, Account acc);
    public String mealTypeExtraction(String filePath);
    public String generateRecipe(String filePath, String mealType);
    public Boolean postAccountAuthentication(Account acc);
    public Boolean postAccountCreation(Account acc);
    public Boolean isServerOnline();
}

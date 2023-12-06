package pantrypal;
import java.util.List;
import java.util.ArrayList;

public class MockMiddlewareModel implements IMiddlewareModel {
    private ArrayList<Account> accounts = new ArrayList<>();
    private List<RecipeData> recipes = new ArrayList<>();

    // TODO: implement or remove from interface
    public List<RecipeData> getRecipes(Account acc) {
        return recipes;
    }

    // TODO: implement or remove from interface
    public void postRecipes(List<RecipeData> rs, Account acc) {
        recipes = rs;
    }

    // TODO: implement or remove from interface
    public String mealTypeExtraction(String filePath) {
        return "";
    }

    // TODO: implement or remove from interface
    public String generateRecipe(String filePath, String mealType) {
        return "";
    }

    public Boolean postAccountAuthentication(Account acc) {
        for(Account a : accounts) {
            if(a.equals(acc)) {
                return true;
            }
        }
        return false;
    }

    public Boolean postAccountCreation(Account acc) {
        for(Account a : accounts) {
            if(a.getUsername().equals(acc.getUsername())) {
                return false;
            }
        }
        accounts.add(acc);
        return true;
    }
}

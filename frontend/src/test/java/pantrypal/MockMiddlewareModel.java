package pantrypal;
import java.util.ArrayList;
import java.util.List;

public class MockMiddlewareModel implements IMiddlewareModel {
    private ArrayList<Account> accounts;
    // private ArrayList<Account> accounts = new ArrayList<>() {{
    //     add(new Account("test1", "pass1"));
    //     add(new Account("test2", "pass2"));
    //     add(new Account("test3", "pass3"));
    // }};

    // TODO: implement or remove from interface
    public List<RecipeData> getRecipes() {
        return null;
    }

    // TODO: implement or remove from interface
    public void postRecipes(List<RecipeData> recipes) {
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
            if(a.getUsername() == acc.getUsername()) {
                return false;
            }
        }
        accounts.add(acc);
        return true;
    }
}

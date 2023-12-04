// package pantrypal;

// import static org.junit.jupiter.api.Assertions.assertArrayEquals;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertNotEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.io.IOException;
// import java.nio.file.*;
// import java.util.ArrayList;

// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// public class CreateAccountBDD {
//     private void resetAccountsFile() {
//         AccountService.changeFilePath("test_account.json");
//         Path path = Paths.get(AccountService.FILE_PATH);
//         try {
//             Files.deleteIfExists(path);
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     @BeforeEach
//     void setUp() {
//         resetAccountsFile();
//     }

//     @AfterEach
//     void tearDown() {
//         resetAccountsFile();
//     }

//     // BDD where the user creates an account and creates some recipes
//     @Test
//     void testCreateAccount() {
//         String username = "chef12";
//         String password = "coolchefboi";
//         Account account1 = new Account(username, password);

//         String[] ingredients1 = { "Chicken", "Rice" };
//         RecipeData recipe1 = new RecipeData("Chicken and rice", ingredients1, "Cook it together");

//         try {
//             AccountService.createAccount(account1);
//             assertTrue(AccountService.accountExists(account1.getUsername()));

//             CRUDRecipes.createRecipe(recipe1);
//             assertTrue(CRUDRecipes.recipeExists(recipe1.title));
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     // BDD where the user creates an account and creates some recipes
//     @Test
//     void testExistingCreateAccount() {
//         String username = "chef12";
//         String password = "coolchefboi";
//         Account account1 = new Account(username, password);

//         String password2 = "coolchefboi2";
//         Account account2 = new Account(username, password);

//         String[] ingredients1 = { "Chicken", "Rice" };
//         RecipeData recipe1 = new RecipeData("Chicken and rice", ingredients1, "Cook it together");

//         try {
//             AccountService.createAccount(account1);
//             assertTrue(AccountService.accountExists(account1.getUsername()));

//             AccountService.createAccount(account2);
            
//             assertEquals(AccountService.getAccount(username).getPassword(), password);
//             assertNotEquals(AccountService.getAccount(username).getPassword(), password2);

//             CRUDRecipes.createRecipe(recipe1);
//             assertTrue(CRUDRecipes.recipeExists(recipe1.title));
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
// }
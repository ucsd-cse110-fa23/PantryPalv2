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

// public class LogInBDD {
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

//     // BDD test where the user logs in successfully and creates recipes
//     @Test
//     void testLogIn() {
//         String username = "chef12";
//         String password = "coolchefboi";
//         Account account1 = new Account(username, password);

//         String[] ingredients1 = { "Chicken", "Rice" };
//         RecipeData recipe1 = new RecipeData("Chicken and rice", ingredients1, "Cook it together");

//         try {
//             AccountService.createAccount(account1);
//             assertTrue(AccountService.accountExists(account1.getUsername()));
//             assertEquals(account1.getPassword(), password);
//             assertTrue(AccountService.login(account1));
//             CRUDRecipes.createRecipe(recipe1);
//             assertTrue(CRUDRecipes.recipeExists(recipe1.title));
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     // BDD test where the user first tries to log in with a username that does not
//     // exist in the database
//     // and then logs in with the correct login credentials that do exist in the
//     // database, and then continues
//     // to create some recipes
//     @Test
//     void testLogInNonExistent() {
//         String username = "chef3";
//         String password = "coolguy";
//         Account account1 = new Account(username, password);

//         String[] ingredients1 = { "Chicken", "Rice" };
//         RecipeData recipe1 = new RecipeData("Chicken and rice", ingredients1, "Cook it together");

//         String nonexistentUser = "chef4";
//         Account nonexistentAccount = new Account(nonexistentUser, password);

//         try {
//             AccountService.createAccount(account1);
//             assertFalse(AccountService.accountExists(nonexistentUser));
//             assertTrue(AccountService.accountExists(account1.getUsername()));
//             assertEquals(account1.getPassword(), password);
//             assertTrue(AccountService.login(account1));
//             CRUDRecipes.createRecipe(recipe1);
//             assertTrue(CRUDRecipes.recipeExists(recipe1.title));
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
// }
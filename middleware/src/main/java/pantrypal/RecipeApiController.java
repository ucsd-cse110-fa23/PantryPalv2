package pantrypal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeApiController {

    private RecipeService recipeService;

    @Autowired
    public RecipeApiController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public ResponseEntity<List<RecipeData>> getAllRecipes() {
        System.out.println("Getting all recipes");
        try {
            List<RecipeData> recipes = recipeService.readRecipes();
            return ResponseEntity.ok(recipes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping
    public ResponseEntity<String> createRecipe(@RequestBody List<RecipeData> recipes) {
        // recipes contains all the recipes that should exist in the file 
        recipeService.deleteRecipesFile();
        try {
            for (RecipeData recipe : recipes) {
                recipeService.createRecipe(recipe);
            }
            return ResponseEntity.ok("Successful recipes creation");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error with recipe creation");
        }

    }

    // @PostMapping
    // public ResponseEntity<String> createRecipe(@RequestBody RecipeData recipe) {
    // try {
    // recipeService.createRecipe(recipe);
    // return ResponseEntity.ok("Successful recipe creation");
    // } catch (IOException e) {
    // e.printStackTrace();
    // return ResponseEntity.internalServerError().body("Error with recipe
    // creation");
    // }

    // }

    @GetMapping("/{title}")
    public ResponseEntity<RecipeData> getRecipeByTitle(@PathVariable String title) {
        RecipeData recipe = null;
        try {
            recipe = recipeService.getRecipeByTitle(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recipe != null ? ResponseEntity.ok(recipe) : ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<String> updateRecipe(@RequestBody RecipeData recipeDetails) {
        try {
            recipeService.updateRecipe(recipeDetails);
            return ResponseEntity.ok("Recipe updated!");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error with recipe update");
        }
    }

    @DeleteMapping("/{title}")
    public ResponseEntity<?> deleteRecipe(@PathVariable String title) {
        try {
            recipeService.deleteRecipe(title);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}

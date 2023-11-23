package pantrypal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeApiController {

    @Autowired
    private RecipeService recipeService;

    @PostMapping
    public ResponseEntity<String> createRecipe(@RequestBody RecipeData recipe) {
        try {
            recipeService.createRecipe(recipe);
            return ResponseEntity.ok("Successful recipe creation");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error with recipe creation");
        }
        
    }

    @GetMapping
    public List<RecipeData> getAllRecipes() {
        try {
            return recipeService.readRecipes();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

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

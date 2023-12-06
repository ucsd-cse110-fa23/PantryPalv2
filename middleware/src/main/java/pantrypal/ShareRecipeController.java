package pantrypal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/share/recipe")
public class ShareRecipeController {

    @GetMapping
    public String getSharedRecipe(@RequestParam(name = "recipe", defaultValue = "None") String recipeTitle) {
        if(recipeTitle.equals("None")) {
            return "Invalid URL";
        }
        String output = "Invalid URL";
        
        try {
            RecipeData recipe = ShareableRecipeService.readRecipe(recipeTitle);

            output = """
                <!DOCTYPE html>
                <html xmlns:th="http://www.thymeleaf.org">
                <head>
                    <title>%s</title>
                </head>
                <body>
                    <h1>%s</h1>
                    <div>%s</div>
                    <div>%s</div>
                </body>
                </html>
                """;

            output = String.format(output, recipe.title, recipe.title, recipe.ingredients, recipe.instructions);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createSharedRecipe(@RequestBody RecipeData recipe) {
        try {
            ShareableRecipeService.writeRecipe(recipe);
            return ResponseEntity.ok(recipe.title);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("An error occurred while .");
        }
    }

}

package pantrypal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
public class RegenerateRecipeController {

    
    @PostMapping("/api/regenerate-recipe")
    public ResponseEntity<String> regenerateRecipe(
            @RequestParam("mealType") String mealType,
            @RequestParam("originalRecipe") String originalRecipe,
            @RequestParam("ingredients") String ingredients) {

        try {
            
            String[] ingredientArray = ingredients.split(",");
            //String newRecipe = recipeService.regenerateRecipe(ingredientArray, mealType, originalRecipe);

            //return ResponseEntity.ok(newRecipe);
            return ResponseEntity.ok(regenerateRecipe(new ChatGPT(), new DallE(), ingredientArray, mealType, originalRecipe));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("An error occurred while regenerating the recipe.");
        }

    }


    public String regenerateRecipe(IChatGPT gpt, DallE dalle, String[] ingredients, String mealType, String originalContext) throws IOException, InterruptedException, URISyntaxException {
        String newRecipe = gpt.regenerateRecipe(ingredients, mealType, originalContext);

        String imageUrl = dalle.generateImageURL(newRecipe);

        //System.out.println(newRecipe);

        return newRecipe + "|||||" + imageUrl;
    }

}

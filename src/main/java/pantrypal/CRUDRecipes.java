package pantrypal;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class CRUDRecipes {

    /**
     * @return a list of RecipeData objects from the RecipeData class 
     */
    public static ArrayList<RecipeData> readRecipes() {
        // load json data from recipes.json 
        ArrayList<RecipeData> recipes = new ArrayList<RecipeData>();
        try {
            // Read the entire file content into a string
            String content = new String(Files.readAllBytes(Paths.get("recipes.json")));

            // Parse the JSON content
            JSONArray jsonArray = new JSONArray(content);

            for (int i = 0; i < jsonArray.length();i++) {
                // Access the data from the JSON object
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                // parse ingredients as string array
                JSONArray jsonIngredients = jsonObject.getJSONArray("ingredients");
                String[] ingredients = new String[jsonIngredients.length()];
                for (int j = 0; j < jsonIngredients.length(); j++) {
                    ingredients[j] = jsonIngredients.getString(j);
                }

                String name = jsonObject.getString("name");
                String instructions = jsonObject.getString("instructions");

                // Use the extracted data as needed
                System.out.println("Recipe Name: " + name);
                System.out.println("Recipe Details: " + instructions);

                RecipeData recipe = new RecipeData(name,ingredients,instructions);
                recipes.add(recipe);
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
            
        return recipes;
    }
}
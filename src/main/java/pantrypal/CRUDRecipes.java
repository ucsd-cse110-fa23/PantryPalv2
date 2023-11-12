package pantrypal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

            for (int i = 0; i < jsonArray.length(); i++) {
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

                RecipeData recipe = new RecipeData(name, ingredients, instructions);
                recipes.add(recipe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return recipes;
    }

    public static boolean recipeExists(String key) {
        ArrayList<RecipeData> recipes = readRecipes();
        for (RecipeData recipe : recipes) {
            if (recipe.title.equals(key)) {
                return true;
            }
        }
        return false;
    }

    public static void updateRecipeContents(String key, String[] ingredients, String instructions) {
        ArrayList<RecipeData> recipes = readRecipes();
        for (RecipeData recipe : recipes) {
            if (recipe.title.equals(key)) {
                recipe.ingredients = ingredients;
                recipe.instructions = instructions;
            }
        }
        overwriteRecipes(recipes);
    }

    public static void overwriteRecipes(ArrayList<RecipeData> recipes) {
        // write list to json file
        try {
            FileWriter writer = new FileWriter("recipes.json", false);

            String str = "[";
            for (int i = 0; i < recipes.size(); i++) {
                RecipeData recipeData = recipes.get(i);
                str += "{\n";
                str += "    \"name\": \"" + recipeData.title + "\",\n";
                str += "    \"ingredients\": [\n";
                for (int j = 0; j < recipeData.ingredients.length; j++) {
                    str += "        \"" + recipeData.ingredients[j] + "\"";
                    if (j != recipeData.ingredients.length - 1) {
                        str += ",";
                    }
                    str += "\n";
                }
                str += "    ],\n";
                str += "    \"instructions\": \"" + recipeData.instructions + "\"\n";
                str += "}";
                if (i != recipes.size() - 1) {
                    str += ",";
                }
                str += "\n";
            }
            str += "]";

            writer.write(str);
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeRecipe(RecipeData recipe) throws IOException {
        // Step 1: Read existing JSON file
        BufferedReader reader = new BufferedReader(new FileReader("recipes.json"));
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }
        reader.close();

        // Step 2: Convert RecipeData to JSON String
        StringBuilder recipeJsonBuilder = new StringBuilder();
        recipeJsonBuilder.append("{");
        recipeJsonBuilder.append("\"name\": \"").append(recipe.title).append("\",");
        recipeJsonBuilder.append("\"ingredients\": [");
        for (int i = 0; i < recipe.ingredients.length; i++) {
            recipeJsonBuilder.append("\"").append(recipe.ingredients[i]).append("\"");
            if (i < recipe.ingredients.length - 1) {
                recipeJsonBuilder.append(",");
            }
        }
        recipeJsonBuilder.append("],");
        recipeJsonBuilder.append("\"instructions\": \"").append(recipe.instructions).append("\"");
        recipeJsonBuilder.append("}");

        // Step 3: Append the new JSON object to the existing JSON array
        String jsonArray = jsonBuilder.toString();
        String updatedJsonArray = jsonArray.substring(0, jsonArray.length() - 1) + "," + recipeJsonBuilder.toString()
                + "]";

        // Step 4: Write updated JSON back to the file
        BufferedWriter writer = new BufferedWriter(new FileWriter("recipes.json"));
        writer.write(updatedJsonArray);
        writer.close();
    }

}
package pantrypal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.lang.reflect.Type;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CRUDRecipes {

    private static final String FILE_PATH = "recipes.json";
    private static final Gson gson = new Gson();

    // Reads the existing recipes from the JSON file
    public static ArrayList<RecipeData> readRecipes() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        Type listType = new TypeToken<ArrayList<RecipeData>>() {
        }.getType();
        ArrayList<RecipeData> recipes = gson.fromJson(reader, listType);
        reader.close();

        return recipes != null ? recipes : new ArrayList<>();
    }

    // Writes the updated recipes to the JSON file
    private static void writeRecipes(List<RecipeData> recipes) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
        gson.toJson(recipes, writer);
        writer.close();
    }

    // Adds a new recipe
    public static void createRecipe(RecipeData newRecipe) throws IOException {
        List<RecipeData> recipes = readRecipes();
        recipes.add(newRecipe);
        writeRecipes(recipes);
    }

    public static boolean recipeExists(String title) throws IOException {
        List<RecipeData> recipes = readRecipes();
        return recipes.stream()
                .anyMatch(recipe -> recipe.title.equals(title));
    }

    // Retrieves a specific recipe based on its title
    public static RecipeData getRecipe(String title) throws IOException {
        List<RecipeData> recipes = readRecipes();
        return recipes.stream()
                .filter(recipe -> recipe.title.equals(title))
                .findFirst()
                .orElse(null);
    }

    // Updates an existing recipe
    public static void updateRecipe(RecipeData updatedRecipe) throws IOException {
        List<RecipeData> recipes = readRecipes();
        for (int i = 0; i < recipes.size(); i++) {
            if (recipes.get(i).title.equals(updatedRecipe.title)) {
                recipes.set(i, updatedRecipe);
                break;
            }
        }
        writeRecipes(recipes);
    }

    // Deletes a recipe
    public static void deleteRecipe(String title) throws IOException {
        List<RecipeData> recipes = readRecipes();
        recipes.removeIf(recipe -> recipe.title.equals(title));
        writeRecipes(recipes);
    }

}
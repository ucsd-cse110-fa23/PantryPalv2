package pantrypal;

import java.io.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.lang.reflect.Array;
import java.lang.reflect.Type;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.scanner.Scanner;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

@Service
public class RecipeService {

    public String FILE_PATH = "src/test/recipes.json"; // change this in the test file
    private final Gson gson = new Gson();

    private String username = "Ben";
    boolean testing = false;

    public RecipeService() {
    }

    public RecipeService(String username) {
        this.username = username;
    }

    public RecipeService(String username, boolean testing) {
        this.username = username;
        this.testing = testing;
    }

    public void changeFilePath(String path) {
        FILE_PATH = path;
    }

    // Writes the updated recipes to the JSON file
    private void writeRecipes(List<RecipeData> recipes) throws IOException {
        if (testing) {
            writeRecipesOld(recipes);
            return;
        }
        String uri = MongoKey.getAPIKey();
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("Pantrypal");
            MongoCollection<Document> collection = database.getCollection(username);
            collection.drop();
            for (RecipeData recipe : recipes) {
                Document recipeDoc = new Document("_id", new ObjectId());
                recipeDoc.append("title", recipe.title)
                        .append("type", recipe.type)
                        .append("createdDate", "TBD")
                        .append("ingredients", Arrays.asList(recipe.ingredients))
                        .append("instructions", recipe.instructions)
                        .append("createdTime", recipe.createdTime)
                        .append("type", recipe.type);
                collection.insertOne(recipeDoc);
            }
        }
    }

    /**
     * Reads recipes from the MongoDB Server
     * 
     * @return the current list of recipes in the database
     */
    public ArrayList<RecipeData> readRecipes() throws IOException {
        if (testing) {
            return readRecipesOld();
        }
        String uri = MongoKey.getAPIKey();
        ArrayList<RecipeData> recipes = new ArrayList<>();
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("Pantrypal");
            MongoCollection<Document> collection = database.getCollection(username);
            // traverse the list of documents in the collection
            ArrayList<Document> recipeList = collection.find().into(new ArrayList<>()); // find all docs
            for (Document recipe : recipeList) {
                System.out.println(recipe.toJson());
                String title = recipe.getString("title");
                List<String> listIng = (List<String>) recipe.get("ingredients");
                String[] ingredients = new String[listIng.size()];
                ingredients = (listIng).toArray(ingredients);
                String instructions = recipe.getString("instructions");
                String type = recipe.getString("type");
                double createdTime = recipe.getDouble("createdTime");

                RecipeData data = new RecipeData(title, ingredients, instructions, type, createdTime);
                recipes.add(data);
            }

        }
        return recipes;
    }

    // ----------------------------------------------------------------------------------------------

    // Adds a new recipe
    public void createRecipe(RecipeData newRecipe) throws IOException {
        List<RecipeData> recipes = readRecipes();
        recipes.add(newRecipe);
        writeRecipes(recipes);
    }

    public boolean recipeExists(String title) throws IOException {
        List<RecipeData> recipes = readRecipes();
        return recipes.stream()
                .anyMatch(recipe -> recipe.title.equals(title));
    }

    // Writes the updated recipes to the JSON file
    private void writeRecipesOld(List<RecipeData> recipes) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
        gson.toJson(recipes, writer);
        writer.close();
    }

    // Reads the existing recipes from the JSON file
    public ArrayList<RecipeData> readRecipesOld() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<RecipeData>();
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        Type listType = new TypeToken<ArrayList<RecipeData>>() {
        }.getType();
        ArrayList<RecipeData> recipes = gson.fromJson(reader, listType);
        reader.close();

        return recipes != null ? recipes : new ArrayList<>();
    }

    // Retrieves a specific recipe based on its title
    public RecipeData getRecipeByTitle(String title) throws IOException {
        List<RecipeData> recipes = readRecipes();
        return recipes.stream()
                .filter(recipe -> recipe.title.equals(title))
                .findFirst()
                .orElse(null);
    }

    // Updates an existing recipe
    public void updateRecipe(RecipeData updatedRecipe) throws IOException {
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
    public void deleteRecipe(String title) throws IOException {
        List<RecipeData> recipes = readRecipes();
        recipes.removeIf(recipe -> recipe.title.equals(title));
        writeRecipes(recipes);
    }

    // Delete the file that contains all recipes locally
    public void deleteRecipesFile() {
        File fileToDelete = new File(FILE_PATH);

        // Check if the file exists before attempting to delete
        if (fileToDelete.exists()) {
            if (fileToDelete.delete()) {
                System.out.println("File deleted successfully.");
            } else {
                System.err.println("Failed to delete the file.");
            }
        } else {
            System.err.println("File not found.");
        }
    }

    /** main method for debugging */
    public static void main(String[] args) {
        RecipeService r = new RecipeService();
        String[] i = { "cream", "sugar", "starch", "vanilla" };
        RecipeData recipe = new RecipeData("Pudding", i,
                "cook all ingredients over a low flame after combining for 10-15 minutes.");
        try {
            r.createRecipe(recipe);
            ArrayList<RecipeData> recipes = r.readRecipes();
            r.updateRecipe(new RecipeData("Pudding", i,
                    "cook all ingredients over a low flame after combining for 10-15 minutes. Then, let cool."));
            ArrayList<RecipeData> recipes2 = r.readRecipes();
            r.deleteRecipe("Pudding");
            ArrayList<RecipeData> recipes3 = r.readRecipes();

            System.out.println(recipes);
            System.out.println(recipes2);
            System.out.println(recipes3);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
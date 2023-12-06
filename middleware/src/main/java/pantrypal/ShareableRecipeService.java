package pantrypal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ShareableRecipeService {

    public static void writeRecipe(RecipeData recipe) throws IOException {
        String uri = MongoKey.getAPIKey();
        System.out.println("INSIDE writerecipe: " + uri);

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("Shared");
            MongoCollection<Document> collection = database.getCollection("Recipes");

            Document recipeDoc = new Document("_id", new ObjectId());
            recipeDoc.append("title", recipe.title)
                    .append("ingredients", Arrays.asList(recipe.ingredients))
                    .append("instructions", recipe.instructions)
                    .append("createdTime", recipe.createdTime)
                    .append("type", recipe.type)
                    .append("image",recipe.imageUrl);
            collection.insertOne(recipeDoc);
        }
    }

    /**
     * Reads recipes from the MongoDB Server
     * 
     * @return the recipe in the database
     */
    public static RecipeData readRecipe(String input) throws IOException {
        String uri = MongoKey.getAPIKey();
        
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("Shared");
            MongoCollection<Document> collection = database.getCollection("Recipes");
            // traverse the list of documents in the collection
            ArrayList<Document> recipeList = collection.find().into(new ArrayList<>()); // find all docs
            for (Document recipe : recipeList) {
                String title = recipe.getString("title");

                if(title.equals(input)) {
                    List<String> listIng = (List<String>) recipe.get("ingredients");
                    String[] ingredients = new String[listIng.size()];
                    ingredients = (listIng).toArray(ingredients);
                    String instructions = recipe.getString("instructions");
                    String type = recipe.getString("type");
                    double createdTime = recipe.getDouble("createdTime");
                    String imageUrl = recipe.getString("image");
                    RecipeData data = new RecipeData(title, ingredients, instructions, type, createdTime);
                    data.imageUrl = imageUrl;
                    return data;
                }
            }

        }
        return null;
    }

}

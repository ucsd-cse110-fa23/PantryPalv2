package pantrypal;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class ChatGPT {
    private ILanguageModel model;

    // Main used for testing
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        String mockTranscription = "I have rice, pasta, tomato sauce, and water.";

        // ChatGPT gpt = new ChatGPT();

        // System.out.println(gpt.generateRecipe(mockTranscription));
    }

    public ChatGPT(ILanguageModel model) {
        this.model = model;
    }

    public String[] generateRecipe(String transcription, String mealType) {
        String[] ingredients = extractIngredients(transcription);
        String recipe = createRecipe(ingredients, mealType);

        String[] toRet = new String[ingredients.length + 1];
        toRet[0] = recipe;
        for (int i = 0; i < ingredients.length; i++) {
            toRet[i + 1] = ingredients[i];
        }
        return toRet;
    }

    public String generateMealType(String transcription) {
        String createMealTypePrompt = "Given the following transcription:\n" +
                transcription + "\n" +
                "Find the meal type of the above transcription. The meal type should be one of the following:\n" +
                "Breakfast, Lunch, Dinner, Snack, Dessert, or Drink.\n" +
                "If there is no valid meal type, return None\n" +
                "The meal type is:";

        return model.callModel(createMealTypePrompt);
    }

    public String createRecipe(String[] ingredients, String mealType) {
        if (ingredients == null)
            return null;

        String ingredList = String.join(", ", ingredients);

        String createRecipePrompt = "Given the following ingredients:\n" +
                ingredList + "\n" +
                "Genereate me a" + mealType + "recipe that can be made using the above ingredients. The create\n" +
                "an in depth step by step guide detailing how to make the recipe. Each step \n" +
                "should be easy to follow because it has so much detial.\n" +
                "Display it in the following format:\n" +
                "\n" +
                "Recipe: \n" +
                "Step 1: \n" +
                "Step 2: \n" +
                "\n" +
                "Fill the following\n" +
                "Recipe:";

        String generatedText = model.callModel(createRecipePrompt);

        return generatedText;
    }

    public String[] extractIngredients(String transcription) { // throws IOException, InterruptedException,
                                                               // URISyntaxException
        String extractIngredientsPrompt = String.format(
                "Transcript: %s\n" +
                        transcription + "\n" +
                        "Given the above transcription create a list of ingredients in a JSON format, do it in the following formation without deviation.\n"
                        +
                        "\n" +
                        "{\n" +
                        "    \"ingredients\": [\n" +
                        "        \"ingredient 1\",\n" +
                        "        \"ingredient 2\",\n" +
                        "        \"ingredient 3\"\n" +
                        "    ]\n" +
                        "}\n" +
                        "\n" +
                        "The JSON response:",
                transcription);

        String generatedText = model.callModel(extractIngredientsPrompt);

        System.out.println(generatedText);

        return parseModelResponse(generatedText);
    }

    public String[] parseModelResponse(String generatedText) {
        // Process response as list of ingredients
        JSONObject obj = new JSONObject(generatedText);
        JSONArray ingredients = obj.getJSONArray("ingredients");
        int ingredLength = ingredients.length();

        String[] ingredientsString = new String[ingredLength];
        for (int i = 0; i < ingredLength; i++) {
            ingredientsString[i] = (String) ingredients.get(i);
            System.out.println(ingredientsString[i]);
        }

        return ingredientsString;
    }

}
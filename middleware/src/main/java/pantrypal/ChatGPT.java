package pantrypal;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class ChatGPT implements IChatGPT {
    private static final String API_ENDPOINT = "https://api.openai.com/v1/completions";
    private static final String API_KEY = APIKey.getAPIKey();
    private static final String MODEL = "text-davinci-003";
    private static final int maxTokens = 1000;

    public ChatGPT() { }

    public String generateRecipe(String transcription, String mealType) {
        String[] ingredients = extractIngredients(transcription);
        String recipe = createRecipe(ingredients, mealType);

        String toRet = recipe + "\n";
        for (int i = 0; i < ingredients.length; i++) {
            toRet += ingredients[i];
            if(i-1 < ingredients.length) {
                toRet += ";";
            }
        }
        return toRet;
    }

    public String generateMealType(String transcription) {
        transcription = transcription.toLowerCase();
        String createMealTypePrompt = "Given the following transcription:\n" +
                transcription + "\n" +
                "Find the meal type of the above transcription. The meal type should be one of the following:\n" +
                "Breakfast, Lunch, Dinner, Snack, Dessert, or Drink.\n" +
                "If there is no valid meal type, return None\n" +
                "The meal type is:";
        if (!transcription.contains(" breakfast") && !transcription.contains(" lunch") && !transcription.contains(" dinner")
            && !transcription.contains("breakfast") && !transcription.contains("lunch") && !transcription.contains("dinner")){
            return "Wrong meal type!";
        }
        return callModel(createMealTypePrompt);
    }

    public String createRecipe(String[] ingredients, String mealType) {
        if (ingredients == null)
            return null;

        String ingredList = String.join(", ", ingredients);

        String createRecipePrompt = "Given the following ingredients:\n" +
                ingredList + "\n" +
                "Genereate me a" + mealType + "recipe that can be made using the above ingredients." +
                "It is utterly crucial that the recipe is a" + mealType + "recipe!\n" +
                "The create\n" +
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

        String generatedText = callModel(createRecipePrompt);

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

        String generatedText = callModel(extractIngredientsPrompt);

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

    public String callModel(String prompt) {
        // Create a request body which you will pass into request object
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", MODEL);
        requestBody.put("prompt", prompt);
        requestBody.put("max_tokens", maxTokens);
        requestBody.put("temperature", 1.0);

        // Create the HTTP Client
        HttpClient client = HttpClient.newHttpClient();

        // Create the request object
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(API_ENDPOINT))
                .header("Content-Type", "application/json")
                .header("Authorization", String.format("Bearer %s", API_KEY))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                .build();

        // Send the request and receive the response
        HttpResponse<String> response = null;
        try {
            response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (response == null) {
            return null;
        }

        // Process the response
        String responseBody = response.body();
        JSONObject responseJson = new JSONObject(responseBody);
        JSONArray choices = responseJson.getJSONArray("choices");
        String generatedText = choices.getJSONObject(0).getString("text");

        return generatedText;
    }

}
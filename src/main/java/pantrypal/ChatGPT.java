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

    private static final String API_ENDPOINT = "https://api.openai.com/v1/completions";
    private static final String API_KEY = APIKey.getAPIKey();
    private static final String MODEL = "text-davinci-003";
    private int maxTokens;

    // Main used for testing
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        String mockTranscription = "I have rice, pasta, tomato sauce, and water.";

        ChatGPT gpt = new ChatGPT();

        System.out.println(gpt.generateRecipe(mockTranscription));
    }

    public ChatGPT() {
        maxTokens = 1500;
    }

    public String[] generateRecipe(String transcription) {
        String[] ingredients = extractIngredients(transcription);
        String recipe = createRecipe(ingredients);

        String[] toRet = new String[ingredients.length + 1];
        toRet[0] = recipe;
        for (int i = 0; i < ingredients.length; i++) {
            toRet[i + 1] = ingredients[i];
        }
        return toRet;
    }

    private String createRecipe(String[] ingredients) {
        if (ingredients == null)
            return null;

        String ingredList = String.join(", ", ingredients);

        String createRecipePrompt = "Given the following ingredients:\n" +
                ingredList + "\n" +
                "Find a recipe that can be made using the above ingredients. The create\n" +
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

        String generatedText = chatGPTCall(createRecipePrompt);

        return generatedText;
    }

    private String[] extractIngredients(String transcription) { // throws IOException, InterruptedException,
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

        String generatedText = chatGPTCall(extractIngredientsPrompt);

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

    private String chatGPTCall(String prompt) {
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
package pantrypal;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;


public class DallE {

    // TODO: Set the URL of the API Endpoint
    private static final String API_ENDPOINT = "https://api.openai.com/v1/images/generations" ;
    private static final String API_KEY = APIKey.getAPIKey();
    private static final String MODEL = "dall-e-2";
    
    public DallE() { }


    public String generateImageURL(String prompt) throws IOException, InterruptedException, URISyntaxException{
        prompt = parsePrompt(prompt);
        
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", MODEL);
        requestBody.put("prompt", prompt); // Dalle prompt has to be under 1000 characters
        requestBody.put("n", 1);
        requestBody.put("size", "256x256");

        // Create the HTTP client
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
        HttpResponse<String> response = client.send(
            request,
            HttpResponse.BodyHandlers.ofString()
        );


        // Process the response
        String responseBody = response.body();
        JSONObject responseJson = new JSONObject(responseBody);
        System.out.println(requestBody);
        System.out.println(responseJson);
        String generatedImageURL = responseJson.getJSONArray("data").getJSONObject(0).getString("url");

        return generatedImageURL;    
    }

    public String parsePrompt(String recipe) {
        int maxLength = 1000; // This is the most characters Dalle accepts in prompt

        String[] recipeDetails = recipe.split("\n");
        String title = recipeDetails[0].trim();

        String prompt = "Create a very realistic photograph of " + title + " being served";

        return ensureMaxLength(prompt, maxLength);
    }

    public String ensureMaxLength(String input, int maxLength) {
        if (input != null && input.length() > maxLength) {
            return input.substring(0, maxLength);
        }
        return input;
    }
}


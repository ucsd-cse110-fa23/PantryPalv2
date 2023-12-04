package pantrypal;

// import org.apache.tomcat.jni.File;
// import org.springframework.http.HttpEntity;
// import org.springframework.http.HttpStatus;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
// import org.apache.http.client.methods.CloseableHttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import java.io.File;
import java.io.IOException;

public class MiddlewareModel implements IMiddlewareModel {
    private static final String API_ENDPOINT = "http://localhost:8080/api"; // TODO: Create a config file with this
                                                                            // instead

    // Gets list of recipes from middleware server
    public List<RecipeData> getRecipes() {
        List<RecipeData> recipes = null;
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(API_ENDPOINT + "/recipes");

            // Execute the HttpGet request and get the response
            HttpResponse response = httpClient.execute(httpGet);

            // Check if the response status code indicates success (e.g., 200 OK)
            if (response.getStatusLine().getStatusCode() == 200) {
                String jsonResponse = EntityUtils.toString(response.getEntity());

                // Use Gson to deserialize the JSON response into a list of RecipeData objects
                Gson gson = new Gson();
                recipes = gson.fromJson(jsonResponse, new TypeToken<List<RecipeData>>() {
                }.getType());
                System.out.println("Server response: " + recipes);
            } else {
                System.err.println(
                        "HTTP GET request failed with status code: " + response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return recipes;
    }

    // Posts all recipes to server
    public void postRecipes(List<RecipeData> recipes) {
        try {
            HttpClient httpClient = HttpClients.createDefault();
            Gson gson = new GsonBuilder().create();

            // Serialize the list of RecipeData objects to JSON
            String jsonRequest = gson.toJson(recipes);

            // Create an HttpPost request
            HttpPost httpPost = new HttpPost(API_ENDPOINT + "/recipes");

            // Set the JSON request body
            StringEntity entity = new StringEntity(jsonRequest);
            entity.setContentType("application/json");
            httpPost.setEntity(entity);

            // Execute the POST request and get the response
            HttpResponse response = httpClient.execute(httpPost);

            // Check if the response status code indicates success (e.g., 200 OK)
            if (response.getStatusLine().getStatusCode() == 200) {
                // Parse the response content
                String jsonResponse = EntityUtils.toString(response.getEntity());

                System.out.println("Server response: " + jsonResponse);
            } else {
                System.err.println(
                        "HTTP POST request failed with status code: " + response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String mealTypeExtraction(String filePath) {
        String path = "/mealtype";
        File file = new File(filePath);

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(API_ENDPOINT + path);

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody("file", file, ContentType.MULTIPART_FORM_DATA, file.getName());

            HttpEntity multipart = builder.build();
            httpPost.setEntity(multipart);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == HttpStatus.SC_OK) {
                    String responseBody = EntityUtils.toString(response.getEntity());
                    return responseBody;
                    // return "Upload successful. Server response: " + responseBody;
                } else {
                    return "Upload failed. Server returned status code: " + statusCode;
                }
            }
        } catch (IOException e) {
            return "Error occurred during upload: " + e.getMessage();
        }
    }

    public String generateRecipe(String filePath, String mealType) {
        String path = "/generate-recipe";
        File file = new File(filePath);

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(API_ENDPOINT + path);

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody("file", file, ContentType.MULTIPART_FORM_DATA, file.getName());
            builder.addTextBody("mealType", mealType, ContentType.TEXT_PLAIN);

            HttpEntity multipart = builder.build();
            httpPost.setEntity(multipart);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == HttpStatus.SC_OK) {
                    String responseBody = EntityUtils.toString(response.getEntity());
                    return responseBody;
                    // return "Upload successful. Server response: " + responseBody;
                } else {
                    return null;
                    // return {"Upload failed. Server returned status code: " + statusCode};
                }
            }
        } catch (IOException e) {
            return null;
            // return "Error occurred during upload: " + e.getMessage();
        }
    }

    // Sends POST request for account authentication
    public Boolean postAccountAuthentication(Account acc) {
        try {
            HttpClient httpClient = HttpClients.createDefault();
            Gson gson = new GsonBuilder().create();

            // Serialize the Account object to JSON
            String jsonRequest = gson.toJson(acc);

            // Create an HttpPost request
            HttpPost httpPost = new HttpPost(API_ENDPOINT + "/account/login");

            // Set the JSON request body
            StringEntity entity = new StringEntity(jsonRequest);
            entity.setContentType("application/json");
            httpPost.setEntity(entity);

            // Execute the POST request and get the response
            HttpResponse response = httpClient.execute(httpPost);

            // Check if the response status code indicates logging in (e.g., 200 OK)
            if (response.getStatusLine().getStatusCode() == 200) {
                // Parse the response content
                String responseBody = EntityUtils.toString(response.getEntity());
                return true;
            } else if (response.getStatusLine().getStatusCode() == 404) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Sends POST request for account creation
    public Boolean postAccountCreation(Account acc) {
        try {
            HttpClient httpClient = HttpClients.createDefault();
            Gson gson = new GsonBuilder().create();

            // Serialize the Account object to JSON
            String jsonRequest = gson.toJson(acc);

            // Create an HttpPost request
            HttpPost httpPost = new HttpPost(API_ENDPOINT + "/account/create");

            // Set the JSON request body
            StringEntity entity = new StringEntity(jsonRequest);
            entity.setContentType("application/json");
            httpPost.setEntity(entity);

            // Execute the POST request and get the response
            HttpResponse response = httpClient.execute(httpPost);

            // Check if the response status code indicates logging in (e.g., 200 OK)
            if (response.getStatusLine().getStatusCode() == 200) {
                // Parse the response content
                String responseBody = EntityUtils.toString(response.getEntity());
                return true; //returns username if logged in
            } else if (response.getStatusLine().getStatusCode() == 404) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

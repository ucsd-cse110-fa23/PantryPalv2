package pantrypal;

// import org.apache.tomcat.jni.File;
// import org.springframework.http.HttpEntity;
// import org.springframework.http.HttpStatus;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.CloseableHttpResponse;
// import org.apache.http.client.methods.CloseableHttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import java.io.File;
import java.io.IOException;

public class MiddlewareModel {
    private static final String API_ENDPOINT = "http://localhost:8080/api"; // TODO: Create a config file with this instead

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
                    //return {"Upload failed. Server returned status code: " + statusCode};
                }
            }
        } catch (IOException e) {
            return null;
            //return "Error occurred during upload: " + e.getMessage();
        }
    }

}

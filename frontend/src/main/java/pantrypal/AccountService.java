package pantrypal;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AccountService {

    private static Account acc;
    private static final String API_ENDPOINT = "http://localhost:8080/api";

    public static void setAccount(Account a) {
        acc = a;
    }

    public static Account getAccount() {
        return acc;
    }

    // Send API request for account creation
    public static Boolean accountSignup(String enteredUsername, String enteredPassword, IMiddlewareModel middleware) {
        Account account = new Account(enteredUsername, enteredPassword);

        try {
            boolean created = middleware.postAccountCreation(account);
            if (created) {
                AccountService.setAccount(account);
                return true;
            }
            return false;
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return null;
    }

    // Send API request for account login
    public static Boolean accountLogin(String enteredUsername, String enteredPassword, IMiddlewareModel middleware) {
        Account account = new Account(enteredUsername, enteredPassword);

        try {
            boolean login = middleware.postAccountAuthentication(account);
            if (login) {
                AccountService.setAccount(account);
                return true;
            } 
            return false;
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return null;
    }
}
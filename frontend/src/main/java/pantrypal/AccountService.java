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

public class AccountService {

    public static String FILE_PATH = "accounts.json"; // change this in the test file
    private static final Gson gson = new Gson();

    public static void changeFilePath(String path) {
        FILE_PATH = path;
    }

    // Reads the existing accounts from the JSON file
    public static ArrayList<Account> readAccounts() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        Type listType = new TypeToken<ArrayList<Account>>() {
        }.getType();
        ArrayList<Account> accounts = gson.fromJson(reader, listType);
        reader.close();

        return accounts != null ? accounts : new ArrayList<>();
    }

    // Writes the updated accounts to the JSON file
    private static void writeAccounts(List<Account> accounts) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
        gson.toJson(accounts, writer);
        writer.close();
    }

    // Adds a new account
    public static void createAccount(Account newAccount) throws IOException {
        List<Account> accounts = readAccounts();
        accounts.add(newAccount);
        writeAccounts(accounts);
    }

    public static boolean accountExists(String username) throws IOException {
        List<Account> accounts = readAccounts();
        return accounts.stream()
                .anyMatch(account -> account.getUsername().equals(username));
    }

    public static boolean login(Account newAccount) throws IOException {
        return true;
    }

    // Retrieves a account based on its username
    public static Account getAccount(String username) throws IOException {
        List<Account> accounts = readAccounts();
        return accounts.stream()
                .filter(account -> account.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}
package pantrypal;

import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class AuthenticationService {
    private static final String MONGO_URI = MongoKey.getAPIKey();
    private static final String DATABASE_NAME = "Cluster0";
    private static final String COLLECTION_NAME = "accounts";

    public static boolean authenticate(String username, String password) {
        System.out.println(MONGO_URI);
        try {
            MongoClient mongoClient = MongoClients.create(MONGO_URI);
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Document user = collection.find(eq("username", username)).first();
            if (user == null) {
                return false;
            }

            String actualPassword = user.getString("password");
            return actualPassword.equals(password);
        } catch (Exception e) {
            System.out.println(username);
            System.out.println(e);
            return false;
        }
    }

    public static boolean createAccount(String username, String password) {
        try {
            MongoClient mongoClient = MongoClients.create(MONGO_URI);
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Document user = collection.find(eq("username", username)).first();
            if (user != null) {
                return false;
            }

            Document newUser = new Document("username", username).append("password", password);
            collection.insertOne(newUser);
            return true;
        } catch (Exception e) {
            System.out.println(username);
            System.out.println(e);
            return false;
        }
    }
}

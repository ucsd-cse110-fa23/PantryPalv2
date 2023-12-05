package pantrypal;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import java.io.*;
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

import javafx.scene.control.CheckBox;

public class AccountScreen extends BorderPane {

    private AccountScreenHeader header;
    private AccountScreenFooter footer;
    private AccountInfo accountInfo;

    private Button signupButton;
    private Button loginButton;

    private CheckBox rememberMe;

    private Stage primaryStage;

    private String rememberUser;
    private String rememberPassword;

    public AccountScreen(Stage primaryStage) throws IOException {

        if (RememberAccount.getLastAccount() != null) {
            rememberUser = RememberAccount.getLastAccount().getUsername();
            rememberPassword = RememberAccount.getLastAccount().getPassword();
        } else {
            rememberUser = "";
            rememberPassword = "";
        }

        // Initialise the header Object
        header = new AccountScreenHeader("Welcome to Pantry Pal!");

        // Initialise the body Object
        accountInfo = new AccountInfo(rememberUser, rememberPassword);

        // Initialise the Footer Object
        footer = new AccountScreenFooter();

        this.setTop(header);
        this.setCenter(accountInfo);
        this.setBottom(footer);

        // Initialise Button Variables through the getters in Footer
        signupButton = footer.getsignupButton();
        loginButton = footer.getloginButton();
        rememberMe = footer.getRememberMe();

        this.primaryStage = primaryStage;

        addListeners();
    }

    public void addListeners() {
        signupButton.setOnAction(e2 -> {
            // Get account from text fields
            String enteredUsername = accountInfo.getUsernameField();
            String enteredPassword = accountInfo.getPasswordField();

            Boolean signup = AccountService.accountSignup(enteredUsername, enteredPassword, new MiddlewareModel());
            if (signup) {
                try {
                    if (rememberMe.isSelected()) {
                        System.out.println("Remember Me!");
                        RememberAccount.createAccount(new Account(enteredUsername, enteredPassword));
                    }
                    primaryStage.setScene(new Scene(new AppFrame(primaryStage)));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else if (signup == false) {
                System.out.println("Account with that username already exists. Choose a different username");
            } else { // if signup == null
                System.out.println("Error with the server");
            }
        });

        loginButton.setOnAction(e2 -> {
            // Get account from text fields
            String enteredUsername = accountInfo.getUsernameField();
            String enteredPassword = accountInfo.getPasswordField();

            Boolean login = AccountService.accountLogin(enteredUsername, enteredPassword, new MiddlewareModel());

            if (login) {
                try {
                    if (rememberMe.isSelected()) {
                        System.out.println("Remember Me!");
                        RememberAccount.createAccount(new Account(enteredUsername, enteredPassword));
                    }
                    primaryStage.setScene(new Scene(new AppFrame(primaryStage)));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else if (login == false) {
                System.out.println("Unsuccessful login attempt");
            } else { // if signup == null
                System.out.println("Error with the server");
            }
        });
    }

}

class AccountScreenHeader extends HBox {

    AccountScreenHeader(String title) {

        this.setPrefSize(500, 60);
        this.setStyle("-fx-background-color: #d5f2ec;");

        Text titleText = new Text(title);
        titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");

        VBox titleVBox = new VBox(titleText);
        titleVBox.setAlignment(Pos.CENTER);

        this.getChildren().addAll(titleVBox);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(50);
    }
}

class AccountScreenFooter extends HBox {
    private Button signupButton;
    private Button loginButton;
    private CheckBox rememberMe;

    AccountScreenFooter() {
        this.setStyle("-fx-background-color: #d5f2ec;");
        this.setPrefSize(500, 60);
        this.setSpacing(15);

        String defaultButtonStyle = "-fx-font-style: italic; -fx-background-color: #FFFFFF;  -fx-font-weight: bold; -fx-font: 11 arial;";
        signupButton = new Button("Sign Up"); // text displayed on clear recipes button
        signupButton.setStyle(defaultButtonStyle);

        loginButton = new Button("Log In"); // text displayed on clear recipes button
        loginButton.setStyle(defaultButtonStyle);

        rememberMe = new CheckBox("Remember Me?");

        this.getChildren().addAll(signupButton, loginButton, rememberMe); // adding buttons to footer
        this.setAlignment(Pos.CENTER); // aligning the buttons to center

    }

    public Button getsignupButton() {
        return signupButton;
    }

    public Button getloginButton() {
        return loginButton;
    }

    public CheckBox getRememberMe() {
        return rememberMe;
    }

}
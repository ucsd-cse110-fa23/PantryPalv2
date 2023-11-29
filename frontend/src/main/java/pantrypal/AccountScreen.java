package pantrypal;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.geometry.Insets;
import javafx.scene.text.*;
import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class AccountScreen extends BorderPane {

    private AccountScreenHeader header;
    private AccountScreenFooter footer;
    private AccountInfo accountInfo;

    private Button signupButton;
    private Button loginButton;

    private Stage primaryStage;

    public AccountScreen(Stage primaryStage) throws IOException {
        // Initialise the header Object
        header = new AccountScreenHeader("Welcome to Pantry Pal!");

        // Initialise the body Object
        // body = new AccountScreenBody();
        accountInfo = new AccountInfo();

        // Initialise the Footer Object
        footer = new AccountScreenFooter();

        this.setTop(header);
        this.setCenter(accountInfo);
        this.setBottom(footer);

        // Initialise Button Variables through the getters in Footer
        signupButton = footer.getsignupButton();
        loginButton = footer.getloginButton();

        this.primaryStage = primaryStage;

        addListeners();
    }

    public void addListeners() {
        signupButton.setOnAction(e2 -> {
            System.out.println("username: " + accountInfo.getUsernameField());
            System.out.println("password: " + accountInfo.getPasswordField());

            String enteredUsername = accountInfo.getUsernameField();
            String enteredPassword = accountInfo.getPasswordField();
            
            // create account document in database
            // display error message if username already used(?)

            // create a new account object
            // Account account = new Account(accountInfo.getUsernameField(), accountInfo.getPasswordField());

            // try {
            //     System.out.println(AccountService.accountExists(account.getUsername()));
            // } catch (IOException e1) {
            //     e1.printStackTrace();
            // }
            try {
                if (AccountService.accountExists(enteredUsername)) {
                    System.out.println("Account with that username already exists. Choose a different username");
                } else {
                    try {
                        Account account = new Account(enteredUsername, enteredPassword);
                        AccountService.createAccount(account);
                        System.out.println("User signed up successfully!");
                        try {
                            primaryStage.setScene(new Scene(new AppFrame(primaryStage)));

                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                }
            catch (IOException e1) {
                e1.printStackTrace();
            }

            // redirect to next screen once authenticated
            // temp transition:
            // try {
            //     primaryStage.setScene(new Scene(new AppFrame(primaryStage)));

            // } catch (IOException e1) {
            //     e1.printStackTrace();
            // }
        });

        loginButton.setOnAction(e2 -> {
            System.out.println("username: " + accountInfo.getUsernameField());
            System.out.println("password: " + accountInfo.getPasswordField());
            
            String enteredUsername = accountInfo.getUsernameField();
            String enteredPassword = accountInfo.getPasswordField();
            try {
                if (AccountService.accountExists(enteredUsername)) {
                    if (AccountService.getAccount(enteredUsername).getPassword().equals(enteredPassword)) {
                        Account account = new Account(enteredUsername, enteredPassword);
                        AccountService.login(account);
                        System.out.println("user logged in successfully!");
                        try {
                            primaryStage.setScene(new Scene(new AppFrame(primaryStage)));

                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        System.out.println("wrong password entered");
                    }
                } else {
                    System.out.println("an account with this username does not exist yet");
                }
                }
            catch (IOException e1) {
                e1.printStackTrace();
            }

            // check if matching document exists in database
            // display error message if not

            // redirect to next screen once authenticated
            // temp transition:
            // try {
            //     primaryStage.setScene(new Scene(new AppFrame(primaryStage)));

            // } catch (IOException e1) {
            //     e1.printStackTrace();
            // }
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

// class AccountScreenBody extends HBox {

// private TextField username;
// private TextField password;

// private VBox fields;

// AccountScreenBody() {
// this.setPrefSize(500, 120); // Size of the header
// this.setStyle("-fx-background-color: #d5f2ec;");

// fields = new VBox();
// fields.setSpacing(5);
// fields.setPrefSize(200, 60);

// username = new TextField();
// username.setPrefSize(150, 20);
// username.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");
// username.setPadding(new Insets(5, 0, 5, 0));
// this.username.setPromptText("Username");
// fields.getChildren().add(username);

// password = new TextField();
// password.setPrefSize(150, 20);
// password.setStyle("-fx-background-color: #DAE5EA; -fx-border-width:0;");
// password.setPadding(new Insets(5, 0, 5, 0));
// this.password.setPromptText("Password");
// fields.getChildren().add(password);

// this.getChildren().add(fields);

// this.setAlignment(Pos.CENTER); // Align the text to the Center
// }

// }

class AccountScreenFooter extends HBox {
    private Button signupButton;
    private Button loginButton;

    AccountScreenFooter() {
        this.setStyle("-fx-background-color: #d5f2ec;");
        this.setPrefSize(500, 60);
        this.setSpacing(15);

        String defaultButtonStyle = "-fx-font-style: italic; -fx-background-color: #FFFFFF;  -fx-font-weight: bold; -fx-font: 11 arial;";
        signupButton = new Button("Sign Up"); // text displayed on clear recipes button
        signupButton.setStyle(defaultButtonStyle);

        loginButton = new Button("Log In"); // text displayed on clear recipes button
        loginButton.setStyle(defaultButtonStyle);

        this.getChildren().addAll(signupButton, loginButton); // adding buttons to footer
        this.setAlignment(Pos.CENTER); // aligning the buttons to center

    }

    public Button getsignupButton() {
        return signupButton;
    }

    public Button getloginButton() {
        return loginButton;
    }

}
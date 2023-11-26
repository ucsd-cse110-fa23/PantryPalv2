package pantrypal;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.TextAlignment;
import javafx.geometry.Insets;
import javafx.scene.text.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

class AccountInfo extends HBox {

    private VBox fields;

    private TextField usernameField;
    private TextField passwordField;

    AccountInfo(String usernameString, String passwordString) {

        this.setPrefSize(500, 120);
        this.setStyle("-fx-background-color: #d5f2ec;");

        fields = new VBox();
        fields.setSpacing(5);
        fields.setPrefSize(200, 60);

        usernameField = new TextField();
        usernameField.setPrefSize(150, 20);
        usernameField.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");
        usernameField.setPadding(new Insets(5, 0, 5, 0));
        this.usernameField.setPromptText("Username");
        fields.getChildren().add(usernameField);

        passwordField = new TextField();
        passwordField.setPrefSize(150, 20);
        passwordField.setStyle("-fx-background-color: #DAE5EA; -fx-border-width:0;");
        passwordField.setPadding(new Insets(5, 0, 5, 0));
        this.passwordField.setPromptText("Password");
        fields.getChildren().add(passwordField);

        this.getChildren().add(fields);

        this.setAlignment(Pos.CENTER);
    }

    public String getUsernameField() {
        return this.usernameField.getText();
    }

    public String getPasswordField() {
        return this.passwordField.getText();
    }

    AccountInfo() {
        this("Username", "Password");
    }

}
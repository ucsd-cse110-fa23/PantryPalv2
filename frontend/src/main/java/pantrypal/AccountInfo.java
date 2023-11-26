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

    private TextField username;
    private TextField password;

    AccountInfo(String usernameString, String passwordString) {

        this.setPrefSize(500, 120);
        this.setStyle("-fx-background-color: #d5f2ec;");

        fields = new VBox();
        fields.setSpacing(5);
        fields.setPrefSize(200, 60);

        username = new TextField();
        username.setPrefSize(150, 20);
        username.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");
        username.setPadding(new Insets(5, 0, 5, 0));
        this.username.setPromptText("Username");
        fields.getChildren().add(username);

        password = new TextField();
        password.setPrefSize(150, 20);
        password.setStyle("-fx-background-color: #DAE5EA; -fx-border-width:0;");
        password.setPadding(new Insets(5, 0, 5, 0));
        this.password.setPromptText("Password");
        fields.getChildren().add(password);

        this.getChildren().add(fields);

        this.setAlignment(Pos.CENTER);
    }

    public TextField getUsername() {
        return this.username;
    }

    public TextField getPassword() {
        return this.password;
    }

    AccountInfo() {
        this("Username", "Password");
    }

}
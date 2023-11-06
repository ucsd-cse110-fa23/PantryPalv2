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

public class NewRecipeScreen extends BorderPane {

    private NewRecipeHeader header;
    private NewRecipeFooter newfooter;
    private NewRecipeBody body;

    private Button editButton;
    private Button saveButton;
    private Button cancelButton;
    private String response;
    private RecipeData recipe;
    private Stage primaryStage;

    public NewRecipeScreen(Stage primaryStage, RecipeData recipe) {

        this.response = recipe.title + "\n" + recipe.instructions;
        // Initialise the header Object
        header = new NewRecipeHeader(recipe.title);

        // Initialise the body Object
        body = new NewRecipeBody(response);

        // Initialise the Footer Object
        newfooter = new NewRecipeFooter();

        // Add header to the top of the BorderPane
        this.setTop(header);
        this.setCenter(body);
        ;
        // Add footer to the bottom of the BorderPane
        this.setBottom(newfooter);

        // Initialise Button Variables through the getters in Footer
        editButton = newfooter.getEditButton();
        saveButton = newfooter.getSaveButton();
        cancelButton = newfooter.getCancelButton();

        this.recipe = recipe;
        this.primaryStage = primaryStage;
        
        // Call Event Listeners for the Buttons
        addListeners();
    }
    
    public void addListeners() {
        cancelButton.setOnAction(e -> {
            primaryStage.setScene(new Scene(new AppFrame(primaryStage)));
        });
        saveButton.setOnAction(e -> {
            try {
            FileWriter writer = new FileWriter("recipes.txt", true);

            String str = recipe.title + "\n" + recipe.instructions;

            writer.write(str);
            writer.write("\n\n");
            writer.close();
            
        } catch (IOException e1) {
            System.out.println("An error occurred.");
            e1.printStackTrace();
        }
            
        });
    }
}

class NewRecipeHeader extends HBox {

    NewRecipeHeader(String title) {
        this.setPrefSize(500, 60); // Size of the header
        this.setStyle("-fx-background-color: #d5f2ec;");

        Text titleText = new Text(title); // Text of the Header
        titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");
        this.getChildren().add(titleText);
        this.setAlignment(Pos.CENTER); // Align the text to the Center
    }
}

class NewRecipeBody extends HBox {

    NewRecipeBody(String res) {
        this.setPrefSize(500, 120); // Size of the header
        this.setStyle("-fx-background-color: #d5f2ec;");

        TextArea details = new TextArea();
        details.setText(res);
        details.setWrapText(true);
        details.setEditable(false);

        this.getChildren().add(details);
        this.setAlignment(Pos.CENTER); // Align the text to the Center
    }
}

class NewRecipeFooter extends HBox {
    private Button editButton;
    private Button saveButton;
    private Button cancelButton;

    NewRecipeFooter() {
        this.setStyle("-fx-background-color: #d5f2ec;");
        this.setPrefSize(500, 60);
        this.setSpacing(15);

        String defaultButtonStyle = "-fx-font-style: italic; -fx-background-color: #FFFFFF;  -fx-font-weight: bold; -fx-font: 11 arial;";
        editButton = new Button("Edit Recipe"); // text displayed on clear recipes button
        editButton.setStyle(defaultButtonStyle);
        saveButton = new Button("Save Recipe"); // text displayed on clear recipes button
        saveButton.setStyle(defaultButtonStyle);
        cancelButton = new Button("Cancel"); // text displayed on clear recipes button
        cancelButton.setStyle(defaultButtonStyle);
        this.getChildren().addAll(editButton, saveButton, cancelButton); // adding buttons to footer
        this.setAlignment(Pos.CENTER); // aligning the buttons to center

    }

    public Button getEditButton() {
        return editButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }

}
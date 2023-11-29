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

    private boolean isEditing = false;

    public NewRecipeScreen(Stage primaryStage, RecipeData recipe) throws IOException {

        this.response = recipe.title + "\n";
        this.response += "Listed Ingredients:\n";
        for (int i = 0; i < recipe.ingredients.length; i++) {
            this.response += recipe.ingredients[i] + "\n";
        }
        this.response += recipe.instructions;
        // Initialise the header Object
        header = new NewRecipeHeader(recipe.title, recipe.imageUrl);

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

    public void addListeners() throws IOException {
        cancelButton.setOnAction(e -> {
            body.getDetails().setEditable(false);
            editButton.setText("Edit Recipe");

            try {
                primaryStage.setScene(new Scene(new AppFrame(primaryStage)));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        saveButton.setOnAction(e -> {
            try {
                System.out.println(CRUDRecipes.recipeExists(recipe.title));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                if (CRUDRecipes.recipeExists(recipe.title)) {

                    try {
                        recipe.instructions = body.getDetails().getText();
                        CRUDRecipes.updateRecipe(recipe);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    try {
                        CRUDRecipes.createRecipe(recipe);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        editButton.setOnAction(e -> {
            // switchEditable(isEditing);
            if (!isEditing) {
                body.getDetails().setEditable(true);
                editButton.setText("Done With Edit");
                isEditing = true;
            } else {
                body.getDetails().setEditable(false);
                editButton.setText("Edit Recipe");
                this.recipe.instructions = body.getDetails().getText();
                isEditing = false;
            }
        });
    }
}

class NewRecipeHeader extends HBox {
    private ImageView imageView;
    NewRecipeHeader(String title, String url) {
        
        
        this.setPrefSize(500, 200); // Size of the header
        this.setStyle("-fx-background-color: #d5f2ec;");

        // Create and set up the ImageView
        imageView = new ImageView(new Image(url, true));
        imageView.setFitHeight(120);
        imageView.setPreserveRatio(true);

        Text titleText = new Text(title); // Text of the Header
        titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");

        // Add ImageView and title Text to the HBox
        this.getChildren().addAll(imageView, titleText);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(15);
    }
}

class NewRecipeBody extends HBox {

    private TextArea details = new TextArea();

    NewRecipeBody(String res) {
        this.setPrefSize(500, 120); // Size of the header
        this.setStyle("-fx-background-color: #d5f2ec;");

        details.setText(res);
        details.setWrapText(true);
        details.setEditable(false);

        this.getChildren().add(details);
        this.setAlignment(Pos.CENTER); // Align the text to the Center
    }

    public TextArea getDetails() {
        return details;
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
        saveButton = new Button("Save to Database"); // text displayed on clear recipes button
        saveButton.setStyle(defaultButtonStyle);
        cancelButton = new Button("Back"); // text displayed on clear recipes button
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
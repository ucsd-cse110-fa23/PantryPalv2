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

public class ViewRecipe extends BorderPane {

    private ViewRecipeHeader header;
    private ViewRecipeFooter footer;
    private ViewRecipeBody body;

    private Button backButton;
    private Button editButton;
    private Button saveButton;
    private Button deleteButton;

    private String response;
    private RecipeData recipe;
    private Stage primaryStage;
    // private MainMenuController test;
    private boolean isEditing = false;

    public ViewRecipe(Stage primaryStage, RecipeData recipe) throws IOException {
        this.response = recipe.title + "\n" + recipe.instructions;
        // Initialise the header Object
        header = new ViewRecipeHeader(recipe.title);

        // Initialise the body Object
        body = new ViewRecipeBody(response);

        // Initialise the Footer Object
        footer = new ViewRecipeFooter();

        // Add header to the top of the BorderPane
        this.setTop(header);
        this.setCenter(body);
        // Add footer to the bottom of the BorderPane
        this.setBottom(footer);

        backButton = header.getBackButton();

        // Initialise Button Variables through the getters in Footer
        editButton = footer.getEditButton();
        saveButton = footer.getSaveButton();
        deleteButton = footer.getDeleteButton();

        this.recipe = recipe;
        this.primaryStage = primaryStage;

        // Call Event Listeners for the Buttons
        addListeners();
    }

    public void addListeners() {
        editButton.setOnAction(e -> {
            if (!isEditing) {
                body.getDetails().setEditable(true);
                editButton.setText("Done Editing");
                isEditing = true;
            } else {
                body.getDetails().setEditable(false);
                editButton.setText("Edit Recipe");
                isEditing = false;
            }

        });

        saveButton.setOnAction(e -> {
            try {
                System.out.println(CRUDRecipes.recipeExists(recipe.title));
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try {
                if (CRUDRecipes.recipeExists(recipe.title)) {

                    try {
                        CRUDRecipes.updateRecipe(recipe);
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                } else {
                    try {
                        CRUDRecipes.createRecipe(recipe);
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        deleteButton.setOnAction(e -> {
            // TODO::deletion
            try {
                primaryStage.setScene(
                        new Scene(new ConfirmDelete(primaryStage, CRUDRecipes.getRecipe(recipe.title)), 500,
                                600));
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        backButton.setOnAction(e -> {
            try {
                primaryStage.setScene(new Scene(new AppFrame(primaryStage)));
                
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            
        });

    }
}

class ViewRecipeHeader extends HBox {

    private Button backButton;

    ViewRecipeHeader(String title) {
        
        String defaultButtonStyle = "-fx-font-style: italic; -fx-background-color: #FFFFFF; -fx-font-weight: bold; -fx-font: 11 arial;";
        backButton = new Button("Back");
        backButton.setStyle(defaultButtonStyle);

        this.setPrefSize(500, 60); // Size of the header
        this.setStyle("-fx-background-color: #d5f2ec;");

        Text titleText = new Text(title);
        titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");

        // VBox for centering titleText
        VBox titleVBox = new VBox(titleText);
        titleVBox.setAlignment(Pos.CENTER);

        this.getChildren().addAll(titleVBox, backButton);
        HBox.setHgrow(backButton, Priority.ALWAYS);
        this.setAlignment(Pos.CENTER_RIGHT); // Align the whole content to the right
        this.setSpacing(50);
    }

    public Button getBackButton() {
        return backButton;
    }
}

class ViewRecipeBody extends HBox {
    TextArea details;

    ViewRecipeBody(String res) {
        this.setPrefSize(500, 120); // Size of the header
        this.setStyle("-fx-background-color: #d5f2ec;");

        details = new TextArea();
        details.setText(res);
        details.setWrapText(true);
        details.setEditable(false);

        this.getChildren().add(details);
        this.setAlignment(Pos.CENTER); // Align the text to the Center
    }

    public TextArea getDetails() {
        return this.details;
    }
}

class ViewRecipeFooter extends HBox {
    private Button editButton;
    private Button deleteButton;
    private Button saveButton;

    ViewRecipeFooter() {
        this.setStyle("-fx-background-color: #d5f2ec;");
        this.setPrefSize(500, 60);
        this.setSpacing(15);

        String defaultButtonStyle = "-fx-font-style: italic; -fx-background-color: #FFFFFF;  -fx-font-weight: bold; -fx-font: 11 arial;";
        editButton = new Button("Edit Recipe"); // text displayed on clear recipes button
        editButton.setStyle(defaultButtonStyle);
        saveButton = new Button("Save Recipe"); // text displayed on clear recipes
        saveButton.setStyle(defaultButtonStyle);
        deleteButton = new Button("Delete Recipe"); // text displayed on clear recipes button
        deleteButton.setStyle(defaultButtonStyle);
        
        this.getChildren().addAll(editButton,saveButton, deleteButton); // adding buttons to footer
        this.setAlignment(Pos.CENTER); // aligning the buttons to center

    }

    public Button getEditButton() {
        return editButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

}
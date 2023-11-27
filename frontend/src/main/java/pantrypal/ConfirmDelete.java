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

public class ConfirmDelete extends BorderPane {
    Button yesButton;
    Button noButton;
    Label confirmLabel;

    public ConfirmDelete(Stage primaryStage, RecipeData recipe) {
        // Create the label
        confirmLabel = new Label("Are you sure you want to delete this recipe?");

        // Create the yes button
        yesButton = new Button("Yes");
        yesButton.setOnAction(e -> {
            try {
                CRUDRecipes.deleteRecipe(recipe.title);
                primaryStage.setScene(new Scene(new AppFrame(primaryStage)));
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        // Create the no button
        noButton = new Button("No");
        noButton.setOnAction(e -> {
            try {
                primaryStage.setScene(new Scene(new AppFrame(primaryStage)));
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        // Create the HBox
        HBox hbox = new HBox();
        hbox.getChildren().addAll(yesButton, noButton);
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER);

        // Create the VBox
        VBox vbox = new VBox();
        vbox.getChildren().addAll(confirmLabel, hbox);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);

        // Add the VBox to the BorderPane
        this.setCenter(vbox);
    }
}

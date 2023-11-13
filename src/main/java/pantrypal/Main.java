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
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;
import javafx.geometry.Insets;
import javafx.scene.text.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.plaf.InsetsUIResource;

import java.io.IOException;
import java.net.URISyntaxException;

class Recipe extends HBox {

    public Label index;
    public TextField recipeName;
    public TextField recipeDetails;

    private Button viewButton;
    private Button deleteButton;

    private boolean markedDone;
    private Stage primaryStage;
    private RecipeData recipeData;

    Recipe(Stage primaryStage, RecipeData recipeData) {
        this.setPrefSize(500, 20); // sets size of recipe
        this.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0; -fx-font-weight: bold;"); // sets background
                                                                                                     // color of recipe
        markedDone = false;
        this.primaryStage = primaryStage;
        this.recipeData = recipeData;

        this.setSpacing(5); // sets spacing between recipes

        index = new Label();
        index.setText(""); // create index label
        index.setPrefSize(40, 20); // set size of Index label
        index.setTextAlignment(TextAlignment.CENTER); // Set alignment of index label
        index.setPadding(new Insets(10, 0, 10, 0)); // adds some padding to the recipe
        this.getChildren().add(index); // add index label to recipe

        recipeName = new TextField(); // create recipe name text field
        recipeName.setPrefSize(380, 20); // set size of text field
        recipeName.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // set background color of texfield
        index.setTextAlignment(TextAlignment.LEFT); // set alignment of text field
        recipeName.setPadding(new Insets(10, 0, 10, 0)); // adds some padding to the text field
        recipeName.setEditable(false);
        this.getChildren().add(recipeName); // add textlabel to recipe

        // recipeDetails = new TextField(); // create recipe name text field
        // recipeDetails.setPrefSize(380, 20); // set size of text field
        // recipeDetails.setStyle("-fx-background-color: #DAE5EA; -fx-border-width:
        // 0;"); // set background color of
        // // texfield
        // index.setTextAlignment(TextAlignment.LEFT); // set alignment of text field
        // recipeDetails.setPadding(new Insets(10, 0, 10, 0)); // adds some padding to
        // the text field
        // this.getChildren().add(recipeDetails); // add textlabel to recipe

        viewButton = new Button("View");
        viewButton.setPrefSize(100, 20);
        viewButton.setPrefHeight(Double.MAX_VALUE);
        viewButton.setStyle("-fx-background-color: #9ad99a; -fx-border-width: 0; -fx-border-radius: 1em;"); // sets
                                                                                                            // style of
                                                                                                            // button

        deleteButton = new Button("Delete"); // creates a button for marking the recipe as done
        deleteButton.setPrefSize(100, 20);
        deleteButton.setPrefHeight(Double.MAX_VALUE);
        deleteButton.setStyle("-fx-background-color: #e394b2; -fx-border-width: 0; -fx-border-radius: 1em;"); // sets
                                                                                                              // style
                                                                                                              // of
                                                                                                              // button
        deleteButton.setOnAction(e -> {
            try {
                primaryStage.setScene(
                        new Scene(new ConfirmDelete(primaryStage, CRUDRecipes.getRecipe(recipeName.getText())), 500,
                                600));
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        viewButton.setOnAction(e -> {
            try {
                Scene scene = new Scene(new ViewRecipe(primaryStage, recipeData), 500, 600);
                primaryStage.setScene(scene);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        this.getChildren().add(viewButton); // add button to recipe
        this.getChildren().add(deleteButton);
    }

    public void setRecipeIndex(int num) {
        this.index.setText(num + ""); // num to String
        // this.recipeName.setPromptText("Recipe " + num);
    }

    public void setRecipeName(String name) {
        this.recipeName.setText(name);
    }

    public void setRecipeDetails(String details) {
        this.recipeDetails.setText(details);
    }

    public TextField getRecipeName() {
        return this.recipeName;
    }

    public Button getDeleteButton() {
        return this.deleteButton;
    }

    public boolean isMarkedDone() {
        return this.markedDone;
    }

}

class RecipeList extends VBox {
    // PointC
    Stage primaryStage;

    RecipeList(Stage primaryStage) throws IOException {
        this.setSpacing(5); // sets spacing between recipes
        this.setPrefSize(500, 560);
        this.setStyle("-fx-background-color: #F0F8FF;");
        this.primaryStage = primaryStage;
        // get the current recipe data (from JSON file)
        ArrayList<RecipeData> recipes = CRUDRecipes.readRecipes();
        // add the recipes to the recipelist
        loadRecipes(recipes);
    }

    /*
     * Load recipes from a file called "recipes.json"
     * Add the recipes to the children of recipelist component
     */
    public void loadRecipes(ArrayList<RecipeData> recipes) {
        Collections.reverse(recipes);
        for (RecipeData recipeData : recipes) {
            // create Recipe object for each recipe data
            Recipe recipe = new Recipe(primaryStage, recipeData);
            recipe.setRecipeName(recipeData.title);
            this.getChildren().add(recipe);
        }
        Collections.reverse(recipes);
    }
}

/**
 * Class to prompt user for their audio recording ~ type of recipe
 * potentially change to StackPane to accomodate a different page for each part
 * of the creating recipe
 */
class CreateRecipe extends VBox {
    // Point A
    // private Button backButton;
    private Label mealTypeLabel;
    private Button addButton;
    private Button stopButton;
    private Label recordingLabel;

    private Stage primaryStage;

    private AudioRecorder ar;
    private String mealType;

    // Point B
    CreateRecipe(Stage primaryStage, String mealType) {
        this.setPrefSize(500, 560);
        this.setStyle("-fx-background-color: #a7d6cc;");
        this.setSpacing(15);

        // backButton = new Button("Back");
        String defaultButtonStyle = "-fx-font-style: italic; -fx-background-color: #FFFFFF;  -fx-font-weight: bold; -fx-font: 15 arial;";

        // backButton.setPadding(new Insets(0, 0, 0, 0));
        // backButton.setStyle(defaultButtonStyle); // styling the button
        // backButton.setPrefSize(90, 30);

        this.primaryStage = primaryStage;
        this.mealType = mealType;

        mealTypeLabel = new Label("Meal Type: " + mealType);

        addButton = new Button(); // text displayed on add button
        addButton.setStyle(defaultButtonStyle); // styling the buttonv
        addButton.setPrefSize(150, 30);
        addButton.setStyle(
                "-fx-background-radius: 5em; " +
                        "-fx-background-color: #71e395;" +
                        "-fx-min-width: 90px; " +
                        "-fx-min-height: 90px; " +
                        "-fx-max-width: 90px; " +
                        "-fx-max-height: 90px;");

        Image mic_img = new Image("file:src/resources/mic.png");
        ImageView mic_img_view = new ImageView(mic_img);
        mic_img_view.setFitHeight(45);
        mic_img_view.setFitWidth(45);

        addButton.setGraphic(mic_img_view);

        // stopButton = new Button("Stop and Generate Recipe");
        stopButton = new Button();
        stopButton.setStyle(defaultButtonStyle); // styling the buttonv
        stopButton.setPrefSize(150, 30);
        stopButton.setStyle(
                "-fx-background-radius: 5em; " +
                        "-fx-background-color: #e37179;" +
                        "-fx-min-width: 90px; " +
                        "-fx-min-height: 90px; " +
                        "-fx-max-width: 90px; " +
                        "-fx-max-height: 90px;");

        Image no_mic_img = new Image("file:src/resources/no_mic.png");
        ImageView no_mic_img_view = new ImageView(no_mic_img);
        no_mic_img_view.setFitHeight(35);
        no_mic_img_view.setFitWidth(35);
        stopButton.setStyle("-fx-background-color: #e37179; ");
        recordingLabel = new Label("Recording...");

        stopButton.setGraphic(no_mic_img_view);

        // backButton.setPadding(new Insets(10)); //10 px "buffer" around button
        // this.getChildren().addAll(backButton, addButton, recipeListButton);
        this.getChildren().addAll(mealTypeLabel, addButton, stopButton, recordingLabel);

        this.setAlignment(Pos.CENTER); // Align the text to the Center

        ar = new AudioRecorder();

        recordingLabel.setVisible(false);

        addListeners();
    }

    // public Button getBackButton() {
    // return backButton;
    // }

    public Button getAddButton() {
        return addButton;
    }

    public Button getStopButton() {
        return stopButton;
    }

    public Label getRecordingLabel() {
        return recordingLabel;
    }

    public void addListeners() {
        // Start Recording Button
        addButton.setOnAction(e -> {
            recordingLabel.setVisible(true);
            ar.startRecording();
        });

        // Stop Recording Button and show the new recipe that was generated
        stopButton.setOnAction(e -> {
            recordingLabel.setVisible(false);
            ar.stopRecording();
            processIngredientRecording();
        });
    }

    // Given the audio recording of the ingredients, create a new recipe and display it in NewRecipeScreen()
    private void processIngredientRecording() {
        String[] result;
        try {
            recordingLabel.setText("Processing...");
            result = getRecipeFromAudio("recording.wav", mealType);
            recordingLabel.setText("Stop and Generate Recipe");

            RecipeData recipe = new RecipeData(result);

            Scene scene = new Scene(new NewRecipeScreen(primaryStage, recipe), 500, 600);
            primaryStage.setScene(scene);

            System.out.println(result[0]);
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
    }

    public static String[] getRecipeFromAudio(String filePath, String mealType) throws IOException, URISyntaxException {
        OpenAI model = new OpenAI();
        ChatGPT gpt = new ChatGPT(model);
        String transcript = Whisper.getTranscript(filePath);
        String[] recipe = gpt.generateRecipe(transcript, mealType);
        return recipe;
    }

}

class CreateMealType extends VBox {
    // private Button backButton;
    private Button addButton;
    private Button stopButton;
    private Label recordingLabel;
    private Button recipeListButton;

    CreateMealType() {
        this.setPrefSize(500, 560);
        this.setStyle("-fx-background-color: #a7d6cc;");
        this.setSpacing(15);

        // backButton = new Button("Back");
        String defaultButtonStyle = "-fx-font-style: italic; -fx-background-color: #FFFFFF;  -fx-font-weight: bold; -fx-font: 15 arial;";

        // backButton.setPadding(new Insets(0, 0, 0, 0));
        // backButton.setStyle(defaultButtonStyle); // styling the button
        // backButton.setPrefSize(90, 30);

        addButton = new Button(); // text displayed on add button
        addButton.setStyle(defaultButtonStyle); // styling the buttonv
        addButton.setPrefSize(150, 30);
        addButton.setStyle(
                "-fx-background-radius: 5em; " +
                        "-fx-background-color: #71e395;" +
                        "-fx-min-width: 90px; " +
                        "-fx-min-height: 90px; " +
                        "-fx-max-width: 90px; " +
                        "-fx-max-height: 90px;");

        Image mic_img = new Image("file:src/resources/mic.png");
        ImageView mic_img_view = new ImageView(mic_img);
        mic_img_view.setFitHeight(45);
        mic_img_view.setFitWidth(45);

        addButton.setGraphic(mic_img_view);

        stopButton = new Button();
        stopButton.setStyle(defaultButtonStyle); // styling the buttonv
        stopButton.setPrefSize(150, 30);
        stopButton.setStyle(
                "-fx-background-radius: 5em; " +
                        "-fx-background-color: #e37179;" +
                        "-fx-min-width: 90px; " +
                        "-fx-min-height: 90px; " +
                        "-fx-max-width: 90px; " +
                        "-fx-max-height: 90px;");

        Image no_mic_img = new Image("file:src/resources/no_mic.png");
        ImageView no_mic_img_view = new ImageView(no_mic_img);
        no_mic_img_view.setFitHeight(35);
        no_mic_img_view.setFitWidth(35);

        stopButton.setGraphic(no_mic_img_view);

        recordingLabel = new Label("Recording...");

        recipeListButton = new Button("View Recipe List"); // text displayed on add button
        recipeListButton.setStyle(defaultButtonStyle); // styling the buttonv
        recipeListButton.setPrefSize(150, 30);

        recipeListButton.setStyle(
                // "-fx-background-radius: 5em; " +
                "-fx-background-color: #e6f5f3;");

        // backButton.setPadding(new Insets(10)); //10 px "buffer" around button
        // this.getChildren().addAll(backButton, addButton, recipeListButton);
        this.getChildren().addAll(addButton, stopButton, recordingLabel, recipeListButton);

        this.setAlignment(Pos.CENTER); // Align the text to the Center
    }

    // public Button getBackButton() {
    // return backButton;
    // }

    public Button getAddButton() {
        return addButton;
    }

    public Button getStopButton() {
        return stopButton;
    }

    public Button getRecipeListButton() {
        return recipeListButton;
    }

    public Label getRecordingLabel() {
        return recordingLabel;
    }
}

class Footer extends HBox {

    // private Button addButton;
    private Button clearButton;
    private Button loadButton;
    private Button saveButton;

    Footer() {
        this.setPrefSize(500, 60);
        this.setStyle("-fx-background-color: #F0F8FF;");
        this.setSpacing(15);

        // set a default style for buttons - background color, font size, italics
        String defaultButtonStyle = "-fx-font-style: italic; -fx-background-color: #FFFFFF;  -fx-font-weight: bold; -fx-font: 11 arial;";

        // addButton = new Button("Create Recipe"); // text displayed on add button
        // addButton.setStyle(defaultButtonStyle); // styling the button
        clearButton = new Button("Delete Selected Recipes"); // text displayed on clear recipes button
        clearButton.setStyle(defaultButtonStyle);
        loadButton = new Button("Load Recipes"); // text displayed on clear recipes button
        loadButton.setStyle(defaultButtonStyle);
        saveButton = new Button("Save Recipes"); // text displayed on clear recipes button
        saveButton.setStyle(defaultButtonStyle);
        this.getChildren().addAll(clearButton, loadButton, saveButton); // adding buttons to footer
        this.setAlignment(Pos.CENTER); // aligning the buttons to center

    }

    // public Button getAddButton() {
    // return addButton;
    // }

    public Button getClearButton() {
        return clearButton;
    }

    public Button getLoadButton() {
        return loadButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }

}

class Header extends HBox {
    Button backButton;
    Text titleText;
    StackPane titleContainer;

    Header() {
        this.setPrefSize(500, 60); // Size of the header
        this.setStyle("-fx-background-color: #d5f2ec;");

        // Back Button
        backButton = new Button("Back");
        String defaultButtonStyle = "-fx-font-style: italic; -fx-background-color: #FFFFFF;  -fx-font-weight: bold; -fx-font: 11 arial;";
        backButton.setStyle(defaultButtonStyle);
        backButton.setPadding(new Insets(10, 10, 10, 10)); // Insets(top, right, bottom, left)

        backButton.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(backButton, Priority.NEVER); // Prevents the back button from growing

        // Title Text
        titleText = new Text("PantryPal");
        titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");

        // StackPane for centering the title
        titleContainer = new StackPane();
        titleContainer.getChildren().add(titleText);
        HBox.setHgrow(titleContainer, Priority.ALWAYS); // Allows the title container to grow and center the title

        // Add components to the HBox
        this.getChildren().addAll(backButton, titleContainer);
        this.backButton.setVisible(false);
    }
}

class AppFrame extends BorderPane {

    private Header header;
    // private Footer footer;
    private RecipeList recipeList;
    private CreateMealType createMealType;
    private StackPane stackPane = new StackPane();

    private Button recipeListButton;

    private Button addButton;
    private Button stopButton;

    private AudioRecorder ar;

    private Label recordingLabel;
    private ScrollPane scrollPane;
    private Stage primaryStage;

    AppFrame(Stage primaryStage) throws IOException {
        // Initialise the header Object
        header = new Header();

        // Create a recipelist Object to hold the recipes
        recipeList = new RecipeList(primaryStage);

        // Initialise the Footer Object
        // footer = new Footer();

        createMealType = new CreateMealType();

        scrollPane = new ScrollPane(recipeList);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        // add the cards to the stackPane
        // TODO: create a pane for the detailed view of each recipe

        stackPane.getChildren().addAll(scrollPane, createMealType);
        scrollPane.setVisible(false);
        createMealType.setVisible(true);

        this.primaryStage = primaryStage;

        // Add header to the top of the BorderPane
        this.setTop(header);
        // Add scroller to the centre of the BorderPane
        this.setCenter(stackPane);
        // Add footer to the bottom of the BorderPane
        // this.setBottom(footer);

        // Initialise Button Variables through the getters in Footer
        addButton = createMealType.getAddButton();
        stopButton = createMealType.getStopButton();
        recordingLabel = createMealType.getRecordingLabel();
        recipeListButton = createMealType.getRecipeListButton();

        recordingLabel.setVisible(false);

        // clearButton = footer.getClearButton();
        // loadButton = footer.getLoadButton();
        // saveButton = footer.getSaveButton();
        // backButton = createRecipe.getBackButton();
        // Call Event Listeners for the Buttons
        ar = new AudioRecorder();

        addListeners();
    }

    public void addListeners() {
        // Start Recording Button
        addButton.setOnAction(e -> {
            recordingLabel.setText("Recording...");
            recordingLabel.setVisible(true);
            ar.startRecording();
        });

        // Stop Recording Button and load CreateRecipe scene with wanted recipe type
        stopButton.setOnAction(e -> {
            ar.stopRecording();
            processMealTypeRecording();
        });

        recipeListButton.setOnAction(e -> {
            // switch to the list screen
            scrollPane.setVisible(true);
            createMealType.setVisible(false);
            header.backButton.setVisible(true);
        });

        header.backButton.setOnAction(e -> {
            scrollPane.setVisible(false);
            createMealType.setVisible(true);
            header.backButton.setVisible(false);
        });

    }

    // Given the audio file, extract the meal type, and if it valid go to CreateRecipe() screen
    private void processMealTypeRecording() {
        String result;
        try {
            recordingLabel.setText("Processing...");
            result = getMealTypeFromAudio("recording.wav");
            System.out.println(result);

            if (!result.equals(" Breakfast") && !result.equals(" Lunch") && !result.equals(" Dinner")) {
                recordingLabel.setText("Invalid meal type generated, please try again");
                return;
            } else {
                Scene scene = new Scene(new CreateRecipe(primaryStage, result), 500, 600);
                primaryStage.setScene(scene);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
    }

    // Given an audio file path, create a transcription, then generate the wanted
    // meal type
    public static String getMealTypeFromAudio(String filePath) throws IOException, URISyntaxException {
        OpenAI model = new OpenAI();
        ChatGPT gpt = new ChatGPT(model);
        String transcript = Whisper.getTranscript(filePath);
        String mealType = gpt.generateMealType(transcript);
        return mealType;
    }

    // public void addListeners() {

    // // Add button functionality
    // addButton.setOnAction(e -> {
    // // Create a new recipe
    // /*
    // * Recipe recipe = new Recipe();
    // * // Add recipe to recipelist
    // * recipeList.getChildren().add(recipe);
    // * // Add doneButtonToggle to the Done button
    // * Button deleteButton = recipe.getDeleteButton();
    // * deleteButton.setOnAction(e1 -> {
    // * // Call toggleDone on click
    // * recipe.toggleDone();
    // * });
    // * // Update recipe indices
    // * recipeList.updateRecipeIndices();
    // */

    // // open next page in the card layout
    // // scrollPane.setVisible(false);
    // // createRecipe.setVisible(true);

    // // CALL WHISPER HERE + TAKE MIC INPUT
    // });

    // // backButton.setOnAction(e -> {
    // // scrollPane.setVisible(true);
    // // createRecipe.setVisible(false);
    // // });

    // recipeListButton.setOnAction(e -> {
    // // TODO: Open up the list of all recipes, and allow user to select one
    // });

    // // Clear finished recipes
    // // clearButton.setOnAction(e -> {
    // // recipeList.removeCompletedRecipes();
    // // });

    // // load recipes from file
    // // loadButton.setOnAction(e -> {
    // // recipeList.loadRecipes();
    // // });

    // // save current recipe list
    // // saveButton.setOnAction(e -> {
    // // recipeList.saveRecipes();
    // // });

    // }
    // }
}

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Setting the Layout of the Window- Should contain a Header, Footer and the
        // RecipeList
        AppFrame root = new AppFrame(primaryStage);

        // Set the title of the app
        primaryStage.setTitle("Pantry Pal");
        // Create scene of mentioned size with the border pane
        primaryStage.setScene(new Scene(root, 500, 600));
        // Make window non-resizable
        primaryStage.setResizable(false);
        // Show the app
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        launch(args);
    }
}

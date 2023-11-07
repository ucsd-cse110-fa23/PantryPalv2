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

import java.io.IOException;
import java.net.URISyntaxException;

class Recipe extends HBox {

    //public Label index;
    public TextField recipeName;
    public TextField recipeDetails;
    private Button deleteButton;

    private boolean markedDone;

    Recipe() {
        this.setPrefSize(500, 20); // sets size of recipe
        this.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0; -fx-font-weight: bold;"); // sets background
                                                                                                     // color of recipe
        markedDone = false;

        // index = new Label();
        // index.setText(""); // create index label
        // index.setPrefSize(40, 20); // set size of Index label
        // index.setTextAlignment(TextAlignment.CENTER); // Set alignment of index label
        // index.setPadding(new Insets(10, 0, 10, 0)); // adds some padding to the recipe
        // this.getChildren().add(index); // add index label to recipe

        recipeName = new TextField(); // create recipe name text field
        recipeName.setPrefSize(380, 20); // set size of text field
        recipeName.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // set background color of texfield
        //index.setTextAlignment(TextAlignment.LEFT); // set alignment of text field
        recipeName.setPadding(new Insets(10, 0, 10, 0)); // adds some padding to the text field
        this.getChildren().add(recipeName); // add textlabel to recipe

        recipeDetails = new TextField(); // create recipe name text field
        recipeDetails.setPrefSize(380, 20); // set size of text field
        recipeDetails.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // set background color of texfield
        //index.setTextAlignment(TextAlignment.LEFT); // set alignment of text field
        recipeDetails.setPadding(new Insets(10, 0, 10, 0)); // adds some padding to the text field
        this.getChildren().add(recipeDetails); // add textlabel to recipe

        deleteButton = new Button("Delete"); // creates a button for marking the recipe as done
        deleteButton.setPrefSize(100, 20);
        deleteButton.setPrefHeight(Double.MAX_VALUE);
        deleteButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // sets style of button

        this.getChildren().add(deleteButton);
    }

    public Recipe(String na, String de) {
        //this.index.setText(in +"");
        this.recipeName.setText(na);
        this.recipeDetails.setText(de);
    }

    // public void setRecipeIndex(int num) {
    //     this.index.setText(num + ""); // num to String
    //     //this.recipeName.setPromptText("Recipe " + num);
    // }

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

    public void toggleDone() {
        markedDone = !markedDone;
        if (markedDone) {
            this.setStyle("-fx-border-color: #000000; -fx-border-width: 0; -fx-font-weight: bold;"); // remove border of
                                                                                                     // recipe
            for (int i = 0; i < this.getChildren().size(); i++) {
                this.getChildren().get(i).setStyle("-fx-background-color: #BCE29E; -fx-border-width: 0;"); // change
                                                                                                           // color of
                                                                                                           // recipe to
                                                                                                           // green
            }
        } else {
            this.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0; -fx-font-weight: bold;"); // put back
                                                                                                         // border
            for (int i = 0; i < this.getChildren().size(); i++) {
                this.getChildren().get(i).setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // change
                                                                                                           // color of
                                                                                                           // recipe to
                                                                                                           // green
            }
        }
    }

    

}

class Pair implements Comparable<Pair> {
    private String one;
    private boolean two;

    public Pair(String str, boolean bool) {
        one = str;
        two = bool;
    }

    public String getOne() {
        return one;
    }

    public boolean getTwo() {
        return two;
    }

    public int compareTo(Pair compare) {
        return (this.one).compareTo(compare.getOne());
    }
}

class RecipeList extends VBox {

    RecipeList() {
        this.setSpacing(5); // sets spacing between recipes
        this.setPrefSize(500, 560);
        this.setStyle("-fx-background-color: #F0F8FF;");
    }

    // public void updateRecipeIndices() {
    //     int index = 1;
    //     for (int i = 0; i < this.getChildren().size(); i++) {
    //         if (this.getChildren().get(i) instanceof Recipe) {
    //             ((Recipe) this.getChildren().get(i)).setRecipeIndex(index);
    //             index++;
    //         }
    //     }
    // }

    // public void removeCompletedRecipes() {
    //     this.getChildren().removeIf(recipe -> recipe instanceof Recipe && ((Recipe) recipe).isMarkedDone());
    //     this.updateRecipeIndices();
    // }

    /*
     * Load recipes from a file called "recipes.txt"
     * Add the recipes to the children of recipelist component
     */
    public void loadRecipes() {
        // hint 1: use try-catch block
        // hint 2: use BufferedReader and FileReader
        // hint 3: recipe.getRecipeName().setText() sets the text of the recipe
        try {
            File file = new File("/Users/akuduvalli/Desktop/CSE-110/java/CSE110-Lab-1/src/TodoList/recipes.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String data = scan.nextLine();
                Recipe recipe = new Recipe();
                recipe.setRecipeName(data);
                // Add recipe to recipelist
                this.getChildren().add(recipe);
                Button doneButton = recipe.getDeleteButton();
                doneButton.setOnAction(e1 -> {
                    // Call toggleDone on click
                    recipe.toggleDone();
                });
                //this.updateRecipeIndices();
                System.out.println(data);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("error: file not found ");
        }
    }

    /*
     * Save recipes to a file called "recipes.txt"
     */
    public void saveRecipes() {
        // hint 1: use try-catch block
        // hint 2: use FileWriter
        // hint 3: this.getChildren() gets the list of recipes
        try {
            FileWriter writer = new FileWriter(
                    "/Users/akuduvalli/Desktop/CSE-110/java/CSE110-Lab-1/src/TodoList/recipes.txt", false);
            for (int i = 0; i < this.getChildren().size(); i++) {
                String str = ((Recipe) this.getChildren().get(i)).getRecipeName().getText()
                        + (i < this.getChildren().size() - 1 ? "\n" : "");
                writer.write(str);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}

/**
 * Class to prompt user for their audio recording ~ type of recipe
 * potentially change to StackPane to accomodate a different page for each part
 * of the creating recipe
 */
class CreateRecipe extends VBox {

    // private Button backButton;
    private Button addButton;
    private Button stopButton;
    private Label recordingLabel;
    private Button recipeListButton;

    CreateRecipe() {
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
                        "-fx-min-width: 90px; " +
                        "-fx-min-height: 90px; " +
                        "-fx-max-width: 90px; " +
                        "-fx-max-height: 90px;");

        Image mic_img = new Image("file:src/resources/mic.png");
        ImageView mic_img_view = new ImageView(mic_img);
        mic_img_view.setFitHeight(45);
        mic_img_view.setFitWidth(45);

        addButton.setGraphic(mic_img_view);

        stopButton = new Button("Stop and Generate Recipe");

        recordingLabel = new Label("Recording...");

        recipeListButton = new Button("View Recipe List"); // text displayed on add button
        recipeListButton.setStyle(defaultButtonStyle); // styling the buttonv
        recipeListButton.setPrefSize(150, 30);

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

    Header() {
        this.setPrefSize(500, 60); // Size of the header
        this.setStyle("-fx-background-color: #d5f2ec;");

        Text titleText = new Text("PantryPal"); // Text of the Header
        titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");
        this.getChildren().add(titleText);
        this.setAlignment(Pos.CENTER); // Align the text to the Center
    }
}

class AppFrame extends BorderPane {

    private Header header;
    // private Footer footer;
    private RecipeList recipeList;
    private CreateRecipe createRecipe;
    private StackPane stackPane = new StackPane();

    private Button recipeListButton;

    private Button addButton;
    private Button stopButton;
    private AudioFormat audioFormat;
    private TargetDataLine targetDataLine;
    private Label recordingLabel;
    private Thread t;

    // private Button clearButton;
    // private Button loadButton;
    // private Button saveButton;

    // private Button backButton;
    private ScrollPane scrollPane;
    private Stage primaryStage;

    AppFrame(Stage primaryStage) {
        // Initialise the header Object
        header = new Header();

        // Create a recipelist Object to hold the recipes
        recipeList = new RecipeList();

        // Initialise the Footer Object
        // footer = new Footer();

        createRecipe = new CreateRecipe();

        scrollPane = new ScrollPane(recipeList);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        // add the cards to the stackPane
        // TODO: create a pane for the detailed view of each recipe

        stackPane.getChildren().addAll(scrollPane, createRecipe);
        scrollPane.setVisible(true);
        createRecipe.setVisible(true);

        this.primaryStage = primaryStage;

        // Add header to the top of the BorderPane
        this.setTop(header);
        // Add scroller to the centre of the BorderPane
        this.setCenter(stackPane);
        // Add footer to the bottom of the BorderPane
        // this.setBottom(footer);

        // Initialise Button Variables through the getters in Footer
        addButton = createRecipe.getAddButton();
        stopButton = createRecipe.getStopButton();
        recordingLabel = createRecipe.getRecordingLabel();
        recipeListButton = createRecipe.getRecipeListButton();

        recordingLabel.setVisible(false);

        // clearButton = footer.getClearButton();
        // loadButton = footer.getLoadButton();
        // saveButton = footer.getSaveButton();
        // backButton = createRecipe.getBackButton();
        // Call Event Listeners for the Buttons
        audioFormat = getAudioFormat();

        addListeners();
    }

    public void addListeners() {
        // Start Button
        addButton.setOnAction(e -> {
            startRecording();
        });

        // Stop Button
        stopButton.setOnAction(e -> {
            stopRecording();
            String[] result;
            try {
                recordingLabel.setText("Processing...");
                result = OpenAI.getRecipeFromAudio("recording.wav");
                recordingLabel.setText("Stop and Generate Recipe");

                RecipeData recipe = new RecipeData(result);

                Scene scene = new Scene(new NewRecipeScreen(primaryStage, recipe), 500, 600);
                primaryStage.setScene(scene);

                System.out.println(result[0]);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (URISyntaxException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        });
    }

    private AudioFormat getAudioFormat() {
        // the number of samples of audio per second.
        // 44100 represents the typical sample rate for CD-quality audio.
        float sampleRate = 44100;

        // the number of bits in each sample of a sound that has been digitized.
        int sampleSizeInBits = 16;

        // the number of audio channels in this format (1 for mono, 2 for stereo).
        int channels = 2;

        // whether the data is signed or unsigned.
        boolean signed = true;

        // whether the audio data is stored in big-endian or little-endian order.
        boolean bigEndian = false;

        return new AudioFormat(
                sampleRate,
                sampleSizeInBits,
                channels,
                signed,
                bigEndian);
    }

    private void startRecording() {
        t = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // the format of the TargetDataLine
                            DataLine.Info dataLineInfo = new DataLine.Info(
                                    TargetDataLine.class,
                                    audioFormat);
                            // the TargetDataLine used to capture audio data from the microphone
                            targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
                            targetDataLine.open(audioFormat);
                            targetDataLine.start();
                            recordingLabel.setVisible(true);

                            // the AudioInputStream that will be used to write the audio data to a file
                            AudioInputStream audioInputStream = new AudioInputStream(
                                    targetDataLine);

                            // the file that will contain the audio data
                            File audioFile = new File("recording.wav");
                            AudioSystem.write(
                                    audioInputStream,
                                    AudioFileFormat.Type.WAVE,
                                    audioFile);
                            recordingLabel.setVisible(false);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
        t.start();
    }

    private void stopRecording() {
        targetDataLine.stop();
        targetDataLine.close();
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

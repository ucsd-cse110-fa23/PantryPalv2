package pantrypal;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ComboBox;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.text.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
                e1.printStackTrace();
            }
        });

        viewButton.setOnAction(e -> {
            try {
                Scene scene = new Scene(new ViewRecipe(primaryStage, recipeData), 500, 600);
                primaryStage.setScene(scene);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        this.getChildren().add(viewButton); // add button to recipe
        this.getChildren().add(deleteButton);
    }

    public void setRecipeIndex(int num) {
        this.index.setText(num + ""); // num to String
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
    ArrayList<RecipeData> recipes; // original recipes 
    ArrayList<RecipeData> currentRecipes, taggedRecipes; // current recipes 

    RecipeList(Stage primaryStage) throws IOException {
        this.setSpacing(5); // sets spacing between recipes
        this.setPrefSize(500, 560);
        this.setStyle("-fx-background-color: #F0F8FF;");
        this.primaryStage = primaryStage;
        // get the current recipe data (from JSON file)
        recipes = CRUDRecipes.readRecipes();
        currentRecipes = taggedRecipes = recipes;
        // add the recipes to the recipelist
        loadRecipes(recipes);
    }

    /*
     * Load recipes from a file called "recipes.json"
     * Add the recipes to the children of recipelist component
     */
    public void loadRecipes(ArrayList<RecipeData> recipes) {
        for (RecipeData recipeData : recipes) {
            // create Recipe object for each recipe data
            Recipe recipe = new Recipe(primaryStage, recipeData);
            recipe.setRecipeName(recipeData.title);
            this.getChildren().add(recipe);
        }
    }

    /**
     * reorder recipes based on the following order
     * @param recipes the recipe order to replace with
     */
    public void redoRecipes(ArrayList<RecipeData> recipes) {
        // drop current recipes
        this.getChildren().clear();
        // load new permutation
        currentRecipes = recipes;
        this.loadRecipes(recipes);
    }

    /**
     * reorder recipes for tagged based on the following order
     * @param recipes the recipe order to replace with
     */
    public void redoRecipesT(ArrayList<RecipeData> recipes) {
        // drop current recipes
        this.getChildren().clear();
        // load new permutation
        taggedRecipes = recipes;
        this.loadRecipes(recipes);
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
    private Button backButton;
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

        String defaultButtonStyle = "-fx-font-style: italic; -fx-background-color: #FFFFFF;  -fx-font-weight: bold; -fx-font: 15 arial;";

        this.primaryStage = primaryStage;
        this.mealType = mealType;

        this.backButton = new Button("Back");
        backButton.setStyle(defaultButtonStyle);
        backButton.setPadding(new Insets(10, 10, 10, 10)); // Insets(top, right, bottom, left)
        backButton.setAlignment(Pos.BOTTOM_CENTER);

        mealTypeLabel = new Label("To generate your " + mealType.strip().toLowerCase()
                + " recipe, press the green button, speak your ingredients, then press the red button.");
        mealTypeLabel.setWrapText(true);
        mealTypeLabel.setTextAlignment(TextAlignment.CENTER);
        mealTypeLabel.setMaxWidth(400);

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
        stopButton.setStyle("-fx-background-color: #e37179; ");
        recordingLabel = new Label("Recording...");
        stopButton.setGraphic(no_mic_img_view);

        this.getChildren().addAll(mealTypeLabel, addButton, stopButton, recordingLabel, backButton);

        this.setAlignment(Pos.CENTER); // Align the text to the Center

        ar = new AudioRecorder();

        recordingLabel.setVisible(false);

        addListeners();
    }

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

        backButton.setOnAction(e -> {
            try {
                primaryStage.setScene(new Scene(new AppFrame(primaryStage)));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    // Given the audio recording of the ingredients, create a new recipe and display
    // it in NewRecipeScreen()
    private void processIngredientRecording() {
        String result;
        try {
            recordingLabel.setText("Processing...");

            result = getRecipeFromAudio("recording.wav", mealType);
            recordingLabel.setText("Stop and Generate Recipe");

            RecipeData recipe = new RecipeData(result, mealType);

            Scene scene = new Scene(new NewRecipeScreen(primaryStage, recipe), 500, 600);
            primaryStage.setScene(scene);

            System.out.println(result);
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
    }

    public static String getRecipeFromAudio(String filePath, String mealType) throws IOException, URISyntaxException {
        MiddlewareModel mm = new MiddlewareModel();
        return mm.generateRecipe(filePath, mealType);

        // OpenAI model = new OpenAI();
        // ChatGPT gpt = new ChatGPT(model);
        // String transcript = Whisper.getTranscript(filePath);
        // String[] recipe = gpt.generateRecipe(transcript, mealType);
        // return recipe;
    }

}

class CreateMealType extends VBox {
    private Button addButton;
    private Button stopButton;
    private Label recordingLabel;
    private Button recipeListButton;
    private Label instructionsLabel;

    CreateMealType() {
        this.setPrefSize(500, 560);
        this.setStyle("-fx-background-color: #a7d6cc;");
        this.setSpacing(15);

        String defaultButtonStyle = "-fx-font-style: italic; -fx-background-color: #FFFFFF;  -fx-font-weight: bold; -fx-font: 15 arial;";

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

        instructionsLabel = new Label(
                "Press the button below to start recording your meal type. Press the red button when you are done recording.");
        instructionsLabel.setWrapText(true);
        instructionsLabel.setMaxWidth(400);

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

        this.getChildren().addAll(instructionsLabel, addButton, stopButton, recordingLabel, recipeListButton);

        this.setAlignment(Pos.CENTER); // Align the text to the Center
    }

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

/** three buttons for filtering by meal type tag */
class TagFilter extends VBox {
    Button breakfast;
    Button lunch;
    Button dinner;

    TagFilter() {

        this.setSpacing(5); // sets spacing between recipes
        this.setPrefSize(100, 100);
        this.setStyle("-fx-background-color: #d5f2ec;");


        // init buttons, add children 
        lunch = new Button("lunch"); // creates a button for marking the contact as done
        lunch.setPrefSize(100, 20);
        //lunch.setPrefHeight(Double.MAX_VALUE);
        lunch.setStyle("-fx-background-color: #2B4162; -fx-border-width: 0;-fx-text-fill: white;"); // sets style of button

        breakfast = new Button("breakfast"); // creates a button for marking the contact as done
        breakfast.setPrefSize(100, 20);
        //breakfast.setPrefHeight(Double.MAX_VALUE);
        breakfast.setStyle("-fx-background-color: #FA9F42; -fx-border-width: 0;"); // sets style of button

        dinner = new Button("dinner"); // creates a button for marking the contact as done
        dinner.setPrefSize(100, 20);
        //dinner.setPrefHeight(Double.MAX_VALUE);
        dinner.setStyle("-fx-background-color: #0B6E4F; -fx-border-width: 0;-fx-text-fill: white;"); // sets style of button
        
        this.getChildren().addAll(breakfast,lunch,dinner);

    }

}

class Header extends HBox {
    Button backButton;
    Button logoutButton;
    Text titleText;
    StackPane titleContainer;

    TagFilter filter;
    ComboBox<String> sortMenu;

    Header() {
        this.setPrefSize(500, 60); // Size of the header
        this.setStyle("-fx-background-color: #d5f2ec;");
        this.setSpacing(5); //
        this.setAlignment(Pos.CENTER);

        // Back Button
        backButton = new Button("Back");
        String defaultButtonStyle = "-fx-font-style: italic; -fx-background-color: #FFFFFF;  -fx-font-weight: bold; -fx-font: 11 arial;";
        backButton.setStyle(defaultButtonStyle);
        backButton.setPadding(new Insets(10, 10, 10, 10)); // Insets(top, right, bottom, left)
        backButton.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(backButton, Priority.NEVER); // Prevents the back button from growing

        logoutButton = new Button("Log Out");
        logoutButton.setStyle(defaultButtonStyle);
        logoutButton.setPadding(new Insets(10, 10, 10, 10));

        logoutButton.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(logoutButton, Priority.NEVER);

        // configure the sort menu
        ObservableList<String> sortOptions = FXCollections.observableArrayList(
                "Sort By",
                "Alphabetically",
                "Reverse Alphabetically",
                "Newest First",
                "Oldest First"
        );
        sortMenu = new ComboBox<String>(sortOptions);
        sortMenu.setValue("Sort By");
        backButton.setAlignment(Pos.CENTER_RIGHT);
        logoutButton.setAlignment(Pos.CENTER_RIGHT);

        sortMenu.setStyle("-fx-font-size: 14px; -fx-background-color: white; -fx-border-color: #ccc;");
        sortMenu.setStyle(defaultButtonStyle);
        sortMenu.setPadding(new Insets(10, 10, 10, 10)); // Insets(top, right, bottom, left)
        sortMenu.setVisible(false);
        HBox.setHgrow(sortMenu, Priority.NEVER); // Prevents the back button from growing

        filter = new TagFilter();
        filter.setAlignment(Pos.CENTER);
        filter.setVisible(false);


        // Title Text
        titleText = new Text("PantryPal");
        titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");

        // StackPane for centering the title
        titleContainer = new StackPane();
        titleContainer.getChildren().add(titleText);
        HBox.setHgrow(titleContainer, Priority.ALWAYS); // Allows the title container to grow and center the title

        // Add components to the HBox
        this.getChildren().addAll(logoutButton, backButton, titleContainer,sortMenu,filter);
        this.backButton.setVisible(false);
        this.logoutButton.setVisible(false);

    }

    /** 
     * getter for the sort dropdown
     * @return the sortMenu object
     */
    public ComboBox<String> getSortMenu() {
        return sortMenu;
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

    private ComboBox<String> sortMenu;

    private Button breakfast;
    private Button lunch;
    private Button dinner;
    private boolean breakYes,lunchYes,dinYes;
    private String currentSort;

    AppFrame(Stage primaryStage) throws IOException {
        // Initialise the header Object
        header = new Header();

        // Create a recipelist Object to hold the recipes
        recipeList = new RecipeList(primaryStage);

        createMealType = new CreateMealType();

        scrollPane = new ScrollPane(recipeList);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        stackPane.getChildren().addAll(scrollPane, createMealType);
        scrollPane.setVisible(false);
        createMealType.setVisible(true);

        this.primaryStage = primaryStage;

        // Add header to the top of the BorderPane
        this.setTop(header);
        // Add scroller to the centre of the BorderPane
        this.setCenter(stackPane);

        // Initialise Button Variables through the getters in Footer
        addButton = createMealType.getAddButton();
        stopButton = createMealType.getStopButton();
        recordingLabel = createMealType.getRecordingLabel();
        recipeListButton = createMealType.getRecipeListButton();

        sortMenu = header.getSortMenu();
        breakfast = header.filter.breakfast;
        lunch = header.filter.lunch;
        dinner = header.filter.dinner;
        breakYes = lunchYes = dinYes = true;
        currentSort = "";

        recordingLabel.setVisible(false);
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

        // opening recipe list
        recipeListButton.setOnAction(e -> {
            // switch to the list screen
            scrollPane.setVisible(true);
            createMealType.setVisible(false);
            header.backButton.setVisible(true);
            header.logoutButton.setVisible(true);

            sortMenu.setVisible(true);
            header.filter.setVisible(true);
        });

        // back to main page
        header.backButton.setOnAction(e -> {
            scrollPane.setVisible(false);
            createMealType.setVisible(true);
            header.backButton.setVisible(false);
            sortMenu.setVisible(false);
            header.filter.setVisible(false);
        });

        header.logoutButton.setOnAction(e -> {
            try {
                scrollPane.setVisible(false);
                header.backButton.setVisible(false);
                header.logoutButton.setVisible(false);

                logout();
            } catch (IOException ex) {
                // Handle the IOException here, log it, show an error message, or take
                // appropriate action.
                ex.printStackTrace();
            }
        });

        // header.logoutButton.setOnAction(e -> {

        // scrollPane.setVisible(false);
        // header.backButton.setVisible(false);
        // header.logoutButton.setVisible(false);

        // logout();

        // });

        sortMenu.setOnAction(event -> {
            // Update styling when the selection changes
            updateList(sortMenu.getValue());
            this.recipeList.redoRecipes(FilterRecipes.filterRecipeByMeal(this.recipeList.currentRecipes,this.getTags()));
        });

         // type listeners

        // switch
        breakfast.setOnAction(e -> {
            if(breakYes) {
                // filter out breakfast
                breakfast.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");
            }
            else {
                breakfast.setStyle("-fx-background-color: #FA9F42; -fx-border-width: 0;");
            }
            breakYes = !breakYes;
            // sort then filter 
            this.updateList(currentSort);
            this.recipeList.redoRecipes(FilterRecipes.filterRecipeByMeal(this.recipeList.currentRecipes,this.getTags()));
        });

        // switch
        lunch.setOnAction(e -> {
            if(lunchYes) {
                // filter out lunch
                lunch.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;-fx-text-fill: black;");
            }
            else {
                lunch.setStyle("-fx-background-color: #2B4162; -fx-border-width: 0;-fx-text-fill: white;");
            }
            lunchYes = !lunchYes;
            // sort then filter
            this.updateList(currentSort);
            this.recipeList.redoRecipes(FilterRecipes.filterRecipeByMeal(this.recipeList.currentRecipes,this.getTags()));
        });

        // switch
        dinner.setOnAction(e -> {
            // switch to the list screen
            if(dinYes) {
                // filter out dinner
                dinner.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");
            }
            else {
                dinner.setStyle("-fx-background-color: #0B6E4F; -fx-border-width: 0;-fx-text-fill: white;");

            }
            dinYes = !dinYes;
            // sort then filter
            this.updateList(currentSort);
            this.recipeList.redoRecipes(FilterRecipes.filterRecipeByMeal(this.recipeList.currentRecipes,this.getTags()));
        });


    }

    /** returns a list of the current meal tags requested
     * @return the meals that were requested 
     */
    private List<String> getTags() {
        List<String> tags = new ArrayList<String>();
        if(dinYes)
            tags.add("Dinner");
        if(lunchYes)
            tags.add("Lunch");
        if(breakYes)
            tags.add("Breakfast");
        return tags;
    }

    public void logout() throws IOException {
        try {
            System.out.println("Logging out...");

            List<RecipeData> recipes = CRUDRecipes.readRecipes();
            MiddlewareModel mm = new MiddlewareModel();
            mm.postRecipes(recipes, AccountService.getAccount());
            CRUDRecipes.deleteRecipesFile();
            Scene accScene = new Scene(new AccountScreen(primaryStage), 500, 600);
            primaryStage.setScene(accScene);

        } catch (IOException e1) {
            // TODO: handle exception
            e1.printStackTrace();
        }

    }

    /**
     * Update recipe list order
     * 
     * @param value the way the list should be ordered
     */
    private void updateList(String value) {
        if (value.equals("Alphabetically")) {
            // sort alphabetical
            this.recipeList.setStyle("-fx-background-color: #A2AEBB;");
            this.recipeList.redoRecipes(SortRecipes.sortAlphabetically(this.recipeList.recipes, false));
        } 
        else if (value.equals("Reverse Alphabetically")) {
            // sort alphabetical
            this.recipeList.setStyle("-fx-background-color: #A2AEBB;");
            this.recipeList.redoRecipes(SortRecipes.sortAlphabetically(this.recipeList.recipes, true));
        } 
        else if (value.equals("Newest First")) {
            // sort recipes reverse chronological
            this.recipeList.setStyle("-fx-background-color: #23B5D3;");
            this.recipeList.redoRecipes(SortRecipes.sortByTime(this.recipeList.recipes, true));
        } else if (value.equals("Oldest First")) {
            // sort recipes chronological
            this.recipeList.setStyle("-fx-background-color: #75ABBC;");
            this.recipeList.redoRecipes(SortRecipes.sortByTime(this.recipeList.recipes, false));
        } else {
            // default
            this.recipeList.setStyle("-fx-background-color: #F0F8FF;");
            this.recipeList.redoRecipes(SortRecipes.sortByTime(this.recipeList.recipes, false));
        }
        this.currentSort = value;
    }

    // Given the audio file, extract the meal type, and if it valid go to
    private void processMealTypeRecording() {
        String result;
        try {
            recordingLabel.setText("Processing...");
            result = getMealTypeFromAudio("recording.wav"); // TODO: Put this file name somewhere else
            System.out.println(result);

            if (!result.equals(" Breakfast") && !result.equals(" Lunch") && !result.equals(" Dinner")
                    && !result.equals("Breakfast") && !result.equals("Lunch") && !result.equals("Dinner")) {
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
        MiddlewareModel mm = new MiddlewareModel();
        return mm.mealTypeExtraction(filePath);
    }
}

public class Main extends Application {
    @Override
    public void stop() throws Exception {
        System.out.println("Application is closing...");

        List<RecipeData> recipes = CRUDRecipes.readRecipes();
        MiddlewareModel mm = new MiddlewareModel();
        mm.postRecipes(recipes, AccountService.getAccount());

        CRUDRecipes.deleteRecipesFile();
        RememberAccount.deleteRememberedAccountsFile();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MiddlewareModel mm = new MiddlewareModel();
        if (!mm.isServerOnline()) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Server Unavailable");
                alert.setHeaderText(null);
                alert.setContentText("The server is currently unavailable. Please check your connection or try again later.");
                alert.showAndWait();
                primaryStage.close();
            });
        } 

        else{
            // Setting the Layout of the Window- Should contain a Header, Footer and the
            // RecipeList
            AppFrame root = new AppFrame(primaryStage);

            // Set the title of the app
            primaryStage.setTitle("Pantry Pal");
            // Create scene of mentioned size with the border pane
            // primaryStage.setScene(new Scene(root, 500, 600));

            Scene accScene = new Scene(new AccountScreen(primaryStage), 500, 600);
            primaryStage.setScene(accScene);
            // Make window non-resizable
            primaryStage.setResizable(false);
            // Show the app
            primaryStage.show();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        launch(args);
    }
}
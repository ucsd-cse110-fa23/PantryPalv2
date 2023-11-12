package pantrypal;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DatabaseManager {
    String fileName;

    public DatabaseManager(String fileName) {
        this.fileName = fileName;
    }

    public void addRecipe(RecipeData recipe) {
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
    }

    public ArrayList<RecipeData> getRecipeList() {
        return null;
    }
}

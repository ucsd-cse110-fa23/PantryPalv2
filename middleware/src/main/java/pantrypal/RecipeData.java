package pantrypal;

public class RecipeData {
    Long id;
    String title;
    String[] ingredients;
    String instructions;
    double createdTime;
    String type;

    public RecipeData(String gptResponse, String type) {
        String[] response = gptResponse.split("\n");

        this.title = response[0];
        this.instructions = joinSubsectionLoop(response, 2, response.length - 1);
        ingredients = response[response.length - 1].split(";");
        this.createdTime = System.currentTimeMillis();
        this.type = type;
    }

    /*
     * Overloaded constructor for database operations
     */
    public RecipeData(String title, String[] ingredients, String instructions, String type, double createdTime) {
        this.title = title;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.createdTime = createdTime;
        this.type = type;
    }

    /*
     * For previous tests
     */
    public RecipeData(String title, String[] ingredients, String instructions) {
        this.title = title;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.createdTime = System.currentTimeMillis();
        this.type = "Breakfast";
    }

    private static String joinSubsectionLoop(String[] array, int startIndex, int endIndex) {
        StringBuilder result = new StringBuilder();
        for (int i = startIndex; i < endIndex; i++) {
            result.append(array[i]);
            if (i < endIndex - 1) {
                result.append(", "); // Add a separator between elements
            }
        }
        return result.toString();
    }

    public String toString() {
        return title + "\n" + instructions + "\n" + String.join(";", ingredients);
    }
}

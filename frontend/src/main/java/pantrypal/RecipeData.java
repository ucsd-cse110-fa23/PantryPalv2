package pantrypal;

public class RecipeData {
    String title;
    String[] ingredients;
    String instructions;

    public RecipeData(String gptResponse) {
        String[] response = gptResponse.split("\n");

        this.title = response[0];
        this.instructions = joinSubsectionLoop(response, 2, response.length-1);
        ingredients = response[response.length-1].split(";");
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

    /*
     * Overloaded constructor for database operations
     */
    public RecipeData(String title, String[] ingredients, String instructions) {
        this.title = title;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }
}

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

        // String body = gptResponse[0];
        // int newlineIndex = body.indexOf("\n");

        // // Use substring to split the string into two parts
        // String firstPart = body.substring(0, newlineIndex);
        // String secondPart = body.substring(newlineIndex + 1); // +1 to exclude the newline character itself

        // this.title = firstPart;
        // this.instructions = secondPart;
        // this.ingredients = new String[gptResponse.length - 1];
        // for (int i = 0; i < gptResponse.length - 1; i++) {
        //     this.ingredients[i] = gptResponse[i + 1];
        // }
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

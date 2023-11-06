package pantrypal;

public class Recipe {
    String title;
    String[] ingredients;
    String instructions;

    public Recipe(String[] gptResponse) {
        String body = gptResponse[0];
        int newlineIndex = body.indexOf("\n");

        // Use substring to split the string into two parts
        String firstPart = body.substring(0, newlineIndex);
        String secondPart = body.substring(newlineIndex + 1); // +1 to exclude the newline character itself

        this.title = firstPart;
        this.instructions = secondPart;
        this.ingredients = new String[gptResponse.length - 1];
        for (int i = 0; i < gptResponse.length - 1; i++) {
            this.ingredients[i] = gptResponse[i + 1];
        }
    }
}

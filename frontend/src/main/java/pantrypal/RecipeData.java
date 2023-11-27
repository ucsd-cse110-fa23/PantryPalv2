package pantrypal;

public class RecipeData {
    String title;
    String instructions;
    String[] ingredients;
    String imageUrl;

    public RecipeData(String gptResponse) {
        // Split by |||||
        String[] parts = gptResponse.split("\\|\\|\\|\\|\\|");

        // Check if the split response has at least two parts (recipe and image URL)
        if (parts.length > 1) {
            
            // Process the first part for recipe details
            String[] recipeDetails = parts[0].split("\n");
            this.title = recipeDetails[0].trim();
            this.instructions = joinSubsectionLoop(recipeDetails, 1, recipeDetails.length - 2);
            this.ingredients = recipeDetails[recipeDetails.length - 1].split(";");

            // Second part is the image URL
            this.imageUrl = parts[1].trim();
        }
    }

    private String joinSubsectionLoop(String[] lines, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++) {
            sb.append(lines[i]);
            if (i < end) {
                sb.append("\n"); // Add newline character between lines
            }
        }
        return sb.toString();
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


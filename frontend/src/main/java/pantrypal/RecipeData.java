package pantrypal;

public class RecipeData {
    Long id;
    String title;
    String instructions;
    String[] ingredients;
    String imageUrl;
    double createdTime;
    String type;

    public RecipeData(String gptResponse, String type) {
        String[] parts = gptResponse.split("\\|\\|\\|\\|\\|");

        if (parts.length > 1) {
            
            // Process the first part for recipe details
            String[] recipeDetails = parts[0].split("\n");
            this.title = recipeDetails[0].trim();
            this.instructions = joinSubsectionLoop(recipeDetails, 1, recipeDetails.length - 2);
            this.ingredients = recipeDetails[recipeDetails.length - 1].split(";");
            this.createdTime = System.currentTimeMillis();
            this.type = type;
            // Second part is the image URL
            this.imageUrl = parts[1].trim();
        }
        // this.title = response[0];
        // this.instructions = joinSubsectionLoop(response, 2, response.length - 1);
        // ingredients = response[response.length - 1].split(";");
        // this.createdTime = System.currentTimeMillis();
        // this.type = type;
    }

    /*
     * Overloaded constructor for database operations
     */
    public RecipeData(String title, String[] ingredients, String instructions, String type) {
        this.title = title;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.createdTime = System.currentTimeMillis();
        this.type = type;
    }

    public RecipeData(String title, String[] ingredients, String instructions) {
        this.title = title;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.createdTime = System.currentTimeMillis();
        this.type = "Breakfast";
    }

    public void updateRecipeData(String gptResponse) {
        String[] parts = gptResponse.split("\\|\\|\\|\\|\\|");

        if (parts.length > 1) {
            
            // Process the first part for recipe details
            String[] recipeDetails = parts[0].split("\n");
            this.title = recipeDetails[0].trim();
            this.instructions = joinSubsectionLoop(recipeDetails, 1, recipeDetails.length - 2);
            this.ingredients = recipeDetails[recipeDetails.length - 1].split(";");
            this.createdTime = System.currentTimeMillis();
            //this.type = type;
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

    public String toString() {
        return title + "\n" + instructions + "\n" + String.join(";", ingredients);
    }
}


package pantrypal;

public class MockGPT implements IChatGPT {

    public String generateRecipe(String transcription, String mealType) {
        String[] ingredients = {"Chicken", "Rice"};
        String title = "Chicken Fried Rice";
        String instructions = "cook it!";
        String toRet = title + "\n";
        toRet += mealType + "\n";
        for (int i = 0; i < ingredients.length; i++) {
            toRet += ingredients[i];
            if(i-1 < ingredients.length) {
                toRet += ";";
            }
        }
        toRet += "\n"+instructions ;

        return toRet;
        
    }


    public String generateMealType(String transcription) {
        return "Breakfast";
    }


    

    public String createRecipe(String[] ingredients, String mealType1) {
        return "Proper recipe here";
    }

    public String[] extractIngredients(String transcription) { // throws IOException, InterruptedException,
                                                               // URISyntaxException
        String[] output = { "Chicken", "Rice" };
        return output;
    }

    public String regenerateRecipe(String[] ingredients, String mealType, String originalContext) {
        String recipe = """
                Put everything in a pot and cook it!
                """;

        String toRet = recipe + "\n";
        for (int i = 0; i < ingredients.length; i++) {
            toRet += ingredients[i];
            if(i-1 < ingredients.length) {
                toRet += ";";
            }
        }

        return "new recipe";
    }

    public String recreateRecipe(String[] ingredients, String mealType1) {
        return "Proper new recipe here";
    }
}

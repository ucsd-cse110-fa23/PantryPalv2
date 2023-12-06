package pantrypal;

public class MockGPT implements IChatGPT {

    public String generateRecipe(String transcription, String mealType) {
        String[] ingredients = {"Chicken", "Rice", "Pasta", "Tomatoes"};
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
        return toRet;
    }


    public String generateMealType(String transcription) {
        return transcription;
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
        return toRet;
    }

}

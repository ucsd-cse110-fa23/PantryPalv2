package pantrypal;

public interface IChatGPT {
    public String generateRecipe(String transcription, String mealType);
    public String generateMealType(String transcription);
}

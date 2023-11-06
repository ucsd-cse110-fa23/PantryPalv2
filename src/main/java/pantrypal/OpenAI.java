package pantrypal;

import java.io.IOException;
import java.net.URISyntaxException;

public class OpenAI {
    public static String getRecipeFromAudio(String filePath) throws IOException, URISyntaxException {
        ChatGPT gpt = new ChatGPT();
        String transcript = Whisper.getTranscript(filePath);
        String recipe = gpt.generateRecipe(transcript);
        return recipe;
    }
}

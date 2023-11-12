package pantrypal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MockModel implements ILanguageModel {

    public String callModel(String prompt) {
        if (prompt.split(" ")[0].equals("Transcript:")) {
            return "{\"ingredients\": [\"Chicken\",\"Rice\"]}";

        } else if (prompt.substring(0, 31).equals("Given the following ingredients")) {
            return "Proper recipe here";
        } else if (prompt.substring(0, 33).equals("Given the following transcription")) {
            return "Proper meal type here";
        } else {
            return "Gibberish";
        }
    }

}

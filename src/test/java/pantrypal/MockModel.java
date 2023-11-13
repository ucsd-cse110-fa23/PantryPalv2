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

class MockModelMealType implements ILanguageModel {
    public boolean phraseInString(String phrase, String s) {
        return s.toLowerCase().contains(phrase.toLowerCase());
    }

    public String callModel(String prompt) {
        if (prompt.substring(0, 33).equals("Given the following transcription")) {
            String firstPart = prompt.substring(0, 70);
            if (phraseInString("breakfast", firstPart)) {
                return "Breakfast";
            } else if (phraseInString("lunch", firstPart)) {
                return "Lunch";
            } else if (phraseInString("dinner", firstPart)) {
                return "Dinner";
            } else {
                return "None";
            }
        }
        if (prompt.split(" ")[0].equals("Transcript:")) {
            return "{\"ingredients\": [\"Chicken\",\"Rice\"]}";
        } else if (prompt.substring(0, 31).equals("Given the following ingredients")) {
            String firstPart = prompt.substring(0, 70);
            if (phraseInString("breakfast", firstPart)) {
                return "Breakfast recipe here";
            } else if (phraseInString("lunch", firstPart)) {
                return "Lunch recipe here";
            } else if (phraseInString("dinner", firstPart)) {
                return "Dinner recipe here";
            } else {
                return "None";
            }
        } else if (prompt.substring(0, 33).equals("Given the following transcription")) {
            return "Proper meal type here";
        } else {
            return "Gibberish";
        }
    }
}

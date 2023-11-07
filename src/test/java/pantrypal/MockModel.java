package pantrypal;

public class MockModel implements ILanguageModel {

    public String callModel(String prompt) {
        if (prompt.split(" ")[0].equals("Transcript:")) {
            return "{\"ingredients\": [\"Chicken\",\"Rice\"]}";

        } else if (prompt.substring(0, 1).equals("G")) {
            return "Proper recipe here";
        } else {
            return "Gibberish";
        }

    }
}

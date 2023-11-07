package pantrypal;

public class MockModel implements ILanguageModel {

    public String callModel(String prompt) {
        return "{\"ingredients\": [\"Chicken\",\"Rice\"]}";
    }
}

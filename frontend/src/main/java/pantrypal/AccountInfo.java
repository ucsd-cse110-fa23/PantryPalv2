package pantrypal;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.geometry.Insets;

class AccountInfo extends HBox {

    private VBox fields;

    private TextField usernameField;
    private TextField passwordField;

    private TextField message;

    AccountInfo(String usernameString, String passwordString) {

        this.setPrefSize(500, 120);
        this.setStyle("-fx-background-color: #d5f2ec;");

        fields = new VBox();
        fields.setSpacing(5);
        fields.setPrefSize(200, 60);

        usernameField = new TextField();
        usernameField.setPrefSize(150, 20);
        usernameField.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");
        usernameField.setPadding(new Insets(5, 0, 5, 0));
        this.usernameField.setPromptText("Username");
        usernameField.setText(usernameString);
        fields.getChildren().add(usernameField);

        passwordField = new TextField();
        passwordField.setPrefSize(150, 20);
        passwordField.setStyle("-fx-background-color: #DAE5EA; -fx-border-width:0;");
        passwordField.setPadding(new Insets(5, 0, 5, 0));
        this.passwordField.setPromptText("Password");
        passwordField.setText(passwordString);

        fields.getChildren().add(passwordField);

        message = new TextField();
        // message.setPrefSize(400, 20);
        message.setStyle("-fx-background-color: #d9faf3; -fx-border-width:0;");
        message.setEditable(false);
        fields.getChildren().add(message);

        this.getChildren().add(fields);

        this.setAlignment(Pos.CENTER);
    }

    public String getUsernameField() {
        return this.usernameField.getText();
    }

    public String getPasswordField() {
        return this.passwordField.getText();
    }

    public void setMessage(String message) {
        this.message.setText(message);
    }

    public void setUsernameField(String username) {
        this.usernameField.setText(username);
    }

    public void setPasswordField(String password) {
        this.passwordField.setText(password);
    }

    AccountInfo() {
        this("Username", "Password");
    }

}
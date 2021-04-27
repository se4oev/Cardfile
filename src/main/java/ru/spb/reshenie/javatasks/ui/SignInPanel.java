package ru.spb.reshenie.javatasks.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.spb.reshenie.javatasks.db.DbConnector;

public class SignInPanel {

    private Stage signInStage;

    @FXML
    private Label labelConnect;
    @FXML
    private TextField userField;
    @FXML
    private PasswordField passwordField;
    @FXML
    public void initialize() {}

    @FXML
    private void handleConnect() {
        DbConnector dbConnector = DbConnector.getInstance();
        dbConnector.setDbUser(userField.getText());
        dbConnector.setDbPassword(passwordField.getText());
        if (dbConnector.getConnection() != null) {
            signInStage.close();
        } else {
            userField.setText("");
            passwordField.setText("");
            labelConnect.setText("Неправильный логин/пароль");
        }
    }

    public void setSignInPanelStage(Stage signInStage) {
        this.signInStage = signInStage;
    }
}

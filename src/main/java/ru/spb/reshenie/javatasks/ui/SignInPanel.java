package ru.spb.reshenie.javatasks.ui;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.spb.reshenie.javatasks.MainApp;
import ru.spb.reshenie.javatasks.db.DbConnector;

public class SignInPanel {

    private Stage signInStage;

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
        signInStage.close();
    }

    public void setSignInPanelStage(Stage signInStage) {
        this.signInStage = signInStage;
    }
}

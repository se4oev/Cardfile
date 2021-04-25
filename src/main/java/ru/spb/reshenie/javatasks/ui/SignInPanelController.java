package ru.spb.reshenie.javatasks.ui;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.spb.reshenie.javatasks.MainApp;

public class SignInPanelController {
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    private Stage signInStage;

    @FXML
    private TextField userField;
    @FXML
    private PasswordField passwordField;
    @FXML
    public void initialize() {}

    @FXML
    private void handleConnect() {
        mainApp.setDbUser(userField.getText());
        mainApp.setDbPassword(passwordField.getText());
        signInStage.close();
    }

    public void setSignInPanelStage(Stage signInStage) {
        this.signInStage = signInStage;
    }
}

package ru.spb.reshenie.javatasks.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.spb.reshenie.javatasks.MainApp;
import ru.spb.reshenie.javatasks.db.PatientDao;

public class SignInPanel {

    private Stage signInStage;
    private String dbURL;
    private MainApp mainApp;

    public void setDbURL(String dbURL) {
        this.dbURL = dbURL;
    }

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

        PatientDao patientDao = new PatientDao(dbURL, userField.getText(), passwordField.getText());

        if (patientDao.getConnection() != null) {
            mainApp.setPatientDao(patientDao);
            signInStage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(signInStage);
            alert.setTitle("Connection Error");
            alert.setHeaderText("Не удалось подключиться к БД");
            alert.setContentText("Проверьте правильность логина/пароля и убедитесь, " +
                    "что указанная база данных существует");
            alert.showAndWait();
            userField.setText("");
            passwordField.setText("");
            userField.requestFocus();
            labelConnect.setText("Неправильный логин/пароль");
        }
    }

    public void setSignInPanelStage(Stage signInStage) {
        this.signInStage = signInStage;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}

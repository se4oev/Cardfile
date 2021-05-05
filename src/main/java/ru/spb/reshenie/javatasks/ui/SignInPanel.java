package ru.spb.reshenie.javatasks.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.spb.reshenie.javatasks.MainApp;
import ru.spb.reshenie.javatasks.db.IBaseDao;
import ru.spb.reshenie.javatasks.db.PatientDao;
import ru.spb.reshenie.javatasks.db.PgDao;

public class SignInPanel {

    private final Logger logger = LoggerFactory.getLogger(getClass());
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

        IBaseDao pgDao = new PgDao(dbURL, userField.getText(), passwordField.getText());

        if (pgDao.getConnection() != null) {
            logger.info("Connection successful, closed signInStage");
            mainApp.setBaseDao(pgDao);
            signInStage.close();
        } else {
            logger.warn("Connection Error. Check if your username and pass are correct.");
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

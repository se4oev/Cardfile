package ru.spb.reshenie.javatasks;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.spb.reshenie.javatasks.db.PatientDao;
import ru.spb.reshenie.javatasks.ui.PatientOverview;
import ru.spb.reshenie.javatasks.ui.SignInPanel;
import ru.spb.reshenie.javatasks.utils.ImageUtil;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class MainApp extends Application {

    private Stage primaryStage;
    private static String dbURL;
    private PatientDao patientDao;

    public void setPatientDao(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            dbURL = args[0];
        } else if (args.length > 1) {
            dbURL = args[1];
        }
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Картотека");
        this.primaryStage.getIcons().add(ImageUtil.cardfileImage);

        showSignInPanel();

        showPatientOverview();
    }

    private void showSignInPanel() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("ui/SignInPanel.fxml"));
            AnchorPane pane = loader.load();

            Stage signInStage = new Stage();
            signInStage.setTitle("Авторизация");
            signInStage.initStyle(StageStyle.UNDECORATED);
            signInStage.initModality(Modality.WINDOW_MODAL);
            signInStage.initOwner(primaryStage);
            Scene scene = new Scene(pane);
            signInStage.setScene(scene);

            SignInPanel controller = loader.getController();
            controller.setSignInPanelStage(signInStage);
            controller.setDbURL(dbURL);
            controller.setMainApp(this);

            signInStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPatientOverview() {
        try {
            PatientOverview patientOverview = new PatientOverview(patientDao);
            patientOverview = loadMainPane(patientOverview);
            Scene scene = new Scene(patientOverview.getRootPane());
            primaryStage.setScene(scene);

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private PatientOverview loadMainPane(PatientOverview patientOverview) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        if (patientOverview != null)
            fxmlLoader.setControllerFactory(param -> patientOverview);
        String resourcePath = "/ru/spb/reshenie/javatasks/ui/PatientOverview.fxml";
        URL url = Objects.requireNonNull(PatientOverview.class.getResource(resourcePath), "Resource not found: " + resourcePath);
        fxmlLoader.setLocation(url);
        if(patientOverview == null || patientOverview.getRootPane() == null)
            throw new RuntimeException("Панель не найдена");
        patientOverview.getRootPane().getProperties().put(new Object(), patientOverview);
        return fxmlLoader.getController();
    }
}

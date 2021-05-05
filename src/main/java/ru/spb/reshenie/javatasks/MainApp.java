package ru.spb.reshenie.javatasks;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.spb.reshenie.javatasks.db.DbConnector;
import ru.spb.reshenie.javatasks.ui.PatientOverviewPanel;
import ru.spb.reshenie.javatasks.ui.SignInPanel;
import ru.spb.reshenie.javatasks.utils.ImageUtil;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private PatientOverviewPanel controller;
    private static String dbURL;

    public static void main(String[] args) {
        if (args.length == 1) {
            dbURL = args[0];
        } else if (args.length > 1) {
            dbURL = args[1];
        }
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
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
            AnchorPane pane = (AnchorPane) loader.load();

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

            signInStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPatientOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("ui/PatientOverview.fxml"));
            AnchorPane patientOverview = (AnchorPane) loader.load();
            Scene scene = new Scene(patientOverview);
            primaryStage.setScene(scene);

            controller = loader.getController();
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

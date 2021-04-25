package ru.spb.reshenie.javatasks;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.spb.reshenie.javatasks.db.DbConnector;
import ru.spb.reshenie.javatasks.entity.PatientDTO;
import ru.spb.reshenie.javatasks.ui.PatientOverviewController;
import ru.spb.reshenie.javatasks.ui.RootLayoutController;
import ru.spb.reshenie.javatasks.ui.SignInPanelController;
import ru.spb.reshenie.javatasks.utils.ImageUtil;

import java.io.IOException;
import java.sql.Connection;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private DbConnector dbConnector;
    private static String[] dbURL;
    private String dbUser;
    private String dbPassword;
    private Connection connection;
    private PatientOverviewController controller;

    public Connection getConnection() {
        return connection;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    private ObservableList<PatientDTO> patientData = FXCollections.observableArrayList();

//    public ObservableList<PatientDTO> getPatientData() {
//        return FXCollections.observableArrayList(MappingUtil.mapToPatientDTOList());
//    }

    public static void main(String[] args) {
        dbURL = args;
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Картотека");

        this.primaryStage.getIcons().add(ImageUtil.cardfileImage);

        showSignInPanel();

        dbConnector = new DbConnector(dbURL, dbUser, dbPassword);
        connection = dbConnector.getConnection();

        initRootLayout();

        showPatientOverview();
        loadPatientsFromDb();
    }

    private void loadPatientsFromDb() {
        controller.loadPatientsFromDb();
    }

    private void showSignInPanel() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("ui/SignInPanel.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();

            Stage signInStage = new Stage();
            signInStage.setTitle("Войдите");
            signInStage.initModality(Modality.WINDOW_MODAL);
            signInStage.initOwner(primaryStage);
            Scene scene = new Scene(pane);
            signInStage.setScene(scene);

            SignInPanelController controller = loader.getController();
            controller.setSignInPanelStage(signInStage);
            controller.setMainApp(this);

            signInStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("ui/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPatientOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("ui/PatientOverview.fxml"));
            AnchorPane patientOverview = (AnchorPane) loader.load();
            rootLayout.setCenter(patientOverview);

            controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

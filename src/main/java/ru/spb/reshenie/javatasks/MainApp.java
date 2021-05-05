package ru.spb.reshenie.javatasks;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.spb.reshenie.javatasks.db.IBaseDao;
import ru.spb.reshenie.javatasks.db.PatientDao;
import ru.spb.reshenie.javatasks.ui.PatientOverview;
import ru.spb.reshenie.javatasks.ui.SignInPanel;
import ru.spb.reshenie.javatasks.utils.ImageUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;

public class MainApp extends Application {
    private static final Logger logger = LoggerFactory.getLogger(MainApp.class);
    private Stage primaryStage;
    private static String dbURL;
    private IBaseDao baseDao;

    public void setBaseDao(IBaseDao baseDao) {
        this.baseDao = baseDao;
    }

    public static void main(String[] args) {
        logger.info("Application started...");
        if (args.length == 1) {
            dbURL = args[0];
            logger.info("Received argument: {}", args[0]);
        } else if (args.length > 1) {
            dbURL = args[1];
            logger.info("Received argument: {}", args[1]);
        }
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Launch start method");
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Картотека");
        this.primaryStage.getIcons().add(ImageUtil.cardfileImage);
        logger.info("Show sign in window");
        showSignInPanel();
        logger.info("Login successfull, show patient overview");
        showPatientOverview();
    }

    @Override
    public void stop() {
        try {
            logger.info("Trying to close connection");
            baseDao.getConnection().close();
            logger.info("Connection closed successful");
        } catch (SQLException e) {
            logger.error("Failed to close connection", e);
            throw new RuntimeException(e);
        }
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
            logger.error("Failed to start sign in panel", e);
            throw new RuntimeException(e);
        }
    }

    public void showPatientOverview() {
        try {
            PatientOverview patientOverview = new PatientOverview(new PatientDao(baseDao));
            patientOverview = loadMainPane(patientOverview);
            Scene scene = new Scene(patientOverview.getRootPane());
            String styleSheetPath = "/ru/spb/reshenie/javatasks/styles.css";
            scene.getStylesheets().addAll(MainApp.class.getResource(styleSheetPath).toExternalForm());
            primaryStage.setScene(scene);

            primaryStage.show();
        } catch (Exception e) {
            logger.error("Failed to start patient overview", e);
            throw new RuntimeException(e);
        }
    }

    private PatientOverview loadMainPane(PatientOverview patientOverview) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        if (patientOverview != null)
            fxmlLoader.setControllerFactory(param -> patientOverview);
        try {
            String resourcePath = "/ru/spb/reshenie/javatasks/ui/PatientOverview.fxml";
            URL url = Objects.requireNonNull(PatientOverview.class.getResource(resourcePath),
                "Resource not found: " + resourcePath);
            fxmlLoader.setLocation(url);
            fxmlLoader.load();
        } catch (IOException e) {
            logger.error("Failed to load PatientOverview panel", e);
            throw new RuntimeException(e);
        }
        if(patientOverview == null || patientOverview.getRootPane() == null) {
            logger.error("Panel not found");
            throw new RuntimeException("Panel not found");
        }
        patientOverview.getRootPane().getProperties().put(new Object(), patientOverview);
        return fxmlLoader.getController();
    }
}

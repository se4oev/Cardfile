package ru.spb.reshenie.javatasks;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.spb.reshenie.javatasks.entity.Patient;
import ru.spb.reshenie.javatasks.ui.PatientOverviewController;
import ru.spb.reshenie.javatasks.ui.RootLayoutController;
import ru.spb.reshenie.javatasks.utils.ImageUtil;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList<Patient> patientData = FXCollections.observableArrayList();

    public MainApp() {
        patientData.add(new Patient(123213, "124-551-533-11", "Мужской", "Иванов И.И.",
                "19.03.1982", "32 года", "Ресо - 323532", ImageUtil.cashImage));
        patientData.add(new Patient(124443, "456-789-123-10", "Женский", "Иванова З.П.",
                "17.11.1975", "31 год", "Альфа - 3341532", ImageUtil.cashImage));
        patientData.add(new Patient(123213, "124-551-533-11", "Мужской", "Иванов И.И.",
                "19.03.1982", "32 года", "Ресо - 323532", ImageUtil.cashImage));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Картотека");

        this.primaryStage.getIcons().add(ImageUtil.cardfileImage);

        initRootLayout();

        showPatientOverview();
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

            PatientOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

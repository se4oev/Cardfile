package ru.spb.reshenie.javatasks;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.spb.reshenie.javatasks.entity.PatientDto;
import ru.spb.reshenie.javatasks.ui.PatientOverviewController;
import ru.spb.reshenie.javatasks.ui.RootLayoutController;
import ru.spb.reshenie.javatasks.utils.ImageUtil;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList<PatientDto> patientData = FXCollections.observableArrayList();

    public ObservableList<PatientDto> getPatientData() {
        return patientData;
    }

    public MainApp() {
//        patientData.add(new Patient(123213, "Иванов Иван Иванович", "11.10.1992", 1,
//                5553535, "Ресо", "12455153311", "34241", 1));
//        patientData.add(new Patient(123213, "Петров Иван Иванович", "11.10.1992", 2,
//                5553535, "Ресо", "12455153311", "34241", 2));
//        patientData.add(new Patient(123213, "Сидоров Иван Иванович", "11.10.1992", 1,
//                5553535, "Ресо", "12455153311", "34241", 3));
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

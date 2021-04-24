package ru.spb.reshenie.javatasks.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import ru.spb.reshenie.javatasks.MainApp;
import ru.spb.reshenie.javatasks.entity.PatientDto;

public class PatientOverviewController {

    @FXML
    private TextField searchField;

    @FXML
    private TableView<PatientDto> patientTable;

    @FXML
    private TableColumn<PatientDto, Integer> cardNumberColumn;

    @FXML
    private TableColumn<PatientDto, String> snilsColumn;

    @FXML
    private TableColumn<PatientDto, String> sexColumn;

    @FXML
    private TableColumn<PatientDto, String> fullnameColumn;

    @FXML
    private TableColumn<PatientDto, String> birthdayColumn;

    @FXML
    private TableColumn<PatientDto, String> ageColumn;

    @FXML
    private TableColumn<PatientDto, String> policyColumn;

    @FXML
    private TableColumn<PatientDto, Image> finSourceColumn;

    private MainApp mainApp;

    @FXML
    public void initialize() {

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void handleSearch() {
        patientTable.setItems(mainApp.getPatientData());
    }

    @FXML
    public void handleClear() {
    }
}

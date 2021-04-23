package ru.spb.reshenie.javatasks.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import ru.spb.reshenie.javatasks.entity.Patient;

public class PatientOverviewController {

    @FXML
    private TextField searchField;

    @FXML
    private TableView<Patient> patientTable;

    @FXML
    private TableColumn<Patient, Integer> cardNumberColumn;

    @FXML
    private TableColumn<Patient, String> snilsColumn;

    @FXML
    private TableColumn<Patient, String> sexColumn;

    @FXML
    private TableColumn<Patient, String> fullnameColumn;

    @FXML
    private TableColumn<Patient, String> birthdayColumn;

    @FXML
    private TableColumn<Patient, String> ageColumn;

    @FXML
    private TableColumn<Patient, String> policyColumn;

    @FXML
    private TableColumn<Patient, Image> finSourceColumn;

    @FXML
    public void handleSearch() {
    }

    @FXML
    public void handleClear() {
    }
}

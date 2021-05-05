package ru.spb.reshenie.javatasks.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.spb.reshenie.javatasks.db.PatientDao;
import ru.spb.reshenie.javatasks.entity.Patient;

public class PatientOverview {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final PatientDao patientDao;

    private ObservableList<Patient> listOfPatients;

    private Patient selectedPatient;

    private int selectedIndex;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField searchField;

    @FXML
    private TableView<Patient> patientTable;

    @FXML
    private TableColumn<Patient, String> cardNumberColumn;

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
    private TableColumn<Patient, Integer> finSourceColumn;

    public PatientOverview(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    @FXML
    public void initialize() {

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.trim().isEmpty()) {
                patientTable.setItems(listOfPatients);
            }
        });

        patientTable.setRowFactory((param) -> new ColorRow());
        patientTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedPatient = patientTable.getSelectionModel().getSelectedItem();
            selectedIndex = patientTable.getSelectionModel().getSelectedIndex() > 0 ?
                    patientTable.getSelectionModel().getSelectedIndex() : -1;
        });

        cardNumberColumn.setCellValueFactory(cellData -> cellData.getValue().cardNumberProperty());
        cardNumberColumn.setStyle("-fx-alignment: CENTER");

        snilsColumn.setCellValueFactory(cellData -> cellData.getValue().snilsProperty());
        snilsColumn.setStyle("-fx-alignment: CENTER");

        sexColumn.setCellValueFactory(cellData -> cellData.getValue().sexProperty());
        sexColumn.setStyle("-fx-alignment: CENTER");

        fullnameColumn.setCellValueFactory(cellData -> cellData.getValue().fullnameProperty());
        fullnameColumn.setCellFactory(column -> new FullnameTableCell());

        birthdayColumn.setCellValueFactory(cellData -> cellData.getValue().birthdayProperty());
        birthdayColumn.setStyle("-fx-alignment: CENTER");

        ageColumn.setCellValueFactory(cellData -> cellData.getValue().ageProperty());
        ageColumn.setStyle("-fx-alignment: CENTER");

        policyColumn.setCellValueFactory(cellData -> cellData.getValue().policyProperty());

        finSourceColumn.setCellValueFactory(cellData -> cellData.getValue().finSourceProperty().asObject());
        finSourceColumn.setCellFactory(column -> new ImageTableCell());
        finSourceColumn.setStyle("-fx-alignment: CENTER");

        rootPane.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                handleClear();
            } else if (event.getCode() == KeyCode.ENTER) {
                handleSearch();
            }
        });

        loadPatientsFromDb();
    }

    public void loadPatientsFromDb() {
        listOfPatients = FXCollections.observableArrayList(patientDao.getAll());
        setPatients(listOfPatients);
    }

    private void setPatients(ObservableList<Patient> listOfPatients) {
        patientTable.setItems(listOfPatients);
        if (selectedPatient != null) {
            if (patientTable.getItems().contains(selectedPatient)) {
                patientTable.getSelectionModel().select(selectedPatient);
            } else {
                if (patientTable.getItems().size() >= selectedIndex) {
                    patientTable.getSelectionModel().select(selectedIndex);
                } else {
                    patientTable.getSelectionModel().select(0);
                }
            }
        } else {
            if (patientTable.getItems() != null && patientTable.getItems().size() > 0) {
                patientTable.getSelectionModel().select(0);
            }
        }
    }

    @FXML
    public void handleRefresh() {
        loadPatientsFromDb();
        handleSearch();
    }

    @FXML
    public void handleSearch() {
        if (searchField.getText().length() > 0) {
            String[] searchQuery = searchField.getText().split(" ");
            FilteredList<Patient> filteredData = new FilteredList<>(listOfPatients, p -> true);
            filteredData.setPredicate(patient -> {
                if (searchQuery.length == 0) {
                    return true;
                }

                for (String s : searchQuery) {
                    String lowerCaseFilter = s.toLowerCase();
                    if (patient.getFullname().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (patient.getPolicy().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (patient.getCardNumber().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (patient.getSnils().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (patient.getSex().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (patient.getBirthday().contains(lowerCaseFilter)) {
                        return true;
                    } else if (patient.getAge().contains(lowerCaseFilter)) {
                        return true;
                    }
                }

                return false;
            });

            setPatients(filteredData);
        }
    }

    @FXML
    public void handleClear() {
        if (searchField.getText().length() > 0) {
            searchField.clear();
            setPatients(listOfPatients);
        }
    }

    public AnchorPane getRootPane() {
        return rootPane;
    }
}

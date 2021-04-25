package ru.spb.reshenie.javatasks.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import ru.spb.reshenie.javatasks.MainApp;
import ru.spb.reshenie.javatasks.db.PatientDao;
import ru.spb.reshenie.javatasks.entity.PatientDTO;
import ru.spb.reshenie.javatasks.utils.MappingUtil;

import java.util.Locale;

public class PatientOverviewController {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField searchField;

    @FXML
    private TableView<PatientDTO> patientTable;

    @FXML
    private TableColumn<PatientDTO, String> cardNumberColumn;

    @FXML
    private TableColumn<PatientDTO, String> snilsColumn;

    @FXML
    private TableColumn<PatientDTO, String> sexColumn;

    @FXML
    private TableColumn<PatientDTO, String> fullnameColumn;

    @FXML
    private TableColumn<PatientDTO, String> birthdayColumn;

    @FXML
    private TableColumn<PatientDTO, String> ageColumn;

    @FXML
    private TableColumn<PatientDTO, String> policyColumn;

    @FXML
    private TableColumn<PatientDTO, ImageView> finSourceColumn;

    private MainApp mainApp;

    private ObservableList<PatientDTO> listOfPatients;

    public PatientOverviewController() {

    }

    @FXML
    public void initialize() {
        cardNumberColumn.setCellValueFactory(cellData -> cellData.getValue().cardNumberProperty());
        snilsColumn.setCellValueFactory(cellData -> cellData.getValue().snilsProperty());
        sexColumn.setCellValueFactory(cellData -> cellData.getValue().sexProperty());
        fullnameColumn.setCellValueFactory(cellData -> cellData.getValue().fullnameProperty());
        birthdayColumn.setCellValueFactory(cellData -> cellData.getValue().birthdayProperty());
        ageColumn.setCellValueFactory(cellData -> cellData.getValue().ageProperty());
        policyColumn.setCellValueFactory(cellData -> cellData.getValue().policyProperty());
        finSourceColumn.setCellValueFactory(cellData -> cellData.getValue().finSourceProperty());
        finSourceColumn.setStyle("-fx-alignment: CENTER");

        rootPane.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ESCAPE) {
                    handleClear();
                } else if (event.getCode() == KeyCode.ENTER) {
                    handleSearch();
                }
            }
        });
    }

    public void loadPatientsFromDb() {
        PatientDao patientDao = new PatientDao(mainApp.getConnection());
        listOfPatients = FXCollections.observableArrayList(MappingUtil.mapToPatientDTOList(patientDao.getAll()));
        patientTable.setItems(listOfPatients);
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void handleSearch() {
        String[] searchQuery = searchField.getText().split(" ");
        FilteredList<PatientDTO> filteredData = new FilteredList<>(listOfPatients, p -> true);
        filteredData.setPredicate(patient -> {
            if (searchQuery == null || searchQuery.length == 0) {
                return true;
            }

            for (String s : searchQuery) {
                String lowerCaseFilter = s.toLowerCase();
                if (patient.getFullname().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (patient.getPolicy().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
            }

            return false;
        });

        patientTable.setItems(filteredData);
    }

    @FXML
    public void handleClear() {
        searchField.clear();
        patientTable.setItems(listOfPatients);
    }
}

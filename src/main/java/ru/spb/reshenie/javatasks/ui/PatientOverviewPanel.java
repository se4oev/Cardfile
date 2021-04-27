package ru.spb.reshenie.javatasks.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import ru.spb.reshenie.javatasks.db.DbConnector;
import ru.spb.reshenie.javatasks.db.PatientDao;
import ru.spb.reshenie.javatasks.entity.PatientDTO;
import ru.spb.reshenie.javatasks.utils.MappingUtil;


public class PatientOverviewPanel {

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
    private TableColumn<PatientDTO, Integer> finSourceColumn;

    private ObservableList<PatientDTO> listOfPatients;

    public PatientOverviewPanel() {

    }

    @FXML
    public void initialize() {

        patientTable.setRowFactory((param) -> new ColorRow());

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

        PatientDao patientDao = new PatientDao(DbConnector.getInstance().getConnection());
        listOfPatients = FXCollections.observableArrayList(MappingUtil.mapToPatientDTOList(patientDao.getAll()));
        patientTable.setItems(listOfPatients);

    }

    @FXML
    public void handleRefresh() {
        loadPatientsFromDb();
        handleSearch();
    }

    @FXML
    public void handleSearch() {
        String[] searchQuery = searchField.getText().split(" ");
        FilteredList<PatientDTO> filteredData = new FilteredList<>(listOfPatients, p -> true);
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

        patientTable.setItems(filteredData);
    }

    @FXML
    public void handleClear() {
        searchField.clear();
        patientTable.setItems(listOfPatients);
    }
}

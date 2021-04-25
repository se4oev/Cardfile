package ru.spb.reshenie.javatasks.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ru.spb.reshenie.javatasks.MainApp;
import ru.spb.reshenie.javatasks.db.PatientDao;
import ru.spb.reshenie.javatasks.entity.PatientDTO;
import ru.spb.reshenie.javatasks.utils.MappingUtil;

public class PatientOverviewController {

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
    }

    public void loadPatientsFromDb() {
        PatientDao patientDao = new PatientDao(mainApp.getConnection());

        patientTable.setItems(FXCollections.observableArrayList(MappingUtil.mapToPatientDTOList(patientDao.getAll())));
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void handleSearch() {
        //patientTable.setItems(mainApp.getPatientData());
    }

    @FXML
    public void handleClear() {
    }
}

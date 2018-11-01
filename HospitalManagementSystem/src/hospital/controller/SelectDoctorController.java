/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTextField;
import hospital.dto.DoctorDTO;
import hospital.dto.SpecializationDTO;
import hospital.observer.Observer;
import hospital.observer.Subject;
import hospital.proxy.ProxyHandler;
import hospital.resource.AdminAddDoctorUtil;
import hospital.resource.DoctorPaneDataDTO;
import hospital.service.ServiceFactory;
import hospital.service.custom.DoctorService;
import java.io.IOException;
import java.net.URL;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Kasun
 */
public class SelectDoctorController implements Initializable, Observer {

    @FXML
    private TableView<DoctorDTO> tblView;

    @FXML
    private TableColumn<DoctorDTO, String> tblColumnSection;
    @FXML
    private TableColumn<DoctorDTO, String> tblColumnFirstName;
    @FXML
    private TableColumn<DoctorDTO, String> tblColumnLastName;
    @FXML
    private TableColumn<DoctorDTO, String> tblColumnSpecialization;
    @FXML
    private TableColumn<DoctorDTO, String> tblColumnslmc;
    @FXML
    private TableColumn<DoctorDTO, String> tblColumnVisitingFee;
    @FXML
    private TableColumn<DoctorDTO, String> tblColumnAddress;
    @FXML
    private TableColumn<DoctorDTO, String> tblColumnGender;
    @FXML
    private TableColumn<DoctorDTO, String> tblColumnMobileNumber;
    @FXML
    private TableColumn<DoctorDTO, String> tblColumnPhoneNumber;
    @FXML
    private TableColumn<DoctorDTO, String> tblColumnEmail;

    @FXML
    private JFXComboBox<String> cmboFilter;
    @FXML
    private JFXTextField txtSearchByName;
    @FXML
    private JFXComboBox<String> cmboFilterValues;
    @FXML
    private JFXButton btnSelect;

    private DoctorService doctorService;
    private DoctorPaneDataDTO doctor;
    private ObservableList<DoctorDTO> tblData;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            UnicastRemoteObject.exportObject(this, 0);
            doctorService = (DoctorService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.DOCTOR);
            ((Subject) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.DOCTOR)).registerObserver(this);
        } catch (IOException ex) {
            Logger.getLogger(AdminDoctorsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AdminDoctorsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        txtSearchByName.setVisible(false);
        cmboFilterValues.setVisible(false);
        cmboFilter.getItems().add("Section");
        cmboFilter.getItems().add("Name");
        cmboFilter.getItems().add("Specialization");
        cmboFilter.getItems().add("SLMC NO");
        cmboFilter.getItems().add("Day");

        tblColumnSection.setCellValueFactory(new PropertyValueFactory<>("section"));
        tblColumnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tblColumnLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tblColumnSpecialization.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSpecialization().getSpecialityName()));//specialization
        tblColumnslmc.setCellValueFactory(new PropertyValueFactory<>("slmcRegNo"));
        tblColumnVisitingFee.setCellValueFactory(new PropertyValueFactory<>("visitingFee"));
        tblColumnAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tblColumnGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        tblColumnMobileNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNo"));
        tblColumnPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        tblColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        tblData = FXCollections.observableArrayList();
        tblView.setItems(tblData);

        cmboFilter.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                txtSearchByName.clear();
                cmboFilter.getSelectionModel().clearSelection();
                if (cmboFilter.getValue().equalsIgnoreCase("Name")) {
                    searchByName();
                } else if (cmboFilter.getValue().equalsIgnoreCase("Section")) {
                    searchBySection();

                } else if (cmboFilter.getValue().equalsIgnoreCase("Specialization")) {
                    searchBySpecialization();
                } else if (cmboFilter.getValue().equalsIgnoreCase("SLMC NO")) {
                    searchBySlmc();
                } else if (cmboFilter.getValue().equalsIgnoreCase("Day")) {
                    searchByDay();
                }
            }

            FilteredList<DoctorDTO> filteredList = new FilteredList<>(tblData, e -> true);

            private void searchByName() {
                txtSearchByName.setVisible(true);
                cmboFilterValues.setVisible(false);
                txtSearchByName.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    filteredList.setPredicate((Predicate<? super DoctorDTO>) doctor -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        } else if (doctor.getFirstName().toLowerCase().contains(newValue.toLowerCase())) {
                            return true;
                        } else if (doctor.getMiddleName().toLowerCase().contains(newValue.toLowerCase())) {
                            return true;
                        } else if (doctor.getLastName().toLowerCase().contains(newValue.toLowerCase())) {
                            return true;
                        } else if ((doctor.getFirstName().toLowerCase() + " " + doctor.getMiddleName().toLowerCase() + " " + doctor.getLastName().toLowerCase()).contains(newValue.toLowerCase())) {
                            return true;
                        }
                        return false;
                    });
                });
                SortedList<DoctorDTO> sortedList = new SortedList<>(filteredList);
                sortedList.comparatorProperty().bind(tblView.comparatorProperty());
                tblView.setItems(sortedList);
            }

            private void searchBySection() {
                txtSearchByName.setVisible(false);
                cmboFilterValues.setVisible(true);
                cmboFilterValues.getItems().clear();
                cmboFilterValues.getItems().add("OPD");
                cmboFilterValues.getItems().add("VISITING");

                cmboFilterValues.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    filteredList.setPredicate((Predicate<? super DoctorDTO>) doctor -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        } else if (doctor.getSection().toLowerCase().contains(newValue.toLowerCase())) {
                            return true;
                        }
                        return false;
                    });
                });
                SortedList<DoctorDTO> sortedList = new SortedList<>(filteredList);
                sortedList.comparatorProperty().bind(tblView.comparatorProperty());
                tblView.setItems(sortedList);

            }

            private void searchBySlmc() {
                txtSearchByName.setVisible(true);
                cmboFilterValues.setVisible(false);

                txtSearchByName.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    filteredList.setPredicate((Predicate<? super DoctorDTO>) doctor -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        } else if (doctor.getSlmcRegNo().toLowerCase().contains(newValue.toLowerCase())) {
                            return true;
                        }
                        return false;
                    });
                });
                SortedList<DoctorDTO> sortedList = new SortedList<>(filteredList);
                sortedList.comparatorProperty().bind(tblView.comparatorProperty());
                tblView.setItems(sortedList);
            }

            private void searchByDay() {
                txtSearchByName.setVisible(false);
                cmboFilterValues.setVisible(true);
                cmboFilterValues.getItems().clear();
                cmboFilterValues.getItems().add("Monday");
                cmboFilterValues.getItems().add("Tuesday");
                cmboFilterValues.getItems().add("Wednesday");
                cmboFilterValues.getItems().add("Thursday");
                cmboFilterValues.getItems().add("Friday");
                cmboFilterValues.getItems().add("Saturday");
                cmboFilterValues.getItems().add("Sunday");

                cmboFilterValues.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    filteredList.setPredicate((Predicate<? super DoctorDTO>) doctor -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        } else if ((doctor.getDoctorAvailableDates().isMonday() ? "Monday" : "").toLowerCase().contains(newValue.toLowerCase())) {
                            return true;
                        } else if ((doctor.getDoctorAvailableDates().isTuesday() ? "Tuesday" : "").toLowerCase().contains(newValue.toLowerCase())) {
                            return true;
                        } else if ((doctor.getDoctorAvailableDates().isWednesday() ? "Wednesday" : "").toLowerCase().contains(newValue.toLowerCase())) {
                            return true;
                        } else if ((doctor.getDoctorAvailableDates().isThursday() ? "Thursday" : "").toLowerCase().contains(newValue.toLowerCase())) {
                            return true;
                        } else if ((doctor.getDoctorAvailableDates().isFriday() ? "Friday" : "").toLowerCase().contains(newValue.toLowerCase())) {
                            return true;
                        } else if ((doctor.getDoctorAvailableDates().isSaturday() ? "Saturday" : "").toLowerCase().contains(newValue.toLowerCase())) {
                            return true;
                        } else if ((doctor.getDoctorAvailableDates().isMonday() ? "Sunday" : "").toLowerCase().contains(newValue.toLowerCase())) {
                            return true;
                        }
                        return false;
                    });
                });
                SortedList<DoctorDTO> sortedList = new SortedList<>(filteredList);
                sortedList.comparatorProperty().bind(tblView.comparatorProperty());
                tblView.setItems(sortedList);

            }

            private void searchBySpecialization() {
                txtSearchByName.setVisible(false);
                cmboFilterValues.setVisible(true);
                cmboFilterValues.getItems().clear();
                try {
                    HashSet<SpecializationDTO> specList = new HashSet<>();
                    for (DoctorDTO doc : doctorService.getAllDoctors()) {
                        specList.add(doc.getSpecialization());
                    }
                    for (SpecializationDTO spec : specList) {
                        cmboFilterValues.getItems().add(spec.getSpecialityName());
                    }

                } catch (Exception ex) {
                    Logger.getLogger(AdminDoctorsController.class.getName()).log(Level.SEVERE, null, ex);
                }

                cmboFilterValues.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    filteredList.setPredicate((Predicate<? super DoctorDTO>) doctor -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        } else if (doctor.getSpecialization().getSpecialityName().toLowerCase().contains(newValue.toLowerCase())) {
                            return true;
                        }
                        return false;
                    });
                });
                SortedList<DoctorDTO> sortedList = new SortedList<>(filteredList);
                sortedList.comparatorProperty().bind(tblView.comparatorProperty());
                tblView.setItems(sortedList);
            }
        }
        );

        try {
            loadAllDoctors();
        } catch (Exception ex) {
            Logger.getLogger(AdminDoctorsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnSelectOnAction(ActionEvent event) {
        DoctorDTO doctorDTO = tblView.getSelectionModel().getSelectedItem();

        Window owner = ((Stage) ((Node) event.getSource()).getScene().getWindow()).getOwner();
        ((JFXTextField) owner.getScene().lookup("#txtDocName")).setText("Dr. " + doctorDTO.getFirstName() + " " + doctorDTO.getLastName());
        ((JFXTextField) owner.getScene().lookup("#txtSpeciality")).setText(doctorDTO.getSpecialization().getSpecialityName());
        ((JFXTextField) owner.getScene().lookup("#txtSLMC")).setText(doctorDTO.getSlmcRegNo());
        ((JFXTextField) owner.getScene().lookup("#txtSection")).setText(doctorDTO.getSection());

        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    private void loadAllDoctors() throws Exception {
        tblData.clear();
        List<DoctorDTO> allDoctors = doctorService.getAllDoctors();
        for (DoctorDTO doctorDTO : allDoctors) {
            tblData.add(doctorDTO);
        }

    }

    @Override
    public void updateObservers() throws Exception {
        loadAllDoctors();
    }
}

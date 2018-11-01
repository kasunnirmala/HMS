/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import hospital.dto.DoctorAvailableTimeDTO;
import hospital.dto.DoctorDTO;
import hospital.dto.SpecializationDTO;
import hospital.observer.Observer;
import hospital.observer.Subject;
import hospital.proxy.ProxyHandler;
import hospital.resource.DoctorPaneDataDTO;
import hospital.resource.AdminAddDoctorUtil;
import hospital.resource.CustomMethod;
import hospital.service.ServiceFactory;
import hospital.service.custom.DoctorService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Kasun
 */
public class AdminDoctorsController implements Initializable, Observer {

    @FXML
    private JFXDrawer drawerAddDoctor;
    @FXML
    private TableView<DoctorDTO> tblView;
    @FXML
    private JFXComboBox<String> cmboFilter;
    @FXML
    private JFXTextField txtSearchByName;
    @FXML
    private JFXComboBox<String> cmboFilterValues;

    private VBox vBox;
    private DoctorPaneDataDTO doctor;
    private ObservableList<DoctorDTO> tblData;
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

    private DoctorService doctorService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            UnicastRemoteObject.exportObject(this, 0);
            doctorService = (DoctorService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.DOCTOR);
            ((Subject) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.DOCTOR)).registerObserver(this);
            vBox = FXMLLoader.load(getClass().getResource("/hospital/view/AdminAddDoctor.fxml"));
            drawerAddDoctor.setSidePane(vBox);
        } catch (IOException ex) {
            Logger.getLogger(AdminDoctorsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AdminDoctorsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        doctor = AdminAddDoctorUtil.getAdminAddDoctorUtil(vBox).getAddDoctor();
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
    private void addDoctorOnMouseClicked(MouseEvent event) {
        if (drawerAddDoctor.isShown()) {
            drawerAddDoctor.close();
        } else {
            drawerAddDoctor.open();
            drawerAddDoctor.toFront();
        }
        doctor.getLblTitle().setText("ADD DOCTOR");
        doctor.getBtnAction().setText("ADD DOCTOR");
        clearAll();
        enableAll();
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

    @FXML
    private void viewHandlerOnAction(ActionEvent event) throws Exception {
        DoctorDTO selectedItem = tblView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {

            drawerAddDoctor.open();
            drawerAddDoctor.toFront();

            doctor.getLblTitle().setText("VIEW DOCTOR - Dr. " + selectedItem.getFirstName() + " " + selectedItem.getLastName());
            doctor.getBtnAction().setVisible(false);

            setValuesFromTable(selectedItem);
            dissableAll();

        } else {
            CustomMethod.errorNotification("No Item Selected To View...!");
        }
    }

    @FXML
    private void deleteHandlerOnAction(ActionEvent event) throws Exception {
        DoctorDTO selectedItem = tblView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            if (doctorService.deleteDoctor(selectedItem.getDoctorID())) {
                CustomMethod.successNotification("Successfully Deleted...!");
            } else {
                CustomMethod.errorNotification("Error in Deleting...!");
            }
        } else {
            CustomMethod.errorNotification("No Item Selected To Delete...!");
        }
    }

    private void dissableAll() {
        doctor.getAllCheckBox().forEach((checkBox) -> {
            checkBox.setDisable(true);
        });

        for (JFXComboBox comboBox : doctor.getAllComboBOX()) {
            comboBox.setEditable(false);
        }

        for (JFXDatePicker datePicker : doctor.getAllDatePicker()) {
            datePicker.setEditable(false);
        }

        for (ImageView imageView : doctor.getAllImageView()) {
            imageView.setDisable(true);
        }
        for (JFXRadioButton radio : doctor.getAllRadioButtons()) {
            radio.setDisable(true);
        }
        for (JFXTextField textFields : doctor.getAllTextFiels()) {
            textFields.setEditable(false);
        }

        for (JFXTimePicker timePicker : doctor.getAllTimePicker()) {
            timePicker.setEditable(false);
        }
        doctor.getBtnOpenImage().setDisable(true);
    }

    private void enableAll() {
        doctor.getAllCheckBox().forEach((checkBox) -> {
            checkBox.setDisable(false);
        });

        for (JFXComboBox comboBox : doctor.getAllComboBOX()) {
            comboBox.setEditable(true);
        }

        for (JFXDatePicker datePicker : doctor.getAllDatePicker()) {
            datePicker.setEditable(true);
        }

        for (ImageView imageView : doctor.getAllImageView()) {
            imageView.setDisable(false);
        }
        for (JFXRadioButton radio : doctor.getAllRadioButtons()) {
            radio.setDisable(false);
        }
        for (JFXTextField textFields : doctor.getAllTextFiels()) {
            textFields.setEditable(true);
        }

        for (JFXTimePicker timePicker : doctor.getAllTimePicker()) {
            timePicker.setEditable(true);
        }
        doctor.getBtnOpenImage().setDisable(false);
    }

    private void clearAll() {
        for (CheckBox checkBox : doctor.getAllCheckBox()) {
            checkBox.setSelected(false);
        }

        for (JFXComboBox comboBox : doctor.getAllComboBOX()) {
            comboBox.setValue(null);
        }

        for (JFXDatePicker datePicker : doctor.getAllDatePicker()) {
            datePicker.getEditor().clear();
        }

        for (ImageView imageView : doctor.getAllImageView()) {
            imageView.setImage(null);
        }
        for (JFXRadioButton radio : doctor.getAllRadioButtons()) {
            radio.setSelected(false);
        }
        for (JFXTextField textFields : doctor.getAllTextFiels()) {
            textFields.clear();
        }

        for (JFXTimePicker timePicker : doctor.getAllTimePicker()) {
            timePicker.getEditor().clear();
        }
    }

    private void setValuesFromTable(DoctorDTO selectedItem) {
        FileOutputStream outputStream = null;

        doctor.getCmbSection().setValue(selectedItem.getSection());
        doctor.getTxtFirstName().setText(selectedItem.getFirstName());
        doctor.getTxtMiddleName().setText(selectedItem.getMiddleName());
        doctor.getTxtLastName().setText(selectedItem.getLastName());
        doctor.getTxtAddress().setText(selectedItem.getAddress());
        if (selectedItem.getGender().equalsIgnoreCase("male")) {
            doctor.getRadioMale().setSelected(true);
        } else {
            doctor.getRadioFemale().setSelected(true);
        }
        doctor.getTxtDOB().getEditor().setText(selectedItem.getDob());
        doctor.getCmboSpecialization().getSelectionModel().select(selectedItem.getSpecialization().getSpecialityName());
        doctor.getTxtSLMCNo().setText(selectedItem.getSlmcRegNo());
        doctor.getTxtMobileNo().setText(selectedItem.getMobileNo());
        doctor.getTxtPhoneNumber().setText(selectedItem.getPhoneNo());
        doctor.getTxtEmail().setText(selectedItem.getEmail());
        if (selectedItem.getDoctorAvailableDates().isMonday()) {
            DoctorAvailableTimeDTO mondayTime = selectedItem.getDoctorAvailableDates().getMondayTime();
            doctor.getCheckMonday().setSelected(true);
            doctor.getStartTimeMonday().getEditor().setText(mondayTime.getStartTime());
            doctor.getEndTimeMonday().getEditor().setText(mondayTime.getEndTime());
        }
        if (selectedItem.getDoctorAvailableDates().isTuesday()) {
            DoctorAvailableTimeDTO tuesdayTime = selectedItem.getDoctorAvailableDates().getTuesdayTime();
            doctor.getCheckTuesday().setSelected(true);
            doctor.getStartTimeTuesday().getEditor().setText(tuesdayTime.getStartTime());
            doctor.getEndTimeTuesday().getEditor().setText(tuesdayTime.getEndTime());
        }
        if (selectedItem.getDoctorAvailableDates().isWednesday()) {
            DoctorAvailableTimeDTO wednesdayTime = selectedItem.getDoctorAvailableDates().getWednesdayTime();
            doctor.getCheckWednesday().setSelected(true);
            doctor.getStartTimeWednesday().getEditor().setText(wednesdayTime.getStartTime());
            doctor.getEndTimeWednesday().getEditor().setText(wednesdayTime.getEndTime());
        }
        if (selectedItem.getDoctorAvailableDates().isThursday()) {
            DoctorAvailableTimeDTO thursdayTime = selectedItem.getDoctorAvailableDates().getThursdayTime();
            doctor.getCheckThursday().setSelected(true);
            doctor.getStartTimeThursday().getEditor().setText(thursdayTime.getStartTime());
            doctor.getEndTimeThursday().getEditor().setText(thursdayTime.getEndTime());
        }
        if (selectedItem.getDoctorAvailableDates().isFriday()) {
            DoctorAvailableTimeDTO fridayTime = selectedItem.getDoctorAvailableDates().getFridayTime();
            doctor.getCheckFriday().setSelected(true);
            doctor.getStartTimeFriday().getEditor().setText(fridayTime.getStartTime());
            doctor.getEndTimeFriday().getEditor().setText(fridayTime.getEndTime());
        }
        if (selectedItem.getDoctorAvailableDates().isSaturday()) {
            DoctorAvailableTimeDTO saturdayTime = selectedItem.getDoctorAvailableDates().getSaturdayTime();
            doctor.getCheckSaturaday().setSelected(true);
            doctor.getStartTimeSaturday().getEditor().setText(saturdayTime.getStartTime());
            doctor.getEndTimeSaturday().getEditor().setText(saturdayTime.getEndTime());
        }
        if (selectedItem.getDoctorAvailableDates().isSunday()) {
            DoctorAvailableTimeDTO sundayTime = selectedItem.getDoctorAvailableDates().getSundayTime();
            doctor.getCheckSunday().setSelected(true);
            doctor.getStartTimeSunday().getEditor().setText(sundayTime.getStartTime());
            doctor.getEndTimeSunday().getEditor().setText(sundayTime.getEndTime());
        }

        try {
            byte[] imageInByte = selectedItem.getDocImage();
            File file = new File("image.jpg");
            outputStream = new FileOutputStream(file);
            outputStream.write(imageInByte);
            Image image = new Image(file.toURI().toString(), 290, 290, true, true);
            doctor.getImgView().setImage(image);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AdminDoctorsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AdminDoctorsController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                outputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(AdminDoctorsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void updateHandlerOnAction(ActionEvent event) throws Exception {
        DoctorDTO selectedItem = tblView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {

            drawerAddDoctor.open();
            drawerAddDoctor.toFront();

            doctor.getLblTitle().setText("UPDATE DOCTOR - Dr. " + selectedItem.getFirstName() + " " + selectedItem.getLastName());
            doctor.getBtnAction().setText("UPDATE");

            setValuesFromTable(selectedItem);
            enableAll();

        } else {
            CustomMethod.errorNotification("No Item Selected To Update...!");
        }

    }

}

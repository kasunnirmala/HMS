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
import hospital.dto.DoctorAvailableDatesDTO;
import hospital.dto.DoctorAvailableTimeDTO;
import hospital.dto.DoctorDTO;
import hospital.dto.SpecializationDTO;
import hospital.observer.Observer;
import hospital.observer.Subject;
import hospital.proxy.ProxyHandler;
import hospital.resource.AdminAddDoctorUtil;
import hospital.resource.CustomMethod;
import hospital.resource.DoctorPaneDataDTO;
import hospital.service.ServiceFactory;
import hospital.service.SuperService;
import hospital.service.custom.DoctorService;
import hospital.service.custom.SpecializationService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.rmi.Remote;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Kasun
 */
public class AdminAddDoctorController implements Initializable, Observer {

    @FXML
    private JFXButton btnCloseAdminAddDoctor;
    @FXML
    private Label lblTitle;
    @FXML
    private ImageView imgView;
    @FXML
    private JFXTextField txtFirstName;
    @FXML
    private JFXTextField txtMiddleName;
    @FXML
    private JFXTextField txtLastName;
    @FXML
    private JFXRadioButton radioMale;
    @FXML
    private JFXRadioButton radioFemale;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXDatePicker txtDOB;
    @FXML
    private JFXTextField txtMobileNo;
    @FXML
    private JFXTextField txtPhoneNumber;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXButton btnOpenImage;
    @FXML
    private JFXButton btnAction;
    @FXML
    private JFXComboBox<String> cmboSpecialization;
    @FXML
    private JFXTextField txtSLMCNo;
    @FXML
    private JFXTextField txtVisitingFee;
    @FXML
    private CheckBox checkMonday;
    @FXML
    private JFXTimePicker startTimeMonday;
    @FXML
    private JFXTimePicker endTimeMonday;
    @FXML
    private CheckBox checkTuesday;
    @FXML
    private JFXTimePicker startTimeTuesday;
    @FXML
    private JFXTimePicker endTimeTuesday;
    @FXML
    private CheckBox checkWednesday;
    @FXML
    private JFXTimePicker startTimeWednesday;
    @FXML
    private JFXTimePicker endTimeWednesday;
    @FXML
    private CheckBox checkThursday;
    @FXML
    private JFXTimePicker startTimeThursday;
    @FXML
    private JFXTimePicker endTimeThursday;
    @FXML
    private CheckBox checkFriday;
    @FXML
    private JFXTimePicker startTimeFriday;
    @FXML
    private JFXTimePicker endTimeFriday;
    @FXML
    private CheckBox checkSaturaday;
    @FXML
    private JFXTimePicker startTimeSaturday;
    @FXML
    private JFXTimePicker endTimeSaturday;
    @FXML
    private CheckBox checkSunday;
    @FXML
    private JFXTimePicker startTimeSunday;
    @FXML
    private JFXTimePicker endTimeSunday;
    @FXML
    private JFXComboBox<String> cmbSection;

    private SpecializationService specializationService;
    private DoctorService doctorService;
    private final ToggleGroup radioBtnGroup = new ToggleGroup();
    private Image image;
    private FileChooser fileChooser;
    private File file;
    //  private DoctorPaneDataDTO doctor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            UnicastRemoteObject.exportObject(this, 0);
            Subject addDoctorSubject = (Subject) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.SPECIALIZATION);
            addDoctorSubject.registerObserver(this);

            cmbSection.getItems().add("VISITING");
            cmbSection.getItems().add("OPD");
            specializationService = (SpecializationService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.SPECIALIZATION);
            doctorService = (DoctorService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.DOCTOR);

            loadSpecializationCombos();
            radioFemale.setUserData("Female");
            radioMale.setUserData("Male");

            radioFemale.setToggleGroup(radioBtnGroup);
            radioMale.setToggleGroup(radioBtnGroup);

            fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
            );

            startTimeMonday.disableProperty().bind(Bindings.not(checkMonday.selectedProperty()));
            endTimeMonday.disableProperty().bind(Bindings.not(checkMonday.selectedProperty()));

            startTimeTuesday.disableProperty().bind(Bindings.not(checkTuesday.selectedProperty()));
            endTimeTuesday.disableProperty().bind(Bindings.not(checkTuesday.selectedProperty()));

            startTimeWednesday.disableProperty().bind(Bindings.not(checkWednesday.selectedProperty()));
            endTimeWednesday.disableProperty().bind(Bindings.not(checkWednesday.selectedProperty()));

            startTimeThursday.disableProperty().bind(Bindings.not(checkThursday.selectedProperty()));
            endTimeThursday.disableProperty().bind(Bindings.not(checkThursday.selectedProperty()));

            startTimeFriday.disableProperty().bind(Bindings.not(checkFriday.selectedProperty()));
            endTimeFriday.disableProperty().bind(Bindings.not(checkFriday.selectedProperty()));

            startTimeSaturday.disableProperty().bind(Bindings.not(checkSaturaday.selectedProperty()));
            endTimeSaturday.disableProperty().bind(Bindings.not(checkSaturaday.selectedProperty()));

            startTimeSunday.disableProperty().bind(Bindings.not(checkSunday.selectedProperty()));
            endTimeSunday.disableProperty().bind(Bindings.not(checkSunday.selectedProperty()));

            // doctor = AdminAddDoctorUtil.getAdminAddDoctorUtil(vBoxDoctor).getAddDoctor();
        } catch (Exception ex) {
            Logger.getLogger(AdminAddDoctorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnCloseAdminAddDoctorOnAction(ActionEvent event) {
        //System.out.println(radioBtnGroup.getSelectedToggle().getUserData().toString());
        JFXDrawer drawer = (JFXDrawer) (((Node) event.getSource()).getScene().lookup("#drawerAddDoctor"));
        if (drawer.isShown()) {
            drawer.close();
        } else {
            drawer.open();
        }

    }

    @FXML
    private void btnOpenImageOnAction(ActionEvent event) {
        file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
        if (file != null) {
            image = new Image(file.toURI().toString(), 290, 290, true, true);
            imgView.setImage(image);
        }
    }

    @FXML
    private void btnActionOnAction(ActionEvent event) throws Exception {
        String text = btnAction.getText();
        if (text.equalsIgnoreCase("ADD DOCTOR")) {
            saveDoctor();
        } else if (text.equalsIgnoreCase("UPDATE")) {
            updateDoctor();
        }
    }

    @FXML
    private void btnAddSpecializationOnAction(ActionEvent event) throws IOException {
        Parent modalWindow = FXMLLoader.load(this.getClass().getResource("/hospital/view/AdminAddSpeciality.fxml"));
        CustomMethod.modalSeet(modalWindow, event);
    }

    @Override
    public void updateObservers() throws Exception {
        loadSpecializationCombos();
    }

    private void loadSpecializationCombos() throws Exception {
        cmboSpecialization.getItems().clear();
        List<SpecializationDTO> allSpecialization = specializationService.getAllSpecialization();
        for (SpecializationDTO specializationDTO : allSpecialization) {
            cmboSpecialization.getItems().add(specializationDTO.getSpecialityName());
        }
    }

    private void saveDoctor() throws Exception {
        DoctorDTO doctorDTO = createDoctorDTO();

        if (doctorService.addDoctor(doctorDTO)) {
          CustomMethod.successNotification("Successfully Saved ...!");
            //  clearAll();
        } else {
            CustomMethod.errorNotification("Error!");
        }
    }

//    private void clearAll() {
//        for (CheckBox checkBox : doctor.getAllCheckBox()) {
//            checkBox.setSelected(false);
//        }
//
//        for (JFXComboBox comboBox : doctor.getAllComboBOX()) {
//            comboBox.getSelectionModel().clearSelection();
//        }
//
//        for (JFXDatePicker datePicker : doctor.getAllDatePicker()) {
//            datePicker.getEditor().clear();
//        }
//
//        for (ImageView imageView : doctor.getAllImageView()) {
//            imageView.setImage(null);
//        }
//        for (JFXRadioButton radio : doctor.getAllRadioButtons()) {
//            radio.setSelected(false);
//        }
//        for (JFXTextField textFields : doctor.getAllTextFiels()) {
//            textFields.clear();
//        }
//
//        for (JFXTimePicker timePicker : doctor.getAllTimePicker()) {
//            timePicker.getEditor().clear();
//        }
//    }
    private void updateDoctor() throws Exception {
        DoctorDTO doctorDTO = createDoctorDTO();

        if (doctorService.updateDoctor(doctorDTO)) {
           CustomMethod.successNotification("Successfully Update ...!");
            //  clearAll();
        } else {
            CustomMethod.errorNotification("Error!");
        }
    }

    private DoctorDTO createDoctorDTO() throws Exception {
        DoctorAvailableTimeDTO mondayTime = checkMonday.isSelected() ? new DoctorAvailableTimeDTO(startTimeMonday.getValue().format(DateTimeFormatter.ISO_TIME), endTimeMonday.getValue().format(DateTimeFormatter.ISO_TIME)) : new DoctorAvailableTimeDTO("", "");
        DoctorAvailableTimeDTO tuesdayTime = checkTuesday.isSelected() ? new DoctorAvailableTimeDTO(startTimeTuesday.getValue().format(DateTimeFormatter.ISO_TIME), endTimeTuesday.getValue().format(DateTimeFormatter.ISO_TIME)) : new DoctorAvailableTimeDTO("", "");
        DoctorAvailableTimeDTO wednesdayTime = checkWednesday.isSelected() ? new DoctorAvailableTimeDTO(startTimeWednesday.getValue().format(DateTimeFormatter.ISO_TIME), endTimeWednesday.getValue().format(DateTimeFormatter.ISO_TIME)) : new DoctorAvailableTimeDTO("", "");
        DoctorAvailableTimeDTO thursdayTime = checkThursday.isSelected() ? new DoctorAvailableTimeDTO(startTimeThursday.getValue().format(DateTimeFormatter.ISO_TIME), endTimeThursday.getValue().format(DateTimeFormatter.ISO_TIME)) : new DoctorAvailableTimeDTO("", "");
        DoctorAvailableTimeDTO fridayTime = checkFriday.isSelected() ? new DoctorAvailableTimeDTO(startTimeFriday.getValue().format(DateTimeFormatter.ISO_TIME), endTimeFriday.getValue().format(DateTimeFormatter.ISO_TIME)) : new DoctorAvailableTimeDTO("", "");
        DoctorAvailableTimeDTO saturdayTime = checkSaturaday.isSelected() ? new DoctorAvailableTimeDTO(startTimeSaturday.getValue().format(DateTimeFormatter.ISO_TIME), endTimeSaturday.getValue().format(DateTimeFormatter.ISO_TIME)) : new DoctorAvailableTimeDTO("", "");
        DoctorAvailableTimeDTO sundayTime = checkSunday.isSelected() ? new DoctorAvailableTimeDTO(startTimeSunday.getValue().format(DateTimeFormatter.ISO_TIME), endTimeSunday.getValue().format(DateTimeFormatter.ISO_TIME)) : new DoctorAvailableTimeDTO("", "");

        DoctorAvailableDatesDTO doctorAvailableDatesDTO = new DoctorAvailableDatesDTO(
                checkMonday.isSelected(),
                checkTuesday.isSelected(),
                checkWednesday.isSelected(),
                checkThursday.isSelected(),
                checkFriday.isSelected(),
                checkSaturaday.isSelected(),
                checkSunday.isSelected(),
                mondayTime,
                tuesdayTime,
                wednesdayTime,
                thursdayTime,
                fridayTime,
                saturdayTime,
                sundayTime
        );

        SpecializationDTO specializationDTO = specializationService.findSpecializationByName(cmboSpecialization.getValue());

        byte[] imageInByte = new byte[(int) file.length()];
        FileInputStream inputStream = new FileInputStream(file);
        inputStream.read(imageInByte);
        inputStream.close();

        DoctorDTO doctorDTO = new DoctorDTO(
                cmbSection.getValue(),
                txtFirstName.getText(),
                txtMiddleName.getText(),
                txtLastName.getText(),
                radioBtnGroup.getSelectedToggle().getUserData().toString(),
                txtAddress.getText(),
                txtDOB.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE),
                specializationDTO,
                txtSLMCNo.getText(),
                txtMobileNo.getText(),
                txtPhoneNumber.getText(),
                txtEmail.getText(),
                Double.parseDouble(txtVisitingFee.getText()),
                imageInByte,
                doctorAvailableDatesDTO
        );

        return doctorDTO;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import hospital.dto.AppointmentDTO;
import hospital.dto.DoctorDTO;
import hospital.dto.PatientDTO;
import hospital.proxy.ProxyHandler;
import hospital.resource.CustomMethod;
import hospital.service.ServiceFactory;
import hospital.service.custom.AppointmentService;
import hospital.service.custom.DoctorService;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Kasun
 */
public class CashierAddAppointmentController implements Initializable {

    @FXML
    private TextField txtAppointmentID;
    @FXML
    private JFXTextField txtPatientID;
    @FXML
    private JFXTextField txtPatientName;
    @FXML
    private JFXTextField txtAge;
    @FXML
    private JFXComboBox<String> cmbGender;
    @FXML
    private JFXButton btnSelectDoctor;
    @FXML
    private JFXTextField txtDocName;
    @FXML
    private JFXTextField txtSpeciality;
    @FXML
    private JFXTextField txtSLMC;
    @FXML
    private JFXTextField txtSection;
    @FXML
    private JFXButton btnAddAppointment;
    @FXML
    private JFXTextField txtDate;

    private AppointmentService appointmentService;
    private DoctorService doctorService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            appointmentService = (AppointmentService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.APPOINTMENT);
            doctorService = (DoctorService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.DOCTOR);
        } catch (Exception ex) {
            Logger.getLogger(CashierAddAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        cmbGender.getItems().add("Male");
        cmbGender.getItems().add("Female");

        txtDate.setText(new SimpleDateFormat("YYYY-MM-dd").format(new Date()));

    }

    @FXML
    private void btnSelectDoctorOnAction(ActionEvent event) throws IOException {
        Parent modalWindow = FXMLLoader.load(this.getClass().getResource("/hospital/view/SelectDoctor.fxml"));
        CustomMethod.modalSeet(modalWindow, event);
    }

    @FXML
    private void btnAddAppointmentOnAction(ActionEvent event) throws Exception {
        DoctorDTO doctorDTO = doctorService.findDoctorBySLMC(txtSLMC.getText());
        PatientDTO patientDTO = new PatientDTO(txtPatientID.getText(), txtPatientName.getText(), Integer.parseInt(txtAge.getText()), cmbGender.getValue());

        System.out.println(txtDate.getText());

        AppointmentDTO appointmentDTO = new AppointmentDTO(txtAppointmentID.getText(), txtDate.getText(), patientDTO, doctorDTO);

        // System.out.println("Co " + doctorDTO.getSlmcRegNo());
        if (appointmentService.addAppointment(appointmentDTO)) {
            Parent modalWindow = FXMLLoader.load(this.getClass().getResource("/hospital/view/AppointmentPayment.fxml"));
            ((JFXTextField) modalWindow.lookup("#txtAppointmentID")).setText(txtAppointmentID.getText());
//        ((JFXTextField) modalWindow.lookup("#txtAppointmentID")).setText("AP-001");
//        ((JFXTextField) modalWindow.lookup("#txtDoctorFee")).setText("800");
            ((JFXTextField) modalWindow.lookup("#txtDoctorFee")).setText(Double.toString(appointmentDTO.getDoctorDTO().getVisitingFee()));
            CustomMethod.modalSeet(modalWindow, event);
            CustomMethod.successNotification("Appointment Saved Successfully...!");
        } else {
            CustomMethod.errorNotification("Error is saving...!");
        }

    }
    private int i = 0;

    @FXML
    private void cmboGenderOnKeyPressed(KeyEvent event) {
        if (i < 0) {
            i = 0;
        }
        if (i > cmbGender.getItems().size()) {
            i = cmbGender.getItems().size();
        }
        if (event.getCode() == KeyCode.DOWN) {
            cmbGender.getSelectionModel().select(i++);
        }

        if (event.getCode() == KeyCode.UP) {
            cmbGender.getSelectionModel().select(i--);
        }
    }

}

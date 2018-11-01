/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.controller;

import com.jfoenix.controls.JFXTextField;
import hospital.dto.AppointmentDTO;
import hospital.dto.PatientDTO;
import hospital.proxy.ProxyHandler;
import hospital.resource.AdminPatientDTO;
import hospital.resource.CustomMethod;
import hospital.service.ServiceFactory;
import hospital.service.custom.AppointmentService;
import hospital.service.custom.PatientService;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Kasun
 */
public class AdminPatientsController implements Initializable {

    @FXML
    private TableView<AdminPatientDTO> tblView;
    @FXML
    private JFXTextField txtSearchByName;

    private ObservableList<AdminPatientDTO> tblData;
    private PatientService patientService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            patientService = (PatientService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.PATIENT);
            AppointmentService appointmentService = (AppointmentService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.APPOINTMENT);

            tblView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("patientID"));
            tblView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
            tblView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("gender"));
            tblView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("age"));
            tblView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("channelView"));
            tblView.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("admitView"));

            tblData = FXCollections.observableArrayList();
            tblView.setItems(tblData);
            tblData.clear();

            for (PatientDTO patientDTO : patientService.getAllPatient()) {
                tblData.add(new AdminPatientDTO(patientDTO.getPatientID(), patientDTO.getPatientName(), patientDTO.getGender(), patientDTO.getPatientAge()));
            }

        } catch (Exception ex) {
            Logger.getLogger(AdminPatientsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

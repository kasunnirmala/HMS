/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import hospital.dto.PatientDTO;
import hospital.dto.PatientDetailsDTO;
import hospital.dto.RoomReserveDTO;
import hospital.proxy.ProxyHandler;
import hospital.resource.CustomMethod;
import hospital.service.ServiceFactory;
import hospital.service.custom.PatientService;
import hospital.service.custom.RoomReserveService;
import hospital.service.custom.RoomService;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Kasun
 */
public class ReserveRoomController implements Initializable {
    
    @FXML
    private JFXTextField txtPatientID;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtContact;
    @FXML
    private JFXTextField txtGuardian;
    @FXML
    private JFXTextField txtRoomNo;
    @FXML
    private JFXTextField txtRoomType;
    @FXML
    private JFXTextField txtRoomFloor;
    @FXML
    private JFXTextField txtRoomPrice;
    @FXML
    private JFXTextField txtPatientName;
    @FXML
    private JFXTextField txtPatientAge;
    @FXML
    private JFXTextField txtPatientGender;
    @FXML
    private TextField txtResID;
    @FXML
    private Label lblDate;
    
    private PatientService patientService;
    private RoomService roomService;
    private RoomReserveService roomReserveService;
    @FXML
    private JFXButton btnReserve;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            patientService = (PatientService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.PATIENT);
            roomService = (RoomService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ROOM);
            roomReserveService = (RoomReserveService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ROOM_RESERVE);
        } catch (Exception ex) {
            Logger.getLogger(ReserveRoomController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        lblDate.setText(new SimpleDateFormat("YYYY-MM-dd").format(new Date()));
    }
    
    @FXML
    private void txtPatientIDOnAction(ActionEvent event) throws Exception {
        PatientDTO findPatientByID = patientService.findPatientByID(txtPatientID.getText());
        txtPatientAge.setText(Integer.toString(findPatientByID.getPatientAge()));
        txtPatientGender.setText(findPatientByID.getGender());
        txtPatientName.setText(findPatientByID.getPatientName());
        
        txtAddress.requestFocus();
        
    }
    
    @FXML
    private void btnReserveOnAction(ActionEvent event) throws Exception {
        RoomReserveDTO roomReserveDTO = new RoomReserveDTO(
                txtResID.getText(),
                patientService.findPatientByID(txtPatientID.getText()),
                new PatientDetailsDTO(
                        txtAddress.getText(),
                        txtContact.getText(),
                        txtGuardian.getText()),
                roomService.findRoomByID(txtRoomNo.getText()),
                true,
                lblDate.getText()
        );
        
        if (roomReserveService.addRoomReserve(roomReserveDTO)) {
            CustomMethod.successNotification("Successfullt Saved...!");
        } else {
            CustomMethod.errorNotification("Error is Save...!");
        }
    }
    
}

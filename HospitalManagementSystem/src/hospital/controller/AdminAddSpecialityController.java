/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.controller;

import com.jfoenix.controls.JFXTextField;
import hospital.dto.SpecializationDTO;
import hospital.proxy.ProxyHandler;
import hospital.resource.CustomMethod;
import hospital.service.ServiceFactory;
import hospital.service.custom.SpecializationService;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Kasun
 */
public class AdminAddSpecialityController implements Initializable {
    
    @FXML
    private JFXTextField txtSpecialityName;
    @FXML
    private JFXTextField txtSpecialityDescription;
    
    private SpecializationService specializationService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            specializationService = (SpecializationService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.SPECIALIZATION);
        } catch (Exception ex) {
            Logger.getLogger(AdminAddSpecialityController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void btnAddSpecializationOnAction(ActionEvent event) throws Exception {
        boolean addSpecialization = specializationService.addSpecialization(new SpecializationDTO(txtSpecialityName.getText(), txtSpecialityDescription.getText()));
        if (addSpecialization) {
            CustomMethod.successNotification("Successfully Saved...!");
        } else {
            CustomMethod.errorNotification("Error...!");
        }
    }
    
    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        closeModal();
        
    }
    
    private void closeModal() {
        Stage stage = (Stage) txtSpecialityDescription.getScene().getWindow();
        stage.close();
    }
    
}

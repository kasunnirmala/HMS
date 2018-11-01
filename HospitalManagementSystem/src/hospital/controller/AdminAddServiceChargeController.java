/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import hospital.dto.OtherServicesDTO;
import hospital.observer.Observer;
import hospital.proxy.ProxyHandler;
import hospital.resource.CustomMethod;
import hospital.service.ServiceFactory;
import hospital.service.custom.OtherChargesService;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Kasun
 */
public class AdminAddServiceChargeController implements Initializable {
    
    @FXML
    private JFXTextField txtServiceDescription;
    @FXML
    private JFXTextField txtRate;
    @FXML
    private JFXButton btnAdd;
    
    private OtherChargesService otherChargesService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            otherChargesService = (OtherChargesService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.OTHERCHARGES);
        } catch (Exception ex) {
            Logger.getLogger(AdminAddServiceChargeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void btnAddOnAction(ActionEvent event) throws Exception {
        if (otherChargesService.addOtherCharges(new OtherServicesDTO(txtServiceDescription.getText(), Double.parseDouble(txtRate.getText())))) {
            CustomMethod.successNotification("Successfully saved...!");
        } else {
            CustomMethod.errorNotification("Error...!");
        }
        
    }
    
}

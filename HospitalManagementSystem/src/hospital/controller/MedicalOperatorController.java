/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.controller;

import com.jfoenix.controls.JFXTextField;
import hospital.dto.PatientDTO;
import hospital.dto.RoomReserveDTO;
import hospital.dto.TreatmentDTO;
import hospital.proxy.ProxyHandler;
import hospital.service.ServiceFactory;
import hospital.service.custom.PatientService;
import hospital.service.custom.RoomReserveService;
import hospital.service.custom.TreatmentService;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Kasun
 */
public class MedicalOperatorController implements Initializable {

    @FXML
    private MenuItem menuNew;
    @FXML
    private AnchorPane anchorShow;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        menuNewOnAction(null);

    }

    @FXML
    private void menuNewOnAction(ActionEvent event) {
        try {
            AnchorPane anchorPlaceOrder = FXMLLoader.load(this.getClass().getResource("/hospital/view/MedicineOperatorMain.fxml"));
            anchorShow.getChildren().setAll(anchorPlaceOrder);
        } catch (IOException ex) {
            Logger.getLogger(AdminDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void menuExitOnAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void menuHelpOnAction(ActionEvent event) {
        System.out.println("Help");
    }

}

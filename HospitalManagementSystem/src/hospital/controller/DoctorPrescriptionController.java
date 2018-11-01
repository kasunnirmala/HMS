/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.controller;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kasun
 */
public class DoctorPrescriptionController implements Initializable {

    @FXML
    private AnchorPane anchorView;

    private boolean fullScreen;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fullScreen = false;
        newMenuOnAction(null);
    }

    @FXML
    private void newMenuOnAction(ActionEvent event) {
        try {
            AnchorPane anchorPlaceOrder = FXMLLoader.load(this.getClass().getResource("/hospital/view/DoctorPrescriptionNew.fxml"));
            anchorView.getChildren().setAll(anchorPlaceOrder);
        } catch (IOException ex) {
            Logger.getLogger(AdminDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void exitMenuOnAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void historyMenuOnAction(ActionEvent event) {
        try {
            AnchorPane anchorPlaceOrder = FXMLLoader.load(this.getClass().getResource("/hospital/view/doctorprescriptionview.fxml"));
            anchorView.getChildren().setAll(anchorPlaceOrder);
        } catch (IOException ex) {
            Logger.getLogger(AdminDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void fullScreenMenuOnAction(ActionEvent event) {
        Stage thisStage = (Stage) anchorView.getScene().getWindow();
        thisStage.setFullScreen(!fullScreen);
        thisStage.centerOnScreen();
        fullScreen = !fullScreen;
    }

    @FXML
    private void helpMenuOnAction(ActionEvent event) {
        System.out.println("help");
    }

    @FXML
    private void aboutMenuOnAction(ActionEvent event) {
    }

}

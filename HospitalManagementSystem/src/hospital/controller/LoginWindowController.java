/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kasun
 */
public class LoginWindowController implements Initializable {

    @FXML
    private JFXComboBox<String> cmboAccount;
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmboAccount.getItems().add("Administrator");
        cmboAccount.getItems().add("Cashier");
        cmboAccount.getItems().add("Doctor Assist");
        cmboAccount.getItems().add("Medicine Operator");
    }

    @FXML
    private void btnLogin(ActionEvent event) throws Exception {
        boolean fullScreen = false;
        String account = cmboAccount.getValue();
        Parent root = null;
        switch (account) {
            case "Administrator":
                root = FXMLLoader.load(this.getClass().getResource("/hospital/view/AdminDashBoard.fxml"));
                fullScreen = true;
                break;
            case "Cashier":
                root = FXMLLoader.load(this.getClass().getResource("/hospital/view/CashierDashBoard.fxml"));
                fullScreen = true;
                break;
            case "Doctor Assist":
                root = FXMLLoader.load(this.getClass().getResource("/hospital/view/DoctorPrescription.fxml"));
                fullScreen = false;
                break;
            case "Medicine Operator":
                root = FXMLLoader.load(this.getClass().getResource("/hospital/view/MedicalOperator.fxml"));
                fullScreen = false;
                break;
        }

        Scene thisScene = new Scene(root);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(thisScene);
        mainStage.centerOnScreen();
        mainStage.setFullScreen(fullScreen);

    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        System.exit(0);
    }

}

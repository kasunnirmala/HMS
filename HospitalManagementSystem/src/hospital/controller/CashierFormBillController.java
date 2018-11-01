/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.controller;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Kasun
 */
public class CashierFormBillController implements Initializable {

    @FXML
    private TableView<?> tblViewTreatment;
    @FXML
    private JFXTextField txtSearchByName;
    @FXML
    private TableView<?> tblViewOther;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnGetBillOnAction(ActionEvent event) {
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.controller;

import com.jfoenix.controls.JFXButton;
import hospital.dto.OtherServicesDTO;
import hospital.observer.Observer;
import hospital.observer.Subject;
import hospital.proxy.ProxyHandler;
import hospital.resource.CustomMethod;
import hospital.service.ServiceFactory;
import hospital.service.custom.OtherChargesService;
import java.io.IOException;
import java.net.URL;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kasun
 */
public class AdminOtherChargesController implements Initializable, Observer {

    @FXML
    private VBox vboxServices;
    private OtherChargesService otherChargesService;
    @FXML
    private JFXButton btnUpdate;
    private List<TextField> txtList = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            UnicastRemoteObject.exportObject(this, 0);
            otherChargesService = (OtherChargesService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.OTHERCHARGES);
            ((Subject) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.OTHERCHARGES)).registerObserver(this);

            loadChargers();
        } catch (Exception ex) {
            Logger.getLogger(AdminOtherChargesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void addMouseClicked(MouseEvent event) throws IOException {
        Parent modalWindow = FXMLLoader.load(this.getClass().getResource("/hospital/view/AdminAddServiceCharge.fxml"));
        //CustomMethod.modalSeet(modalWindow, event);
        Stage dialog = new Stage();
        Scene scene = new Scene(modalWindow);

        dialog.setScene(scene);
//        dialog.initOwner(theStage);
//        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.centerOnScreen();
        dialog.showAndWait();
    }

    private void loadChargers() throws Exception {
        List<OtherServicesDTO> allOtherCharges = otherChargesService.getAllOtherCharges();
        vboxServices.getChildren().clear();
        for (OtherServicesDTO otherServicesDTO : allOtherCharges) {
            HBox hBox = new HBox();
            Label lbl = new Label(otherServicesDTO.getServiceDescription());
            lbl.setPrefWidth(200);

            hBox.setSpacing(50);
            vboxServices.setSpacing(50);

            TextField txtField = new TextField(Double.toString(otherServicesDTO.getRate()));
            txtField.setAccessibleText(Integer.toString(otherServicesDTO.getServiceID()));
            txtList.add(txtField);
            hBox.getChildren().add(lbl);
            hBox.getChildren().add(txtField);

            vboxServices.getChildren().add(hBox);
        }
    }

    @Override
    public void updateObservers() throws Exception {
        loadChargers();
    }

    @Override
    public String getName() throws Exception {
        return "kasun";
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) throws Exception {
        for (TextField txt : txtList) {
            OtherServicesDTO findOtherChargesByID = otherChargesService.findOtherChargesByID(Integer.parseInt(txt.getAccessibleText()));
            findOtherChargesByID.setRate(Double.parseDouble(txt.getText()));
            if (otherChargesService.updateOtherCharges(findOtherChargesByID)) {
                CustomMethod.successNotification("Successfully Updated...!");
            } else {
                CustomMethod.errorNotification("Error...!");
            }

        }
    }

}

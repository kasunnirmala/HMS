/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import hospital.dto.NurseDTO;
import hospital.observer.Observer;
import hospital.observer.Subject;
import hospital.proxy.ProxyHandler;
import hospital.resource.CustomMethod;
import hospital.service.ServiceFactory;
import hospital.service.custom.NurseService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Kasun
 */
public class AdminAddNurseController implements Initializable {

    @FXML
    private Label lblTitle;
    @FXML
    private ImageView imgView;
    @FXML
    private JFXTextField txtFirstName;
    @FXML
    private JFXTextField txtMiddleName;
    @FXML
    private JFXTextField txtLastName;
    @FXML
    private JFXRadioButton radioMale;
    @FXML
    private JFXRadioButton radioFemale;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXDatePicker txtDOB;
    @FXML
    private JFXTextField txtMobileNo;
    @FXML
    private JFXTextField txtPhoneNumber;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXButton btnOpenImage;
    @FXML
    private JFXButton btnAction;
    @FXML
    private JFXButton btnCloseAdminAddNurse;
    @FXML
    private JFXTextField txtNurseID;

    private NurseService nurseService;
    private final ToggleGroup radioBtnGroup = new ToggleGroup();
    private Image image;
    private FileChooser fileChooser;
    private File file;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            nurseService = (NurseService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.NURSE);
        } catch (Exception ex) {
            Logger.getLogger(AdminAddNurseController.class.getName()).log(Level.SEVERE, null, ex);
        }

        radioFemale.setUserData("Female");
        radioMale.setUserData("Male");

        radioFemale.setToggleGroup(radioBtnGroup);
        radioMale.setToggleGroup(radioBtnGroup);

        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
    }

    @FXML
    private void btnOpenImageOnAction(ActionEvent event) {
        file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
        if (file != null) {
            image = new Image(file.toURI().toString(), 290, 290, true, true);
            imgView.setImage(image);
        }
    }

    @FXML
    private void btnActionOnAction(ActionEvent event) throws Exception {
        String text = btnAction.getText();
        if (text.equalsIgnoreCase("ADD NURSE")) {
            saveNurse();
        } else if (text.equalsIgnoreCase("UPDATE")) {
            updateNurse();
        }
    }

    @FXML
    private void btnCloseAdminAddNurseOnAction(ActionEvent event) {
        JFXDrawer drawer = (JFXDrawer) (((Node) event.getSource()).getScene().lookup("#drawerAddDoctor"));
        if (drawer.isShown()) {
            drawer.close();
        } else {
            drawer.open();
        }
    }

    private void saveNurse() throws Exception {
        NurseDTO nurseDTO = createNurseDTO();

        if (nurseService.addNurse(nurseDTO)) {
            CustomMethod.successNotification("Successfully Saved ...!");
            //  clearAll();
        } else {
            CustomMethod.errorNotification("Error...!");
        }
    }

    private void updateNurse() throws Exception {
        NurseDTO nurseDTO = createNurseDTO();

        if (nurseService.updateNurse(nurseDTO)) {
            CustomMethod.successNotification("Successfully Saved ...!");
            //  clearAll();
        } else {
            CustomMethod.errorNotification("Error...!");
        }
    }

    private NurseDTO createNurseDTO() throws Exception {

        byte[] imageInByte = new byte[(int) file.length()];
        FileInputStream inputStream = new FileInputStream(file);
        inputStream.read(imageInByte);
        inputStream.close();

        return new NurseDTO(
                txtNurseID.getText(),
                txtFirstName.getText(),
                txtMiddleName.getText(),
                txtLastName.getText(),
                radioBtnGroup.getSelectedToggle().getUserData().toString(),
                txtAddress.getText(),
                txtDOB.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE),
                txtMobileNo.getText(),
                txtPhoneNumber.getText(),
                txtEmail.getText(),
                imageInByte
        );

    }

}

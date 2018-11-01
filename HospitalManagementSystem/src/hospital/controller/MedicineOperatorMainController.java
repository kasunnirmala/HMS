/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.controller;

import com.jfoenix.controls.JFXTextField;
import hospital.dto.AppointmentDTO;
import hospital.dto.PatientDTO;
import hospital.dto.RoomReserveDTO;
import hospital.dto.TreatmentDTO;
import hospital.proxy.ProxyHandler;
import hospital.resource.CustomMethod;
import hospital.service.ServiceFactory;
import hospital.service.custom.PatientService;
import hospital.service.custom.RoomReserveService;
import hospital.service.custom.TreatmentService;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Kasun
 */
public class MedicineOperatorMainController implements Initializable {

    @FXML
    private JFXTextField txtRoomNo;
    @FXML
    private JFXTextField txtRoomType;
    @FXML
    private JFXTextField txtPatientID;
    @FXML
    private JFXTextField txtPatientName;
    @FXML
    private JFXTextField txtDateAdmit;
    @FXML
    private JFXTextField txtTreatment;
    @FXML
    private JFXTextField txtMedicine;
    @FXML
    private JFXTextField txtAmount;
    @FXML
    private TableView<TreatmentDTO> tblView;

    private RoomReserveService roomReserveService;
    private PatientService patientService;
    private TreatmentService treatmentService;
    private ObservableList<TreatmentDTO> tblData;
    @FXML
    private ImageView imgViewerLogout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            roomReserveService = (RoomReserveService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ROOM_RESERVE);
            patientService = (PatientService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.PATIENT);
            treatmentService = (TreatmentService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.TREATMENT);
        } catch (Exception ex) {
            Logger.getLogger(MedicalOperatorController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tblView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("treatment"));
        tblView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("medicine"));
        tblView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("amount"));
        tblView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("date"));

        tblData = FXCollections.observableArrayList();
        tblView.setItems(tblData);
        try {
            ArrayList<String> allRooms = new ArrayList<>();
            for (RoomReserveDTO roomReserveDTO : roomReserveService.getAllReservedRoomReserves()) {
                allRooms.add(roomReserveDTO.getRoomDTO().getRoomNo());

            }
            TextFields.bindAutoCompletion(txtRoomNo, allRooms);
        } catch (Exception ex) {
            Logger.getLogger(MedicalOperatorController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void txtRoomNoOnAction(ActionEvent event) throws Exception {
        RoomReserveDTO findRoomReserveByRoomNo = roomReserveService.findRoomReserveByRoomNo(txtRoomNo.getText());
        txtDateAdmit.setText(findRoomReserveByRoomNo.getDate());
        txtPatientID.setText(findRoomReserveByRoomNo.getPatientDTO().getPatientID());
        txtPatientName.setText(findRoomReserveByRoomNo.getPatientDTO().getPatientName());
        txtRoomType.setText(findRoomReserveByRoomNo.getRoomDTO().getRoomType());
        txtTreatment.requestFocus();

        loadTable(txtPatientID.getText());
    }

    @FXML
    private void txtTreatmentOnAction(ActionEvent event) {
        txtMedicine.requestFocus();
    }

    @FXML
    private void txtMedicineOnAction(ActionEvent event) {
        txtMedicine.requestFocus();
    }

    @FXML
    private void txtAmountOnAction(ActionEvent event) throws Exception {
        PatientDTO patientDTO = patientService.findPatientByID(txtPatientID.getText());
        TreatmentDTO treatmentDTO = new TreatmentDTO(
                txtTreatment.getText(),
                txtMedicine.getText(),
                Double.parseDouble(txtAmount.getText()),
                new SimpleDateFormat("YYYY-MM-dd").format(new Date()),
                patientDTO);

        if (treatmentService.addTreatment(treatmentDTO)) {
            CustomMethod.successNotification("Success...!");
            loadTable(txtPatientID.getText());
            txtTreatment.clear();
            txtMedicine.clear();
            txtAmount.clear();
            txtTreatment.requestFocus();
        } else {
            CustomMethod.errorNotification("Failed...!");
        }
    }

    private void loadTable(String text) throws Exception {
        tblView.setItems(FXCollections.observableArrayList(treatmentService.getAllTreatmentsByPatient(text)));
    }

    @FXML
    private void logoutOnMouseExited(MouseEvent event) {
        imgViewerLogout.setStyle("-fx-image:url(/hospital/assets/Logout_Rounded_Left_96px.png)");
    }

    @FXML
    private void logoutOnMouseEntered(MouseEvent event) {
        imgViewerLogout.setStyle("-fx-image:url(/hospital/assets/Logout_Rounded_Left_96pxonMouseOver.png)");
    }

    @FXML
    private void logoutOnMouseClicked(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/hospital/view/LoginWindow.fxml"));
        Scene thisScene = new Scene(root);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(thisScene);
        mainStage.centerOnScreen();
    }
}

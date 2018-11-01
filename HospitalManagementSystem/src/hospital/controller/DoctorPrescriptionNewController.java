/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.scene.control.skin.TextFieldSkin;
import hospital.dto.AppointmentDTO;
import hospital.dto.DoctorDTO;
import hospital.dto.PatientDTO;
import hospital.dto.PrescriptionDTO;
import hospital.dto.PrescriptionDetailsDTO;
import hospital.proxy.ProxyHandler;
import hospital.resource.CustomMethod;
import hospital.service.ServiceFactory;
import hospital.service.custom.AppointmentService;
import hospital.service.custom.DoctorService;
import hospital.service.custom.PatientService;
import hospital.service.custom.PrescriptionService;
import hospital.service.custom.ReportsService;
import java.awt.Dialog;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import lk.ijse.jasper.IJSEJasperViewer;
import net.sf.jasperreports.engine.JasperPrint;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Kasun
 */
public class DoctorPrescriptionNewController implements Initializable {

    @FXML
    private AnchorPane anchorView;
    @FXML
    private JFXTextField txtPatientID;
    @FXML
    private JFXTextField txtPatientName;
    @FXML
    private JFXTextField txtPatientGender;
    @FXML
    private JFXTextField txtPatientAge;
    @FXML
    private JFXTextField txtDocSLMC;
    @FXML
    private JFXTextField txtDoctorName;
    @FXML
    private JFXTextField txtSpeciality;
    @FXML
    private TextField txtMedicineName;
    @FXML
    private TextField txtDose;
    @FXML
    private TextField txtDays;
    @FXML
    private TableView<PrescriptionDetailsDTO> tblView;
    @FXML
    private Label lblDateTime;
    private ObservableList<PrescriptionDetailsDTO> tblData;
    @FXML
    private ComboBox<String> cmbFrequncy;
    @FXML
    private TextField txtPrescriptionID;

    private PatientService patientService;
    private PatientDTO patientDTO;
    private DoctorService doctorService;
    private DoctorDTO doctorDTO;
    private PrescriptionService prescriptionService;
    private AppointmentService appointmentService;
    @FXML
    private HBox addHBox;
    @FXML
    private JFXButton btnSave;
    private ReportsService reportsService;
    @FXML
    private ImageView imgViewerLogout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            patientService = (PatientService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.PATIENT);
            doctorService = (DoctorService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.DOCTOR);
            prescriptionService = (PrescriptionService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.PRESCRIPTION);
            appointmentService = (AppointmentService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.APPOINTMENT);
            reportsService = (ReportsService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.REPORTS);
            tblView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("medicineName"));
            tblView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("dose"));
            tblView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("frequency"));
            tblView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("days"));
            setDateTime();
            tblData = FXCollections.observableArrayList();
            tblView.setItems(tblData);

            cmbFrequncy.getItems().add("BD");
            cmbFrequncy.getItems().add("TD");
            ArrayList<String> allPatients = new ArrayList<>();
            for (AppointmentDTO appointmentDTO : appointmentService.getAllByDate(new SimpleDateFormat("YYYY-MM-dd").format(new Date()))) {
                allPatients.add(appointmentDTO.getPatientDTO().getPatientID());

            }
            TextFields.bindAutoCompletion(txtPatientID, allPatients);

        } catch (Exception ex) {
            Logger.getLogger(DoctorPrescriptionNewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void txtPatientIDOnAction(ActionEvent event) throws Exception {
        txtDocSLMC.requestFocus();
        patientDTO = patientService.findPatientByID(txtPatientID.getText());
        txtPatientAge.setText(Integer.toString(patientDTO.getPatientAge()));
        txtPatientGender.setText(patientDTO.getGender());
        txtPatientName.setText(patientDTO.getPatientName());

        AppointmentDTO appointmentDTO = appointmentService.findAppointmentByPatient(txtPatientID.getText());
        txtDocSLMC.setText(appointmentDTO.getDoctorDTO().getSlmcRegNo());
        txtDoctorName.setText(appointmentDTO.getDoctorDTO().getFirstName() + " " + appointmentDTO.getDoctorDTO().getLastName());
        txtSpeciality.setText(appointmentDTO.getDoctorDTO().getSpecialization().getSpecialityName());

    }

    @FXML
    private void txtDocSLMCOnAction(ActionEvent event) throws Exception {
        txtMedicineName.requestFocus();
        doctorDTO = doctorService.findDoctorBySLMC(txtDocSLMC.getText());
        txtDoctorName.setText(doctorDTO.getFirstName() + " " + doctorDTO.getLastName());
        txtSpeciality.setText(doctorDTO.getSpecialization().getSpecialityName());
    }

    @FXML
    private void txtMedicineOnAction(ActionEvent event) {
        txtDose.requestFocus();
    }

    @FXML
    private void txtDoseOnAction(ActionEvent event) {
        cmbFrequncy.requestFocus();
    }

    @FXML
    private void txtDaysOnAction(ActionEvent event) {
        txtMedicineName.requestFocus();
        tblData.add(new PrescriptionDetailsDTO(txtMedicineName.getText(), txtDose.getText(), cmbFrequncy.getValue(), txtDays.getText()));
        txtDays.clear();
        txtMedicineName.clear();
        txtDose.clear();

    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) throws Exception {
        List<PrescriptionDetailsDTO> presciptionDetails = new ArrayList<PrescriptionDetailsDTO>();

        for (int j = 0; j < tblData.size(); j++) {
            presciptionDetails.add(tblData.get(j));
        }

        PrescriptionDTO prescriptionDTO = new PrescriptionDTO(txtPrescriptionID.getText(), patientDTO, doctorDTO, presciptionDetails);
        if (prescriptionService.addPrescription(prescriptionDTO)) {
            CustomMethod.successNotification("Prescription saved successfully...!");

            JasperPrint filledReport = reportsService.getPrescription(txtPatientID.getText());
            IJSEJasperViewer frmJasperViewer = new IJSEJasperViewer(filledReport);
            // frmJasperViewer.setDefaultCloseOperation(.DISPOSE_ON_CLOSE);
            frmJasperViewer.setTitle("Prescription");
            frmJasperViewer.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
            frmJasperViewer.setVisible(true);
        } else {
            CustomMethod.errorNotification("Error to save...!");
        }
        clearAll();
    }

    private void setDateTime() {
        Timeline time = new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                lblDateTime.setText(new SimpleDateFormat("YYYY-MM-dd  hh:mm:ss a").format(new Date()));
            }
        }), new KeyFrame(Duration.seconds(1)));
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    private void clearAll() {
        txtDays.clear();
        txtDocSLMC.clear();
        txtDoctorName.clear();
        txtMedicineName.clear();
        txtPatientID.clear();
        txtPatientName.clear();
        txtPatientGender.clear();
        txtPatientAge.clear();
        txtSpeciality.clear();
        txtDose.clear();
        tblData.clear();
        txtPrescriptionID.clear();
    }

    private int i = 0;

    @FXML
    private void cmbFrequencyOnKeyPressed(KeyEvent event) {
        if (i < 0) {
            i = 0;
        }
        if (i > cmbFrequncy.getItems().size()) {
            i = cmbFrequncy.getItems().size();
        }
        if (event.getCode() == KeyCode.DOWN) {
            cmbFrequncy.getSelectionModel().select(i++);
        }

        if (event.getCode() == KeyCode.UP) {
            cmbFrequncy.getSelectionModel().select(i--);
        }

        if (event.getCode() == KeyCode.ENTER) {
            txtDays.requestFocus();
        }
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

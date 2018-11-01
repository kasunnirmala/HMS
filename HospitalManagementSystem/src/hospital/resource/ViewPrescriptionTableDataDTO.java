/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.resource;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import hospital.dto.PrescriptionDTO;
import hospital.dto.PrescriptionDetailsDTO;
import hospital.proxy.ProxyHandler;
import hospital.service.ServiceFactory;
import hospital.service.custom.PrescriptionService;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 *
 * @author Kasun
 */
public class ViewPrescriptionTableDataDTO {

    private String prescriptionID;
    private String patientID;
    private String parientName;
    private String doctorName;
    private String docSLMCNo;
    private JFXButton viewButton;
    private PrescriptionService prescriptionService;

    public ViewPrescriptionTableDataDTO() throws Exception {
        prescriptionService = (PrescriptionService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.PRESCRIPTION);
        this.viewButton = new JFXButton("View Medicines");
        viewButton.setStyle("-fx-font-weight:bold; -fx-background-color:#5bc0de");
        viewButton.setButtonType(JFXButton.ButtonType.RAISED);
        viewButton.setOnAction((evt) -> {
            try {
                viewData(prescriptionID, evt);
            } catch (Exception ex) {
                Logger.getLogger(ViewPrescriptionTableDataDTO.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public ViewPrescriptionTableDataDTO(String prescriptionID, String patientID, String parientName, String doctorName, String docSLMCNo) throws Exception {
        this();
        this.prescriptionID = prescriptionID;
        this.patientID = patientID;
        this.parientName = parientName;
        this.doctorName = doctorName;
        this.docSLMCNo = docSLMCNo;
    }

    /**
     * @return the prescriptionID
     */
    public String getPrescriptionID() {
        return prescriptionID;
    }

    /**
     * @param prescriptionID the prescriptionID to set
     */
    public void setPrescriptionID(String prescriptionID) {
        this.prescriptionID = prescriptionID;
    }

    /**
     * @return the patientID
     */
    public String getPatientID() {
        return patientID;
    }

    /**
     * @param patientID the patientID to set
     */
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    /**
     * @return the parientName
     */
    public String getParientName() {
        return parientName;
    }

    /**
     * @param parientName the parientName to set
     */
    public void setParientName(String parientName) {
        this.parientName = parientName;
    }

    /**
     * @return the doctorName
     */
    public String getDoctorName() {
        return doctorName;
    }

    /**
     * @param doctorName the doctorName to set
     */
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    /**
     * @return the docSLMCNo
     */
    public String getDocSLMCNo() {
        return docSLMCNo;
    }

    /**
     * @param docSLMCNo the docSLMCNo to set
     */
    public void setDocSLMCNo(String docSLMCNo) {
        this.docSLMCNo = docSLMCNo;
    }

    /**
     * @return the viewButton
     */
    public JFXButton getViewButton() {
        return viewButton;
    }

    /**
     * @param viewButton the viewButton to set
     */
    public void setViewButton(JFXButton viewButton) {
        this.viewButton = viewButton;
    }

    private void viewData(String prescriptionID, ActionEvent evt) throws Exception {
        PrescriptionDTO prescription = prescriptionService.findPrescriptionByID(prescriptionID);
        Parent modalWindow = FXMLLoader.load(this.getClass().getResource("/hospital/view/DoctorPrescriptionNew.fxml"));

        ((TextField) modalWindow.lookup("#txtPrescriptionID")).setText(prescription.getPrescriptionID());
        ((TextField) modalWindow.lookup("#txtPrescriptionID")).setEditable(false);

        ((JFXTextField) modalWindow.lookup("#txtPatientID")).setText(prescription.getPatientDTO().getPatientID());
        ((JFXTextField) modalWindow.lookup("#txtPatientID")).setEditable(false);

        ((JFXTextField) modalWindow.lookup("#txtPatientName")).setText(prescription.getPatientDTO().getPatientName());
        ((JFXTextField) modalWindow.lookup("#txtPatientGender")).setText(prescription.getPatientDTO().getGender());
        ((JFXTextField) modalWindow.lookup("#txtPatientAge")).setText(Integer.toString(prescription.getPatientDTO().getPatientAge()));

        ((JFXTextField) modalWindow.lookup("#txtDocSLMC")).setText(prescription.getDoctorDTO().getSlmcRegNo());
        ((JFXTextField) modalWindow.lookup("#txtDocSLMC")).setEditable(false);

        ((JFXTextField) modalWindow.lookup("#txtDoctorName")).setText("Dr. " + prescription.getDoctorDTO().getFirstName() + " " + prescription.getDoctorDTO().getLastName());
        ((JFXTextField) modalWindow.lookup("#txtSpeciality")).setText(prescription.getDoctorDTO().getSpecialization().getSpecialityName());

        ((HBox) modalWindow.lookup("#addHBox")).setVisible(false);
        ((JFXButton) modalWindow.lookup("#btnSave")).setVisible(false);

        ObservableList<PrescriptionDetailsDTO> items = ((TableView<PrescriptionDetailsDTO>) modalWindow.lookup("#tblView")).getItems();
        items.clear();
        for (PrescriptionDetailsDTO prescriptionDetailsDTO : prescription.getPrescriptionDetailsDTOs()) {
            items.add(prescriptionDetailsDTO);
        }

        CustomMethod.modalSeet(modalWindow, evt);

    }

}

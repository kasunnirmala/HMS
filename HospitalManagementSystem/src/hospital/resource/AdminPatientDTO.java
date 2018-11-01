/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.resource;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import hospital.dto.PrescriptionDTO;
import hospital.dto.PrescriptionDetailsDTO;
import hospital.dto.RoomReserveDTO;
import hospital.proxy.ProxyHandler;
import hospital.service.ServiceFactory;
import hospital.service.custom.PatientService;
import hospital.service.custom.PrescriptionService;
import hospital.service.custom.RoomReserveService;
import hospital.service.custom.RoomTypeService;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 *
 * @author Kasun
 */
public class AdminPatientDTO {

    private String patientID;
    private String name;
    private String gender;
    private int age;
    private JFXButton channelView;
    private JFXButton admitView;
    private PrescriptionService prescriptionService;
    private PatientService patientService;
    private RoomReserveService roomReserveService;
    private RoomTypeService roomTypeService;

    public AdminPatientDTO(String patientID) throws Exception {
        prescriptionService = (PrescriptionService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.PRESCRIPTION);
        patientService = (PatientService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.PATIENT);
        roomReserveService = (RoomReserveService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ROOM_RESERVE);
        roomTypeService = (RoomTypeService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ROOM_TYPE);

        PrescriptionDTO prescriptionDTO = prescriptionService.findPrescriptionByPatientID(patientID);
        if (prescriptionDTO != null) {
            this.channelView = new JFXButton("View Channel");
            channelView.setStyle("-fx-font-weight:bold; -fx-background-color:#5bc0de");
            channelView.setButtonType(JFXButton.ButtonType.RAISED);
            channelView.setOnAction((evt) -> {
                try {
                    viewChannelData(prescriptionDTO.getPrescriptionID(), evt);
                } catch (Exception ex) {
                    Logger.getLogger(ViewPrescriptionTableDataDTO.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }

        RoomReserveDTO roomReserveDTO = roomReserveService.findRoomReserveByPatientID(patientID);
        if (roomReserveDTO != null) {
            viewAdmitData(roomReserveDTO);
        }
    }

    public AdminPatientDTO(String patientID, String name, String gender, int age) throws Exception {
        this(patientID);
        this.patientID = patientID;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.channelView = channelView;
        this.admitView = admitView;

    }

    
  

    private void viewChannelData(String prescriptionID, ActionEvent evt) throws Exception {
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

    private void viewAdmitData(RoomReserveDTO roomReserveDTO) throws Exception {
        Parent modalWindow = FXMLLoader.load(this.getClass().getResource("/hospital/view/ReserveRoom.fxml"));
        JFXTextField txtPatientID = (JFXTextField) modalWindow.lookup("#txtPatientID");
        JFXTextField txtPatientName = (JFXTextField) modalWindow.lookup("#txtPatientName");
        JFXTextField txtPatientAge = (JFXTextField) modalWindow.lookup("#txtPatientAge");
        JFXTextField txtPatientGender = (JFXTextField) modalWindow.lookup("#txtPatientGender");

        JFXTextField txtAddress = (JFXTextField) modalWindow.lookup("#txtAddress");
        JFXTextField txtContact = (JFXTextField) modalWindow.lookup("#txtContact");
        JFXTextField txtGuardian = (JFXTextField) modalWindow.lookup("#txtGuardian");

        JFXTextField txtRoomNo = (JFXTextField) modalWindow.lookup("#txtRoomNo");
        JFXTextField txtRoomType = (JFXTextField) modalWindow.lookup("#txtRoomType");
        JFXTextField txtRoomFloor = (JFXTextField) modalWindow.lookup("#txtRoomFloor");
        JFXTextField txtRoomPrice = (JFXTextField) modalWindow.lookup("#txtRoomPrice");

        TextField txtResID = (TextField) modalWindow.lookup("#txtResID");

        Label lblDate = (Label) modalWindow.lookup("#lblDate");

        JFXButton btnReserve = (JFXButton) modalWindow.lookup("#btnReserve");
        btnReserve.setVisible(false);

        txtPatientID.setDisable(true);
        txtPatientName.setDisable(true);
        txtPatientAge.setDisable(true);
        txtPatientGender.setDisable(true);

        txtAddress.setDisable(true);
        txtContact.setDisable(true);
        txtGuardian.setDisable(true);

        txtRoomNo.setDisable(true);
        txtRoomType.setDisable(true);
        txtRoomFloor.setDisable(true);
        txtRoomPrice.setDisable(true);

        txtResID.setDisable(true);

        txtPatientID.setText(roomReserveDTO.getPatientDTO().getPatientID());
        txtPatientName.setText(roomReserveDTO.getPatientDTO().getPatientName());
        txtPatientAge.setText(Integer.toString(roomReserveDTO.getPatientDTO().getPatientAge()));
        txtPatientGender.setText(roomReserveDTO.getPatientDTO().getGender());

        txtAddress.setText(roomReserveDTO.getPatientDetailsDTO().getAddress());
        txtContact.setText(roomReserveDTO.getPatientDetailsDTO().getContact());
        txtGuardian.setText(roomReserveDTO.getPatientDetailsDTO().getGuardian());

        txtRoomNo.setText(roomReserveDTO.getRoomDTO().getRoomNo());
        txtRoomType.setText(roomReserveDTO.getRoomDTO().getRoomType());
        txtRoomFloor.setText(Integer.toString(roomReserveDTO.getRoomDTO().getRoomFloor()));
        txtRoomPrice.setText(Double.toString(roomTypeService.findLastRoomTypeBYType(roomReserveDTO.getRoomDTO().getRoomType()).getRoomPrice()));

        txtResID.setText(roomReserveDTO.getResID());

        lblDate.setText(roomReserveDTO.getDate());

        this.setAdmitView(new JFXButton("View Admit"));
        getAdmitView().setStyle("-fx-font-weight:bold; -fx-background-color:#5cb85c");
        getAdmitView().setButtonType(JFXButton.ButtonType.RAISED);
        getAdmitView().setOnAction((evt) -> {
            try {
                CustomMethod.modalSeet(modalWindow, evt);
            } catch (IOException ex) {
                Logger.getLogger(ReserverLuxuryRoom.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the channelView
     */
    public JFXButton getChannelView() {
        return channelView;
    }

    /**
     * @param channelView the channelView to set
     */
    public void setChannelView(JFXButton channelView) {
        this.channelView = channelView;
    }

    /**
     * @return the admitView
     */
    public JFXButton getAdmitView() {
        return admitView;
    }

    /**
     * @param admitView the admitView to set
     */
    public void setAdmitView(JFXButton admitView) {
        this.admitView = admitView;
    }

}

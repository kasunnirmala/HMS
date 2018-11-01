/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.resource;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javax.swing.JTextField;

/**
 *
 * @author Kasun
 */
public class DoctorPaneDataDTO {

    private JFXButton btnCloseAdminAddDoctor;

    private Label lblTitle;

    private ImageView imgView;

    private JFXTextField txtFirstName;

    private JFXTextField txtMiddleName;

    private JFXTextField txtLastName;

    private JFXRadioButton radioMale;

    private JFXRadioButton radioFemale;

    private JFXTextField txtAddress;

    private JFXDatePicker txtDOB;

    private JFXComboBox<String> cmboSpecialization;

    private JFXTextField txtSLMCNo;

    private JFXTextField txtMobileNo;

    private JFXTextField txtPhoneNumber;

    private JFXTextField txtEmail;

    private JFXButton btnOpenImage;

    private JFXTextField txtVisitingFee;

    private CheckBox checkMonday;

    private JFXTimePicker startTimeMonday;

    private JFXTimePicker endTimeMonday;

    private CheckBox checkTuesday;

    private JFXTimePicker startTimeTuesday;

    private JFXTimePicker endTimeTuesday;

    private CheckBox checkWednesday;

    private JFXTimePicker startTimeWednesday;

    private JFXTimePicker endTimeWednesday;

    private CheckBox checkThursday;

    private JFXTimePicker startTimeThursday;

    private JFXTimePicker endTimeThursday;

    private CheckBox checkFriday;

    private JFXTimePicker startTimeFriday;

    private JFXTimePicker endTimeFriday;

    private CheckBox checkSaturaday;

    private JFXTimePicker startTimeSaturday;

    private JFXTimePicker endTimeSaturday;

    private CheckBox checkSunday;

    private JFXTimePicker startTimeSunday;

    private JFXTimePicker endTimeSunday;

    private JFXButton btnAction;

    private JFXComboBox<String> cmbSection;

    public DoctorPaneDataDTO(JFXButton btnCloseAdminAddDoctor, Label lblTitle, ImageView imgView, JFXTextField txtFirstName, JFXTextField txtMiddleName, JFXTextField txtLastName, JFXRadioButton radioMale, JFXRadioButton radioFemale, JFXTextField txtAddress, JFXDatePicker txtDOB, JFXComboBox<String> cmboSpecialization, JFXTextField txtSLMCNo, JFXTextField txtMobileNo, JFXTextField txtPhoneNumber, JFXTextField txtEmail, JFXButton btnOpenImage, JFXTextField txtVisitingFee, CheckBox checkMonday, JFXTimePicker startTimeMonday, JFXTimePicker endTimeMonday, CheckBox checkTuesday, JFXTimePicker startTimeTuesday, JFXTimePicker endTimeTuesday, CheckBox checkWednesday, JFXTimePicker startTimeWednesday, JFXTimePicker endTimeWednesday, CheckBox checkThursday, JFXTimePicker startTimeThursday, JFXTimePicker endTimeThursday, CheckBox checkFriday, JFXTimePicker startTimeFriday, JFXTimePicker endTimeFriday, CheckBox checkSaturaday, JFXTimePicker startTimeSaturday, JFXTimePicker endTimeSaturday, CheckBox checkSunday, JFXTimePicker startTimeSunday, JFXTimePicker endTimeSunday, JFXButton btnAction, JFXComboBox<String> cmbSection) {
        this.btnCloseAdminAddDoctor = btnCloseAdminAddDoctor;
        this.lblTitle = lblTitle;
        this.imgView = imgView;
        this.txtFirstName = txtFirstName;
        this.txtMiddleName = txtMiddleName;
        this.txtLastName = txtLastName;
        this.radioMale = radioMale;
        this.radioFemale = radioFemale;
        this.txtAddress = txtAddress;
        this.txtDOB = txtDOB;
        this.cmboSpecialization = cmboSpecialization;
        this.txtSLMCNo = txtSLMCNo;
        this.txtMobileNo = txtMobileNo;
        this.txtPhoneNumber = txtPhoneNumber;
        this.txtEmail = txtEmail;
        this.btnOpenImage = btnOpenImage;
        this.txtVisitingFee = txtVisitingFee;
        this.checkMonday = checkMonday;
        this.startTimeMonday = startTimeMonday;
        this.endTimeMonday = endTimeMonday;
        this.checkTuesday = checkTuesday;
        this.startTimeTuesday = startTimeTuesday;
        this.endTimeTuesday = endTimeTuesday;
        this.checkWednesday = checkWednesday;
        this.startTimeWednesday = startTimeWednesday;
        this.endTimeWednesday = endTimeWednesday;
        this.checkThursday = checkThursday;
        this.startTimeThursday = startTimeThursday;
        this.endTimeThursday = endTimeThursday;
        this.checkFriday = checkFriday;
        this.startTimeFriday = startTimeFriday;
        this.endTimeFriday = endTimeFriday;
        this.checkSaturaday = checkSaturaday;
        this.startTimeSaturday = startTimeSaturday;
        this.endTimeSaturday = endTimeSaturday;
        this.checkSunday = checkSunday;
        this.startTimeSunday = startTimeSunday;
        this.endTimeSunday = endTimeSunday;
        this.btnAction = btnAction;
        this.cmbSection = cmbSection;
    }

    /**
     * @return the btnCloseAdminAddDoctor
     */
    public JFXButton getBtnCloseAdminAddDoctor() {
        return btnCloseAdminAddDoctor;
    }

    /**
     * @return the lblTitle
     */
    public Label getLblTitle() {
        return lblTitle;
    }

    /**
     * @return the imgView
     */
    public ImageView getImgView() {
        return imgView;
    }

    /**
     * @return the txtFirstName
     */
    public JFXTextField getTxtFirstName() {
        return txtFirstName;
    }

    /**
     * @return the txtMiddleName
     */
    public JFXTextField getTxtMiddleName() {
        return txtMiddleName;
    }

    /**
     * @return the txtLastName
     */
    public JFXTextField getTxtLastName() {
        return txtLastName;
    }

    /**
     * @return the radioMale
     */
    public JFXRadioButton getRadioMale() {
        return radioMale;
    }

    /**
     * @return the radioFemale
     */
    public JFXRadioButton getRadioFemale() {
        return radioFemale;
    }

    /**
     * @return the txtAddress
     */
    public JFXTextField getTxtAddress() {
        return txtAddress;
    }

    /**
     * @return the txtDOB
     */
    public JFXDatePicker getTxtDOB() {
        return txtDOB;
    }

    /**
     * @return the cmboSpecialization
     */
    public JFXComboBox<String> getCmboSpecialization() {
        return cmboSpecialization;
    }

    /**
     * @return the txtSLMCNo
     */
    public JFXTextField getTxtSLMCNo() {
        return txtSLMCNo;
    }

    /**
     * @return the txtMobileNo
     */
    public JFXTextField getTxtMobileNo() {
        return txtMobileNo;
    }

    /**
     * @return the txtPhoneNumber
     */
    public JFXTextField getTxtPhoneNumber() {
        return txtPhoneNumber;
    }

    /**
     * @return the txtEmail
     */
    public JFXTextField getTxtEmail() {
        return txtEmail;
    }

    /**
     * @return the btnOpenImage
     */
    public JFXButton getBtnOpenImage() {
        return btnOpenImage;
    }

    /**
     * @return the txtVisitingFee
     */
    public JFXTextField getTxtVisitingFee() {
        return txtVisitingFee;
    }

    /**
     * @return the checkMonday
     */
    public CheckBox getCheckMonday() {
        return checkMonday;
    }

    /**
     * @return the startTimeMonday
     */
    public JFXTimePicker getStartTimeMonday() {
        return startTimeMonday;
    }

    /**
     * @return the endTimeMonday
     */
    public JFXTimePicker getEndTimeMonday() {
        return endTimeMonday;
    }

    /**
     * @return the checkTuesday
     */
    public CheckBox getCheckTuesday() {
        return checkTuesday;
    }

    /**
     * @return the startTimeTuesday
     */
    public JFXTimePicker getStartTimeTuesday() {
        return startTimeTuesday;
    }

    /**
     * @return the endTimeTuesday
     */
    public JFXTimePicker getEndTimeTuesday() {
        return endTimeTuesday;
    }

    /**
     * @return the checkWednesday
     */
    public CheckBox getCheckWednesday() {
        return checkWednesday;
    }

    /**
     * @return the startTimeWednesday
     */
    public JFXTimePicker getStartTimeWednesday() {
        return startTimeWednesday;
    }

    /**
     * @return the endTimeWednesday
     */
    public JFXTimePicker getEndTimeWednesday() {
        return endTimeWednesday;
    }

    /**
     * @return the checkThursday
     */
    public CheckBox getCheckThursday() {
        return checkThursday;
    }

    /**
     * @return the startTimeThursday
     */
    public JFXTimePicker getStartTimeThursday() {
        return startTimeThursday;
    }

    /**
     * @return the endTimeThursday
     */
    public JFXTimePicker getEndTimeThursday() {
        return endTimeThursday;
    }

    /**
     * @return the checkFriday
     */
    public CheckBox getCheckFriday() {
        return checkFriday;
    }

    /**
     * @return the startTimeFriday
     */
    public JFXTimePicker getStartTimeFriday() {
        return startTimeFriday;
    }

    /**
     * @return the endTimeFriday
     */
    public JFXTimePicker getEndTimeFriday() {
        return endTimeFriday;
    }

    /**
     * @return the checkSaturaday
     */
    public CheckBox getCheckSaturaday() {
        return checkSaturaday;
    }

    /**
     * @return the startTimeSaturday
     */
    public JFXTimePicker getStartTimeSaturday() {
        return startTimeSaturday;
    }

    /**
     * @return the endTimeSaturday
     */
    public JFXTimePicker getEndTimeSaturday() {
        return endTimeSaturday;
    }

    /**
     * @return the checkSunday
     */
    public CheckBox getCheckSunday() {
        return checkSunday;
    }

    /**
     * @return the startTimeSunday
     */
    public JFXTimePicker getStartTimeSunday() {
        return startTimeSunday;
    }

    /**
     * @return the endTimeSunday
     */
    public JFXTimePicker getEndTimeSunday() {
        return endTimeSunday;
    }

    /**
     * @return the btnAction
     */
    public JFXButton getBtnAction() {
        return btnAction;
    }

    public DoctorPaneDataDTO(Label lblTitle) {
        this.lblTitle = lblTitle;
    }

    /**
     * @return the cmbSection
     */
    public JFXComboBox<String> getCmbSection() {
        return cmbSection;
    }

    public ArrayList<JFXTextField> getAllTextFiels() {
        ArrayList list = new ArrayList<JFXTextField>();
        list.add(txtFirstName);
        list.add(txtMiddleName);
        list.add(txtLastName);
        list.add(txtAddress);
        list.add(txtSLMCNo);
        list.add(txtMobileNo);
        list.add(txtPhoneNumber);
        list.add(txtEmail);
        list.add(txtVisitingFee);
        return list;
    }

    public ArrayList<JFXButton> getAllButtons() {
        ArrayList list = new ArrayList<JFXButton>();
        list.add(btnCloseAdminAddDoctor);
        list.add(btnOpenImage);
        list.add(btnAction);
        return list;
    }

    public ArrayList<JFXTimePicker> getAllTimePicker() {
        ArrayList list = new ArrayList<JFXTimePicker>();
        list.add(startTimeMonday);
        list.add(endTimeMonday);
        list.add(startTimeTuesday);
        list.add(endTimeTuesday);
        list.add(startTimeWednesday);
        list.add(endTimeWednesday);
        list.add(startTimeThursday);
        list.add(endTimeThursday);
        list.add(startTimeFriday);
        list.add(endTimeFriday);
        list.add(startTimeSaturday);
        list.add(endTimeSaturday);
        list.add(startTimeSunday);
        list.add(endTimeSunday);
        return list;
    }

    public ArrayList<CheckBox> getAllCheckBox() {
        ArrayList list = new ArrayList<CheckBox>();
        list.add(checkMonday);
        list.add(checkTuesday);
        list.add(checkWednesday);
        list.add(checkThursday);
        list.add(checkFriday);
        list.add(checkSaturaday);
        list.add(checkSunday);
        return list;
    }

    public ArrayList<JFXComboBox> getAllComboBOX() {
        ArrayList list = new ArrayList<JFXComboBox>();
        list.add(cmboSpecialization);
        list.add(cmbSection);
        return list;
    }

    public ArrayList<JFXRadioButton> getAllRadioButtons() {
        ArrayList list = new ArrayList<JFXRadioButton>();
        list.add(radioMale);
        list.add(radioFemale);
        return list;
    }

    public ArrayList<ImageView> getAllImageView() {
        ArrayList list = new ArrayList<ImageView>();
        list.add(imgView);
        return list;
    }

    public ArrayList<JFXDatePicker> getAllDatePicker() {
        ArrayList list = new ArrayList<JFXDatePicker>();
        list.add(txtDOB);
        return list;
    }

}

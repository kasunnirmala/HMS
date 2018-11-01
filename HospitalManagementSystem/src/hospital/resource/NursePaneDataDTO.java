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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 *
 * @author Kasun
 */
public class NursePaneDataDTO {

    private Label lblTitle;

    private ImageView imgView;

    private JFXTextField txtFirstName;

    private JFXTextField txtMiddleName;

    private JFXTextField txtLastName;

    private JFXRadioButton radioMale;

    private JFXRadioButton radioFemale;

    private JFXTextField txtAddress;

    private JFXDatePicker txtDOB;

    private JFXTextField txtMobileNo;

    private JFXTextField txtPhoneNumber;

    private JFXTextField txtEmail;

    private JFXButton btnOpenImage;

    private JFXButton btnAction;

    private JFXTextField txtNurseID;

    public NursePaneDataDTO(Label lblTitle, ImageView imgView, JFXTextField txtFirstName, JFXTextField txtMiddleName, JFXTextField txtLastName, JFXRadioButton radioMale, JFXRadioButton radioFemale, JFXTextField txtAddress, JFXDatePicker txtDOB, JFXTextField txtMobileNo, JFXTextField txtPhoneNumber, JFXTextField txtEmail, JFXButton btnOpenImage, JFXButton btnAction, JFXTextField txtNurseID) {
        this.lblTitle = lblTitle;
        this.imgView = imgView;
        this.txtFirstName = txtFirstName;
        this.txtMiddleName = txtMiddleName;
        this.txtLastName = txtLastName;
        this.radioMale = radioMale;
        this.radioFemale = radioFemale;
        this.txtAddress = txtAddress;
        this.txtDOB = txtDOB;
        this.txtMobileNo = txtMobileNo;
        this.txtPhoneNumber = txtPhoneNumber;
        this.txtEmail = txtEmail;
        this.btnOpenImage = btnOpenImage;
        this.btnAction = btnAction;
        this.txtNurseID = txtNurseID;
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
     * @return the btnAction
     */
    public JFXButton getBtnAction() {
        return btnAction;
    }

    /**
     * @return the txtNurseID
     */
    public JFXTextField getTxtNurseID() {
        return txtNurseID;
    }

    public ArrayList<JFXTextField> getAllTextFiels() {
        ArrayList list = new ArrayList<JFXTextField>();
        list.add(txtFirstName);
        list.add(txtMiddleName);
        list.add(txtLastName);
        list.add(txtAddress);
        list.add(txtMobileNo);
        list.add(txtPhoneNumber);
        list.add(txtEmail);
        list.add(txtNurseID);
        return list;
    }

    public ArrayList<JFXButton> getAllButtons() {
        ArrayList list = new ArrayList<JFXButton>();
        list.add(btnOpenImage);
        list.add(btnAction);
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

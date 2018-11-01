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
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 *
 * @author Kasun
 */
public class CashierPaneDataDTO {

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

    private JFXTextField txtCashierID;

    public CashierPaneDataDTO() {
    }

    public CashierPaneDataDTO(Label lblTitle, ImageView imgView, JFXTextField txtFirstName, JFXTextField txtMiddleName, JFXTextField txtLastName, JFXRadioButton radioMale, JFXRadioButton radioFemale, JFXTextField txtAddress, JFXDatePicker txtDOB, JFXTextField txtMobileNo, JFXTextField txtPhoneNumber, JFXTextField txtEmail, JFXButton btnOpenImage, JFXButton btnAction, JFXTextField txtCashierID) {
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
        this.txtCashierID = txtCashierID;
    }

    /**
     * @return the lblTitle
     */
    public Label getLblTitle() {
        return lblTitle;
    }

    /**
     * @param lblTitle the lblTitle to set
     */
    public void setLblTitle(Label lblTitle) {
        this.lblTitle = lblTitle;
    }

    /**
     * @return the imgView
     */
    public ImageView getImgView() {
        return imgView;
    }

    /**
     * @param imgView the imgView to set
     */
    public void setImgView(ImageView imgView) {
        this.imgView = imgView;
    }

    /**
     * @return the txtFirstName
     */
    public JFXTextField getTxtFirstName() {
        return txtFirstName;
    }

    /**
     * @param txtFirstName the txtFirstName to set
     */
    public void setTxtFirstName(JFXTextField txtFirstName) {
        this.txtFirstName = txtFirstName;
    }

    /**
     * @return the txtMiddleName
     */
    public JFXTextField getTxtMiddleName() {
        return txtMiddleName;
    }

    /**
     * @param txtMiddleName the txtMiddleName to set
     */
    public void setTxtMiddleName(JFXTextField txtMiddleName) {
        this.txtMiddleName = txtMiddleName;
    }

    /**
     * @return the txtLastName
     */
    public JFXTextField getTxtLastName() {
        return txtLastName;
    }

    /**
     * @param txtLastName the txtLastName to set
     */
    public void setTxtLastName(JFXTextField txtLastName) {
        this.txtLastName = txtLastName;
    }

    /**
     * @return the radioMale
     */
    public JFXRadioButton getRadioMale() {
        return radioMale;
    }

    /**
     * @param radioMale the radioMale to set
     */
    public void setRadioMale(JFXRadioButton radioMale) {
        this.radioMale = radioMale;
    }

    /**
     * @return the radioFemale
     */
    public JFXRadioButton getRadioFemale() {
        return radioFemale;
    }

    /**
     * @param radioFemale the radioFemale to set
     */
    public void setRadioFemale(JFXRadioButton radioFemale) {
        this.radioFemale = radioFemale;
    }

    /**
     * @return the txtAddress
     */
    public JFXTextField getTxtAddress() {
        return txtAddress;
    }

    /**
     * @param txtAddress the txtAddress to set
     */
    public void setTxtAddress(JFXTextField txtAddress) {
        this.txtAddress = txtAddress;
    }

    /**
     * @return the txtDOB
     */
    public JFXDatePicker getTxtDOB() {
        return txtDOB;
    }

    /**
     * @param txtDOB the txtDOB to set
     */
    public void setTxtDOB(JFXDatePicker txtDOB) {
        this.txtDOB = txtDOB;
    }

    /**
     * @return the txtMobileNo
     */
    public JFXTextField getTxtMobileNo() {
        return txtMobileNo;
    }

    /**
     * @param txtMobileNo the txtMobileNo to set
     */
    public void setTxtMobileNo(JFXTextField txtMobileNo) {
        this.txtMobileNo = txtMobileNo;
    }

    /**
     * @return the txtPhoneNumber
     */
    public JFXTextField getTxtPhoneNumber() {
        return txtPhoneNumber;
    }

    /**
     * @param txtPhoneNumber the txtPhoneNumber to set
     */
    public void setTxtPhoneNumber(JFXTextField txtPhoneNumber) {
        this.txtPhoneNumber = txtPhoneNumber;
    }

    /**
     * @return the txtEmail
     */
    public JFXTextField getTxtEmail() {
        return txtEmail;
    }

    /**
     * @param txtEmail the txtEmail to set
     */
    public void setTxtEmail(JFXTextField txtEmail) {
        this.txtEmail = txtEmail;
    }

    /**
     * @return the btnOpenImage
     */
    public JFXButton getBtnOpenImage() {
        return btnOpenImage;
    }

    /**
     * @param btnOpenImage the btnOpenImage to set
     */
    public void setBtnOpenImage(JFXButton btnOpenImage) {
        this.btnOpenImage = btnOpenImage;
    }

    /**
     * @return the btnAction
     */
    public JFXButton getBtnAction() {
        return btnAction;
    }

    /**
     * @param btnAction the btnAction to set
     */
    public void setBtnAction(JFXButton btnAction) {
        this.btnAction = btnAction;
    }

    /**
     * @return the txtCashierID
     */
    public JFXTextField getTxtCashierID() {
        return txtCashierID;
    }

    /**
     * @param txtCashierID the txtCashierID to set
     */
    public void setTxtCashierID(JFXTextField txtCashierID) {
        this.txtCashierID = txtCashierID;
    }

    public ArrayList<JFXTextField> getAllTextFiels() {
        ArrayList<JFXTextField> list = new ArrayList<JFXTextField>();
        list.add(txtFirstName);
        list.add(txtMiddleName);
        list.add(txtLastName);
        list.add(txtAddress);
        list.add(txtMobileNo);
        list.add(txtPhoneNumber);
        list.add(txtEmail);
        list.add(txtCashierID);
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

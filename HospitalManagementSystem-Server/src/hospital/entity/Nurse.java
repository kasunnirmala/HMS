/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Kasun
 */
@Entity
public class Nurse {

    @Id
    private String nurseID;
    private String firstName;
    private String middleName;
    private String latName;
    private String gender;
    private String address;
    private String dob;
    private String mobileNo;
    private String phoneNo;
    private String email;
    @Column(columnDefinition = "LONGBLOB")
    private byte[] nurseImage;

    public Nurse() {
    }

    public Nurse(String nurseID, String firstName, String middleName, String latName, String gender, String address, String dob, String mobileNo, String phoneNo, String email, byte[] nurseImage) {
        this.nurseID = nurseID;
        this.firstName = firstName;
        this.middleName = middleName;
        this.latName = latName;
        this.gender = gender;
        this.address = address;
        this.dob = dob;
        this.mobileNo = mobileNo;
        this.phoneNo = phoneNo;
        this.email = email;
        this.nurseImage = nurseImage;
    }

    /**
     * @return the nurseID
     */
    public String getNurseID() {
        return nurseID;
    }

    /**
     * @param nurseID the nurseID to set
     */
    public void setNurseID(String nurseID) {
        this.nurseID = nurseID;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return the latName
     */
    public String getLatName() {
        return latName;
    }

    /**
     * @param latName the latName to set
     */
    public void setLatName(String latName) {
        this.latName = latName;
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
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the dob
     */
    public String getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * @return the mobileNo
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * @param mobileNo the mobileNo to set
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    /**
     * @return the phoneNo
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * @param phoneNo the phoneNo to set
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the nurseImage
     */
    public byte[] getNurseImage() {
        return nurseImage;
    }

    /**
     * @param nurseImage the nurseImage to set
     */
    public void setNurseImage(byte[] nurseImage) {
        this.nurseImage = nurseImage;
    }

}

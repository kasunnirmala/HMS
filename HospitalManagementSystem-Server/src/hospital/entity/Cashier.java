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
public class Cashier {

    @Id
    private String cashierID;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String address;
    private String dob;
    private String mobileNo;
    private String phoneNo;
    private String email;
    @Column(columnDefinition = "LONGBLOB")
    private byte[] cashierImage;

    public Cashier() {
    }

    public Cashier(String cashierID, String firstName, String middleName, String lastName, String gender, String address, String dob, String mobileNo, String phoneNo, String email, byte[] cashierImage) {
        this.cashierID = cashierID;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.address = address;
        this.dob = dob;
        this.mobileNo = mobileNo;
        this.phoneNo = phoneNo;
        this.email = email;
        this.cashierImage = cashierImage;
    }

    /**
     * @return the cashierID
     */
    public String getCashierID() {
        return cashierID;
    }

    /**
     * @param cashierID the cashierID to set
     */
    public void setCashierID(String cashierID) {
        this.cashierID = cashierID;
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
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
     * @return the cashierImage
     */
    public byte[] getCashierImage() {
        return cashierImage;
    }

    /**
     * @param cashierImage the cashierImage to set
     */
    public void setCashierImage(byte[] cashierImage) {
        this.cashierImage = cashierImage;
    }
    

}

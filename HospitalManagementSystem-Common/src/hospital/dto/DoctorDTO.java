/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.dto;

import java.io.Serializable;
import java.sql.Blob;

/**
 *
 * @author Kasun
 */
public class DoctorDTO extends SuperDTO {

    private int doctorID;
    private String section;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String address;
    private String dob;
    private SpecializationDTO specialization;
    private String slmcRegNo;
    private String mobileNo;
    private String phoneNo;
    private String email;
    private double visitingFee;
    private byte[] docImage;
    private DoctorAvailableDatesDTO doctorAvailableDates;

    public DoctorDTO() {
    }

    public DoctorDTO(String section, String firstName, String middleName, String lastName, String gender, String address, String dob, SpecializationDTO specialization, String slmcRegNo, String mobileNo, String phoneNo, String email, double visitingFee, byte[] docImage, DoctorAvailableDatesDTO doctorAvailableDates) {
        this.section = section;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.address = address;
        this.dob = dob;
        this.specialization = specialization;
        this.slmcRegNo = slmcRegNo;
        this.mobileNo = mobileNo;
        this.phoneNo = phoneNo;
        this.email = email;
        this.visitingFee = visitingFee;
        this.docImage = docImage;
        this.doctorAvailableDates = doctorAvailableDates;
    }

    public DoctorDTO(int doctorID, String section, String firstName, String middleName, String lastName, String gender, String address, String dob, SpecializationDTO specialization, String slmcRegNo, String mobileNo, String phoneNo, String email, double visitingFee, byte[] docImage, DoctorAvailableDatesDTO doctorAvailableDates) {
        this.doctorID = doctorID;
        this.section = section;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.address = address;
        this.dob = dob;
        this.specialization = specialization;
        this.slmcRegNo = slmcRegNo;
        this.mobileNo = mobileNo;
        this.phoneNo = phoneNo;
        this.email = email;
        this.visitingFee = visitingFee;
        this.docImage = docImage;
        this.doctorAvailableDates = doctorAvailableDates;
    }

    /**
     * @return the doctorID
     */
    public int getDoctorID() {
        return doctorID;
    }

    /**
     * @param doctorID the doctorID to set
     */
    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    /**
     * @return the section
     */
    public String getSection() {
        return section;
    }

    /**
     * @param section the section to set
     */
    public void setSection(String section) {
        this.section = section;
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
     * @return the specialization
     */
    public SpecializationDTO getSpecialization() {
        return specialization;
    }

    /**
     * @param specialization the specialization to set
     */
    public void setSpecialization(SpecializationDTO specialization) {
        this.specialization = specialization;
    }

    /**
     * @return the slmcRegNo
     */
    public String getSlmcRegNo() {
        return slmcRegNo;
    }

    /**
     * @param slmcRegNo the slmcRegNo to set
     */
    public void setSlmcRegNo(String slmcRegNo) {
        this.slmcRegNo = slmcRegNo;
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
     * @return the visitingFee
     */
    public double getVisitingFee() {
        return visitingFee;
    }

    /**
     * @param visitingFee the visitingFee to set
     */
    public void setVisitingFee(double visitingFee) {
        this.visitingFee = visitingFee;
    }

    /**
     * @return the docImage
     */
    public byte[] getDocImage() {
        return docImage;
    }

    /**
     * @param docImage the docImage to set
     */
    public void setDocImage(byte[] docImage) {
        this.docImage = docImage;
    }

    /**
     * @return the doctorAvailableDates
     */
    public DoctorAvailableDatesDTO getDoctorAvailableDates() {
        return doctorAvailableDates;
    }

    /**
     * @param doctorAvailableDates the doctorAvailableDates to set
     */
    public void setDoctorAvailableDates(DoctorAvailableDatesDTO doctorAvailableDates) {
        this.doctorAvailableDates = doctorAvailableDates;
    }

}

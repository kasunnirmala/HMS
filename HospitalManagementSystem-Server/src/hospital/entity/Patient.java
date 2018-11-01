/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Kasun
 */
@Entity
public class Patient {

    @Id
    private String patientID;
    private String patientName;
    private int patientAge;
    private String gender;

    public Patient() {
    }

    public Patient(String patientID, String patientName, int patientAge, String gender) {
        this.patientID = patientID;
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.gender = gender;
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
     * @return the patientName
     */
    public String getPatientName() {
        return patientName;
    }

    /**
     * @param patientName the patientName to set
     */
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    /**
     * @return the patientAge
     */
    public int getPatientAge() {
        return patientAge;
    }

    /**
     * @param patientAge the patientAge to set
     */
    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
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

}

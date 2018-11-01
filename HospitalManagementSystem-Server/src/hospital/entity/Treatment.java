/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Kasun
 */
@Entity
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int treatmentID;
    private String treatment;
    private String medicine;
    private double amount;
    private String date;
    @ManyToOne(cascade = CascadeType.ALL)
    private Patient patient;

    public Treatment() {
    }

    public Treatment(int treatmentID, String treatment, String medicine, double amount, String date, Patient patient) {
        this.treatmentID = treatmentID;
        this.treatment = treatment;
        this.medicine = medicine;
        this.amount = amount;
        this.date = date;
        this.patient = patient;
    }

    public Treatment(String treatment, String medicine, double amount, String date, Patient patient) {
        this.treatment = treatment;
        this.medicine = medicine;
        this.amount = amount;
        this.date = date;
        this.patient = patient;
    }

    /**
     * @return the treatmentID
     */
    public int getTreatmentID() {
        return treatmentID;
    }

    /**
     * @param treatmentID the treatmentID to set
     */
    public void setTreatmentID(int treatmentID) {
        this.treatmentID = treatmentID;
    }

    /**
     * @return the treatment
     */
    public String getTreatment() {
        return treatment;
    }

    /**
     * @param treatment the treatment to set
     */
    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    /**
     * @return the medicine
     */
    public String getMedicine() {
        return medicine;
    }

    /**
     * @param medicine the medicine to set
     */
    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * @param patient the patient to set
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

}

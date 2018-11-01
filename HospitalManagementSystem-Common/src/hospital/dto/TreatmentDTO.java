/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.dto;

/**
 *
 * @author Kasun
 */
public class TreatmentDTO extends SuperDTO {

    private int treatmentID;
    private String treatment;
    private String medicine;
    private double amount;
    private String date;
    private PatientDTO patientDTO;

    public TreatmentDTO() {
    }

    public TreatmentDTO(int treatmentID, String treatment, String medicine, double amount, String date, PatientDTO patientDTO) {
        this.treatmentID = treatmentID;
        this.treatment = treatment;
        this.medicine = medicine;
        this.amount = amount;
        this.date = date;
        this.patientDTO = patientDTO;
    }

    public TreatmentDTO(String treatment, String medicine, double amount, String date, PatientDTO patientDTO) {
        this.treatment = treatment;
        this.medicine = medicine;
        this.amount = amount;
        this.date = date;
        this.patientDTO = patientDTO;
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
     * @return the patientDTO
     */
    public PatientDTO getPatientDTO() {
        return patientDTO;
    }

    /**
     * @param patientDTO the patientDTO to set
     */
    public void setPatientDTO(PatientDTO patientDTO) {
        this.patientDTO = patientDTO;
    }

}

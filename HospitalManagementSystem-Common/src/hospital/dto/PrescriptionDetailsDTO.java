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
public class PrescriptionDetailsDTO extends SuperDTO {

    private int presDetailID;
    private String medicineName;
    private String dose;
    private String frequency;
    private String days;

    public PrescriptionDetailsDTO() {
    }

    public PrescriptionDetailsDTO(int presDetailID, String medicineName, String dose, String frequency, String days) {
        this.presDetailID = presDetailID;
        this.medicineName = medicineName;
        this.dose = dose;
        this.frequency = frequency;
        this.days = days;
    }

    public PrescriptionDetailsDTO(String medicineName, String dose, String frequency, String days) {
        this.medicineName = medicineName;
        this.dose = dose;
        this.frequency = frequency;
        this.days = days;
    }
    
    

    /**
     * @return the presDetailID
     */
    public int getPresDetailID() {
        return presDetailID;
    }

    /**
     * @param presDetailID the presDetailID to set
     */
    public void setPresDetailID(int presDetailID) {
        this.presDetailID = presDetailID;
    }

    /**
     * @return the medicineName
     */
    public String getMedicineName() {
        return medicineName;
    }

    /**
     * @param medicineName the medicineName to set
     */
    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    /**
     * @return the dose
     */
    public String getDose() {
        return dose;
    }

    /**
     * @param dose the dose to set
     */
    public void setDose(String dose) {
        this.dose = dose;
    }

    /**
     * @return the frequency
     */
    public String getFrequency() {
        return frequency;
    }

    /**
     * @param frequency the frequency to set
     */
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    /**
     * @return the days
     */
    public String getDays() {
        return days;
    }

    /**
     * @param days the days to set
     */
    public void setDays(String days) {
        this.days = days;
    }

}

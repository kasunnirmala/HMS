/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Kasun
 */
@Entity
public class Prescription {

    @Id
    private String prescriptionID;
    @OneToOne(cascade = CascadeType.ALL)
    private Patient patient;
    @OneToOne(cascade = CascadeType.ALL)
    private Doctor doctor;
    @OneToMany(cascade = CascadeType.ALL)
    private List<PrescriptionDetails> prescriptionDetails;

    public Prescription() {
    }

    public Prescription(String prescriptionID, Patient patient, Doctor doctor, List<PrescriptionDetails> prescriptionDetails) {
        this.prescriptionID = prescriptionID;
        this.patient = patient;
        this.doctor = doctor;
        this.prescriptionDetails = prescriptionDetails;
    }

    /**
     * @return the prescriptionID
     */
    public String getPrescriptionID() {
        return prescriptionID;
    }

    /**
     * @param prescriptionID the prescriptionID to set
     */
    public void setPrescriptionID(String prescriptionID) {
        this.prescriptionID = prescriptionID;
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

    /**
     * @return the doctor
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * @param doctor the doctor to set
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * @return the prescriptionDetails
     */
    public List<PrescriptionDetails> getPrescriptionDetails() {
        return prescriptionDetails;
    }

    /**
     * @param prescriptionDetails the prescriptionDetails to set
     */
    public void setPrescriptionDetails(List<PrescriptionDetails> prescriptionDetails) {
        this.prescriptionDetails = prescriptionDetails;
    }

}

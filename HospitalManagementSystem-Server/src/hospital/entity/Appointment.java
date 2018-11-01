/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Kasun
 */
@Entity
public class Appointment {

    @Id
    private String appointmentID;
    private String appointDate;
    @ManyToOne(cascade = CascadeType.ALL)
    private Patient patient;
    @ManyToOne(cascade = CascadeType.ALL)
    private Doctor doctor;

    public Appointment() {
    }

    public Appointment(String appointmentID, String appointDate, Patient patient, Doctor doctor) {
        this.appointmentID = appointmentID;
        this.appointDate = appointDate;
        this.patient = patient;
        this.doctor = doctor;
    }

    /**
     * @return the appointmentID
     */
    public String getAppointmentID() {
        return appointmentID;
    }

    /**
     * @param appointmentID the appointmentID to set
     */
    public void setAppointmentID(String appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     * @return the appointDate
     */
    public String getAppointDate() {
        return appointDate;
    }

    /**
     * @param appointDate the appointDate to set
     */
    public void setAppointDate(String appointDate) {
        this.appointDate = appointDate;
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

}

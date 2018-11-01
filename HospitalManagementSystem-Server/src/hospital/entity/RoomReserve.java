/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.entity;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Kasun
 */
@Entity
public class RoomReserve {

    @Id
    private String resID;
    @OneToOne(cascade = CascadeType.ALL)
    private Patient patient;
    @Embedded
    private PatientDetails patientDetails;
    @OneToOne(cascade = CascadeType.ALL)
    private Room room;
    private boolean reserve;
    private String date;

    public RoomReserve() {
    }

    public RoomReserve(String resID, Patient patient, PatientDetails patientDetails, Room room, boolean reserve, String date) {
        this.resID = resID;
        this.patient = patient;
        this.patientDetails = patientDetails;
        this.room = room;
        this.reserve = reserve;
        this.date = date;
    }

    /**
     * @return the resID
     */
    public String getResID() {
        return resID;
    }

    /**
     * @param resID the resID to set
     */
    public void setResID(String resID) {
        this.resID = resID;
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
     * @return the patientDetails
     */
    public PatientDetails getPatientDetails() {
        return patientDetails;
    }

    /**
     * @param patientDetails the patientDetails to set
     */
    public void setPatientDetails(PatientDetails patientDetails) {
        this.patientDetails = patientDetails;
    }

    /**
     * @return the room
     */
    public Room getRoom() {
        return room;
    }

    /**
     * @param room the room to set
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * @return the reserve
     */
    public boolean isReserve() {
        return reserve;
    }

    /**
     * @param reserve the reserve to set
     */
    public void setReserve(boolean reserve) {
        this.reserve = reserve;
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

   
}

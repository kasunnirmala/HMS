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
public class RoomReserveDTO extends SuperDTO {

    private String resID;
    private PatientDTO patientDTO;
    private PatientDetailsDTO patientDetailsDTO;
    private RoomDTO roomDTO;
    private boolean reserve;
    private String date;

    public RoomReserveDTO() {
    }

    public RoomReserveDTO(String resID, PatientDTO patientDTO, PatientDetailsDTO patientDetailsDTO, RoomDTO roomDTO, boolean reserve, String date) {
        this.resID = resID;
        this.patientDTO = patientDTO;
        this.patientDetailsDTO = patientDetailsDTO;
        this.roomDTO = roomDTO;
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

    /**
     * @return the patientDetailsDTO
     */
    public PatientDetailsDTO getPatientDetailsDTO() {
        return patientDetailsDTO;
    }

    /**
     * @param patientDetailsDTO the patientDetailsDTO to set
     */
    public void setPatientDetailsDTO(PatientDetailsDTO patientDetailsDTO) {
        this.patientDetailsDTO = patientDetailsDTO;
    }

    /**
     * @return the roomDTO
     */
    public RoomDTO getRoomDTO() {
        return roomDTO;
    }

    /**
     * @param roomDTO the roomDTO to set
     */
    public void setRoomDTO(RoomDTO roomDTO) {
        this.roomDTO = roomDTO;
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

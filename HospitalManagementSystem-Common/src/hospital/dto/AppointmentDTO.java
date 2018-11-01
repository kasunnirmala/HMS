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
public class AppointmentDTO extends SuperDTO {

    private String appointmentID;
    private String appointDate;
    private PatientDTO patientDTO;
    private DoctorDTO doctorDTO;

    public AppointmentDTO() {
    }

    public AppointmentDTO(String appointmentID, String appointDate, PatientDTO patientDTO, DoctorDTO doctorDTO) {
        this.appointmentID = appointmentID;
        this.appointDate = appointDate;
        this.patientDTO = patientDTO;
        this.doctorDTO = doctorDTO;
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
     * @return the doctorDTO
     */
    public DoctorDTO getDoctorDTO() {
        return doctorDTO;
    }

    /**
     * @param doctorDTO the doctorDTO to set
     */
    public void setDoctorDTO(DoctorDTO doctorDTO) {
        this.doctorDTO = doctorDTO;
    }

}

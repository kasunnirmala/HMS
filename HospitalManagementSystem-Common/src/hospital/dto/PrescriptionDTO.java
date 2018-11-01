/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.dto;

import java.util.List;

/**
 *
 * @author Kasun
 */
public class PrescriptionDTO extends SuperDTO {

    private String prescriptionID;
    private PatientDTO patientDTO;
    private DoctorDTO doctorDTO;
    private List<PrescriptionDetailsDTO> prescriptionDetailsDTOs;

    public PrescriptionDTO() {
    }

    public PrescriptionDTO(String prescriptionID, PatientDTO patientDTO, DoctorDTO doctorDTO, List<PrescriptionDetailsDTO> prescriptionDetailsDTOs) {
        this.prescriptionID = prescriptionID;
        this.patientDTO = patientDTO;
        this.doctorDTO = doctorDTO;
        this.prescriptionDetailsDTOs = prescriptionDetailsDTOs;
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

    /**
     * @return the prescriptionDetailsDTOs
     */
    public List<PrescriptionDetailsDTO> getPrescriptionDetailsDTOs() {
        return prescriptionDetailsDTOs;
    }

    /**
     * @param prescriptionDetailsDTOs the prescriptionDetailsDTOs to set
     */
    public void setPrescriptionDetailsDTOs(List<PrescriptionDetailsDTO> prescriptionDetailsDTOs) {
        this.prescriptionDetailsDTOs = prescriptionDetailsDTOs;
    }
    
}

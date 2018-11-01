/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.business.custom;

import hospital.business.SuperBO;
import hospital.dto.PatientDTO;
import java.util.List;

/**
 *
 * @author Kasun
 */
public interface PatientBO extends SuperBO {

    public boolean addPatient(PatientDTO patientDTO) throws Exception;

    public boolean updatePatient(PatientDTO patientDTO) throws Exception;

    public boolean deletePatient(String patienID) throws Exception;

    public PatientDTO findPatientByID(String ID) throws Exception;

    public List<PatientDTO> getAllPatient() throws Exception;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.service.custom;

import hospital.dto.PatientDTO;
import hospital.service.SuperService;
import java.util.List;

/**
 *
 * @author Kasun
 */
public interface PatientService extends SuperService {

    public boolean addPatient(PatientDTO patientDTO) throws Exception;

    public boolean updatePatient(PatientDTO patientDTO) throws Exception;

    public boolean deletePatient(String patienID) throws Exception;

    public PatientDTO findPatientByID(String ID) throws Exception;

    public List<PatientDTO> getAllPatient() throws Exception;
}

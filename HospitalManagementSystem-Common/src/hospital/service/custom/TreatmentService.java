/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.service.custom;

import hospital.dto.TreatmentDTO;
import hospital.service.SuperService;
import java.util.List;

/**
 *
 * @author Kasun
 */
public interface TreatmentService extends SuperService {

    public boolean addTreatment(TreatmentDTO treatmentDTO) throws Exception;

    public boolean updateTreatment(TreatmentDTO treatmentDTO) throws Exception;

    public boolean deleteTreatment(String treatmentID) throws Exception;

    public TreatmentDTO findTreatmentByID(int ID) throws Exception;

    public TreatmentDTO findTreatmentByPatient(String patientID) throws Exception;

    public List<TreatmentDTO> getAllTreatments() throws Exception;

    public List<TreatmentDTO> getAllTreatmentsByPatient(String patientID) throws Exception;
}

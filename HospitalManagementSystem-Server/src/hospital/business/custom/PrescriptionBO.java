/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.business.custom;

import hospital.business.SuperBO;
import hospital.dto.PrescriptionDTO;
import java.util.List;

/**
 *
 * @author Kasun
 */
public interface PrescriptionBO extends SuperBO {

    public boolean addPrescription(PrescriptionDTO prescriptionDTO) throws Exception;

    public boolean updatePrescription(PrescriptionDTO prescriptionDTO) throws Exception;

    public boolean deletePrescription(String presID) throws Exception;

    public PrescriptionDTO findPrescriptionByID(String ID) throws Exception;

    public List<PrescriptionDTO> getAllPrescriptions() throws Exception;

    public PrescriptionDTO findPrescriptionByPatientID(String patientID) throws Exception;
}

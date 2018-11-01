/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.repository.custom;

import hospital.entity.Treatment;
import hospital.repository.SuperRepository;
import java.util.List;

/**
 *
 * @author Kasun
 */
public interface TreatmentRepository extends SuperRepository<Treatment, Integer> {

    public Treatment findTreatmentByPatient(String patientID);

    public List<Treatment> findAllByPatient(String patientID);
    
}

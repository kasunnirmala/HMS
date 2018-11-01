/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.repository.custom;

import hospital.entity.Doctor;
import hospital.repository.SuperRepository;
import java.util.List;

/**
 *
 * @author Kasun
 */
public interface DoctorRepository extends SuperRepository<Doctor, Integer> {

    public Doctor findDoctorBySLMC(String regNo) throws Exception;

    public List<Doctor> getDoctorBySection(String section) throws Exception;
}

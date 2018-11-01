/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.repository.custom.impl;

import hospital.entity.Doctor;
import hospital.repository.SuperRepositoryImpl;
import hospital.repository.custom.DoctorRepository;
import java.util.List;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Kasun
 */
public class DoctorRepositoryImpl extends SuperRepositoryImpl<Doctor, Integer> implements DoctorRepository {

    public DoctorRepositoryImpl() {
    }

    @Override
    public Doctor findDoctorBySLMC(String regNo) throws Exception {
        return (Doctor) session.createCriteria(entityClass).add(Restrictions.eq("slmcRegNo", regNo)).list().get(0);
    }

    @Override
    public List<Doctor> getDoctorBySection(String section) throws Exception {
        return session.createCriteria(entityClass).add(Restrictions.eq("section", section)).list();
    }

}

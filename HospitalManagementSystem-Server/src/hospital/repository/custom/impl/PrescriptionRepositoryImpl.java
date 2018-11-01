/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.repository.custom.impl;

import hospital.entity.Prescription;
import hospital.repository.SuperRepositoryImpl;
import hospital.repository.custom.PrescriptionRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Kasun
 */
public class PrescriptionRepositoryImpl extends SuperRepositoryImpl<Prescription, String> implements PrescriptionRepository {

    public PrescriptionRepositoryImpl() {
    }

    @Override
    public Prescription findByPatientId(String patientID) {
        Criteria c = session.createCriteria(entityClass, "prescription");
        c.createAlias("prescription.patient", "patient");
        c.add(Restrictions.eq("patient.patientID", patientID));
        System.out.println("kkkkkkkkkkkkk     " + c.list().size());
        return (Prescription) c.list().get(0);
    }

}

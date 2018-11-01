/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.repository.custom.impl;

import hospital.entity.Treatment;
import hospital.repository.SuperRepositoryImpl;
import hospital.repository.custom.TreatmentRepository;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Kasun
 */
public class TreatmentRepositoryImpl extends SuperRepositoryImpl<Treatment, Integer> implements TreatmentRepository {

    @Override
    public Treatment findTreatmentByPatient(String patientID) {
        Criteria c = session.createCriteria(entityClass, "treatment");
        c.createAlias("treatment.patient", "patient");
        c.add(Restrictions.eq("patient.patientID", patientID));

        return (Treatment) c.list().get(0);

    }

    @Override
    public List<Treatment> findAllByPatient(String patientID) {
        Criteria c = session.createCriteria(entityClass, "treatment");
        c.createAlias("treatment.patient", "patient");
        c.add(Restrictions.eq("patient.patientID", patientID));

        return c.list();
    }

}

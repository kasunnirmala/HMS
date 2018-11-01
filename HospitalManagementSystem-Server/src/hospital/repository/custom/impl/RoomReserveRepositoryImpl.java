/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.repository.custom.impl;

import hospital.entity.RoomReserve;
import hospital.repository.SuperRepositoryImpl;
import hospital.repository.custom.RoomReserveRepository;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Kasun
 */
public class RoomReserveRepositoryImpl extends SuperRepositoryImpl<RoomReserve, String> implements RoomReserveRepository {

    @Override
    public List<RoomReserve> findAllReserved() throws Exception {
        return session.createCriteria(entityClass).add(Restrictions.eq("reserve", true)).list();
    }

    @Override
    public List<RoomReserve> findAllNonReserved() throws Exception {
        return session.createCriteria(entityClass).add(Restrictions.eq("reserve", false)).list();
    }

    @Override
    public RoomReserve findByPatientId(String patientID) {
        Criteria c = session.createCriteria(entityClass, "roomreserve");
        c.createAlias("roomreserve.patient", "patient");
        c.add(Restrictions.eq("patient.patientID", patientID));

        System.out.println("aaaaaaaaaaaaa     " + (c.list().size()));

        return (RoomReserve) c.list().get(0);
    }

}

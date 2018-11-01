/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.repository.custom.impl;

import hospital.entity.Appointment;
import hospital.repository.SuperRepositoryImpl;
import hospital.repository.custom.AppoinmentRepository;
import java.util.List;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Kasun
 */
public class AppointmentRepositoryImpl extends SuperRepositoryImpl<Appointment, String> implements AppoinmentRepository {

    public AppointmentRepositoryImpl() {
    }

    @Override
    public Appointment findByDate(String date) {
        return (Appointment) session.createCriteria(entityClass).add(Restrictions.eq("appointDate", date)).list().get(0);
    }

    @Override
    public int sumAppointmentByDate(String date) throws Exception {
        Long get = (Long) session.createCriteria(entityClass)
                .setProjection(Projections.count("appointDate"))
                .add(Restrictions.eq("appointDate", date)).list().get(0);

        return Integer.parseInt(Long.toString(get));

    }

    @Override
    public List<Appointment> getAllByDate(String date) {
        return session.createCriteria(entityClass).add(Restrictions.eq("appointDate", date)).list();
    }

}

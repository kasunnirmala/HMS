/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.business.custom.impl;

import hospital.business.custom.AppointmentPaymentBO;
import hospital.dto.AppointmentPaymentDTO;
import hospital.entity.AppointmentPayment;
import hospital.repository.RepositoryFactory;
import hospital.repository.custom.AppoinmentRepository;
import hospital.repository.custom.AppointmentPaymentRepository;
import hospital.resource.HibernateUtil;
import org.hibernate.Session;

public class AppointmentPaymentBOImpl implements AppointmentPaymentBO {

    private AppointmentPaymentRepository appointmentPaymentRepository;
    private AppoinmentRepository appoinmentRepository;

    public AppointmentPaymentBOImpl() {
        appointmentPaymentRepository = (AppointmentPaymentRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.APPOINTMENT_PAYMENT);
        appoinmentRepository = (AppoinmentRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.APPOINTMENT);
    }

    @Override
    public boolean addAppointmentPayment(AppointmentPaymentDTO appointmentPaymentDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            appointmentPaymentRepository.setSession(session);
            appoinmentRepository.setSession(session);

            session.beginTransaction();

            AppointmentPayment appointmentPayment = new AppointmentPayment(
                    appoinmentRepository.findById(appointmentPaymentDTO.getAppointmentDTO().getAppointmentID()),
                    appointmentPaymentDTO.getTotalValue(),
                    appointmentPaymentDTO.getChange(),
                    appointmentPaymentDTO.getUserAmount());

            boolean result = appointmentPaymentRepository.save(appointmentPayment);

            session.getTransaction().commit();

            return result;
        }
    }

}

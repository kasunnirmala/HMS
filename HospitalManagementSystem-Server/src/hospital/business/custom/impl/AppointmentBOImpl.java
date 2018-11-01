/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.business.custom.impl;

import hospital.business.BOFactory;
import hospital.business.custom.AppointmentBO;
import hospital.business.custom.DoctorBO;
import hospital.business.custom.PatientBO;
import hospital.dto.AppointmentDTO;
import hospital.entity.Appointment;
import hospital.entity.Doctor;
import hospital.entity.Patient;
import hospital.repository.RepositoryFactory;
import hospital.repository.custom.AppoinmentRepository;
import hospital.repository.custom.DoctorRepository;
import hospital.repository.custom.PatientRepository;
import hospital.resource.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

public class AppointmentBOImpl implements AppointmentBO {

    private AppoinmentRepository appoinmentRepository;
    private DoctorRepository doctorRepository;
    private DoctorBO doctorBO;
    private PatientBO patientBO;

    public AppointmentBOImpl() {
        appoinmentRepository = (AppoinmentRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.APPOINTMENT);
        doctorRepository = (DoctorRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.DOCTOR);
        doctorBO = (DoctorBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.DOCTOR);
        patientBO = (PatientBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PATIENT);
    }

    @Override
    public boolean addAppointment(AppointmentDTO appointmentDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            appoinmentRepository.setSession(session);
            doctorRepository.setSession(session);

            session.beginTransaction();
            System.out.println("BO   " + appointmentDTO.getAppointDate());

            Doctor findDoctorBySLMC = doctorRepository.findDoctorBySLMC(appointmentDTO.getDoctorDTO().getSlmcRegNo());
            Appointment appointment = new Appointment(
                    appointmentDTO.getAppointmentID(),
                    appointmentDTO.getAppointDate(),
                    new Patient(
                            appointmentDTO.getPatientDTO().getPatientID(),
                            appointmentDTO.getPatientDTO().getPatientName(),
                            appointmentDTO.getPatientDTO().getPatientAge(),
                            appointmentDTO.getPatientDTO().getGender()),
                    findDoctorBySLMC
            );
            boolean result = appoinmentRepository.save(appointment);

            session.getTransaction().commit();

            return result;
        }
    }

    @Override
    public boolean updateAppointment(AppointmentDTO appointmentDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            appoinmentRepository.setSession(session);
            doctorRepository.setSession(session);

            session.beginTransaction();

            Appointment appointment = new Appointment(
                    appointmentDTO.getAppointmentID(),
                    appointmentDTO.getAppointDate(),
                    new Patient(
                            appointmentDTO.getPatientDTO().getPatientID(),
                            appointmentDTO.getPatientDTO().getPatientName(),
                            appointmentDTO.getPatientDTO().getPatientAge(),
                            appointmentDTO.getPatientDTO().getGender()),
                    doctorRepository.findDoctorBySLMC(appointmentDTO.getDoctorDTO().getSlmcRegNo()));

            appoinmentRepository.update(appointment);

            session.getTransaction().commit();

            return true;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteAppointment(String appointmentID) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            appoinmentRepository.setSession(session);
            doctorRepository.setSession(session);

            session.beginTransaction();

            Appointment appointment = appoinmentRepository.findById(appointmentID);
            boolean result = false;

            if (appointment != null) {

                appoinmentRepository.delete(appointment);
            }

            session.getTransaction().commit();

            return result;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
    }

    @Override
    public AppointmentDTO findAppointmentByID(String ID) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            appoinmentRepository.setSession(session);
            doctorRepository.setSession(session);

            session.beginTransaction();

            Appointment appointment = appoinmentRepository.findById(ID);

            session.getTransaction().commit();
            if (appointment != null) {
                return new AppointmentDTO(
                        appointment.getAppointmentID(),
                        appointment.getAppointDate(),
                        patientBO.findPatientByID(appointment.getPatient().getPatientID()),
                        doctorBO.findDoctorBySLMC(appointment.getDoctor().getSlmcRegNo())
                );
            }
            return null;
        }

    }

    @Override
    public AppointmentDTO findAppointmentByDate(String date) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            appoinmentRepository.setSession(session);
            doctorRepository.setSession(session);

            session.beginTransaction();

            Appointment appointment = appoinmentRepository.findByDate(date);

            session.getTransaction().commit();
            if (appointment != null) {
                return new AppointmentDTO(
                        appointment.getAppointmentID(),
                        appointment.getAppointDate(),
                        patientBO.findPatientByID(appointment.getPatient().getPatientID()),
                        doctorBO.findDoctorBySLMC(appointment.getDoctor().getSlmcRegNo())
                );
            }
            return null;
        }

    }

    @Override
    public List<AppointmentDTO> getAllAppointments() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            appoinmentRepository.setSession(session);
            doctorRepository.setSession(session);

            session.beginTransaction();

            List<Appointment> appointments = appoinmentRepository.findAll();

            session.getTransaction().commit();

            if (appointments != null) {

                List<AppointmentDTO> alAppointments = new ArrayList<>();

                for (Appointment appointment : appointments) {

                    AppointmentDTO dto = new AppointmentDTO(
                            appointment.getAppointmentID(),
                            appointment.getAppointDate(),
                            patientBO.findPatientByID(appointment.getPatient().getPatientID()),
                            doctorBO.findDoctorBySLMC(appointment.getDoctor().getSlmcRegNo())
                    );
                    alAppointments.add(dto);
                }

                return alAppointments;

            } else {

                return null;
            }

        }
    }

    @Override
    public int sumAppointmentByDate(String date) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            appoinmentRepository.setSession(session);

            session.beginTransaction();

            int result = 0;
            result = appoinmentRepository.sumAppointmentByDate(date);

            session.getTransaction().commit();

            return result;
        }
    }

    @Override
    public AppointmentDTO findAppointmentByPatient(String patientID) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            appoinmentRepository.setSession(session);
            doctorRepository.setSession(session);

            session.beginTransaction();

            List<Appointment> appointments = appoinmentRepository.findAll();

            session.getTransaction().commit();

            for (Appointment appointment : appointments) {
                if (appointment.getPatient().getPatientID().equalsIgnoreCase(patientID)) {
                    return new AppointmentDTO(
                            appointment.getAppointmentID(),
                            appointment.getAppointDate(),
                            patientBO.findPatientByID(appointment.getPatient().getPatientID()),
                            doctorBO.findDoctorBySLMC(appointment.getDoctor().getSlmcRegNo())
                    );
                }
            }
            return null;
        }
    }

    @Override
    public List<AppointmentDTO> getAllByDate(String date) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            appoinmentRepository.setSession(session);
            doctorRepository.setSession(session);

            session.beginTransaction();

            List<Appointment> appointments = appoinmentRepository.getAllByDate(date);

            session.getTransaction().commit();

            if (appointments != null) {

                List<AppointmentDTO> alAppointments = new ArrayList<>();

                for (Appointment appointment : appointments) {

                    AppointmentDTO dto = new AppointmentDTO(
                            appointment.getAppointmentID(),
                            appointment.getAppointDate(),
                            patientBO.findPatientByID(appointment.getPatient().getPatientID()),
                            doctorBO.findDoctorBySLMC(appointment.getDoctor().getSlmcRegNo())
                    );
                    alAppointments.add(dto);
                }

                return alAppointments;

            } else {

                return null;
            }

        }
    }

}

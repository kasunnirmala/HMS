/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.business.custom.impl;

import hospital.business.custom.PatientBO;
import hospital.dto.PatientDTO;
import hospital.entity.Cashier;
import hospital.entity.Patient;
import hospital.repository.RepositoryFactory;
import hospital.repository.custom.PatientRepository;
import hospital.resource.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

public class PatientBOImpl implements PatientBO {

    private PatientRepository patientRepository;

    public PatientBOImpl() {
        patientRepository = (PatientRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.PATIENT);
    }

    @Override
    public boolean addPatient(PatientDTO patientDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            patientRepository.setSession(session);

            session.beginTransaction();

            Patient patient = new Patient(patientDTO.getPatientID(), patientDTO.getPatientName(), patientDTO.getPatientAge(), patientDTO.getGender());
            boolean result = patientRepository.save(patient);

            session.getTransaction().commit();

            return result;
        }
    }

    @Override
    public boolean updatePatient(PatientDTO patientDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            patientRepository.setSession(session);

            session.beginTransaction();

            Patient patient = new Patient(patientDTO.getPatientID(), patientDTO.getPatientName(), patientDTO.getPatientAge(), patientDTO.getGender());
            patientRepository.update(patient);

            session.getTransaction().commit();

            return true;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePatient(String patienID) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            patientRepository.setSession(session);

            session.beginTransaction();

            Patient patient = patientRepository.findById(patienID);
            boolean result = false;

            if (patient != null) {

                patientRepository.delete(patient);
            }

            session.getTransaction().commit();

            return result;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
    }

    @Override
    public PatientDTO findPatientByID(String ID) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            patientRepository.setSession(session);

            session.beginTransaction();

            Patient patient = patientRepository.findById(ID);

            session.getTransaction().commit();

            if (patient != null) {
                return new PatientDTO(patient.getPatientID(), patient.getPatientName(), patient.getPatientAge(), patient.getGender());
            } else {
                return null;
            }

        }
    }

    @Override
    public List<PatientDTO> getAllPatient() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            patientRepository.setSession(session);

            session.beginTransaction();

            List<Patient> patients = patientRepository.findAll();

            session.getTransaction().commit();

            if (patients != null) {

                List<PatientDTO> alPatients = new ArrayList<>();

                for (Patient patient : patients) {
                    PatientDTO dto = new PatientDTO(patient.getPatientID(), patient.getPatientName(), patient.getPatientAge(), patient.getGender());

                    alPatients.add(dto);
                }
                return alPatients;
            } else {
                return null;
            }

        }
    }

}

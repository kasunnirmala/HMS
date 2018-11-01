/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.business.custom.impl;

import hospital.business.custom.TreatmentBO;
import hospital.dto.PatientDTO;
import hospital.dto.TreatmentDTO;
import hospital.entity.Treatment;
import hospital.repository.RepositoryFactory;
import hospital.repository.custom.PatientRepository;
import hospital.repository.custom.TreatmentRepository;
import hospital.resource.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

public class TreatmentBOImpl implements TreatmentBO {

    private TreatmentRepository treatmentRepository;
    private PatientRepository patientRepository;

    public TreatmentBOImpl() {
        treatmentRepository = (TreatmentRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.TREATMENT);
        patientRepository = (PatientRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.PATIENT);
    }

    @Override
    public boolean addTreatment(TreatmentDTO treatmentDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            treatmentRepository.setSession(session);
            patientRepository.setSession(session);

            session.beginTransaction();

            Treatment treatment = new Treatment(treatmentDTO.getTreatment(), treatmentDTO.getMedicine(), treatmentDTO.getAmount(), treatmentDTO.getDate(), patientRepository.findById(treatmentDTO.getPatientDTO().getPatientID()));

            boolean result = treatmentRepository.save(treatment);

            session.getTransaction().commit();

            return result;
        }
    }

    @Override
    public boolean updateTreatment(TreatmentDTO appointmentDTO) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteTreatment(String treatmentID) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TreatmentDTO findTreatmentByID(int ID) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            treatmentRepository.setSession(session);

            session.beginTransaction();

            Treatment treatment = treatmentRepository.findById(ID);

            session.getTransaction().commit();

            if (treatment != null) {

                return new TreatmentDTO(
                        treatment.getTreatmentID(),
                        treatment.getTreatment(),
                        treatment.getMedicine(),
                        treatment.getAmount(),
                        treatment.getDate(),
                        new PatientDTO(
                                treatment.getPatient().getPatientID(),
                                treatment.getPatient().getPatientName(),
                                treatment.getPatient().getPatientAge(),
                                treatment.getPatient().getGender()));

            }
            return null;
        }
    }

    @Override
    public TreatmentDTO findTreatmentByPatient(String patientID) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            treatmentRepository.setSession(session);

            session.beginTransaction();

            Treatment treatment = treatmentRepository.findTreatmentByPatient(patientID);

            session.getTransaction().commit();

            if (treatment != null) {

                return new TreatmentDTO(
                        treatment.getTreatmentID(),
                        treatment.getTreatment(),
                        treatment.getMedicine(),
                        treatment.getAmount(),
                        treatment.getDate(),
                        new PatientDTO(
                                treatment.getPatient().getPatientID(),
                                treatment.getPatient().getPatientName(),
                                treatment.getPatient().getPatientAge(),
                                treatment.getPatient().getGender()));

            }
            return null;
        }
    }

    @Override
    public List<TreatmentDTO> getAllTreatments() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            treatmentRepository.setSession(session);

            session.beginTransaction();

            List<Treatment> treatments = treatmentRepository.findAll();

            session.getTransaction().commit();

            if (treatments != null) {

                List<TreatmentDTO> alTreatments = new ArrayList<>();

                for (Treatment treatment : treatments) {

                    TreatmentDTO dto = new TreatmentDTO(
                            treatment.getTreatmentID(),
                            treatment.getTreatment(),
                            treatment.getMedicine(),
                            treatment.getAmount(),
                            treatment.getDate(),
                            new PatientDTO(
                                    treatment.getPatient().getPatientID(),
                                    treatment.getPatient().getPatientName(),
                                    treatment.getPatient().getPatientAge(),
                                    treatment.getPatient().getGender()));

                    alTreatments.add(dto);
                }

                return alTreatments;

            } else {

                return null;
            }

        }
    }

    @Override
    public List<TreatmentDTO> getAllTreatmentsByPatient(String patientID) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            treatmentRepository.setSession(session);

            session.beginTransaction();

            List<Treatment> treatments = treatmentRepository.findAllByPatient(patientID);

            session.getTransaction().commit();

            if (treatments != null) {

                List<TreatmentDTO> alTreatments = new ArrayList<>();

                for (Treatment treatment : treatments) {

                    TreatmentDTO dto = new TreatmentDTO(
                            treatment.getTreatmentID(),
                            treatment.getTreatment(),
                            treatment.getMedicine(),
                            treatment.getAmount(),
                            treatment.getDate(),
                            new PatientDTO(
                                    treatment.getPatient().getPatientID(),
                                    treatment.getPatient().getPatientName(),
                                    treatment.getPatient().getPatientAge(),
                                    treatment.getPatient().getGender()));

                    alTreatments.add(dto);
                }

                return alTreatments;

            } else {

                return null;
            }

        }
    }

}

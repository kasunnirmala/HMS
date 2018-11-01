/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.business.custom.impl;

import hospital.business.BOFactory;
import hospital.business.custom.DoctorBO;
import hospital.business.custom.PatientBO;
import hospital.business.custom.PrescriptionBO;
import hospital.dto.PrescriptionDTO;
import hospital.dto.PrescriptionDetailsDTO;
import hospital.entity.Doctor;
import hospital.entity.Patient;
import hospital.entity.Prescription;
import hospital.entity.PrescriptionDetails;
import hospital.repository.RepositoryFactory;
import hospital.repository.custom.DoctorRepository;
import hospital.repository.custom.PatientRepository;
import hospital.repository.custom.PrescriptionRepository;
import hospital.resource.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

public class PrescriptionBOImpl implements PrescriptionBO {

    private PrescriptionRepository prescriptionRepository;
    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;

    public PrescriptionBOImpl() {
        prescriptionRepository = (PrescriptionRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.PRESCRIPTION);
        patientRepository = (PatientRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.PATIENT);
        doctorRepository = (DoctorRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.DOCTOR);
    }

    @Override
    public boolean addPrescription(PrescriptionDTO prescriptionDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            prescriptionRepository.setSession(session);
            doctorRepository.setSession(session);
            patientRepository.setSession(session);
            session.beginTransaction();
            System.out.println(prescriptionDTO.getDoctorDTO().getSlmcRegNo());
            System.out.println(prescriptionDTO.getPatientDTO().getPatientID());
            Doctor doctor = doctorRepository.findDoctorBySLMC(prescriptionDTO.getDoctorDTO().getSlmcRegNo());
            Patient patient = patientRepository.findById(prescriptionDTO.getPatientDTO().getPatientID());

            List<PrescriptionDetails> prescriptionDetailses = new ArrayList<>();
            for (PrescriptionDetailsDTO prescriptionDetailsDTO : prescriptionDTO.getPrescriptionDetailsDTOs()) {
                prescriptionDetailses.add(new PrescriptionDetails(
                        prescriptionDetailsDTO.getMedicineName(),
                        prescriptionDetailsDTO.getDose(),
                        prescriptionDetailsDTO.getFrequency(),
                        prescriptionDetailsDTO.getDays())
                );
            }
            System.out.println("presID" + prescriptionDTO.getPrescriptionID());
            Prescription prescription = new Prescription(
                    prescriptionDTO.getPrescriptionID(),
                    patient,
                    doctor,
                    prescriptionDetailses
            );
            boolean result = prescriptionRepository.save(prescription);

            session.getTransaction().commit();

            return result;
        }
    }

    @Override
    public boolean updatePrescription(PrescriptionDTO prescriptionDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            prescriptionRepository.setSession(session);
            doctorRepository.setSession(session);
            patientRepository.setSession(session);

            session.beginTransaction();

            Doctor doctor = doctorRepository.findById(prescriptionDTO.getDoctorDTO().getDoctorID());
            Patient patient = patientRepository.findById(prescriptionDTO.getPatientDTO().getPatientID());

            List<PrescriptionDetails> prescriptionDetailses = new ArrayList<>();
            for (PrescriptionDetailsDTO prescriptionDetailsDTO : prescriptionDTO.getPrescriptionDetailsDTOs()) {
                prescriptionDetailses.add(new PrescriptionDetails(
                        prescriptionDetailsDTO.getMedicineName(),
                        prescriptionDetailsDTO.getDose(),
                        prescriptionDetailsDTO.getFrequency(),
                        prescriptionDetailsDTO.getDays())
                );
            }
            Prescription prescription = new Prescription(
                    prescriptionDTO.getPrescriptionID(),
                    patient,
                    doctor,
                    prescriptionDetailses
            );
            prescriptionRepository.update(prescription);

            session.getTransaction().commit();

            return true;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePrescription(String presID) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            prescriptionRepository.setSession(session);
            doctorRepository.setSession(session);
            patientRepository.setSession(session);

            session.beginTransaction();

            Prescription prescription = prescriptionRepository.findById(presID);
            boolean result = false;

            if (prescription != null) {

                prescriptionRepository.delete(prescription);
            }

            session.getTransaction().commit();

            return result;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
    }

    @Override
    public PrescriptionDTO findPrescriptionByID(String ID) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            prescriptionRepository.setSession(session);
            doctorRepository.setSession(session);
            patientRepository.setSession(session);

            session.beginTransaction();

            Prescription prescription = prescriptionRepository.findById(ID);

            session.getTransaction().commit();

            if (prescription != null) {
                List<PrescriptionDetailsDTO> prescriptionDetailsDTOs = new ArrayList<>();
                for (PrescriptionDetails prescriptionDetails : prescription.getPrescriptionDetails()) {
                    prescriptionDetailsDTOs.add(new PrescriptionDetailsDTO(prescriptionDetails.getPresDetailID(), prescriptionDetails.getMedicineName(), prescriptionDetails.getDose(), prescriptionDetails.getFrequency(), prescriptionDetails.getDays()));
                }
                return new PrescriptionDTO(
                        prescription.getPrescriptionID(),
                        ((PatientBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PATIENT)).findPatientByID(prescription.getPatient().getPatientID()),
                        ((DoctorBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.DOCTOR)).findDoctorBySLMC(prescription.getDoctor().getSlmcRegNo()),
                        prescriptionDetailsDTOs
                );
            } else {
                return null;
            }

        }
    }

    @Override
    public List<PrescriptionDTO> getAllPrescriptions() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            prescriptionRepository.setSession(session);
            doctorRepository.setSession(session);
            patientRepository.setSession(session);

            session.beginTransaction();

            List<Prescription> prescriptions = prescriptionRepository.findAll();

            session.getTransaction().commit();

            if (prescriptions != null) {

                List<PrescriptionDTO> alPrescription = new ArrayList<>();

                for (Prescription prescription : prescriptions) {
                    List<PrescriptionDetailsDTO> prescriptionDetailsDTOs = new ArrayList<>();
                    for (PrescriptionDetails prescriptionDetails : prescription.getPrescriptionDetails()) {
                        prescriptionDetailsDTOs.add(new PrescriptionDetailsDTO(prescriptionDetails.getPresDetailID(), prescriptionDetails.getMedicineName(), prescriptionDetails.getDose(), prescriptionDetails.getFrequency(), prescriptionDetails.getDays()));
                    }
                    PrescriptionDTO dto = new PrescriptionDTO(
                            prescription.getPrescriptionID(),
                            ((PatientBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PATIENT)).findPatientByID(prescription.getPatient().getPatientID()),
                            ((DoctorBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.DOCTOR)).findDoctorBySLMC(prescription.getDoctor().getSlmcRegNo()),
                            prescriptionDetailsDTOs
                    );

                    alPrescription.add(dto);
                }
                return alPrescription;
            } else {
                return null;
            }

        }
    }

    @Override
    public PrescriptionDTO findPrescriptionByPatientID(String patientID) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            prescriptionRepository.setSession(session);
            doctorRepository.setSession(session);
            patientRepository.setSession(session);

            session.beginTransaction();

            List<Prescription> prescriptions = prescriptionRepository.findAll();

            // Prescription prescription = prescriptionRepository.findByPatientId(patientID);
            session.getTransaction().commit();
            for (Prescription prescription : prescriptions) {
                if (prescription.getPatient().getPatientID().equalsIgnoreCase(patientID)) {
                    List<PrescriptionDetailsDTO> prescriptionDetailsDTOs = new ArrayList<>();
                    for (PrescriptionDetails prescriptionDetails : prescription.getPrescriptionDetails()) {
                        prescriptionDetailsDTOs.add(new PrescriptionDetailsDTO(prescriptionDetails.getPresDetailID(), prescriptionDetails.getMedicineName(), prescriptionDetails.getDose(), prescriptionDetails.getFrequency(), prescriptionDetails.getDays()));
                    }
                    return new PrescriptionDTO(
                            prescription.getPrescriptionID(),
                            ((PatientBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PATIENT)).findPatientByID(prescription.getPatient().getPatientID()),
                            ((DoctorBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.DOCTOR)).findDoctorBySLMC(prescription.getDoctor().getSlmcRegNo()),
                            prescriptionDetailsDTOs
                    );
                }
            }
        }
        return null;
    }

}

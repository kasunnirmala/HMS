/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.business.custom.impl;

import hospital.business.custom.NurseBO;
import hospital.dto.NurseDTO;
import hospital.entity.Nurse;
import hospital.repository.RepositoryFactory;
import hospital.repository.custom.NurseRepository;
import hospital.resource.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

public class NurseBOImpl implements NurseBO {

    private NurseRepository nurseRepository;

    public NurseBOImpl() {
        nurseRepository = (NurseRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.NURSE);
    }

    @Override
    public boolean addNurse(NurseDTO nurseDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            nurseRepository.setSession(session);

            session.beginTransaction();

            Nurse nurse = new Nurse(
                    nurseDTO.getNurseID(),
                    nurseDTO.getFirstName(),
                    nurseDTO.getMiddleName(),
                    nurseDTO.getLatName(),
                    nurseDTO.getGender(),
                    nurseDTO.getAddress(),
                    nurseDTO.getDob(),
                    nurseDTO.getMobileNo(),
                    nurseDTO.getPhoneNo(),
                    nurseDTO.getEmail(),
                    nurseDTO.getNurseImage()
            );
            boolean result = nurseRepository.save(nurse);

            session.getTransaction().commit();

            return result;
        }
    }

    @Override
    public boolean updateNurse(NurseDTO nurseDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            nurseRepository.setSession(session);

            session.beginTransaction();

            Nurse nurse = new Nurse(
                    nurseDTO.getNurseID(),
                    nurseDTO.getFirstName(),
                    nurseDTO.getMiddleName(),
                    nurseDTO.getLatName(),
                    nurseDTO.getGender(),
                    nurseDTO.getAddress(),
                    nurseDTO.getDob(),
                    nurseDTO.getMobileNo(),
                    nurseDTO.getPhoneNo(),
                    nurseDTO.getEmail(),
                    nurseDTO.getNurseImage()
            );
            nurseRepository.update(nurse);

            session.getTransaction().commit();

            return true;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteNurse(String nurseID) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            nurseRepository.setSession(session);

            session.beginTransaction();

            Nurse nurse = nurseRepository.findById(nurseID);
            boolean result = false;

            if (nurse != null) {

                nurseRepository.delete(nurse);
            }

            session.getTransaction().commit();

            return result;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
    }

    @Override
    public NurseDTO findNurseByID(String ID) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            nurseRepository.setSession(session);

            session.beginTransaction();

            Nurse nurse = nurseRepository.findById(ID);

            session.getTransaction().commit();

            if (nurse != null) {
                return new NurseDTO(
                        nurse.getNurseID(),
                        nurse.getFirstName(),
                        nurse.getMiddleName(),
                        nurse.getLatName(),
                        nurse.getGender(),
                        nurse.getAddress(),
                        nurse.getDob(),
                        nurse.getMobileNo(),
                        nurse.getPhoneNo(),
                        nurse.getEmail(),
                        nurse.getNurseImage()
                );
            } else {
                return null;
            }

        }
    }

    @Override
    public NurseDTO findNurseByName(String name) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            nurseRepository.setSession(session);

            session.beginTransaction();

            Nurse nurse = nurseRepository.findByName(name);

            session.getTransaction().commit();

            if (nurse != null) {
                return new NurseDTO(
                        nurse.getNurseID(),
                        nurse.getFirstName(),
                        nurse.getMiddleName(),
                        nurse.getLatName(),
                        nurse.getGender(),
                        nurse.getAddress(),
                        nurse.getDob(),
                        nurse.getMobileNo(),
                        nurse.getPhoneNo(),
                        nurse.getEmail(),
                        nurse.getNurseImage()
                );
            } else {
                return null;
            }

        }
    }

    @Override
    public List<NurseDTO> getAllNurses() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            nurseRepository.setSession(session);

            session.beginTransaction();

            List<Nurse> nurses = nurseRepository.findAll();

            session.getTransaction().commit();

            if (nurses != null) {

                List<NurseDTO> alNurses = new ArrayList<>();

                for (Nurse nurse : nurses) {
                    NurseDTO dto = new NurseDTO(
                            nurse.getNurseID(),
                            nurse.getFirstName(),
                            nurse.getMiddleName(),
                            nurse.getLatName(),
                            nurse.getGender(),
                            nurse.getAddress(),
                            nurse.getDob(),
                            nurse.getMobileNo(),
                            nurse.getPhoneNo(),
                            nurse.getEmail(),
                            nurse.getNurseImage()
                    );

                    alNurses.add(dto);
                }
                return alNurses;
            } else {
                return null;
            }

        }
    }

}

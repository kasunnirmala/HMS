/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.business.custom.impl;

import hospital.business.BOFactory;
import hospital.business.custom.PatientBO;
import hospital.business.custom.RoomBO;
import hospital.business.custom.RoomReserveBO;
import hospital.dto.PatientDetailsDTO;
import hospital.dto.RoomReserveDTO;
import hospital.entity.PatientDetails;
import hospital.entity.RoomReserve;
import hospital.repository.RepositoryFactory;
import hospital.repository.custom.PatientRepository;
import hospital.repository.custom.RoomRepository;
import hospital.repository.custom.RoomReserveRepository;
import hospital.resource.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

public class RoomReserveBOImpl implements RoomReserveBO {

    private RoomReserveRepository roomReserveRepository;
    private PatientRepository patientRepository;
    private RoomRepository roomRepository;
    private PatientBO patientBO;
    private RoomBO roomBO;

    public RoomReserveBOImpl() {
        roomReserveRepository = (RoomReserveRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.ROOM_RESERVE);
        roomRepository = (RoomRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.ROOM);
        patientRepository = (PatientRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.PATIENT);
        patientBO = (PatientBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PATIENT);
        roomBO = (RoomBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ROOM);
    }

    @Override
    public boolean addRoomReserve(RoomReserveDTO roomReserveDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            roomReserveRepository.setSession(session);
            roomRepository.setSession(session);
            patientRepository.setSession(session);

            session.beginTransaction();

            RoomReserve roomReserve = new RoomReserve(
                    roomReserveDTO.getResID(),
                    patientRepository.findById(roomReserveDTO.getPatientDTO().getPatientID()),
                    new PatientDetails(
                            roomReserveDTO.getPatientDetailsDTO().getAddress(),
                            roomReserveDTO.getPatientDetailsDTO().getContact(),
                            roomReserveDTO.getPatientDetailsDTO().getGuardian()),
                    roomRepository.findById(roomReserveDTO.getRoomDTO().getRoomNo()),
                    roomReserveDTO.isReserve(),
                    roomReserveDTO.getDate()
            );

            boolean result = roomReserveRepository.save(roomReserve);

            session.getTransaction().commit();

            return result;
        }
    }

    @Override
    public boolean updateRoomReserve(RoomReserveDTO roomReserveDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            roomReserveRepository.setSession(session);
            roomRepository.setSession(session);
            patientRepository.setSession(session);

            session.beginTransaction();

            RoomReserve roomReserve = new RoomReserve(
                    roomReserveDTO.getResID(),
                    patientRepository.findById(roomReserveDTO.getPatientDTO().getPatientID()),
                    new PatientDetails(
                            roomReserveDTO.getPatientDetailsDTO().getAddress(),
                            roomReserveDTO.getPatientDetailsDTO().getContact(),
                            roomReserveDTO.getPatientDetailsDTO().getGuardian()),
                    roomRepository.findById(roomReserveDTO.getRoomDTO().getRoomNo()),
                    roomReserveDTO.isReserve(),
                    roomReserveDTO.getDate()
            );

            roomReserveRepository.update(roomReserve);

            session.getTransaction().commit();

            return true;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteRoomReserve(String roomReserveID) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            roomReserveRepository.setSession(session);

            session.beginTransaction();

            RoomReserve roomReserve = roomReserveRepository.findById(roomReserveID);
            boolean result = false;

            if (roomReserve != null) {

                roomReserveRepository.delete(roomReserve);
            }

            session.getTransaction().commit();

            return result;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
    }

    @Override
    public RoomReserveDTO findRoomReserveByID(String ID) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            roomReserveRepository.setSession(session);

            session.beginTransaction();

            RoomReserve roomReserve = roomReserveRepository.findById(ID);

            session.getTransaction().commit();

            if (roomReserve != null) {

                return new RoomReserveDTO(
                        roomReserve.getResID(),
                        patientBO.findPatientByID(roomReserve.getPatient().getPatientID()),
                        new PatientDetailsDTO(
                                roomReserve.getPatientDetails().getAddress(),
                                roomReserve.getPatientDetails().getContact(),
                                roomReserve.getPatientDetails().getGuardian()),
                        roomBO.findRoomByID(roomReserve.getRoom().getRoomNo()),
                        roomReserve.isReserve(),
                        roomReserve.getDate()
                );

            } else {

                return null;
            }

        }
    }

    @Override
    public List<RoomReserveDTO> getAllRoomReserves() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            roomReserveRepository.setSession(session);

            session.beginTransaction();

            List<RoomReserve> roomReserves = roomReserveRepository.findAll();

            session.getTransaction().commit();

            if (roomReserves != null) {

                List<RoomReserveDTO> alReserves = new ArrayList<>();

                for (RoomReserve roomReserve : roomReserves) {

                    RoomReserveDTO dto = new RoomReserveDTO(
                            roomReserve.getResID(),
                            patientBO.findPatientByID(roomReserve.getPatient().getPatientID()),
                            new PatientDetailsDTO(
                                    roomReserve.getPatientDetails().getAddress(),
                                    roomReserve.getPatientDetails().getContact(),
                                    roomReserve.getPatientDetails().getGuardian()),
                            roomBO.findRoomByID(roomReserve.getRoom().getRoomNo()),
                            roomReserve.isReserve(),
                            roomReserve.getDate()
                    );

                    alReserves.add(dto);
                }

                return alReserves;

            } else {

                return null;
            }

        }
    }

    @Override
    public List<RoomReserveDTO> getAllReservedRoomReserves() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            roomReserveRepository.setSession(session);

            session.beginTransaction();

            List<RoomReserve> roomReserves = roomReserveRepository.findAllReserved();

            session.getTransaction().commit();

            if (roomReserves != null) {

                List<RoomReserveDTO> alReserves = new ArrayList<>();

                for (RoomReserve roomReserve : roomReserves) {

                    RoomReserveDTO dto = new RoomReserveDTO(
                            roomReserve.getResID(),
                            patientBO.findPatientByID(roomReserve.getPatient().getPatientID()),
                            new PatientDetailsDTO(
                                    roomReserve.getPatientDetails().getAddress(),
                                    roomReserve.getPatientDetails().getContact(),
                                    roomReserve.getPatientDetails().getGuardian()),
                            roomBO.findRoomByID(roomReserve.getRoom().getRoomNo()),
                            roomReserve.isReserve(),
                            roomReserve.getDate()
                    );

                    alReserves.add(dto);
                }

                return alReserves;

            } else {

                return null;
            }

        }
    }

    @Override
    public List<RoomReserveDTO> getAllNonReservedRoomReserves() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            roomReserveRepository.setSession(session);

            session.beginTransaction();

            List<RoomReserve> roomReserves = roomReserveRepository.findAllNonReserved();

            session.getTransaction().commit();

            if (roomReserves != null) {

                List<RoomReserveDTO> alReserves = new ArrayList<>();

                for (RoomReserve roomReserve : roomReserves) {

                    RoomReserveDTO dto = new RoomReserveDTO(
                            roomReserve.getResID(),
                            patientBO.findPatientByID(roomReserve.getPatient().getPatientID()),
                            new PatientDetailsDTO(
                                    roomReserve.getPatientDetails().getAddress(),
                                    roomReserve.getPatientDetails().getContact(),
                                    roomReserve.getPatientDetails().getGuardian()),
                            roomBO.findRoomByID(roomReserve.getRoom().getRoomNo()),
                            roomReserve.isReserve(),
                            roomReserve.getDate()
                    );

                    alReserves.add(dto);
                }

                return alReserves;

            } else {

                return null;
            }

        }
    }

    @Override
    public RoomReserveDTO findRoomReserveByRoomNo(String roomNo) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            roomReserveRepository.setSession(session);

            session.beginTransaction();
            List<RoomReserve> findAll = roomReserveRepository.findAll();
            session.getTransaction().commit();

            for (RoomReserve roomReserve : findAll) {
                if (roomReserve.getRoom().getRoomNo().equalsIgnoreCase(roomNo)) {

                    return new RoomReserveDTO(
                            roomReserve.getResID(),
                            patientBO.findPatientByID(roomReserve.getPatient().getPatientID()),
                            new PatientDetailsDTO(
                                    roomReserve.getPatientDetails().getAddress(),
                                    roomReserve.getPatientDetails().getContact(),
                                    roomReserve.getPatientDetails().getGuardian()),
                            roomBO.findRoomByID(roomReserve.getRoom().getRoomNo()),
                            roomReserve.isReserve(),
                            roomReserve.getDate()
                    );

                }
            }
            return null;
        }
    }

    @Override
    public RoomReserveDTO findRoomReserveByPatientID(String patientID) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            roomReserveRepository.setSession(session);

            session.beginTransaction();

            List<RoomReserve> roomReserves = roomReserveRepository.findAll();

            //  RoomReserve roomReserve = roomReserveRepository.findByPatientId(patientID);
            session.getTransaction().commit();
            for (RoomReserve roomReserve : roomReserves) {
                if (roomReserve.getPatient().getPatientID().equalsIgnoreCase(patientID)) {

                    return new RoomReserveDTO(
                            roomReserve.getResID(),
                            patientBO.findPatientByID(roomReserve.getPatient().getPatientID()),
                            new PatientDetailsDTO(
                                    roomReserve.getPatientDetails().getAddress(),
                                    roomReserve.getPatientDetails().getContact(),
                                    roomReserve.getPatientDetails().getGuardian()),
                            roomBO.findRoomByID(roomReserve.getRoom().getRoomNo()),
                            roomReserve.isReserve(),
                            roomReserve.getDate()
                    );

                }
            }

            return null;
        }

    }

}

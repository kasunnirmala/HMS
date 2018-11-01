/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.business.custom.impl;

import hospital.business.custom.RoomBO;
import hospital.dto.RoomDTO;
import hospital.entity.Doctor;
import hospital.entity.Room;
import hospital.repository.RepositoryFactory;
import hospital.repository.custom.RoomRepository;
import hospital.resource.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

public class RoomBOImpl implements RoomBO {

    private RoomRepository roomRepository;

    public RoomBOImpl() {
        roomRepository = (RoomRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.ROOM);
    }

    @Override
    public boolean addRoom(RoomDTO roomDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            roomRepository.setSession(session);

            session.beginTransaction();
            Room room = new Room(roomDTO.getRoomNo(), roomDTO.getRoomType(), roomDTO.getRoomFloor());

            boolean result = roomRepository.save(room);

            session.getTransaction().commit();

            return result;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            roomRepository.setSession(session);

            session.beginTransaction();

            Room room = new Room(roomDTO.getRoomNo(), roomDTO.getRoomType(), roomDTO.getRoomFloor());
            roomRepository.update(room);

            session.getTransaction().commit();

            return true;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteRoom(String roomID) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            roomRepository.setSession(session);

            session.beginTransaction();

            Room room = roomRepository.findById(roomID);
            boolean result = false;

            if (room != null) {

                roomRepository.delete(room);
                result = true;
            }

            session.getTransaction().commit();

            return result;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
    }

    @Override
    public RoomDTO findRoomByID(String roomID) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            roomRepository.setSession(session);

            session.beginTransaction();

            Room room = roomRepository.findById(roomID);

            session.getTransaction().commit();
            if (room != null) {
                return new RoomDTO(room.getRoomNo(), room.getRoomType(), room.getRoomFloor());
            } else {
                return null;
            }

        }
    }

    @Override
    public List<RoomDTO> getAllRooms() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            roomRepository.setSession(session);

            session.beginTransaction();

            List<Room> rooms = roomRepository.findAll();

            session.getTransaction().commit();

            if (rooms != null) {

                List<RoomDTO> allRoomDTOs = new ArrayList<>();

                for (Room room : rooms) {

                    RoomDTO dto = new RoomDTO(room.getRoomNo(), room.getRoomType(), room.getRoomFloor());
                    allRoomDTOs.add(dto);
                }

                return allRoomDTOs;

            } else {

                return null;
            }

        }
    }

}

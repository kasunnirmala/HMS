/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.business.custom.impl;

import hospital.business.custom.RoomTypeBO;
import hospital.dto.RoomTypeDTO;
import hospital.entity.RoomType;
import hospital.repository.RepositoryFactory;
import hospital.repository.custom.RoomTypeRepository;
import hospital.resource.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

public class RoomTypeBOImpl implements RoomTypeBO {

    private RoomTypeRepository roomTypeRepository;

    public RoomTypeBOImpl() {
        roomTypeRepository = (RoomTypeRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.ROOM_TYPE);
    }

    @Override
    public boolean addRoomType(RoomTypeDTO roomTypeDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            roomTypeRepository.setSession(session);

            session.beginTransaction();

            RoomType roomType = new RoomType(roomTypeDTO.getRoomType(), roomTypeDTO.getRoomPrice(), roomTypeDTO.getDate());
            boolean result = roomTypeRepository.save(roomType);

            session.getTransaction().commit();

            return result;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateRoomType(RoomTypeDTO roomTypeDTO) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteRoomType(int roomTypeID) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoomTypeDTO findRoomTypeID(int ID) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoomTypeDTO findLastRoomTypeBYType(String roomTypes) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            roomTypeRepository.setSession(session);

            session.beginTransaction();

            RoomType findLastRoomTypeBYType = roomTypeRepository.findLastRoomTypeBYType(roomTypes);
            session.getTransaction().commit();

            RoomTypeDTO roomTypeDTO = new RoomTypeDTO(
                    findLastRoomTypeBYType.getRoomTypeID(),
                    findLastRoomTypeBYType.getRoomType(),
                    findLastRoomTypeBYType.getRoomPrice(),
                    findLastRoomTypeBYType.getDate()
            );

            return roomTypeDTO;
        } catch (Exception exp) {
            exp.printStackTrace();
            return null;
        }
    }

    @Override
    public List<RoomTypeDTO> getAllRoomTypes() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

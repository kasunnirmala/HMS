/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.service.custom;

import hospital.dto.RoomTypeDTO;
import hospital.service.SuperService;
import java.util.List;

/**
 *
 * @author Kasun
 */
public interface RoomTypeService extends SuperService {

    public boolean addRoomType(RoomTypeDTO roomTypeDTO) throws Exception;

    public boolean updateRoomType(RoomTypeDTO roomTypeDTO) throws Exception;

    public boolean deleteRoomType(int roomTypeID) throws Exception;

    public RoomTypeDTO findRoomTypeID(int ID) throws Exception;

    public RoomTypeDTO findLastRoomTypeBYType(String roomTypes) throws Exception;

    public List<RoomTypeDTO> getAllRoomTypes() throws Exception;
}

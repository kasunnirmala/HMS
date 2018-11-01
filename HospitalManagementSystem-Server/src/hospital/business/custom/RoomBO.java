/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.business.custom;

import hospital.business.SuperBO;
import hospital.dto.DoctorDTO;
import hospital.dto.RoomDTO;
import java.util.List;

/**
 *
 * @author Kasun
 */
public interface RoomBO extends SuperBO {

    public boolean addRoom(RoomDTO roomDTO) throws Exception;

    public boolean updateRoom(RoomDTO roomDTO) throws Exception;

    public boolean deleteRoom(String roomID) throws Exception;

    public RoomDTO findRoomByID(String roomID) throws Exception;

    public List<RoomDTO> getAllRooms() throws Exception;
}

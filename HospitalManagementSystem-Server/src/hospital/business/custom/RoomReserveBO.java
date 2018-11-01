/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.business.custom;

import hospital.business.SuperBO;
import hospital.dto.RoomReserveDTO;
import java.util.List;

/**
 *
 * @author Kasun
 */
public interface RoomReserveBO extends SuperBO {

    public boolean addRoomReserve(RoomReserveDTO roomReserveDTO) throws Exception;

    public boolean updateRoomReserve(RoomReserveDTO roomReserveDTO) throws Exception;

    public boolean deleteRoomReserve(String roomReserveID) throws Exception;

    public RoomReserveDTO findRoomReserveByID(String ID) throws Exception;

    public RoomReserveDTO findRoomReserveByRoomNo(String roomNo) throws Exception;

    public RoomReserveDTO findRoomReserveByPatientID(String patientID) throws Exception;

    public List<RoomReserveDTO> getAllRoomReserves() throws Exception;

    public List<RoomReserveDTO> getAllReservedRoomReserves() throws Exception;

    public List<RoomReserveDTO> getAllNonReservedRoomReserves() throws Exception;

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.repository.custom;

import hospital.dto.RoomTypeDTO;
import hospital.entity.RoomType;
import hospital.repository.SuperRepository;

/**
 *
 * @author Kasun
 */
public interface RoomTypeRepository extends SuperRepository<RoomType, Integer> {

    public RoomType findLastRoomTypeBYType(String roomTypes) throws Exception;
}

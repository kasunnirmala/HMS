/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.repository.custom.impl;

import hospital.dto.RoomTypeDTO;
import hospital.entity.RoomType;
import hospital.repository.SuperRepositoryImpl;
import hospital.repository.custom.RoomTypeRepository;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class RoomTypeRepositoryImpl extends SuperRepositoryImpl<RoomType, Integer> implements RoomTypeRepository {

    public RoomTypeRepositoryImpl() {
    }

    @Override
    public RoomType findLastRoomTypeBYType(String roomTypes) throws Exception {
        return (RoomType) session.createCriteria(entityClass)
                .add(Restrictions.eq("roomType", roomTypes))
                .addOrder(Order.desc("roomTypeID")).list().get(0);

    }

}

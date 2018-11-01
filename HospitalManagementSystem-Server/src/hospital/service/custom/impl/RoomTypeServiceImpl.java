/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.service.custom.impl;

import hospital.business.BOFactory;
import hospital.business.custom.RoomTypeBO;
import hospital.dto.RoomTypeDTO;
import hospital.observer.Observer;
import hospital.observer.Subject;
import hospital.service.custom.RoomTypeService;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kasun
 */
public class RoomTypeServiceImpl extends UnicastRemoteObject implements RoomTypeService, Subject {

    private RoomTypeBO roomTypeBO;
    private static ArrayList<Observer> alObservers = new ArrayList<>();

    public RoomTypeServiceImpl() throws RemoteException {
        roomTypeBO = (RoomTypeBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ROOM_TYPE);
    }

    @Override
    public boolean addRoomType(RoomTypeDTO roomTypeDTO) throws Exception {
        return roomTypeBO.addRoomType(roomTypeDTO);
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
        return roomTypeBO.findLastRoomTypeBYType(roomTypes);
    }

    @Override
    public List<RoomTypeDTO> getAllRoomTypes() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void registerObserver(Observer observer) throws Exception {
        alObservers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) throws Exception {
        alObservers.remove(observer);
    }

    @Override
    public void notifyObservers() throws Exception {
        new Thread(() -> {
            for (Observer observer : alObservers) {
                try {
                    observer.updateObservers();
                } catch (Exception ex) {
                    Logger.getLogger(SpecializationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }
}

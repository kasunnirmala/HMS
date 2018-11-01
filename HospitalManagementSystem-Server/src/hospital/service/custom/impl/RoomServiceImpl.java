/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.service.custom.impl;

import hospital.business.BOFactory;
import hospital.business.custom.RoomBO;
import hospital.dto.RoomDTO;
import hospital.observer.Observer;
import hospital.observer.Subject;
import hospital.service.custom.RoomService;
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
public class RoomServiceImpl extends UnicastRemoteObject implements RoomService, Subject {

    private RoomBO roomBO;
    private static ArrayList<Observer> alObservers = new ArrayList<>();

    public RoomServiceImpl() throws RemoteException {
        roomBO = (RoomBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ROOM);
    }

    @Override
    public boolean addRoom(RoomDTO roomDTO) throws Exception {
        return roomBO.addRoom(roomDTO);
    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteRoom(String roomID) throws Exception {
        return roomBO.deleteRoom(roomID);
    }

    @Override
    public RoomDTO findRoomByID(String roomID) throws Exception {
        return roomBO.findRoomByID(roomID);
    }

    @Override
    public List<RoomDTO> getAllRooms() throws Exception {
        return roomBO.getAllRooms();
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

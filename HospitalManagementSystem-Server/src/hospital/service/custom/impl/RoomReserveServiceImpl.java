/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.service.custom.impl;

import hospital.business.BOFactory;
import hospital.business.custom.RoomReserveBO;
import hospital.dto.RoomReserveDTO;
import hospital.observer.Observer;
import hospital.observer.Subject;
import hospital.service.custom.RoomReserveService;
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
public class RoomReserveServiceImpl extends UnicastRemoteObject implements RoomReserveService, Subject {

    private static ArrayList<Observer> alObservers = new ArrayList<>();
    private RoomReserveBO roomReserveBO;

    public RoomReserveServiceImpl() throws RemoteException {
        roomReserveBO = (RoomReserveBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ROOM_RESERVE);
    }

    @Override
    public boolean addRoomReserve(RoomReserveDTO roomReserveDTO) throws Exception {
        boolean result = roomReserveBO.addRoomReserve(roomReserveDTO);
        notifyObservers();
        return result;
    }

    @Override
    public boolean updateRoomReserve(RoomReserveDTO roomReserveDTO) throws Exception {
        boolean result = roomReserveBO.updateRoomReserve(roomReserveDTO);
        notifyObservers();
        return result;
    }

    @Override
    public boolean deleteRoomReserve(String roomReserveID) throws Exception {
        boolean result = roomReserveBO.deleteRoomReserve(roomReserveID);
        notifyObservers();
        return result;
    }

    @Override
    public RoomReserveDTO findRoomReserveByID(String ID) throws Exception {
        return roomReserveBO.findRoomReserveByID(ID);
    }

    @Override
    public List<RoomReserveDTO> getAllRoomReserves() throws Exception {
        return roomReserveBO.getAllRoomReserves();
    }

    @Override
    public List<RoomReserveDTO> getAllReservedRoomReserves() throws Exception {
        return roomReserveBO.getAllReservedRoomReserves();
    }

    @Override
    public List<RoomReserveDTO> getAllNonReservedRoomReserves() throws Exception {
        return roomReserveBO.getAllNonReservedRoomReserves();
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

    @Override
    public RoomReserveDTO findRoomReserveByRoomNo(String roomNo) throws Exception {
        return roomReserveBO.findRoomReserveByRoomNo(roomNo);
    }

    @Override
    public RoomReserveDTO findRoomReserveByPatientID(String patientID) throws Exception {
        return roomReserveBO.findRoomReserveByPatientID(patientID);
    }

}

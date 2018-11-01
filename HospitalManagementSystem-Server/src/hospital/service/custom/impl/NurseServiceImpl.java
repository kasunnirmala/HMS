/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.service.custom.impl;

import hospital.business.BOFactory;
import hospital.business.custom.NurseBO;
import hospital.dto.NurseDTO;
import hospital.observer.Observer;
import hospital.observer.Subject;
import hospital.service.custom.NurseService;
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
public class NurseServiceImpl extends UnicastRemoteObject implements NurseService, Subject {

    private NurseBO nurseBO;
    private static ArrayList<Observer> alObservers = new ArrayList<>();

    public NurseServiceImpl() throws RemoteException {
        nurseBO = (NurseBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.NURSE);
    }

    @Override
    public boolean addNurse(NurseDTO nurseDTO) throws Exception {
        boolean result = nurseBO.addNurse(nurseDTO);
        notifyObservers();
        return result;
    }

    @Override
    public boolean updateNurse(NurseDTO nurseDTO) throws Exception {
        boolean result = nurseBO.updateNurse(nurseDTO);
        notifyObservers();
        return result;
    }

    @Override
    public boolean deleteNurse(String nurseID) throws Exception {
        boolean result = nurseBO.deleteNurse(nurseID);
        notifyObservers();
        return result;
    }

    @Override
    public NurseDTO findNurseByID(String ID) throws Exception {
        return nurseBO.findNurseByID(ID);
    }

    @Override
    public NurseDTO findNurseByName(String name) throws Exception {
        return nurseBO.findNurseByName(name);
    }

    @Override
    public List<NurseDTO> getAllNurses() throws Exception {
        return nurseBO.getAllNurses();
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

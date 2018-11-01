/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.service.custom.impl;

import hospital.business.BOFactory;
import hospital.business.custom.SpecializationBO;
import hospital.dto.SpecializationDTO;
import hospital.observer.Observer;
import hospital.observer.Subject;
import hospital.service.custom.SpecializationService;
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
public class SpecializationServiceImpl extends UnicastRemoteObject implements SpecializationService, Subject {

    private SpecializationBO specializationBO;
    private static ArrayList<Observer> alObservers = new ArrayList<>();

    public SpecializationServiceImpl() throws RemoteException {
        specializationBO = (SpecializationBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SPECIALIZATION);
    }

    @Override
    public boolean addSpecialization(SpecializationDTO specializationDTO) throws Exception {
        boolean result = specializationBO.addSpecialization(specializationDTO);
        notifyObservers();
        return result;
    }

    @Override
    public boolean updateSpecialization(SpecializationDTO specializationDTO) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteSpecialization(String SpecializationID) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SpecializationDTO findSpecializationByID(String ID) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SpecializationDTO> getAllSpecialization() throws Exception {
        return specializationBO.getAllSpecialization();
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
    public SpecializationDTO findSpecializationByName(String name) throws Exception {
        return specializationBO.findSpecializationByName(name);
    }

}

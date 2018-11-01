/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.service.custom.impl;

import hospital.business.BOFactory;
import hospital.business.custom.OtherChargesBO;
import hospital.dto.OtherServicesDTO;
import hospital.observer.Observer;
import hospital.observer.Subject;
import hospital.service.custom.OtherChargesService;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 *
 * @author Kasun
 */
public class OtherChargesServiceImpl extends UnicastRemoteObject implements OtherChargesService, Subject {

    private static ArrayList<Observer> alObservers = new ArrayList<>();
    private OtherChargesBO otherChargesBO;

    public OtherChargesServiceImpl() throws RemoteException {
        otherChargesBO = (OtherChargesBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.OTHERCHARGES);
    }

    @Override
    public boolean addOtherCharges(OtherServicesDTO otherServicesDTO) throws Exception {
        boolean result = otherChargesBO.addOtherCharges(otherServicesDTO);
        notifyObservers();
        return result;
    }

    @Override
    public boolean updateOtherCharges(OtherServicesDTO otherServicesDTO) throws Exception {
        boolean result = otherChargesBO.updateOtherCharges(otherServicesDTO);
        notifyObservers();
        return result;
    }

    @Override
    public boolean deleteOtherCharges(int ID) throws Exception {
        boolean result = otherChargesBO.deleteOtherCharges(ID);
        notifyObservers();
        return result;
    }

    @Override
    public OtherServicesDTO findOtherChargesByID(int ID) throws Exception {
        return otherChargesBO.findOtherChargesByID(ID);
    }

    @Override
    public List<OtherServicesDTO> getAllOtherCharges() throws Exception {
        return otherChargesBO.getAllOtherCharges();
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

        // System.out.println("aaaaa     " + alObservers.get(0));
//        new Thread(() -> {
//            for (Observer observer : alObservers) {
//                try {
//                    observer.updateObservers();
//                } catch (Exception ex) {
//                    Logger.getLogger(SpecializationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }).start();
        new Thread(() -> {
            alObservers.forEach((observer) -> {
                try {
                    System.out.println(observer.getName());
                    observer.updateObservers();
                } catch (Exception ex) {
                    Logger.getLogger(OtherChargesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }).start();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.service.custom.impl;

import hospital.business.BOFactory;
import hospital.business.custom.PrescriptionBO;
import hospital.dto.PrescriptionDTO;
import hospital.observer.Observer;
import hospital.observer.Subject;
import hospital.service.custom.PrescriptionService;
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
public class PrescriptionServiceImpl extends UnicastRemoteObject implements PrescriptionService, Subject {

    private PrescriptionBO prescriptionBO;
    private static ArrayList<Observer> alObservers = new ArrayList<>();

    public PrescriptionServiceImpl() throws RemoteException {
        prescriptionBO = (PrescriptionBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PRESCRIPTION);
    }

    @Override
    public boolean addPrescription(PrescriptionDTO prescriptionDTO) throws Exception {
        boolean result = prescriptionBO.addPrescription(prescriptionDTO);
        notifyObservers();
        return result;
    }

    @Override
    public boolean updatePrescription(PrescriptionDTO prescriptionDTO) throws Exception {
        boolean result = prescriptionBO.updatePrescription(prescriptionDTO);
        notifyObservers();
        return result;
    }

    @Override
    public boolean deletePrescription(String presID) throws Exception {
        boolean result = prescriptionBO.deletePrescription(presID);
        notifyObservers();
        return result;
    }

    @Override
    public PrescriptionDTO findPrescriptionByID(String ID) throws Exception {
        return prescriptionBO.findPrescriptionByID(ID);
    }

    @Override
    public List<PrescriptionDTO> getAllPrescriptions() throws Exception {
        return prescriptionBO.getAllPrescriptions();
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
    public PrescriptionDTO findPrescriptionByPatientID(String patientID) throws Exception {
        return prescriptionBO.findPrescriptionByPatientID(patientID);
    }

}

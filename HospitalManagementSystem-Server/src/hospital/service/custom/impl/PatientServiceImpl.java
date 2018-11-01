/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.service.custom.impl;

import hospital.business.BOFactory;
import hospital.business.custom.PatientBO;
import hospital.dto.CashierDTO;
import hospital.dto.PatientDTO;
import hospital.observer.Observer;
import hospital.observer.Subject;
import hospital.service.custom.PatientService;
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
public class PatientServiceImpl extends UnicastRemoteObject implements PatientService, Subject {

    private PatientBO patientBO;
    private static ArrayList<Observer> alObservers = new ArrayList<>();

    public PatientServiceImpl() throws RemoteException {
        patientBO = (PatientBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PATIENT);
    }

    @Override
    public boolean addPatient(PatientDTO patientDTO) throws Exception {
        boolean result = patientBO.addPatient(patientDTO);
        notifyObservers();
        return result;
    }

    @Override
    public boolean updatePatient(PatientDTO patientDTO) throws Exception {
        boolean result = patientBO.updatePatient(patientDTO);
        notifyObservers();
        return result;
    }

    @Override
    public boolean deletePatient(String patienID) throws Exception {
        boolean result = patientBO.deletePatient(patienID);
        notifyObservers();
        return result;
    }

    @Override
    public PatientDTO findPatientByID(String ID) throws Exception {
        return patientBO.findPatientByID(ID);
    }

    @Override
    public List<PatientDTO> getAllPatient() throws Exception {
        return patientBO.getAllPatient();
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

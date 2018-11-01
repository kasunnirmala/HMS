/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.service.custom.impl;

import hospital.business.BOFactory;
import hospital.business.custom.DoctorBO;
import hospital.dto.DoctorDTO;
import hospital.observer.Observer;
import hospital.observer.Subject;
import hospital.service.custom.DoctorService;
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
public class DoctorServiceImpl extends UnicastRemoteObject implements DoctorService, Subject {

    private DoctorBO doctorBO;
    private static ArrayList<Observer> alObservers = new ArrayList<>();

    public DoctorServiceImpl() throws RemoteException {
        doctorBO = (DoctorBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.DOCTOR);
    }

    @Override
    public boolean addDoctor(DoctorDTO doctorDTO) throws Exception {
        boolean result = doctorBO.addDoctor(doctorDTO);
        notifyObservers();
        return result;
    }

    @Override
    public boolean updateDoctor(DoctorDTO doctorDTO) throws Exception {
        boolean result = doctorBO.updateDoctor(doctorDTO);
        notifyObservers();
        return result;
    }

    @Override
    public boolean deleteDoctor(int DoctorID) throws Exception {
        boolean result = doctorBO.deleteDoctor(DoctorID);
        notifyObservers();
        return result;
    }

    @Override
    public DoctorDTO findDoctorByID(int ID) throws Exception {
        return doctorBO.findDoctorByID(ID);
    }

    @Override
    public DoctorDTO findDoctorBySLMC(String regNo) throws Exception {
        return doctorBO.findDoctorBySLMC(regNo);
    }

    @Override
    public List<DoctorDTO> getAllDoctors() throws Exception {
        return doctorBO.getAllDoctors();
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
    public List<DoctorDTO> getDoctorsByDate(String Date) throws Exception {
        return doctorBO.getDoctorsByDate(Date);
    }

    @Override
    public List<DoctorDTO> getOPDDoctor() throws Exception {
        return doctorBO.getOPDDoctor();
    }

    @Override
    public List<DoctorDTO> getVisitingDoctor() throws Exception {
        return doctorBO.getVisitingDoctor();
    }

    @Override
    public List<DoctorDTO> getOPDDoctorByDate(String Date) throws Exception {
        return doctorBO.getOPDDoctorByDate(Date);
    }

    @Override
    public List<DoctorDTO> getVisitingDoctorByDate(String Date) throws Exception {
        return doctorBO.getVisitingDoctorByDate(Date);
    }

}

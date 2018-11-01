/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.service.custom.impl;

import hospital.business.BOFactory;
import hospital.business.custom.AppointmentBO;
import hospital.dto.AppointmentDTO;
import hospital.observer.Observer;
import hospital.observer.Subject;
import hospital.service.custom.AppointmentService;
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
public class AppointmentServiceImpl extends UnicastRemoteObject implements AppointmentService, Subject {

    private static ArrayList<Observer> alObservers = new ArrayList<>();
    private AppointmentBO appointmentBO;

    public AppointmentServiceImpl() throws RemoteException {
        appointmentBO = (AppointmentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.APPOINTMENT);
    }

    @Override
    public boolean addAppointment(AppointmentDTO appointmentDTO) throws Exception {
        boolean result = appointmentBO.addAppointment(appointmentDTO);
        notifyObservers();
        return result;
    }

    @Override
    public boolean updateAppointment(AppointmentDTO appointmentDTO) throws Exception {
        boolean result = appointmentBO.updateAppointment(appointmentDTO);
        notifyObservers();
        return result;
    }

    @Override
    public boolean deleteAppointment(String appointmentID) throws Exception {
        boolean result = appointmentBO.deleteAppointment(appointmentID);
        notifyObservers();
        return result;
    }

    @Override
    public AppointmentDTO findAppointmentByID(String ID) throws Exception {
        return appointmentBO.findAppointmentByID(ID);
    }

    @Override
    public AppointmentDTO findAppointmentByDate(String date) throws Exception {
        return appointmentBO.findAppointmentByDate(date);
    }

    @Override
    public List<AppointmentDTO> getAllAppointments() throws Exception {
        return appointmentBO.getAllAppointments();
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
    public int sumAppointmentByDate(String date) throws Exception {

        return appointmentBO.sumAppointmentByDate(date);

    }

    @Override
    public AppointmentDTO findAppointmentByPatient(String patientID) throws Exception {
        return appointmentBO.findAppointmentByPatient(patientID);
    }

    @Override
    public List<AppointmentDTO> getAllByDate(String date) throws Exception {
        return appointmentBO.getAllByDate(date);
    }

}

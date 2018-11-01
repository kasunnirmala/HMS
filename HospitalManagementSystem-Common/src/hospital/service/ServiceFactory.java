/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.service;

import java.rmi.Remote;

/**
 *
 * @author Kasun
 */
public interface ServiceFactory extends Remote {

    public enum ServiceTypes {
        SPECIALIZATION, DOCTOR, ROOM_TYPE, ROOM, NURSE, CASHIER, PATIENT, PRESCRIPTION, APPOINTMENT, ROOM_RESERVE,TREATMENT,REPORTS,OTHERCHARGES,APPOINTMENT_PAYMENT
    }

    public SuperService getService(ServiceTypes type) throws Exception;
}

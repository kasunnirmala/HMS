/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.service.impl;

import hospital.service.ServiceFactory;
import hospital.service.SuperService;
import hospital.service.custom.impl.AppointmentPaymentServiceImpl;
import hospital.service.custom.impl.AppointmentServiceImpl;
import hospital.service.custom.impl.CashierServiceImpl;
import hospital.service.custom.impl.DoctorServiceImpl;
import hospital.service.custom.impl.NurseServiceImpl;
import hospital.service.custom.impl.OtherChargesServiceImpl;
import hospital.service.custom.impl.PatientServiceImpl;
import hospital.service.custom.impl.PrescriptionServiceImpl;
import hospital.service.custom.impl.ReportsServiceImpl;
import hospital.service.custom.impl.RoomReserveServiceImpl;
import hospital.service.custom.impl.RoomServiceImpl;
import hospital.service.custom.impl.RoomTypeServiceImpl;
import hospital.service.custom.impl.SpecializationServiceImpl;
import hospital.service.custom.impl.TreatmentServiceImpl;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Kasun
 */
public class ServiceFactoryImpl extends UnicastRemoteObject implements ServiceFactory {

    public static ServiceFactory serviceFactory;

    private ServiceFactoryImpl() throws RemoteException {

    }

    public static ServiceFactory getInstance() throws RemoteException {
        if (serviceFactory == null) {
            serviceFactory = new ServiceFactoryImpl();
        }
        return serviceFactory;
    }

    @Override
    public SuperService getService(ServiceTypes type) throws Exception {
        switch (type) {
            case SPECIALIZATION:
                return new SpecializationServiceImpl();
            case DOCTOR:
                return new DoctorServiceImpl();
            case ROOM_TYPE:
                return new RoomTypeServiceImpl();
            case ROOM:
                return new RoomServiceImpl();
            case NURSE:
                return new NurseServiceImpl();
            case CASHIER:
                return new CashierServiceImpl();
            case PATIENT:
                return new PatientServiceImpl();
            case PRESCRIPTION:
                return new PrescriptionServiceImpl();
            case APPOINTMENT:
                return new AppointmentServiceImpl();
            case ROOM_RESERVE:
                return new RoomReserveServiceImpl();
            case TREATMENT:
                return new TreatmentServiceImpl();
            case REPORTS:
                return new ReportsServiceImpl();
            case OTHERCHARGES:
                return new OtherChargesServiceImpl();
            case APPOINTMENT_PAYMENT:
                return new AppointmentPaymentServiceImpl();
            default:
                return null;
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.proxy;

import hospital.service.ServiceFactory;
import hospital.service.SuperService;
import hospital.service.custom.AppointmentPaymentService;
import hospital.service.custom.AppointmentService;
import hospital.service.custom.CashierService;
import hospital.service.custom.DoctorService;
import hospital.service.custom.NurseService;
import hospital.service.custom.OtherChargesService;
import hospital.service.custom.PatientService;
import hospital.service.custom.PrescriptionService;
import hospital.service.custom.ReportsService;
import hospital.service.custom.RoomReserveService;
import hospital.service.custom.RoomService;
import hospital.service.custom.RoomTypeService;
import hospital.service.custom.SpecializationService;
import hospital.service.custom.TreatmentService;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kasun
 */
public class ProxyHandler implements ServiceFactory {

    private static ProxyHandler proxyHandler;
    private ServiceFactory serviceFactory;
    private SpecializationService specializationService;
    private DoctorService doctorService;
    private RoomTypeService roomTypeService;
    private RoomService roomService;
    private NurseService nurseService;
    private CashierService cashierService;
    private PatientService patientService;
    private PrescriptionService prescriptionService;
    private AppointmentService appointmentService;
    private RoomReserveService roomReserveService;
    private TreatmentService treatmentService;
    private ReportsService reportsService;
    private OtherChargesService otherChargesService;
    private AppointmentPaymentService appointmentPaymentService;

    private ProxyHandler() {
        try {
            serviceFactory = (ServiceFactory) Naming.lookup("rmi://localhost:5050/hospital");
            specializationService = (SpecializationService) serviceFactory.getService(ServiceFactory.ServiceTypes.SPECIALIZATION);
            doctorService = (DoctorService) serviceFactory.getService(ServiceFactory.ServiceTypes.DOCTOR);
            roomTypeService = (RoomTypeService) serviceFactory.getService(ServiceFactory.ServiceTypes.ROOM_TYPE);
            roomService = (RoomService) serviceFactory.getService(ServiceFactory.ServiceTypes.ROOM);
            nurseService = (NurseService) serviceFactory.getService(ServiceFactory.ServiceTypes.NURSE);
            cashierService = (CashierService) serviceFactory.getService(ServiceFactory.ServiceTypes.CASHIER);
            patientService = (PatientService) serviceFactory.getService(ServiceFactory.ServiceTypes.PATIENT);
            prescriptionService = (PrescriptionService) serviceFactory.getService(ServiceFactory.ServiceTypes.PRESCRIPTION);
            appointmentService = (AppointmentService) serviceFactory.getService(ServiceFactory.ServiceTypes.APPOINTMENT);
            roomReserveService = (RoomReserveService) serviceFactory.getService(ServiceFactory.ServiceTypes.ROOM_RESERVE);
            treatmentService = (TreatmentService) serviceFactory.getService(ServiceFactory.ServiceTypes.TREATMENT);
            reportsService = (ReportsService) serviceFactory.getService(ServiceFactory.ServiceTypes.REPORTS);
            otherChargesService = (OtherChargesService) serviceFactory.getService(ServiceFactory.ServiceTypes.OTHERCHARGES);
            appointmentPaymentService = (AppointmentPaymentService) serviceFactory.getService(ServiceFactory.ServiceTypes.APPOINTMENT_PAYMENT);
        } catch (NotBoundException ex) {
            Logger.getLogger(ProxyHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ProxyHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ProxyHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProxyHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ProxyHandler getInstance() {
        if (proxyHandler == null) {
            proxyHandler = new ProxyHandler();
        }
        return proxyHandler;
    }

    @Override
    public SuperService getService(ServiceTypes type) throws Exception {
        switch (type) {
            case SPECIALIZATION:
                return specializationService;
            case DOCTOR:
                return doctorService;
            case ROOM_TYPE:
                return roomTypeService;
            case ROOM:
                return roomService;
            case NURSE:
                return nurseService;
            case CASHIER:
                return cashierService;
            case PATIENT:
                return patientService;
            case PRESCRIPTION:
                return prescriptionService;
            case APPOINTMENT:
                return appointmentService;
            case ROOM_RESERVE:
                return roomReserveService;
            case TREATMENT:
                return treatmentService;
            case REPORTS:
                return reportsService;
            case OTHERCHARGES:
                return otherChargesService;
            case APPOINTMENT_PAYMENT:
                return appointmentPaymentService;
            default:
                return null;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.repository;

import hospital.business.custom.impl.OtherChargesBOImpl;
import hospital.repository.custom.impl.AppointmentPaymentRepositoryImpl;
import hospital.repository.custom.impl.AppointmentRepositoryImpl;
import hospital.repository.custom.impl.CashierRepositoryImpl;
import hospital.repository.custom.impl.DoctorRepositoryImpl;
import hospital.repository.custom.impl.NurseRepositoryImpl;
import hospital.repository.custom.impl.OtherChargesRepositoryImpl;
import hospital.repository.custom.impl.PatientRepositoryImpl;
import hospital.repository.custom.impl.PrescriptionRepositoryImpl;
import hospital.repository.custom.impl.RoomRepositoryImpl;
import hospital.repository.custom.impl.RoomReserveRepositoryImpl;
import hospital.repository.custom.impl.RoomTypeRepositoryImpl;
import hospital.repository.custom.impl.SpecializationRepositoryImpl;
import hospital.repository.custom.impl.TreatmentRepositoryImpl;

/**
 *
 * @author Kasun
 */
public class RepositoryFactory {

    public enum RepositoryTypes {
        SPECIALIZATION, DOCTOR, ROOM_TYPE, ROOM, NURSE, CASHIER, PATIENT, PRESCRIPTION, APPOINTMENT, ROOM_RESERVE, TREATMENT, OTHERCHARGES, APPOINTMENT_PAYMENT
    }

    public static RepositoryFactory repositoryFactory;

    private RepositoryFactory() {

    }

    public static RepositoryFactory getInstance() {
        if (repositoryFactory == null) {
            repositoryFactory = new RepositoryFactory();
        }
        return repositoryFactory;
    }

    public SuperRepository getRepository(RepositoryTypes type) {
        switch (type) {
            case SPECIALIZATION:
                return new SpecializationRepositoryImpl();
            case DOCTOR:
                return new DoctorRepositoryImpl();
            case ROOM_TYPE:
                return new RoomTypeRepositoryImpl();
            case ROOM:
                return new RoomRepositoryImpl();
            case NURSE:
                return new NurseRepositoryImpl();
            case CASHIER:
                return new CashierRepositoryImpl();
            case PATIENT:
                return new PatientRepositoryImpl();
            case PRESCRIPTION:
                return new PrescriptionRepositoryImpl();
            case APPOINTMENT:
                return new AppointmentRepositoryImpl();
            case ROOM_RESERVE:
                return new RoomReserveRepositoryImpl();
            case TREATMENT:
                return new TreatmentRepositoryImpl();
            case OTHERCHARGES:
                return new OtherChargesRepositoryImpl();
            case APPOINTMENT_PAYMENT:
                return new AppointmentPaymentRepositoryImpl();
            default:
                return null;
        }
    }
}

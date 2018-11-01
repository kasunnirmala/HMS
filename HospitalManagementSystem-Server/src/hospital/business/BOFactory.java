/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.business;

import hospital.business.custom.impl.AppointmentBOImpl;
import hospital.business.custom.impl.AppointmentPaymentBOImpl;
import hospital.business.custom.impl.CashierBOImpl;
import hospital.business.custom.impl.DoctorBOImpl;
import hospital.business.custom.impl.NurseBOImpl;
import hospital.business.custom.impl.OtherChargesBOImpl;
import hospital.business.custom.impl.PatientBOImpl;
import hospital.business.custom.impl.PrescriptionBOImpl;
import hospital.business.custom.impl.ReportsBOImpl;
import hospital.business.custom.impl.RoomBOImpl;
import hospital.business.custom.impl.RoomReserveBOImpl;
import hospital.business.custom.impl.RoomTypeBOImpl;
import hospital.business.custom.impl.SpecializationBOImpl;
import hospital.business.custom.impl.TreatmentBOImpl;

/**
 *
 * @author Kasun
 */
public class BOFactory {

    public enum BOTypes {
        SPECIALIZATION, DOCTOR, ROOM_TYPE, ROOM, NURSE, CASHIER, PATIENT, PRESCRIPTION, APPOINTMENT, ROOM_RESERVE, TREATMENT, REPORTS, OTHERCHARGES, APPOINTMENT_PAYMENT
    }

    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BOTypes type) {
        switch (type) {
            case SPECIALIZATION:
                return new SpecializationBOImpl();
            case DOCTOR:
                return new DoctorBOImpl();
            case ROOM_TYPE:
                return new RoomTypeBOImpl();
            case ROOM:
                return new RoomBOImpl();
            case NURSE:
                return new NurseBOImpl();
            case CASHIER:
                return new CashierBOImpl();
            case PATIENT:
                return new PatientBOImpl();
            case PRESCRIPTION:
                return new PrescriptionBOImpl();
            case APPOINTMENT:
                return new AppointmentBOImpl();
            case ROOM_RESERVE:
                return new RoomReserveBOImpl();
            case TREATMENT:
                return new TreatmentBOImpl();
            case REPORTS:
                return new ReportsBOImpl();
            case OTHERCHARGES:
                return new OtherChargesBOImpl();
            case APPOINTMENT_PAYMENT:
                return new AppointmentPaymentBOImpl();
            default:
                return null;
        }
    }
}

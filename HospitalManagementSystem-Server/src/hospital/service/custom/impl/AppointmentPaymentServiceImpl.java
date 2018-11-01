/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.service.custom.impl;

import hospital.business.BOFactory;
import hospital.business.custom.AppointmentPaymentBO;
import hospital.dto.AppointmentPaymentDTO;
import hospital.service.custom.AppointmentPaymentService;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Kasun
 */
public class AppointmentPaymentServiceImpl extends UnicastRemoteObject implements AppointmentPaymentService {

    private AppointmentPaymentBO appointmentPaymentBO;

    public AppointmentPaymentServiceImpl() throws RemoteException {
        appointmentPaymentBO = (AppointmentPaymentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.APPOINTMENT_PAYMENT);
    }

    @Override
    public boolean addAppointmentPayment(AppointmentPaymentDTO appointmentPaymentDTO) throws Exception {
        return appointmentPaymentBO.addAppointmentPayment(appointmentPaymentDTO);
    }

}

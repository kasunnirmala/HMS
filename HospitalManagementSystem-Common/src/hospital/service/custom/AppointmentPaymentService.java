/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.service.custom;

import hospital.dto.AppointmentPaymentDTO;
import hospital.service.SuperService;

/**
 *
 * @author Kasun
 */
public interface AppointmentPaymentService extends SuperService{
      public boolean addAppointmentPayment(AppointmentPaymentDTO appointmentPaymentDTO) throws Exception;
}

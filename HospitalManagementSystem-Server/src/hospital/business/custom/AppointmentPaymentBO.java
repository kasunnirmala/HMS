/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.business.custom;

import hospital.business.SuperBO;
import hospital.dto.AppointmentPaymentDTO;
import hospital.entity.AppointmentPayment;

/**
 *
 * @author Kasun
 */
public interface AppointmentPaymentBO extends SuperBO{
      public boolean addAppointmentPayment(AppointmentPaymentDTO appointmentPaymentDTO) throws Exception;
}

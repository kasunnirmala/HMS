/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.service.custom;

import hospital.dto.AppointmentDTO;
import hospital.service.SuperService;
import java.util.List;

/**
 *
 * @author Kasun
 */
public interface AppointmentService extends SuperService {

    public boolean addAppointment(AppointmentDTO appointmentDTO) throws Exception;

    public boolean updateAppointment(AppointmentDTO appointmentDTO) throws Exception;

    public boolean deleteAppointment(String appointmentID) throws Exception;

    public AppointmentDTO findAppointmentByID(String ID) throws Exception;

    public AppointmentDTO findAppointmentByDate(String date) throws Exception;

    public AppointmentDTO findAppointmentByPatient(String patientID) throws Exception;

    public List<AppointmentDTO> getAllAppointments() throws Exception;

    public List<AppointmentDTO> getAllByDate(String date) throws Exception;

    public int sumAppointmentByDate(String date) throws Exception;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.repository.custom;

import hospital.entity.Appointment;
import hospital.repository.SuperRepository;
import java.util.List;

/**
 *
 * @author Kasun
 */
public interface AppoinmentRepository extends SuperRepository<Appointment, String> {

    public Appointment findByDate(String date);

    public List<Appointment> getAllByDate(String date);

    public int sumAppointmentByDate(String date) throws Exception;

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.service.custom;

import hospital.service.SuperService;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Kasun
 */
public interface ReportsService extends SuperService {

    public JasperPrint getBill(String patientID, String roomNo, String billNo, String date) throws Exception;
    public JasperPrint getPrescription(String patientID) throws Exception;
    public JasperPrint getAppointment(String appointmentID) throws Exception;
}

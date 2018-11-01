/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.business.custom;

import hospital.business.SuperBO;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Kasun
 */
public interface ReportsBO extends SuperBO {

    public JasperPrint getBill(String patientID, String roomNo, String billNo, String date) throws Exception;

    public JasperPrint getPrescription(String patientID) throws Exception;
    
     public JasperPrint getAppointment(String appointmentID) throws Exception;
}

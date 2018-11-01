/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.service.custom.impl;

import hospital.business.BOFactory;
import hospital.business.custom.ReportsBO;
import hospital.service.custom.ReportsService;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Kasun
 */
public class ReportsServiceImpl extends UnicastRemoteObject implements ReportsService {

    private ReportsBO reportsBO;

    public ReportsServiceImpl() throws RemoteException {
        reportsBO = (ReportsBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.REPORTS);
    }

    @Override
    public JasperPrint getBill(String patientID, String roomNo, String billNo, String date) throws Exception {
        return reportsBO.getBill(patientID, roomNo, billNo, date);
    }

    @Override
    public JasperPrint getPrescription(String patientID) throws Exception {
      return reportsBO.getPrescription(patientID);
    }

    @Override
    public JasperPrint getAppointment(String appointmentID) throws Exception {
      return reportsBO.getAppointment(appointmentID);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.business.custom.impl;

import hospital.business.custom.ReportsBO;
import hospital.resource.HibernateUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

public class ReportsBOImpl implements ReportsBO {

    private Connection connection = null;

    @Override
    public JasperPrint getBill(String patientID, String roomNo, String billNo, String date) throws Exception {
        JasperReport compiledReport = (JasperReport) JRLoader.loadObject(ReportsBOImpl.class.getResourceAsStream("/hospital/reports/bill.jasper"));

        HashMap<String, Object> reportParams = new HashMap<>();
        reportParams.put("PatientID", patientID);
        reportParams.put("roomNo", roomNo);
        reportParams.put("BillNo", billNo);
        reportParams.put("dateDischarge", date);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.doWork((new Work() {
            @Override
            public void execute(Connection cnctn) throws SQLException {
                connection = cnctn;
            }
        }));
        JasperPrint filledReport = JasperFillManager.fillReport(compiledReport, reportParams, connection);
        return filledReport;
    }

    @Override
    public JasperPrint getPrescription(String patientID) throws Exception {
        JasperReport compiledReport = (JasperReport) JRLoader.loadObject(ReportsBOImpl.class.getResourceAsStream("/hospital/reports/Prescription.jasper"));

        HashMap<String, Object> reportParams = new HashMap<>();
        reportParams.put("PatientID", patientID);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.doWork((new Work() {
            @Override
            public void execute(Connection cnctn) throws SQLException {
                connection = cnctn;
            }
        }));
        JasperPrint filledReport = JasperFillManager.fillReport(compiledReport, reportParams, connection);
        return filledReport;
    }

    @Override
    public JasperPrint getAppointment(String appointmentID) throws Exception {
         JasperReport compiledReport = (JasperReport) JRLoader.loadObject(ReportsBOImpl.class.getResourceAsStream("/hospital/reports/Appointment.jasper"));

        HashMap<String, Object> reportParams = new HashMap<>();
        reportParams.put("AppointmentID", appointmentID);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.doWork((new Work() {
            @Override
            public void execute(Connection cnctn) throws SQLException {
                connection = cnctn;
            }
        }));
        JasperPrint filledReport = JasperFillManager.fillReport(compiledReport, reportParams, connection);
        return filledReport;
    }

}

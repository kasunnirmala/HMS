/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.controller;

import com.jfoenix.controls.JFXTextField;
import hospital.dto.AppointmentDTO;
import hospital.dto.AppointmentPaymentDTO;
import hospital.proxy.ProxyHandler;
import hospital.resource.CustomMethod;
import hospital.service.ServiceFactory;
import hospital.service.custom.AppointmentPaymentService;
import hospital.service.custom.AppointmentService;
import hospital.service.custom.ReportsService;
import java.awt.Dialog;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javax.swing.JFrame;
import lk.ijse.jasper.IJSEJasperViewer;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * FXML Controller class
 *
 * @author Kasun
 */
public class AppointmentPaymentController implements Initializable {

    @FXML
    private JFXTextField txtAppointmentID;
    @FXML
    private JFXTextField txtDoctorFee;
    @FXML
    private JFXTextField txtChannelingFee;
    @FXML
    private TextField txtTotal;
    @FXML
    private TextField txtUserAmount;
    @FXML
    private TextField txtChange;
    private AppointmentService appointmentService;
    private AppointmentPaymentService appointmentPaymentService;
    private ReportsService reportsService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            appointmentService = (AppointmentService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.APPOINTMENT);
            appointmentPaymentService = (AppointmentPaymentService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.APPOINTMENT_PAYMENT);
            reportsService = (ReportsService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.REPORTS);
        } catch (Exception ex) {
            Logger.getLogger(AppointmentPaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void txtChannelingFeeOnAction(ActionEvent event) {
        txtTotal.setText(Double.toString(Double.parseDouble(txtDoctorFee.getText()) + Double.parseDouble(txtChannelingFee.getText())));
    }

    @FXML
    private void txtUserAmountOnAction(ActionEvent event) {
        txtChange.setText(Double.toString(Double.parseDouble(txtUserAmount.getText()) - Double.parseDouble(txtTotal.getText())));
    }

    @FXML
    private void btnPayOnAction(ActionEvent event) throws Exception {

        AppointmentDTO appointmentDTO = appointmentService.findAppointmentByID(txtAppointmentID.getText());
        AppointmentPaymentDTO appointmentPaymentDTO = new AppointmentPaymentDTO(appointmentDTO, Double.parseDouble(txtTotal.getText()), Double.parseDouble(txtChange.getText()), Double.parseDouble(txtUserAmount.getText()));
        if (appointmentPaymentService.addAppointmentPayment(appointmentPaymentDTO)) {
            CustomMethod.successNotification("Success....!");

            JasperPrint filledReport = reportsService.getAppointment(appointmentDTO.getAppointmentID());
            IJSEJasperViewer frmJasperViewer = new IJSEJasperViewer(filledReport);
            frmJasperViewer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frmJasperViewer.setTitle("Patient Bill");
            frmJasperViewer.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
            frmJasperViewer.setVisible(true);

        } else {
            CustomMethod.errorNotification("Error...!");
        }
    }

}

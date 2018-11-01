/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.core;

import hospital.proxy.ProxyHandler;
import hospital.resource.CustomMethod;
import hospital.service.ServiceFactory;
import hospital.service.custom.ReportsService;
import java.awt.Dialog;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JFrame;
import lk.ijse.jasper.IJSEJasperViewer;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Kasun
 */
public class Testt extends Application {

    private ReportsService reportsService;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //   try {
        Parent root = FXMLLoader.load(this.getClass().getResource("/hospital/view/AdminPatients.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("Hospital Management System");
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
//        primaryStage.initStyle(StageStyle.UNDECORATED);
//        primaryStage.setFullScreen(true);
        primaryStage.show();

       

//            reportsService = (ReportsService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.REPORTS);
//
//            JasperPrint filledReport = reportsService.getBill("PAT-001", "LX752", "BILL001", new SimpleDateFormat("YYYY-MM-dd hh:mm:ss").format(new Date()));
//            IJSEJasperViewer frmJasperViewer = new IJSEJasperViewer(filledReport);
//            frmJasperViewer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//            frmJasperViewer.setTitle("Patient Bill");
//            frmJasperViewer.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
//            frmJasperViewer.setVisible(true);
//        } catch (Exception ex) {
//            Logger.getLogger(Testt.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

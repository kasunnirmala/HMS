/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.controller;

import com.jfoenix.controls.JFXProgressBar;
import hospital.dto.RoomDTO;
import hospital.dto.RoomReserveDTO;
import hospital.observer.Observer;
import hospital.observer.Subject;
import hospital.proxy.ProxyHandler;
import hospital.service.ServiceFactory;
import hospital.service.custom.AppointmentService;
import hospital.service.custom.CashierService;
import hospital.service.custom.DoctorService;
import hospital.service.custom.NurseService;
import hospital.service.custom.PatientService;
import hospital.service.custom.RoomReserveService;
import hospital.service.custom.RoomService;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Kasun
 */
public class AdminHomeController implements Initializable, Observer {

    @FXML
    private Label lblVisitingDoctors;
    @FXML
    private JFXProgressBar progressDoctors;
    @FXML
    private Label lblTodayVisitingDoctors;
    @FXML
    private Label lblTotalVisitingDoctors;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    @FXML
    private Label lblNurse;
    @FXML
    private Label lblStaff;
    @FXML
    private Label lblPatients;
    @FXML
    private Label lblOPDDoctors;
    @FXML
    private PieChart pieLuxuryRooms;
    @FXML
    private PieChart pieNormalRooms;
    @FXML
    private LineChart<?, ?> lineChartAppointment;

    private DoctorService doctorService;
    private NurseService nurseService;
    private PatientService patientService;
    private CashierService cashierService;
    private AppointmentService appointmentService;
    private RoomService roomService;
    private RoomReserveService roomReserveService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setDateTime();

        try {
            UnicastRemoteObject.exportObject(this, 0);
            Subject doctorSubject = (Subject) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.DOCTOR);
            Subject nurseSubject = (Subject) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.NURSE);
            doctorSubject.registerObserver(this);
            nurseSubject.registerObserver(this);

            doctorService = (DoctorService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.DOCTOR);
            nurseService = (NurseService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.NURSE);
            patientService = (PatientService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.PATIENT);
            cashierService = (CashierService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.CASHIER);
            appointmentService = (AppointmentService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.APPOINTMENT);
            roomService = (RoomService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ROOM);
            roomReserveService = (RoomReserveService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ROOM_RESERVE);

            updateHomeStatistics();

        } catch (Exception ex) {
            Logger.getLogger(AdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void setDateTime() {
        Timeline time = new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                lblTime.setText(new SimpleDateFormat("hh:mm a").format(new Date()));
                lblDate.setText(new SimpleDateFormat("YYYY-MM-dd").format(new Date()));
            }
        }), new KeyFrame(Duration.seconds(1)));
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    private void setAppointmentChart() throws Exception {
        XYChart.Series series = new XYChart.Series<>();
        series.setName("Appointment Statistics");

        for (int i = 7; i >= 0; i--) {

            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.DATE, -i);
            String format = new SimpleDateFormat("YYYY-MM-dd").format(c.getTime());
            series.getData().add(new XYChart.Data<>(format, appointmentService.sumAppointmentByDate(format)));
        }
//        series.getData().add(new XYChart.Data<>("2017-1-1", 10));
//        series.getData().add(new XYChart.Data<>("2017-1-2", 20));
//        series.getData().add(new XYChart.Data<>("2017-1-3", 40));
//        series.getData().add(new XYChart.Data<>("2017-1-4", 80));

        lineChartAppointment.getData().addAll(series);
    }

    private void setPieCharts() throws Exception {

        int totalLuxury = 0;
        int totalNormal = 0;

        int totReservesLuxury = 0;
        int totReservesNormal = 0;

        List<RoomDTO> allRooms = roomService.getAllRooms();
        if (allRooms != null) {
            for (RoomDTO roomDTO : allRooms) {
                if (roomDTO.getRoomType().equalsIgnoreCase("LXRY")) {
                    totalLuxury++;
                } else if (roomDTO.getRoomType().equalsIgnoreCase("NRML")) {
                    totalNormal++;
                }
            }
        }

        List<RoomReserveDTO> allReservedRoomReserves = roomReserveService.getAllReservedRoomReserves();
        if (allReservedRoomReserves != null) {
            for (RoomReserveDTO roomReserveDTO : allReservedRoomReserves) {
                if (roomReserveDTO.getRoomDTO().getRoomType().equalsIgnoreCase("LXRY")) {
                    totReservesLuxury++;
                } else if (roomReserveDTO.getRoomDTO().getRoomType().equalsIgnoreCase("NRML")) {
                    totReservesNormal++;
                }
            }
        }

        ObservableList<PieChart.Data> pieDataNormalRooms = FXCollections.observableArrayList();
        pieDataNormalRooms.add(new PieChart.Data("Reserved (" + totReservesNormal + ")", totReservesNormal));
        pieDataNormalRooms.add(new PieChart.Data("Free (" + (totalNormal - totReservesNormal) + ")", (totalNormal - totReservesNormal)));
        pieNormalRooms.setData(pieDataNormalRooms);

        ObservableList<PieChart.Data> pieDataLuxuryRooms = FXCollections.observableArrayList();
        pieDataLuxuryRooms.add(new PieChart.Data("Reserved (" + totReservesLuxury + ")", totReservesLuxury));
        pieDataLuxuryRooms.add(new PieChart.Data("Free (" + (totalLuxury - totReservesLuxury) + ")", (totalLuxury - totReservesLuxury)));
        pieLuxuryRooms.setData(pieDataLuxuryRooms);
    }

    @Override
    public void updateObservers() throws Exception {
        updateHomeStatistics();
    }

    private void updateHomeStatistics() throws Exception {

        setAppointmentChart();
        setPieCharts();

        String currDate = new SimpleDateFormat("EEEEE").format(new Date());
        lblTotalVisitingDoctors.setText(Integer.toString(doctorService.getVisitingDoctor().size()));
        lblTodayVisitingDoctors.setText(Integer.toString(doctorService.getVisitingDoctorByDate(currDate).size()));

        lblOPDDoctors.setText(Integer.toString(doctorService.getOPDDoctor().size()));
        lblVisitingDoctors.setText(Integer.toString(doctorService.getVisitingDoctor().size()));
        Double progress = (double) doctorService.getVisitingDoctorByDate(currDate).size() / doctorService.getVisitingDoctor().size();
        progressDoctors.setProgress(progress);

        lblNurse.setText(Integer.toString(nurseService.getAllNurses().size()));

        lblStaff.setText(Integer.toString(cashierService.getAllCashier().size()));
        lblPatients.setText(Integer.toString(roomReserveService.getAllReservedRoomReserves().size()));
    }

}

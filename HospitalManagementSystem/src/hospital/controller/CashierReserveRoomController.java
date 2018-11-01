/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.controller;

import hospital.dto.RoomDTO;
import hospital.dto.RoomReserveDTO;
import hospital.observer.Observer;
import hospital.observer.Subject;
import hospital.proxy.ProxyHandler;
import hospital.resource.AdminAddLuxuryRoom;
import hospital.resource.AdminAddNormalRoom;
import hospital.resource.AdminCreateRoomFloor;
import hospital.resource.ReserveNormalRoom;
import hospital.resource.ReserverLuxuryRoom;
import hospital.resource.SuperRooms;
import hospital.service.ServiceFactory;
import hospital.service.custom.RoomReserveService;
import hospital.service.custom.RoomService;
import hospital.service.custom.RoomTypeService;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Kasun
 */
public class CashierReserveRoomController implements Initializable, Observer {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox vbox;
    @FXML
    private Label lblLuxRoomPrice;
    @FXML
    private Label lblNormRoomPrice;
    @FXML
    private PieChart pieLuxury;
    @FXML
    private PieChart pieNormal;

    private RoomTypeService roomTypeService;
    private RoomService roomService;

    private ObservableList<RoomDTO> luxuryRoomsList;
    private ObservableList<RoomDTO> normalRoomsList;
    private RoomReserveService roomReserveService;

    private ObservableList<SuperRooms> RoomsList;
    private int totFloors = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            UnicastRemoteObject.exportObject(this, 0);
            Subject addRoomTypeSubject = (Subject) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ROOM_TYPE);
            addRoomTypeSubject.registerObserver(this);
            roomTypeService = (RoomTypeService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ROOM_TYPE);
            roomService = (RoomService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ROOM);
            roomReserveService = (RoomReserveService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ROOM_RESERVE);

            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

            normalRoomsList = FXCollections.observableArrayList();
            luxuryRoomsList = FXCollections.observableArrayList();
            RoomsList = FXCollections.observableArrayList();

            lblLuxRoomPrice.setText(Double.toString(roomTypeService.findLastRoomTypeBYType("LXRY").getRoomPrice()));
            lblNormRoomPrice.setText(Double.toString(roomTypeService.findLastRoomTypeBYType("NRML").getRoomPrice()));

            loadRooms();

            setPieCharts();

        } catch (Exception ex) {
            Logger.getLogger(CashierReserveRoomController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void setPieCharts() throws Exception {
        int totalLuxury = 0;
        int totalNormal = 0;

        int totReservesLuxury = 0;
        int totReservesNormal = 0;

        for (RoomDTO roomDTO : roomService.getAllRooms()) {
            if (roomDTO.getRoomType().equalsIgnoreCase("LXRY")) {
                totalLuxury++;
            } else if (roomDTO.getRoomType().equalsIgnoreCase("NRML")) {
                totalNormal++;
            }
        }

        for (RoomReserveDTO roomReserveDTO : roomReserveService.getAllReservedRoomReserves()) {
            if (roomReserveDTO.getRoomDTO().getRoomType().equalsIgnoreCase("LXRY")) {
                totReservesLuxury++;
            } else if (roomReserveDTO.getRoomDTO().getRoomType().equalsIgnoreCase("NRML")) {
                totReservesNormal++;
            }
        }

        ObservableList<PieChart.Data> pieDataNormalRooms = FXCollections.observableArrayList();
        pieDataNormalRooms.add(new PieChart.Data("Reserved (" + totReservesNormal + ")", totReservesNormal));
        pieDataNormalRooms.add(new PieChart.Data("Free (" + (totalNormal - totReservesNormal) + ")", (totalNormal - totReservesNormal)));
        pieNormal.setData(pieDataNormalRooms);

        ObservableList<PieChart.Data> pieDataLuxuryRooms = FXCollections.observableArrayList();
        pieDataLuxuryRooms.add(new PieChart.Data("Reserved (" + totReservesLuxury + ")", totReservesLuxury));
        pieDataLuxuryRooms.add(new PieChart.Data("Free (" + (totalLuxury - totReservesLuxury) + ")", (totalLuxury - totReservesLuxury)));
        pieLuxury.setData(pieDataLuxuryRooms);
    }

    @Override
    public void updateObservers() throws Exception {
        loadRooms();
    }

    private void loadRooms() throws Exception {
        luxuryRoomsList.clear();
        normalRoomsList.clear();
        totFloors = 0;
        vbox.getChildren().clear();
        for (RoomDTO room : roomService.getAllRooms()) {
            if (room.getRoomType().equals("LXRY")) {
                luxuryRoomsList.add(room);
            } else if (room.getRoomType().equals("NRML")) {
                normalRoomsList.add(room);
            }
            totFloors = totFloors < room.getRoomFloor() ? room.getRoomFloor() : totFloors;
        }
        AdminCreateRoomFloor[] floorMap = new AdminCreateRoomFloor[totFloors];
        for (int i = 0; i < totFloors; i++) {
            floorMap[i] = new AdminCreateRoomFloor(i + 1);
            vbox.getChildren().add(floorMap[i].getPane());
        }
        RoomsList.clear();
        for (RoomDTO room : luxuryRoomsList) {
            ReserverLuxuryRoom reserverLuxuryRoom = new ReserverLuxuryRoom(room.getRoomNo());

            if (isReserved(room.getRoomNo())) {
                reserverLuxuryRoom.setRoomReserve(roomReserveService.findRoomReserveByRoomNo(room.getRoomNo()));
                reserverLuxuryRoom.disablePanel();
            }

            RoomsList.add(reserverLuxuryRoom);
            floorMap[room.getRoomFloor() - 1].getLuxuryTilePane().getChildren().add(reserverLuxuryRoom.getAnchorPane());
        }
        for (RoomDTO room : normalRoomsList) {
            ReserveNormalRoom reserveNormalRoom = new ReserveNormalRoom(room.getRoomNo());

            if (isReserved(room.getRoomNo())) {
                reserveNormalRoom.setRoomReserve(roomReserveService.findRoomReserveByRoomNo(room.getRoomNo()));
                reserveNormalRoom.disablePanel();
            }

            RoomsList.add(reserveNormalRoom);
            floorMap[room.getRoomFloor() - 1].getNormalTilePane().getChildren().add(reserveNormalRoom.getAnchorPane());
        }
    }

    private boolean isReserved(String roomNo) throws Exception {

        for (RoomReserveDTO roomReserveDTO : roomReserveService.getAllReservedRoomReserves()) {
            if (roomReserveDTO.getRoomDTO().getRoomNo().equalsIgnoreCase(roomNo)) {
                return true;
            }
        }
        return false;
    }

}

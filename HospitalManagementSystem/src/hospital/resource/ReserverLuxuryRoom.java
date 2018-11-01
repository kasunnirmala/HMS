/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.resource;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import hospital.dto.RoomDTO;
import hospital.dto.RoomReserveDTO;
import hospital.proxy.ProxyHandler;
import hospital.service.ServiceFactory;
import hospital.service.custom.RoomService;
import hospital.service.custom.RoomTypeService;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

/**
 *
 * @author Kasun
 */
public class ReserverLuxuryRoom extends AdminAddLuxuryRoom {

    private RoomService roomService;
    private RoomTypeService roomTypeService;
    private RoomReserveDTO roomReserveDTO;

    public ReserverLuxuryRoom(String roomNo) throws Exception {
        super(roomNo);

        roomService = (RoomService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ROOM);
        roomTypeService = (RoomTypeService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ROOM_TYPE);
        setMouseClickedToReserve();
    }

    public void disablePanel() throws Exception {

        anchorPane.setStyle("-fx-background-image:url('/hospital/assets/luxuryReserve.png')");

        ((AnchorPane) anchorPane.getChildren().get(0)).setStyle("-fx-background-color:rgba(200,200,200,0.5)");
        ((Label) ((AnchorPane) anchorPane.getChildren().get(0)).getChildren().get(0)).setTextFill(Paint.valueOf("#000"));
        ((Label) ((AnchorPane) anchorPane.getChildren().get(0)).getChildren().get(0)).setText("VIEW");
        setMouseClickedToView();
    }

    public void enablePanel() throws Exception {

        anchorPane.setStyle("-fx-background-image:url('/hospital/assets/luxury.png')");

        ((AnchorPane) anchorPane.getChildren().get(0)).setStyle("-fx-background-color:rgba(0,0,255,0.5)");
        ((Label) ((AnchorPane) anchorPane.getChildren().get(0)).getChildren().get(0)).setTextFill(Paint.valueOf("#fff"));
        ((Label) ((AnchorPane) anchorPane.getChildren().get(0)).getChildren().get(0)).setText(roomNumer);

        setMouseClickedToReserve();
    }

    public void setMouseClickedToReserve() throws Exception {
        RoomDTO findRoomByID = roomService.findRoomByID(roomNumer);
        Parent modalWindow = FXMLLoader.load(this.getClass().getResource("/hospital/view/ReserveRoom.fxml"));
        JFXTextField txtRoomNo = (JFXTextField) modalWindow.lookup("#txtRoomNo");
        JFXTextField txtRoomType = (JFXTextField) modalWindow.lookup("#txtRoomType");
        JFXTextField txtRoomFloor = (JFXTextField) modalWindow.lookup("#txtRoomFloor");
        JFXTextField txtRoomPrice = (JFXTextField) modalWindow.lookup("#txtRoomPrice");

        txtRoomFloor.setText(Integer.toString(findRoomByID.getRoomFloor()));
        txtRoomNo.setText(findRoomByID.getRoomNo());
        txtRoomType.setText(findRoomByID.getRoomType());
        txtRoomPrice.setText(Double.toString(roomTypeService.findLastRoomTypeBYType(findRoomByID.getRoomType()).getRoomPrice()));

        anchorPane.setOnMouseClicked((MouseEvent evt) -> {
            try {
                CustomMethod.modalSeet(modalWindow, evt);
            } catch (IOException ex) {
                Logger.getLogger(ReserverLuxuryRoom.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void setMouseClickedToView() throws Exception {
        Parent modalWindow = FXMLLoader.load(this.getClass().getResource("/hospital/view/ReserveRoom.fxml"));
        JFXTextField txtPatientID = (JFXTextField) modalWindow.lookup("#txtPatientID");
        JFXTextField txtPatientName = (JFXTextField) modalWindow.lookup("#txtPatientName");
        JFXTextField txtPatientAge = (JFXTextField) modalWindow.lookup("#txtPatientAge");
        JFXTextField txtPatientGender = (JFXTextField) modalWindow.lookup("#txtPatientGender");

        JFXTextField txtAddress = (JFXTextField) modalWindow.lookup("#txtAddress");
        JFXTextField txtContact = (JFXTextField) modalWindow.lookup("#txtContact");
        JFXTextField txtGuardian = (JFXTextField) modalWindow.lookup("#txtGuardian");

        JFXTextField txtRoomNo = (JFXTextField) modalWindow.lookup("#txtRoomNo");
        JFXTextField txtRoomType = (JFXTextField) modalWindow.lookup("#txtRoomType");
        JFXTextField txtRoomFloor = (JFXTextField) modalWindow.lookup("#txtRoomFloor");
        JFXTextField txtRoomPrice = (JFXTextField) modalWindow.lookup("#txtRoomPrice");

        TextField txtResID = (TextField) modalWindow.lookup("#txtResID");

        Label lblDate = (Label) modalWindow.lookup("#lblDate");

        JFXButton btnReserve = (JFXButton) modalWindow.lookup("#btnReserve");
        btnReserve.setVisible(false);

        txtPatientID.setDisable(true);
        txtPatientName.setDisable(true);
        txtPatientAge.setDisable(true);
        txtPatientGender.setDisable(true);

        txtAddress.setDisable(true);
        txtContact.setDisable(true);
        txtGuardian.setDisable(true);

        txtRoomNo.setDisable(true);
        txtRoomType.setDisable(true);
        txtRoomFloor.setDisable(true);
        txtRoomPrice.setDisable(true);

        txtResID.setDisable(true);

        txtPatientID.setText(roomReserveDTO.getPatientDTO().getPatientID());
        txtPatientName.setText(roomReserveDTO.getPatientDTO().getPatientName());
        txtPatientAge.setText(Integer.toString(roomReserveDTO.getPatientDTO().getPatientAge()));
        txtPatientGender.setText(roomReserveDTO.getPatientDTO().getGender());

        txtAddress.setText(roomReserveDTO.getPatientDetailsDTO().getAddress());
        txtContact.setText(roomReserveDTO.getPatientDetailsDTO().getContact());
        txtGuardian.setText(roomReserveDTO.getPatientDetailsDTO().getGuardian());

        txtRoomNo.setText(roomReserveDTO.getRoomDTO().getRoomNo());
        txtRoomType.setText(roomReserveDTO.getRoomDTO().getRoomType());
        txtRoomFloor.setText(Integer.toString(roomReserveDTO.getRoomDTO().getRoomFloor()));
        txtRoomPrice.setText(Double.toString(roomTypeService.findLastRoomTypeBYType(roomReserveDTO.getRoomDTO().getRoomType()).getRoomPrice()));

        txtResID.setText(roomReserveDTO.getResID());

        lblDate.setText(roomReserveDTO.getDate());

        anchorPane.setOnMouseClicked((MouseEvent evt) -> {
            try {
                CustomMethod.modalSeet(modalWindow, evt);
            } catch (IOException ex) {
                Logger.getLogger(ReserverLuxuryRoom.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void setRoomReserve(RoomReserveDTO roomReserveDTO) {
        this.roomReserveDTO = roomReserveDTO;
    }

    public void removeMouseClicked() throws Exception {
        anchorPane.setOnMouseClicked((MouseEvent evt) -> {

        });
    }

}

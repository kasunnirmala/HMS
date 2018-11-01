/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import hospital.dto.RoomDTO;
import hospital.dto.RoomTypeDTO;
import hospital.observer.Observer;
import hospital.observer.Subject;
import hospital.proxy.ProxyHandler;
import hospital.resource.AdminAddLuxuryRoom;
import hospital.resource.AdminAddNormalRoom;
import hospital.resource.AdminCreateRoomFloor;
import hospital.resource.CustomMethod;
import hospital.service.ServiceFactory;
import hospital.service.custom.RoomService;
import hospital.service.custom.RoomTypeService;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import hospital.resource.SuperRooms;
import javafx.stage.Modality;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Kasun
 */
public class AdminRoomMapController implements Initializable, Observer {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox vbox;
    public static AdminCreateRoomFloor floor;
    @FXML
    private JFXButton btnRoomEdit;
    @FXML
    private AnchorPane editAnchorPane;
    private String roomNo;
    @FXML
    private JFXTextField txtLuxuryRoomPrice;
    @FXML
    private JFXTextField txtNormalRoomPrice;
    @FXML
    private NumberValidator numberValidator;

    private RoomTypeService roomTypeService;
    private RoomService roomService;

    private ObservableList<RoomDTO> luxuryRoomsList;
    private ObservableList<RoomDTO> normalRoomsList;

    private ObservableList<SuperRooms> RoomsList;
    private int totFloors = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            UnicastRemoteObject.exportObject(this, 0);
            Subject addRoomTypeSubject = (Subject) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ROOM_TYPE);
            addRoomTypeSubject.registerObserver(this);
            roomTypeService = (RoomTypeService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ROOM_TYPE);
            roomService = (RoomService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ROOM);

            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

            editAnchorPane.setVisible(false);
            txtLuxuryRoomPrice.setValidators(numberValidator);
            txtNormalRoomPrice.setValidators(numberValidator);

            normalRoomsList = FXCollections.observableArrayList();
            luxuryRoomsList = FXCollections.observableArrayList();
            RoomsList = FXCollections.observableArrayList();
            loadRooms();

            disableRoomContextMenues();
        } catch (RemoteException ex) {
            Logger.getLogger(AdminRoomMapController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AdminRoomMapController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnAddFloor(ActionEvent event) {
        floor = new AdminCreateRoomFloor(++totFloors);
        vbox.getChildren().add(floor.getPane());

    }

    @FXML
    private void btnAddLuxuryRoom(ActionEvent event) {
        Window theStage = ((Node) event.getSource()).getScene().getWindow();
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Enter the Number");
        dialog.setHeaderText("Enter Room Number");
        dialog.setContentText("Room Number : ");
        dialog.initOwner(theStage);
        dialog.initModality(Modality.APPLICATION_MODAL);
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(new Consumer<String>() {
            @Override
            public void accept(String name) {
                roomNo = name;
                AdminAddLuxuryRoom adminAddLuxuryRoom = new AdminAddLuxuryRoom(roomNo);
                RoomsList.add(adminAddLuxuryRoom);
                floor.getLuxuryTilePane().getChildren().add(adminAddLuxuryRoom.getAnchorPane());
                luxuryRoomsList.add(new RoomDTO(roomNo, "LXRY", floor.getFloor()));
            }
        });

    }

    @FXML
    private void btnAddNormalRoom(ActionEvent event) {
        Window theStage = ((Node) event.getSource()).getScene().getWindow();
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Enter the Number");
        dialog.setHeaderText("Enter Room Number");
        dialog.setContentText("Room Number : ");
        dialog.initOwner(theStage);
        dialog.initModality(Modality.APPLICATION_MODAL);
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(new Consumer<String>() {
            @Override
            public void accept(String name) {
                roomNo = name;
                AdminAddNormalRoom adminAddNormalRoom = new AdminAddNormalRoom(roomNo);
                RoomsList.add(adminAddNormalRoom);
                floor.getNormalTilePane().getChildren().add(adminAddNormalRoom.getAnchorPane());
                normalRoomsList.add(new RoomDTO(roomNo, "NRML", floor.getFloor()));
            }
        });

    }

    @FXML
    private void btnRoomEditOnAction(ActionEvent event) {
        enableRoomContextMenues();
        editAnchorPane.setVisible(true);
    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) throws Exception {
        disableRoomContextMenues();
        editAnchorPane.setVisible(false);
        for (RoomDTO roomDTO : normalRoomsList) {
            if (roomService.findRoomByID(roomDTO.getRoomNo()) == null) {
                if (roomService.addRoom(roomDTO)) {
                    CustomMethod.successNotification("Successfully");
                } else {
                    CustomMethod.errorNotification("Error On Save...!");
                }
            }
        }
        for (RoomDTO roomDTO : luxuryRoomsList) {
            if (roomService.findRoomByID(roomDTO.getRoomNo()) == null) {
                if (roomService.addRoom(roomDTO)) {
                    CustomMethod.successNotification("Successfully");
                } else {
                    CustomMethod.errorNotification("Error On Save...!");
                }
            }
        }
        loadRooms();
    }

    @FXML
    private void btnSetPriceLuxuryOnAction(ActionEvent event) throws Exception {
        RoomTypeDTO roomLuxuryTypeDTO = new RoomTypeDTO("LXRY", Double.parseDouble(txtLuxuryRoomPrice.getText()), new SimpleDateFormat("dd-MM-YYYY").format(new Date()));
        if (roomTypeService.addRoomType(roomLuxuryTypeDTO)) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Success");
//            alert.setHeaderText("Successfully saved");
//            alert.show();

            CustomMethod.successNotification("Success...!");
        }
    }

    @FXML
    private void btnSetPriceNormalOnAction(ActionEvent event) throws Exception {
        RoomTypeDTO roomNormalTypeDTO = new RoomTypeDTO("NRML", Double.parseDouble(txtNormalRoomPrice.getText()), new SimpleDateFormat("dd-MM-YYYY").format(new Date()));
        if (roomTypeService.addRoomType(roomNormalTypeDTO)) {
            CustomMethod.successNotification("Success...!");
        }
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
            AdminAddLuxuryRoom adminAddLuxuryRoom = new AdminAddLuxuryRoom(room.getRoomNo());
            RoomsList.add(adminAddLuxuryRoom);
            floorMap[room.getRoomFloor() - 1].getLuxuryTilePane().getChildren().add(adminAddLuxuryRoom.getAnchorPane());
        }
        for (RoomDTO room : normalRoomsList) {
            AdminAddNormalRoom adminAddNormalRoom = new AdminAddNormalRoom(room.getRoomNo());
            RoomsList.add(adminAddNormalRoom);
            floorMap[room.getRoomFloor() - 1].getNormalTilePane().getChildren().add(adminAddNormalRoom.getAnchorPane());
        }
    }

    private void enableRoomContextMenues() {
        ContextMenu contextMenu = new ContextMenu();
        for (SuperRooms room : RoomsList) {
            contextMenu = room.getContextMenu();

            MenuItem deleteMenu = new MenuItem("Delete");
            deleteMenu.setOnAction((evt) -> {
                try {
                    if (roomService.deleteRoom(room.getRoomNumer())) {
                        loadRooms();
                        enableRoomContextMenues();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(AdminRoomMapController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            contextMenu.getItems().add(deleteMenu);
        }

    }

    private void disableRoomContextMenues() {
        for (SuperRooms room : RoomsList) {
            ContextMenu contextMenu = room.getContextMenu();
            contextMenu = null;
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.resource;

import hospital.controller.AdminRoomMapController;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;

/**
 *
 * @author Kasun
 */
public class AdminCreateRoomFloor extends RoomFloor {

    public AdminCreateRoomFloor(int floor) {
        this.floor = floor;

        normalTilePane.setOnMouseClicked((evt) -> {
            AdminRoomMapController.floor = this;
        });

        luxuryTilePane.setOnMouseClicked((evt) -> {
            AdminRoomMapController.floor = this;
        });

        pane.setOnMouseClicked((evt) -> {
            AdminRoomMapController.floor = this;
        });

    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getFloor() {
        return floor;
    }


}

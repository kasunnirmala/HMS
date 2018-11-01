/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.resource;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

/**
 *
 * @author Kasun
 */
public class AddRoom implements SuperRooms {

    protected AnchorPane anchorPane;
    protected ContextMenu contextMenu = new ContextMenu();
    protected String roomNumer;

    public AddRoom(String roomNo) {
        roomNumer = roomNo;
        anchorPane = new AnchorPane();
        anchorPane.setPrefSize(60, 60);
        anchorPane.setStyle("-fx-background-image:url('/hospital/assets/luxury.png')");

        AnchorPane subPane = new AnchorPane();
        anchorPane.getChildren().addAll(subPane);
        AnchorPane.setTopAnchor(subPane, 5.0);
        AnchorPane.setBottomAnchor(subPane, 5.0);
        AnchorPane.setLeftAnchor(subPane, 5.0);
        AnchorPane.setRightAnchor(subPane, 5.0);

        subPane.setStyle("-fx-background-color:rgba(0,0,255,0.5)");
        Label label = new Label(roomNo);
        AnchorPane.setTopAnchor(label, 0.0);
        AnchorPane.setBottomAnchor(label, 0.0);
        AnchorPane.setLeftAnchor(label, 0.0);
        AnchorPane.setRightAnchor(label, 0.0);
        label.setAlignment(Pos.CENTER);
        label.setTextFill(Paint.valueOf("#FFF"));
        label.setStyle("-fx-font-size:20");
        label.setStyle("-fx-font-weight:bold");
        label.setCursor(Cursor.HAND);
        subPane.getChildren().add(label);
        subPane.setVisible(false);

        anchorPane.setOnMouseEntered((MouseEvent evt) -> {
            subPane.setVisible(true);
        });

        anchorPane.setOnMouseExited((MouseEvent evt) -> {
            subPane.setVisible(false);
        });

        anchorPane.setOnContextMenuRequested((ContextMenuEvent event) -> {
            if (contextMenu != null) {
                contextMenu.show(anchorPane, event.getScreenX(), event.getScreenY());
            }
        });

    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    @Override
    public ContextMenu getContextMenu() {
        return contextMenu;
    }

    @Override
    public String getRoomNumer() {
        return roomNumer;
    }

    @Override
    public void enableContextMenu() {
        contextMenu = new ContextMenu();
        MenuItem roomNumberMenu = new MenuItem("Change Room Number");
        roomNumberMenu.setOnAction((evt) -> {
            System.out.println(roomNumer);
        });
        MenuItem deleteMenu = new MenuItem("Delete");
        contextMenu.getItems().add(roomNumberMenu);
        contextMenu.getItems().add(deleteMenu);
    }

    @Override
    public void disableContextMenu() {
        contextMenu = null;
    }

}

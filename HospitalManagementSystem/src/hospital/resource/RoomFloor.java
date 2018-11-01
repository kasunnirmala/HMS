/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.resource;

import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;

/**
 *
 * @author Kasun
 */
public class RoomFloor {

    protected TilePane luxuryTilePane;
    protected TilePane normalTilePane;
    protected AnchorPane pane;
    protected int floor;

    public RoomFloor() {
        //setting anchor pane inside vbox
        pane = new AnchorPane();
        pane.getStylesheets().add("/hospital/style/adminroommap.css");
        pane.getStyleClass().add("paneStyle");
        pane.setPrefSize(1000, 500);

        //setting hbox
        HBox hbox = new HBox();
        hbox.setSpacing(20);
        AnchorPane.setTopAnchor(hbox, 70.0);
        AnchorPane.setBottomAnchor(hbox, 70.0);
        AnchorPane.setLeftAnchor(hbox, 20.0);
        AnchorPane.setRightAnchor(hbox, 20.0);

        //titledpane for normal rooms
        AnchorPane normalPane = new AnchorPane();
        normalPane.getStylesheets().add("/hospital/style/adminroommap.css");
        normalPane.getStyleClass().add("anchorStyle");
        TitledPane normalTitle = new TitledPane("Normal Rooms", normalPane);
        normalTitle.getStylesheets().add("/hospital/style/adminroommap.css");
        normalTitle.setCollapsible(false);
        normalTitle.setPrefSize(860, 360);
        ////// Tile Pane for Normal Room
        normalTilePane = new TilePane(30, 30);
        AnchorPane.setTopAnchor(normalTilePane, 0.0);
        AnchorPane.setBottomAnchor(normalTilePane, 0.0);
        AnchorPane.setLeftAnchor(normalTilePane, 0.0);
        AnchorPane.setRightAnchor(normalTilePane, 0.0);
        normalPane.getChildren().addAll(normalTilePane);

        //titledpane for normal rooms
        AnchorPane luxuryPane = new AnchorPane();
        luxuryPane.getStylesheets().add("/hospital/style/adminroommap.css");
        luxuryPane.getStyleClass().add("anchorStyle");
        TitledPane luxuryTitle = new TitledPane("Luxury Rooms", luxuryPane);
        luxuryTitle.getStylesheets().add("/hospital/style/adminroommap.css");
        luxuryTitle.setCollapsible(false);
        luxuryTitle.setPrefSize(860, 360);
        ////// Tile Pane for Normal Room
        luxuryTilePane = new TilePane(30, 30);
        AnchorPane.setTopAnchor(luxuryTilePane, 0.0);
        AnchorPane.setBottomAnchor(luxuryTilePane, 0.0);
        AnchorPane.setLeftAnchor(luxuryTilePane, 0.0);
        AnchorPane.setRightAnchor(luxuryTilePane, 0.0);
        luxuryPane.getChildren().addAll(luxuryTilePane);

        hbox.getChildren().add(normalTitle);
        hbox.getChildren().add(luxuryTitle);

        pane.getChildren().addAll(hbox);

    }

    public AnchorPane getPane() {
        return pane;
    }

    public TilePane getLuxuryTilePane() {
        return luxuryTilePane;
    }

    public TilePane getNormalTilePane() {
        return normalTilePane;
    }

}

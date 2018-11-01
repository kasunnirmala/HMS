/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.resource;

import javafx.scene.control.ContextMenu;

/**
 *
 * @author Kasun
 */
public interface SuperRooms {

    public ContextMenu getContextMenu();

    public String getRoomNumer();

    public void enableContextMenu();

    public void disableContextMenu();
}

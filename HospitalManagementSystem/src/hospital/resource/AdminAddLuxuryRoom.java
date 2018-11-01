/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.resource;

/**
 *
 * @author Kasun
 */
public class AdminAddLuxuryRoom extends AddRoom implements SuperRooms {

    public AdminAddLuxuryRoom(String roomNo) {
        super(roomNo);
       anchorPane.setStyle("-fx-background-image:url('/hospital/assets/Luxury.png')");

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Kasun
 */
@Entity
public class Room {

    @Id
    private String roomNo;
    @Column(nullable = false)
    private String roomType;
    @Column(nullable = false)
    private int roomFloor;

    public Room() {
    }

    public Room(String roomNo, String roomType, int roomFloor) {
        this.roomNo = roomNo;
        this.roomType = roomType;
        this.roomFloor = roomFloor;
    }

    /**
     * @return the roomNo
     */
    public String getRoomNo() {
        return roomNo;
    }

    /**
     * @param roomNo the roomNo to set
     */
    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    /**
     * @return the roomType
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * @param roomType the roomType to set
     */
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    /**
     * @return the roomFloor
     */
    public int getRoomFloor() {
        return roomFloor;
    }

    /**
     * @param roomFloor the roomFloor to set
     */
    public void setRoomFloor(int roomFloor) {
        this.roomFloor = roomFloor;
    }

}

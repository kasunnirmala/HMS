/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.dto;

/**
 *
 * @author Kasun
 */
public class RoomTypeDTO extends SuperDTO {

    private int roomTypeID;
    private String roomType;
    private double roomPrice;
    private String date;

    public RoomTypeDTO() {
    }

    public RoomTypeDTO(String roomType, double roomPrice, String date) {
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.date = date;
    }

    public RoomTypeDTO(int roomTypeID, String roomType, double roomPrice, String date) {
        this.roomTypeID = roomTypeID;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.date = date;
    }

    /**
     * @return the roomTypeID
     */
    public int getRoomTypeID() {
        return roomTypeID;
    }

    /**
     * @param roomTypeID the roomTypeID to set
     */
    public void setRoomTypeID(int roomTypeID) {
        this.roomTypeID = roomTypeID;
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
     * @return the roomPrice
     */
    public double getRoomPrice() {
        return roomPrice;
    }

    /**
     * @param roomPrice the roomPrice to set
     */
    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

}

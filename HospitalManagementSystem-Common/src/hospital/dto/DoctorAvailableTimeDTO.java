/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.dto;

import java.io.Serializable;

/**
 *
 * @author Kasun
 */
public class DoctorAvailableTimeDTO extends SuperDTO {

    private String startTime;
    private String endTime;

    public DoctorAvailableTimeDTO() {
    }

    public DoctorAvailableTimeDTO(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * @return the startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

}

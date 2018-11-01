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
public class DoctorAvailableDatesDTO extends SuperDTO {

    private boolean monday;
    private boolean tuesday;
    private boolean wednesday;
    private boolean thursday;
    private boolean friday;
    private boolean saturday;
    private boolean sunday;
    private DoctorAvailableTimeDTO mondayTime;
    private DoctorAvailableTimeDTO tuesdayTime;
    private DoctorAvailableTimeDTO wednesdayTime;
    private DoctorAvailableTimeDTO thursdayTime;
    private DoctorAvailableTimeDTO fridayTime;
    private DoctorAvailableTimeDTO saturdayTime;
    private DoctorAvailableTimeDTO sundayTime;

    public DoctorAvailableDatesDTO() {
    }

    public DoctorAvailableDatesDTO(boolean monday, boolean tuesday, boolean wednesday, boolean thursday, boolean friday, boolean saturday, boolean sunday, DoctorAvailableTimeDTO mondayTime, DoctorAvailableTimeDTO tuesdayTime, DoctorAvailableTimeDTO wednesdayTime, DoctorAvailableTimeDTO thursdayTime, DoctorAvailableTimeDTO fridayTime, DoctorAvailableTimeDTO saturdayTime, DoctorAvailableTimeDTO sundayTime) {
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
        this.mondayTime = mondayTime;
        this.tuesdayTime = tuesdayTime;
        this.wednesdayTime = wednesdayTime;
        this.thursdayTime = thursdayTime;
        this.fridayTime = fridayTime;
        this.saturdayTime = saturdayTime;
        this.sundayTime = sundayTime;
    }

    /**
     * @return the monday
     */
    public boolean isMonday() {
        return monday;
    }

    /**
     * @param monday the monday to set
     */
    public void setMonday(boolean monday) {
        this.monday = monday;
    }

    /**
     * @return the tuesday
     */
    public boolean isTuesday() {
        return tuesday;
    }

    /**
     * @param tuesday the tuesday to set
     */
    public void setTuesday(boolean tuesday) {
        this.tuesday = tuesday;
    }

    /**
     * @return the wednesday
     */
    public boolean isWednesday() {
        return wednesday;
    }

    /**
     * @param wednesday the wednesday to set
     */
    public void setWednesday(boolean wednesday) {
        this.wednesday = wednesday;
    }

    /**
     * @return the thursday
     */
    public boolean isThursday() {
        return thursday;
    }

    /**
     * @param thursday the thursday to set
     */
    public void setThursday(boolean thursday) {
        this.thursday = thursday;
    }

    /**
     * @return the friday
     */
    public boolean isFriday() {
        return friday;
    }

    /**
     * @param friday the friday to set
     */
    public void setFriday(boolean friday) {
        this.friday = friday;
    }

    /**
     * @return the saturday
     */
    public boolean isSaturday() {
        return saturday;
    }

    /**
     * @param saturday the saturday to set
     */
    public void setSaturday(boolean saturday) {
        this.saturday = saturday;
    }

    /**
     * @return the sunday
     */
    public boolean isSunday() {
        return sunday;
    }

    /**
     * @param sunday the sunday to set
     */
    public void setSunday(boolean sunday) {
        this.sunday = sunday;
    }

    /**
     * @return the mondayTime
     */
    public DoctorAvailableTimeDTO getMondayTime() {
        return mondayTime;
    }

    /**
     * @param mondayTime the mondayTime to set
     */
    public void setMondayTime(DoctorAvailableTimeDTO mondayTime) {
        this.mondayTime = mondayTime;
    }

    /**
     * @return the tuesdayTime
     */
    public DoctorAvailableTimeDTO getTuesdayTime() {
        return tuesdayTime;
    }

    /**
     * @param tuesdayTime the tuesdayTime to set
     */
    public void setTuesdayTime(DoctorAvailableTimeDTO tuesdayTime) {
        this.tuesdayTime = tuesdayTime;
    }

    /**
     * @return the wednesdayTime
     */
    public DoctorAvailableTimeDTO getWednesdayTime() {
        return wednesdayTime;
    }

    /**
     * @param wednesdayTime the wednesdayTime to set
     */
    public void setWednesdayTime(DoctorAvailableTimeDTO wednesdayTime) {
        this.wednesdayTime = wednesdayTime;
    }

    /**
     * @return the thursdayTime
     */
    public DoctorAvailableTimeDTO getThursdayTime() {
        return thursdayTime;
    }

    /**
     * @param thursdayTime the thursdayTime to set
     */
    public void setThursdayTime(DoctorAvailableTimeDTO thursdayTime) {
        this.thursdayTime = thursdayTime;
    }

    /**
     * @return the fridayTime
     */
    public DoctorAvailableTimeDTO getFridayTime() {
        return fridayTime;
    }

    /**
     * @param fridayTime the fridayTime to set
     */
    public void setFridayTime(DoctorAvailableTimeDTO fridayTime) {
        this.fridayTime = fridayTime;
    }

    /**
     * @return the saturdayTime
     */
    public DoctorAvailableTimeDTO getSaturdayTime() {
        return saturdayTime;
    }

    /**
     * @param saturdayTime the saturdayTime to set
     */
    public void setSaturdayTime(DoctorAvailableTimeDTO saturdayTime) {
        this.saturdayTime = saturdayTime;
    }

    /**
     * @return the sundayTime
     */
    public DoctorAvailableTimeDTO getSundayTime() {
        return sundayTime;
    }

    /**
     * @param sundayTime the sundayTime to set
     */
    public void setSundayTime(DoctorAvailableTimeDTO sundayTime) {
        this.sundayTime = sundayTime;
    }

}

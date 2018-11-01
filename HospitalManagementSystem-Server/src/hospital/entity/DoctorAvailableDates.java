/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Kasun
 */
@Embeddable
public class DoctorAvailableDates {

    private boolean monday;
    private boolean tuesday;
    private boolean wednesday;
    private boolean thursday;
    private boolean friday;
    private boolean saturday;
    private boolean sunday;
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "startTime", column = @Column(name = "monday_startTime"))
        ,@AttributeOverride(name = "endTime", column = @Column(name = "monday_endTime"))})
    private DoctorAvailableTime mondayTime;
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "startTime", column = @Column(name = "tuesday_startTime"))
        ,@AttributeOverride(name = "endTime", column = @Column(name = "tuesday_endTime"))})
    private DoctorAvailableTime tuesdayTime;
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "startTime", column = @Column(name = "wednesday_startTime"))
        ,@AttributeOverride(name = "endTime", column = @Column(name = "wednesday_endTime"))})
    private DoctorAvailableTime wednesdayTime;
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "startTime", column = @Column(name = "thursday_startTime"))
        ,@AttributeOverride(name = "endTime", column = @Column(name = "thursday_endTime"))})
    private DoctorAvailableTime thursdayTime;
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "startTime", column = @Column(name = "friday_startTime"))
        ,@AttributeOverride(name = "endTime", column = @Column(name = "friday_endTime"))})
    private DoctorAvailableTime fridayTime;
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "startTime", column = @Column(name = "saturday_startTime"))
        ,@AttributeOverride(name = "endTime", column = @Column(name = "saturday_endTime"))})
    private DoctorAvailableTime saturdayTime;
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "startTime", column = @Column(name = "sunday_startTime"))
        ,@AttributeOverride(name = "endTime", column = @Column(name = "sunday_endTime"))})
    private DoctorAvailableTime sundayTime;

    public DoctorAvailableDates() {
    }

    public DoctorAvailableDates(boolean monday, boolean tuesday, boolean wednesday, boolean thursday, boolean friday, boolean saturday, boolean sunday, DoctorAvailableTime mondayTime, DoctorAvailableTime tuesdayTime, DoctorAvailableTime wednesdayTime, DoctorAvailableTime thursdayTime, DoctorAvailableTime fridayTime, DoctorAvailableTime saturdayTime, DoctorAvailableTime sundayTime) {
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
    public DoctorAvailableTime getMondayTime() {
        return mondayTime;
    }

    /**
     * @param mondayTime the mondayTime to set
     */
    public void setMondayTime(DoctorAvailableTime mondayTime) {
        this.mondayTime = mondayTime;
    }

    /**
     * @return the tuesdayTime
     */
    public DoctorAvailableTime getTuesdayTime() {
        return tuesdayTime;
    }

    /**
     * @param tuesdayTime the tuesdayTime to set
     */
    public void setTuesdayTime(DoctorAvailableTime tuesdayTime) {
        this.tuesdayTime = tuesdayTime;
    }

    /**
     * @return the wednesdayTime
     */
    public DoctorAvailableTime getWednesdayTime() {
        return wednesdayTime;
    }

    /**
     * @param wednesdayTime the wednesdayTime to set
     */
    public void setWednesdayTime(DoctorAvailableTime wednesdayTime) {
        this.wednesdayTime = wednesdayTime;
    }

    /**
     * @return the thursdayTime
     */
    public DoctorAvailableTime getThursdayTime() {
        return thursdayTime;
    }

    /**
     * @param thursdayTime the thursdayTime to set
     */
    public void setThursdayTime(DoctorAvailableTime thursdayTime) {
        this.thursdayTime = thursdayTime;
    }

    /**
     * @return the fridayTime
     */
    public DoctorAvailableTime getFridayTime() {
        return fridayTime;
    }

    /**
     * @param fridayTime the fridayTime to set
     */
    public void setFridayTime(DoctorAvailableTime fridayTime) {
        this.fridayTime = fridayTime;
    }

    /**
     * @return the saturdayTime
     */
    public DoctorAvailableTime getSaturdayTime() {
        return saturdayTime;
    }

    /**
     * @param saturdayTime the saturdayTime to set
     */
    public void setSaturdayTime(DoctorAvailableTime saturdayTime) {
        this.saturdayTime = saturdayTime;
    }

    /**
     * @return the sundayTime
     */
    public DoctorAvailableTime getSundayTime() {
        return sundayTime;
    }

    /**
     * @param sundayTime the sundayTime to set
     */
    public void setSundayTime(DoctorAvailableTime sundayTime) {
        this.sundayTime = sundayTime;
    }

}

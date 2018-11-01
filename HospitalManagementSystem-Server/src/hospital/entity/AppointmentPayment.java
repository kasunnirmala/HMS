/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Kasun
 */
@Entity
public class AppointmentPayment {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String appPaymentID;
    @OneToOne(cascade = CascadeType.ALL)
    private Appointment appointment;
    private double totalValue;
    private double changeAmount;
    private double userAmount;

    public AppointmentPayment() {
    }

    public AppointmentPayment(String appPaymentID, Appointment appointment, double totalValue, double changeAmount, double userAmount) {
        this.appPaymentID = appPaymentID;
        this.appointment = appointment;
        this.totalValue = totalValue;
        this.changeAmount = changeAmount;
        this.userAmount = userAmount;
    }

    public AppointmentPayment(Appointment appointment, double totalValue, double changeAmount, double userAmount) {
        this.appointment = appointment;
        this.totalValue = totalValue;
        this.changeAmount = changeAmount;
        this.userAmount = userAmount;
    }

    /**
     * @return the appPaymentID
     */
    public String getAppPaymentID() {
        return appPaymentID;
    }

    /**
     * @param appPaymentID the appPaymentID to set
     */
    public void setAppPaymentID(String appPaymentID) {
        this.appPaymentID = appPaymentID;
    }

    /**
     * @return the appointment
     */
    public Appointment getAppointment() {
        return appointment;
    }

    /**
     * @param appointment the appointment to set
     */
    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    /**
     * @return the totalValue
     */
    public double getTotalValue() {
        return totalValue;
    }

    /**
     * @param totalValue the totalValue to set
     */
    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    /**
     * @return the changeAmount
     */
    public double getChangeAmount() {
        return changeAmount;
    }

    /**
     * @param changeAmount the changeAmount to set
     */
    public void setChangeAmount(double changeAmount) {
        this.changeAmount = changeAmount;
    }

    /**
     * @return the userAmount
     */
    public double getUserAmount() {
        return userAmount;
    }

    /**
     * @param userAmount the userAmount to set
     */
    public void setUserAmount(double userAmount) {
        this.userAmount = userAmount;
    }

}

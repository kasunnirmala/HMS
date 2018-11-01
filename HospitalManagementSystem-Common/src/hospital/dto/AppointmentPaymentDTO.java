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
public class AppointmentPaymentDTO extends SuperDTO{
      private String appPaymentID;
    private AppointmentDTO appointmentDTO;
    private double totalValue;
    private double change;
    private double userAmount;

    public AppointmentPaymentDTO() {
    }

    public AppointmentPaymentDTO(String appPaymentID, AppointmentDTO appointmentDTO, double totalValue, double change, double userAmount) {
        this.appPaymentID = appPaymentID;
        this.appointmentDTO = appointmentDTO;
        this.totalValue = totalValue;
        this.change = change;
        this.userAmount = userAmount;
    }

    public AppointmentPaymentDTO(AppointmentDTO appointmentDTO, double totalValue, double change, double userAmount) {
        this.appointmentDTO = appointmentDTO;
        this.totalValue = totalValue;
        this.change = change;
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
     * @return the appointmentDTO
     */
    public AppointmentDTO getAppointmentDTO() {
        return appointmentDTO;
    }

    /**
     * @param appointmentDTO the appointmentDTO to set
     */
    public void setAppointmentDTO(AppointmentDTO appointmentDTO) {
        this.appointmentDTO = appointmentDTO;
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
     * @return the change
     */
    public double getChange() {
        return change;
    }

    /**
     * @param change the change to set
     */
    public void setChange(double change) {
        this.change = change;
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

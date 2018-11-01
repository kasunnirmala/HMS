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
public class OtherServicesDTO extends SuperDTO {

    private int serviceID;
    private String serviceDescription;
    private double rate;

    public OtherServicesDTO() {
    }

    public OtherServicesDTO(int serviceID, String serviceDescription, double rate) {
        this.serviceID = serviceID;
        this.serviceDescription = serviceDescription;
        this.rate = rate;
    }

    public OtherServicesDTO(String serviceDescription, double rate) {
        this.serviceDescription = serviceDescription;
        this.rate = rate;
    }
    

    /**
     * @return the serviceID
     */
    public int getServiceID() {
        return serviceID;
    }

    /**
     * @param serviceID the serviceID to set
     */
    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    /**
     * @return the serviceDescription
     */
    public String getServiceDescription() {
        return serviceDescription;
    }

    /**
     * @param serviceDescription the serviceDescription to set
     */
    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    /**
     * @return the rate
     */
    public double getRate() {
        return rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(double rate) {
        this.rate = rate;
    }

}

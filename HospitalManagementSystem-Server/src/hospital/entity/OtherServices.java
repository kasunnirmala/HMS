/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Kasun
 */
@Entity
public class OtherServices {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int serviceID;
    private String serviceDescription;
    private double rate;

    public OtherServices() {
    }

    public OtherServices(int serviceID, String serviceDescription, double rate) {
        this.serviceID = serviceID;
        this.serviceDescription = serviceDescription;
        this.rate = rate;
    }

    public OtherServices(String serviceDescription, double rate) {
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

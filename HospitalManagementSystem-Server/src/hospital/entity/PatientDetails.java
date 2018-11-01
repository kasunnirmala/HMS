/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.entity;

import javax.persistence.Embeddable;

/**
 *
 * @author Kasun
 */
@Embeddable
public class PatientDetails {
    private String address;
    private String contact;
    private String guardian;

    public PatientDetails() {
    }

    public PatientDetails(String address, String contact, String guardian) {
        this.address = address;
        this.contact = contact;
        this.guardian = guardian;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * @return the guardian
     */
    public String getGuardian() {
        return guardian;
    }

    /**
     * @param guardian the guardian to set
     */
    public void setGuardian(String guardian) {
        this.guardian = guardian;
    }
    
}

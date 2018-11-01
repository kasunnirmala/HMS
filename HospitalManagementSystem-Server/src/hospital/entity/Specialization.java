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
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Kasun
 */
@Entity
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int specialityID;
    private String specialityName;
    private String specialityDetails;

    public Specialization() {
    }

    public Specialization(String specialityName, String specialityDetails) {
        this.specialityName = specialityName;
        this.specialityDetails = specialityDetails;
    }

    public Specialization(int specialityID, String specialityName, String specialityDetails) {
        this.specialityID = specialityID;
        this.specialityName = specialityName;
        this.specialityDetails = specialityDetails;
    }

    /**
     * @return the specialityID
     */
    public int getSpecialityID() {
        return specialityID;
    }

    /**
     * @param specialityID the specialityID to set
     */
    public void setSpecialityID(int specialityID) {
        this.specialityID = specialityID;
    }

    /**
     * @return the specialityName
     */
    public String getSpecialityName() {
        return specialityName;
    }

    /**
     * @param specialityName the specialityName to set
     */
    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }

    /**
     * @return the specialityDetails
     */
    public String getSpecialityDetails() {
        return specialityDetails;
    }

    /**
     * @param specialityDetails the specialityDetails to set
     */
    public void setSpecialityDetails(String specialityDetails) {
        this.specialityDetails = specialityDetails;
    }

    

}

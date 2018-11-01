/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Kasun
 */
public class SpecializationDTO implements Serializable {

    private int specialityID;
    private String specialityName;
    private String specialityDetails;

    public SpecializationDTO() {
    }

    public SpecializationDTO(String specialityName, String specialityDetails) {
        this.specialityName = specialityName;
        this.specialityDetails = specialityDetails;
    }

    public SpecializationDTO(int specialityID, String specialityName, String specialityDetails) {
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.specialityID;
        hash = 37 * hash + Objects.hashCode(this.specialityName);
        hash = 37 * hash + Objects.hashCode(this.specialityDetails);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SpecializationDTO other = (SpecializationDTO) obj;
        if (this.specialityID != other.specialityID) {
            return false;
        }
        if (!Objects.equals(this.specialityName, other.specialityName)) {
            return false;
        }
        if (!Objects.equals(this.specialityDetails, other.specialityDetails)) {
            return false;
        }
        return true;
    }

}

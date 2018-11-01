/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.business.custom;

import hospital.business.SuperBO;
import hospital.dto.SpecializationDTO;
import java.util.List;

/**
 *
 * @author Kasun
 */
public interface SpecializationBO extends SuperBO {

    public boolean addSpecialization(SpecializationDTO specializationDTO) throws Exception;

    public boolean updateSpecialization(SpecializationDTO specializationDTO) throws Exception;

    public boolean deleteSpecialization(String SpecializationID) throws Exception;

    public SpecializationDTO findSpecializationByID(String ID) throws Exception;

    public SpecializationDTO findSpecializationByName(String name) throws Exception;

    public List<SpecializationDTO> getAllSpecialization() throws Exception;
}

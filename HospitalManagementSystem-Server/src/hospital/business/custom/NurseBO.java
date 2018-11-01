/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.business.custom;

import hospital.business.SuperBO;
import hospital.dto.NurseDTO;
import java.util.List;

/**
 *
 * @author Kasun
 */
public interface NurseBO extends SuperBO {

    public boolean addNurse(NurseDTO nurseDTO) throws Exception;

    public boolean updateNurse(NurseDTO nurseDTO) throws Exception;

    public boolean deleteNurse(String nurseID) throws Exception;

    public NurseDTO findNurseByID(String ID) throws Exception;

    public NurseDTO findNurseByName(String name) throws Exception;

    public List<NurseDTO> getAllNurses() throws Exception;
}

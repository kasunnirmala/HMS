/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.service.custom;

import hospital.dto.NurseDTO;
import hospital.service.SuperService;
import java.util.List;

/**
 *
 * @author Kasun
 */
public interface NurseService extends SuperService {

    public boolean addNurse(NurseDTO nurseDTO) throws Exception;

    public boolean updateNurse(NurseDTO nurseDTO) throws Exception;

    public boolean deleteNurse(String nurseID) throws Exception;

    public NurseDTO findNurseByID(String ID) throws Exception;

    public NurseDTO findNurseByName(String name) throws Exception;

    public List<NurseDTO> getAllNurses() throws Exception;

}

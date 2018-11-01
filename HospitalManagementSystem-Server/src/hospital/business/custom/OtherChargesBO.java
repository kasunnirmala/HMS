/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.business.custom;

import hospital.business.SuperBO;
import hospital.dto.OtherServicesDTO;
import java.util.List;

/**
 *
 * @author Kasun
 */
public interface OtherChargesBO extends SuperBO {

    public boolean addOtherCharges(OtherServicesDTO otherServicesDTO) throws Exception;

    public boolean updateOtherCharges(OtherServicesDTO otherServicesDTO) throws Exception;

    public boolean deleteOtherCharges(int ID) throws Exception;

    public OtherServicesDTO findOtherChargesByID(int ID) throws Exception;

    public List<OtherServicesDTO> getAllOtherCharges() throws Exception;
}

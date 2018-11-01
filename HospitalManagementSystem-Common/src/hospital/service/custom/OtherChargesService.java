/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.service.custom;

import hospital.dto.OtherServicesDTO;
import hospital.service.SuperService;
import java.util.List;

/**
 *
 * @author Kasun
 */
public interface OtherChargesService extends SuperService {

    public boolean addOtherCharges(OtherServicesDTO otherServicesDTO) throws Exception;

    public boolean updateOtherCharges(OtherServicesDTO otherServicesDTO) throws Exception;

    public boolean deleteOtherCharges(int ID) throws Exception;

    public OtherServicesDTO findOtherChargesByID(int ID) throws Exception;

    public List<OtherServicesDTO> getAllOtherCharges() throws Exception;
}

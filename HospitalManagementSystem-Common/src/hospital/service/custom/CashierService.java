/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.service.custom;

import hospital.dto.CashierDTO;
import hospital.service.SuperService;
import java.util.List;

/**
 *
 * @author Kasun
 */
public interface CashierService extends SuperService {

    public boolean addCashier(CashierDTO cashierDTO) throws Exception;

    public boolean updateCashier(CashierDTO cashierDTO) throws Exception;

    public boolean deleteCashier(String cashierID) throws Exception;

    public CashierDTO findCashierByID(String ID) throws Exception;

    public CashierDTO findCashierByName(String name) throws Exception;

    public List<CashierDTO> getAllCashier() throws Exception;
}

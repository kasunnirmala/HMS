/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.business.custom;

import hospital.business.SuperBO;
import hospital.dto.CashierDTO;
import java.util.List;

/**
 *
 * @author Kasun
 */
public interface CashierBO extends SuperBO{

    public boolean addCashier(CashierDTO cashierDTO) throws Exception;

    public boolean updateCashier(CashierDTO cashierDTO) throws Exception;

    public boolean deleteCashier(String cashierID) throws Exception;

    public CashierDTO findCashierByID(String ID) throws Exception;

    public CashierDTO findCashierByName(String name) throws Exception;

    public List<CashierDTO> getAllCashier() throws Exception;
}

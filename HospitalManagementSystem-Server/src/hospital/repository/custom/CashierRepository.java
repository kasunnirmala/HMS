/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.repository.custom;

import hospital.entity.Cashier;
import hospital.repository.SuperRepository;

/**
 *
 * @author Kasun
 */
public interface CashierRepository extends SuperRepository<Cashier, String> {

    public Cashier findByName(String name) throws Exception;
}

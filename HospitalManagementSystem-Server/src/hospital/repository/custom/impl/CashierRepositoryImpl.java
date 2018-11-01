/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.repository.custom.impl;

import hospital.entity.Cashier;
import hospital.repository.SuperRepositoryImpl;
import hospital.repository.custom.CashierRepository;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Kasun
 */
public class CashierRepositoryImpl extends SuperRepositoryImpl<Cashier, String> implements CashierRepository {

    public CashierRepositoryImpl() {
    }

    @Override
    public Cashier findByName(String name) throws Exception {
         return (Cashier) session.createCriteria(entityClass).add(Restrictions.eq("firstName", name)).list().get(0);
    }

}

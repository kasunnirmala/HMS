/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.repository.custom.impl;

import hospital.entity.Nurse;
import hospital.repository.SuperRepositoryImpl;
import hospital.repository.custom.NurseRepository;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Kasun
 */
public class NurseRepositoryImpl extends SuperRepositoryImpl<Nurse, String> implements NurseRepository {

    public NurseRepositoryImpl() {
    }

    @Override
    public Nurse findByName(String name) throws Exception {
        return (Nurse) session.createCriteria(entityClass).add(Restrictions.eq("firstName", name)).list().get(0);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.repository.custom.impl;

import hospital.entity.Specialization;
import hospital.repository.SuperRepositoryImpl;
import hospital.repository.custom.SpecializationRepository;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class SpecializationRepositoryImpl extends SuperRepositoryImpl<Specialization, String> implements SpecializationRepository {

    public SpecializationRepositoryImpl() {
    }

    @Override
    public Specialization findByName(String name) throws Exception {
        return (Specialization) session.createCriteria(entityClass).add(Restrictions.eq("specialityName", name)).list().get(0);
    }

}

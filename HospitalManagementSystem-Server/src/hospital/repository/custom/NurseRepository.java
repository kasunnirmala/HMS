/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.repository.custom;

import hospital.entity.Nurse;
import hospital.repository.SuperRepository;

/**
 *
 * @author Kasun
 */
public interface NurseRepository extends SuperRepository<Nurse, String> {
       public Nurse findByName(String name) throws Exception;
}

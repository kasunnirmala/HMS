/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.repository.custom.impl;

import hospital.entity.Patient;
import hospital.repository.SuperRepositoryImpl;
import hospital.repository.custom.PatientRepository;
import javax.sound.midi.Patch;

/**
 *
 * @author Kasun
 */
public class PatientRepositoryImpl extends SuperRepositoryImpl<Patient, String> implements PatientRepository {

    public PatientRepositoryImpl() {
    }

}

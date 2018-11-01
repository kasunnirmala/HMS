/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.service.custom.impl;

import hospital.business.BOFactory;
import hospital.business.custom.TreatmentBO;
import hospital.dto.TreatmentDTO;
import hospital.service.custom.TreatmentService;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 *
 * @author Kasun
 */
public class TreatmentServiceImpl extends UnicastRemoteObject implements TreatmentService {

    private TreatmentBO treatmentBO;

    public TreatmentServiceImpl() throws RemoteException {
        treatmentBO = (TreatmentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.TREATMENT);
    }

    @Override
    public boolean addTreatment(TreatmentDTO treatmentDTO) throws Exception {
        boolean result = treatmentBO.addTreatment(treatmentDTO);
        return result;
    }

    @Override
    public boolean updateTreatment(TreatmentDTO treatmentDTO) throws Exception {
        boolean result = treatmentBO.updateTreatment(treatmentDTO);
        return result;
    }

    @Override
    public boolean deleteTreatment(String treatmentID) throws Exception {
        boolean result = treatmentBO.deleteTreatment(treatmentID);
        return result;
    }

    @Override
    public TreatmentDTO findTreatmentByID(int ID) throws Exception {
        return treatmentBO.findTreatmentByID(ID);
    }

    @Override
    public TreatmentDTO findTreatmentByPatient(String patientID) throws Exception {
        System.out.println("service");
        return treatmentBO.findTreatmentByPatient(patientID);
    }

    @Override
    public List<TreatmentDTO> getAllTreatments() throws Exception {
        return treatmentBO.getAllTreatments();
    }

    @Override
    public List<TreatmentDTO> getAllTreatmentsByPatient(String patientID) throws Exception {

        return treatmentBO.getAllTreatmentsByPatient(patientID);
    }

}

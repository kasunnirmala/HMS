/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.service.custom.impl;

import hospital.business.BOFactory;
import hospital.business.custom.CashierBO;
import hospital.dto.CashierDTO;
import hospital.observer.Observer;
import hospital.observer.Subject;
import hospital.service.custom.CashierService;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kasun
 */
public class CashierServiceImpl extends UnicastRemoteObject implements CashierService, Subject {

    private CashierBO cashierBO;
    private static ArrayList<Observer> alObservers = new ArrayList<>();

    public CashierServiceImpl() throws RemoteException {
        cashierBO = (CashierBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CASHIER);
    }

    @Override
    public boolean addCashier(CashierDTO cashierDTO) throws Exception {
        boolean result = cashierBO.addCashier(cashierDTO);
        notifyObservers();
        return result;
    }

    @Override
    public boolean updateCashier(CashierDTO cashierDTO) throws Exception {
        boolean result = cashierBO.updateCashier(cashierDTO);
        return result;
    }

    @Override
    public boolean deleteCashier(String cashierID) throws Exception {
        boolean result = cashierBO.deleteCashier(cashierID);
        notifyObservers();
        return result;
    }

    @Override
    public CashierDTO findCashierByID(String ID) throws Exception {
        return cashierBO.findCashierByID(ID);
    }

    @Override
    public CashierDTO findCashierByName(String name) throws Exception {
        return cashierBO.findCashierByName(name);
    }

    @Override
    public List<CashierDTO> getAllCashier() throws Exception {
        return cashierBO.getAllCashier();
    }

    @Override
    public void registerObserver(Observer observer) throws Exception {
        alObservers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) throws Exception {
        alObservers.remove(observer);
    }

    @Override
    public void notifyObservers() throws Exception {
        new Thread(() -> {
            for (Observer observer : alObservers) {
                try {
                    observer.updateObservers();
                } catch (Exception ex) {
                    Logger.getLogger(SpecializationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }

}

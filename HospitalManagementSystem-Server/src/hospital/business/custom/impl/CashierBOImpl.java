/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.business.custom.impl;

import hospital.business.custom.CashierBO;
import hospital.dto.CashierDTO;
import hospital.entity.Cashier;
import hospital.repository.RepositoryFactory;
import hospital.repository.custom.CashierRepository;
import hospital.resource.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

public class CashierBOImpl implements CashierBO {

    private CashierRepository cashierRepository;

    public CashierBOImpl() {
        cashierRepository = (CashierRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.CASHIER);
    }

    @Override
    public boolean addCashier(CashierDTO cashierDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            cashierRepository.setSession(session);

            session.beginTransaction();

            Cashier cashier = new Cashier(
                    cashierDTO.getCashierID(),
                    cashierDTO.getFirstName(),
                    cashierDTO.getMiddleName(),
                    cashierDTO.getLastName(),
                    cashierDTO.getGender(),
                    cashierDTO.getAddress(),
                    cashierDTO.getDob(),
                    cashierDTO.getMobileNo(),
                    cashierDTO.getPhoneNo(),
                    cashierDTO.getEmail(),
                    cashierDTO.getCashierImage()
            );
            boolean result = cashierRepository.save(cashier);

            session.getTransaction().commit();

            return result;
        }
    }

    @Override
    public boolean updateCashier(CashierDTO cashierDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            cashierRepository.setSession(session);

            session.beginTransaction();

            Cashier cashier = new Cashier(
                    cashierDTO.getCashierID(),
                    cashierDTO.getFirstName(),
                    cashierDTO.getMiddleName(),
                    cashierDTO.getLastName(),
                    cashierDTO.getGender(),
                    cashierDTO.getAddress(),
                    cashierDTO.getDob(),
                    cashierDTO.getMobileNo(),
                    cashierDTO.getPhoneNo(),
                    cashierDTO.getEmail(),
                    cashierDTO.getCashierImage()
            );
            cashierRepository.update(cashier);

            session.getTransaction().commit();

            return true;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCashier(String cashierID) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            cashierRepository.setSession(session);

            session.beginTransaction();

            Cashier cashier = cashierRepository.findById(cashierID);
            boolean result = false;

            if (cashier != null) {

                cashierRepository.delete(cashier);
            }

            session.getTransaction().commit();

            return result;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
    }

    @Override
    public CashierDTO findCashierByID(String ID) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            cashierRepository.setSession(session);

            session.beginTransaction();

            Cashier cashier = cashierRepository.findById(ID);

            session.getTransaction().commit();

            if (cashier != null) {
                return new CashierDTO(
                        cashier.getCashierID(),
                        cashier.getFirstName(),
                        cashier.getMiddleName(),
                        cashier.getLastName(),
                        cashier.getGender(),
                        cashier.getAddress(),
                        cashier.getDob(),
                        cashier.getMobileNo(),
                        cashier.getPhoneNo(),
                        cashier.getEmail(),
                        cashier.getCashierImage()
                );
            } else {
                return null;
            }

        }
    }

    @Override
    public CashierDTO findCashierByName(String name) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            cashierRepository.setSession(session);

            session.beginTransaction();

            Cashier cashier = cashierRepository.findByName(name);

            session.getTransaction().commit();

            if (cashier != null) {
                return new CashierDTO(
                        cashier.getCashierID(),
                        cashier.getFirstName(),
                        cashier.getMiddleName(),
                        cashier.getLastName(),
                        cashier.getGender(),
                        cashier.getAddress(),
                        cashier.getDob(),
                        cashier.getMobileNo(),
                        cashier.getPhoneNo(),
                        cashier.getEmail(),
                        cashier.getCashierImage()
                );
            } else {
                return null;
            }

        }
    }

    @Override
    public List<CashierDTO> getAllCashier() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            cashierRepository.setSession(session);

            session.beginTransaction();

            List<Cashier> cashiers = cashierRepository.findAll();

            session.getTransaction().commit();

            if (cashiers != null) {

                List<CashierDTO> alCashiers = new ArrayList<>();

                for (Cashier cashier : cashiers) {
                    CashierDTO dto = new CashierDTO(
                            cashier.getCashierID(),
                            cashier.getFirstName(),
                            cashier.getMiddleName(),
                            cashier.getLastName(),
                            cashier.getGender(),
                            cashier.getAddress(),
                            cashier.getDob(),
                            cashier.getMobileNo(),
                            cashier.getPhoneNo(),
                            cashier.getEmail(),
                            cashier.getCashierImage()
                    );

                    alCashiers.add(dto);
                }
                return alCashiers;
            } else {
                return null;
            }

        }
    }
}

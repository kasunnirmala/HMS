/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.business.custom.impl;

import hospital.business.custom.OtherChargesBO;
import hospital.dto.OtherServicesDTO;
import hospital.entity.OtherServices;
import hospital.repository.RepositoryFactory;
import hospital.repository.custom.OtherChargesRepository;
import hospital.resource.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

public class OtherChargesBOImpl implements OtherChargesBO {

    private OtherChargesRepository otherChargesRepository;

    public OtherChargesBOImpl() {
        otherChargesRepository = (OtherChargesRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.OTHERCHARGES);
    }

    @Override
    public boolean addOtherCharges(OtherServicesDTO otherServicesDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            otherChargesRepository.setSession(session);

            session.beginTransaction();

            OtherServices otherServices = new OtherServices(otherServicesDTO.getServiceDescription(), otherServicesDTO.getRate());
            boolean result = otherChargesRepository.save(otherServices);

            session.getTransaction().commit();

            return result;
        }
    }

    @Override
    public boolean updateOtherCharges(OtherServicesDTO otherServicesDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            otherChargesRepository.setSession(session);

            session.beginTransaction();

            OtherServices otherServices = new OtherServices(otherServicesDTO.getServiceID(),otherServicesDTO.getServiceDescription(), otherServicesDTO.getRate());
            otherChargesRepository.update(otherServices);

            session.getTransaction().commit();

            return true;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteOtherCharges(int ID) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            otherChargesRepository.setSession(session);

            session.beginTransaction();

            OtherServices otherServices = otherChargesRepository.findById(ID);
            boolean result = false;

            if (otherServices != null) {

                otherChargesRepository.delete(otherServices);
            }

            session.getTransaction().commit();

            return result;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
    }

    @Override
    public OtherServicesDTO findOtherChargesByID(int ID) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            otherChargesRepository.setSession(session);

            session.beginTransaction();

            OtherServices otherServices = otherChargesRepository.findById(ID);

            session.getTransaction().commit();

            if (otherServices != null) {

                return new OtherServicesDTO(otherServices.getServiceID(), otherServices.getServiceDescription(), otherServices.getRate());
            }

            return null;
        }

    }

    @Override
    public List<OtherServicesDTO> getAllOtherCharges() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            otherChargesRepository.setSession(session);

            session.beginTransaction();

            List<OtherServices> otherServiceses = otherChargesRepository.findAll();

            session.getTransaction().commit();

            if (otherServiceses != null) {

                List<OtherServicesDTO> alOtherServices = new ArrayList<>();

                for (OtherServices otherServices : otherServiceses) {
                    OtherServicesDTO dto = new OtherServicesDTO(otherServices.getServiceID(), otherServices.getServiceDescription(), otherServices.getRate());
                    alOtherServices.add(dto);
                }

                return alOtherServices;

            } else {

                return null;
            }

        }
    }

}

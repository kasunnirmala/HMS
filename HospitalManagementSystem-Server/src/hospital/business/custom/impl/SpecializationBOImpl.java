/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.business.custom.impl;

import hospital.business.custom.SpecializationBO;
import hospital.dto.SpecializationDTO;
import hospital.entity.Specialization;
import hospital.repository.RepositoryFactory;
import hospital.repository.custom.SpecializationRepository;
import hospital.resource.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

public class SpecializationBOImpl implements SpecializationBO {

    private SpecializationRepository specializationRepository;

    public SpecializationBOImpl() {
        specializationRepository = (SpecializationRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.SPECIALIZATION);

    }

    @Override
    public boolean addSpecialization(SpecializationDTO specializationDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            specializationRepository.setSession(session);

            session.beginTransaction();
            Specialization specialization = new Specialization(
                    specializationDTO.getSpecialityName(),
                    specializationDTO.getSpecialityDetails()
            );
            boolean result = specializationRepository.save(specialization);

            session.getTransaction().commit();

            return result;
        }
    }

    @Override
    public boolean updateSpecialization(SpecializationDTO specializationDTO) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteSpecialization(String SpecializationID) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SpecializationDTO findSpecializationByID(String ID) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SpecializationDTO> getAllSpecialization() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            specializationRepository.setSession(session);

            session.beginTransaction();

            List<Specialization> specializations = specializationRepository.findAll();

            session.getTransaction().commit();

            if (specializations != null) {

                List<SpecializationDTO> allSpecializationDTOs = new ArrayList<>();

                for (Specialization specialization : specializations) {
                    SpecializationDTO dto = new SpecializationDTO(
                            specialization.getSpecialityID(),
                            specialization.getSpecialityName(),
                            specialization.getSpecialityDetails()
                    );
                    allSpecializationDTOs.add(dto);
                }

                return allSpecializationDTOs;

            } else {

                return null;
            }

        }
    }

    @Override
    public SpecializationDTO findSpecializationByName(String name) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            specializationRepository.setSession(session);

            session.beginTransaction();

            Specialization specialization = specializationRepository.findByName(name);

            session.getTransaction().commit();

            if (specialization != null) {

                return new SpecializationDTO(
                        specialization.getSpecialityID(),
                        specialization.getSpecialityName(),
                        specialization.getSpecialityDetails()
                );

            } else {

                return null;
            }

        }
    }

}

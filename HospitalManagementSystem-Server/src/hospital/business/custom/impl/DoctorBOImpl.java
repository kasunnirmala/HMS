/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.business.custom.impl;

import hospital.business.custom.DoctorBO;
import hospital.dto.DoctorAvailableDatesDTO;
import hospital.dto.DoctorAvailableTimeDTO;
import hospital.dto.DoctorDTO;
import hospital.dto.SpecializationDTO;
import hospital.entity.Doctor;
import hospital.entity.DoctorAvailableDates;
import hospital.entity.DoctorAvailableTime;
import hospital.entity.Specialization;
import hospital.repository.RepositoryFactory;
import hospital.repository.custom.DoctorRepository;
import hospital.resource.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

public class DoctorBOImpl implements DoctorBO {

    private DoctorRepository doctorRepository;

    public DoctorBOImpl() {
        doctorRepository = (DoctorRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.DOCTOR);
    }

    @Override
    public boolean addDoctor(DoctorDTO doctorDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            doctorRepository.setSession(session);

            session.beginTransaction();

            Doctor doctor = doctorDtoToDoctor(doctorDTO);
            boolean result = doctorRepository.save(doctor);

            session.getTransaction().commit();

            return result;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateDoctor(DoctorDTO doctorDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            doctorRepository.setSession(session);

            session.beginTransaction();

            Doctor doctor = doctorDtoToDoctor(doctorDTO);
            doctorRepository.update(doctor);

            session.getTransaction().commit();

            return true;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteDoctor(int DoctorID) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            doctorRepository.setSession(session);

            session.beginTransaction();

            Doctor doctor = doctorRepository.findById(DoctorID);
            Boolean result = false;
            if (doctor != null) {
                doctorRepository.delete(doctor);
                result = true;
            }

            session.getTransaction().commit();

            return result;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
    }

    @Override
    public DoctorDTO findDoctorByID(int ID) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            doctorRepository.setSession(session);

            session.beginTransaction();

            Doctor doctor = doctorRepository.findById(ID);

            session.getTransaction().commit();

            if (doctor != null) {
                return doctorToDoctorDTO(doctor);
            } else {
                return null;
            }

        }
    }

    @Override
    public DoctorDTO findDoctorBySLMC(String regNo) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            doctorRepository.setSession(session);

            session.beginTransaction();

            Doctor doctor = doctorRepository.findDoctorBySLMC(regNo);

            session.getTransaction().commit();

            if (doctor != null) {
                return doctorToDoctorDTO(doctor);
            } else {
                return null;
            }

        }
    }

    @Override
    public List<DoctorDTO> getAllDoctors() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            doctorRepository.setSession(session);

            session.beginTransaction();

            List<Doctor> doctors = doctorRepository.findAll();

            session.getTransaction().commit();

            if (doctors != null) {

                List<DoctorDTO> allDoctorDTOs = new ArrayList<>();

                for (Doctor doctor : doctors) {

                    DoctorDTO dto = doctorToDoctorDTO(doctor);
                    allDoctorDTOs.add(dto);
                }

                return allDoctorDTOs;

            } else {

                return null;
            }

        }
    }

    private DoctorDTO doctorToDoctorDTO(Doctor doctor) {
        DoctorDTO dto = new DoctorDTO(
                doctor.getDoctorID(),
                doctor.getSection(),
                doctor.getFirstName(),
                doctor.getMiddleName(),
                doctor.getLastName(),
                doctor.getGender(),
                doctor.getAddress(),
                doctor.getDob(),
                new SpecializationDTO(
                        doctor.getSpecialization().getSpecialityID(),
                        doctor.getSpecialization().getSpecialityName(),
                        doctor.getSpecialization().getSpecialityDetails()
                ),
                doctor.getSlmcRegNo(),
                doctor.getMobileNo(),
                doctor.getPhoneNo(),
                doctor.getEmail(),
                doctor.getVisitingFee(),
                doctor.getDocImage(),
                new DoctorAvailableDatesDTO(
                        doctor.getDoctorAvailableDates().isMonday(),
                        doctor.getDoctorAvailableDates().isTuesday(),
                        doctor.getDoctorAvailableDates().isWednesday(),
                        doctor.getDoctorAvailableDates().isThursday(),
                        doctor.getDoctorAvailableDates().isFriday(),
                        doctor.getDoctorAvailableDates().isSaturday(),
                        doctor.getDoctorAvailableDates().isSunday(),
                        new DoctorAvailableTimeDTO(doctor.getDoctorAvailableDates().getMondayTime().getStartTime(), doctor.getDoctorAvailableDates().getMondayTime().getEndTime()),
                        new DoctorAvailableTimeDTO(doctor.getDoctorAvailableDates().getTuesdayTime().getStartTime(), doctor.getDoctorAvailableDates().getTuesdayTime().getEndTime()),
                        new DoctorAvailableTimeDTO(doctor.getDoctorAvailableDates().getWednesdayTime().getStartTime(), doctor.getDoctorAvailableDates().getWednesdayTime().getEndTime()),
                        new DoctorAvailableTimeDTO(doctor.getDoctorAvailableDates().getThursdayTime().getStartTime(), doctor.getDoctorAvailableDates().getThursdayTime().getEndTime()),
                        new DoctorAvailableTimeDTO(doctor.getDoctorAvailableDates().getFridayTime().getStartTime(), doctor.getDoctorAvailableDates().getFridayTime().getEndTime()),
                        new DoctorAvailableTimeDTO(doctor.getDoctorAvailableDates().getSaturdayTime().getStartTime(), doctor.getDoctorAvailableDates().getSaturdayTime().getEndTime()),
                        new DoctorAvailableTimeDTO(doctor.getDoctorAvailableDates().getSundayTime().getStartTime(), doctor.getDoctorAvailableDates().getSundayTime().getEndTime()))
        );
        return dto;
    }

    private Doctor doctorDtoToDoctor(DoctorDTO doctorDTO) {
        SpecializationDTO specializationDTO = doctorDTO.getSpecialization();
        Specialization specialization = new Specialization(specializationDTO.getSpecialityID(), specializationDTO.getSpecialityName(), specializationDTO.getSpecialityDetails());

        DoctorAvailableDatesDTO doctorAvailableDatesDTO = doctorDTO.getDoctorAvailableDates();

        DoctorAvailableTime mondayTime = new DoctorAvailableTime(doctorAvailableDatesDTO.getMondayTime().getStartTime(), doctorAvailableDatesDTO.getMondayTime().getEndTime());
        DoctorAvailableTime tuesdayTime = new DoctorAvailableTime(doctorAvailableDatesDTO.getTuesdayTime().getStartTime(), doctorAvailableDatesDTO.getTuesdayTime().getEndTime());
        DoctorAvailableTime wednesdayTime = new DoctorAvailableTime(doctorAvailableDatesDTO.getWednesdayTime().getStartTime(), doctorAvailableDatesDTO.getWednesdayTime().getEndTime());
        DoctorAvailableTime thursdayTime = new DoctorAvailableTime(doctorAvailableDatesDTO.getThursdayTime().getStartTime(), doctorAvailableDatesDTO.getThursdayTime().getEndTime());
        DoctorAvailableTime fridayTime = new DoctorAvailableTime(doctorAvailableDatesDTO.getFridayTime().getStartTime(), doctorAvailableDatesDTO.getFridayTime().getEndTime());
        DoctorAvailableTime saturdayTime = new DoctorAvailableTime(doctorAvailableDatesDTO.getSaturdayTime().getStartTime(), doctorAvailableDatesDTO.getSaturdayTime().getEndTime());
        DoctorAvailableTime sundayTime = new DoctorAvailableTime(doctorAvailableDatesDTO.getSundayTime().getStartTime(), doctorAvailableDatesDTO.getSundayTime().getEndTime());

        DoctorAvailableDates doctorAvailableDates = new DoctorAvailableDates(
                doctorAvailableDatesDTO.isMonday(),
                doctorAvailableDatesDTO.isTuesday(),
                doctorAvailableDatesDTO.isWednesday(),
                doctorAvailableDatesDTO.isThursday(),
                doctorAvailableDatesDTO.isFriday(),
                doctorAvailableDatesDTO.isSaturday(),
                doctorAvailableDatesDTO.isSunday(),
                mondayTime,
                tuesdayTime,
                wednesdayTime,
                thursdayTime,
                fridayTime,
                saturdayTime,
                sundayTime
        );

        Doctor doctor = new Doctor(
                doctorDTO.getSection(),
                doctorDTO.getFirstName(),
                doctorDTO.getMiddleName(),
                doctorDTO.getLastName(),
                doctorDTO.getGender(),
                doctorDTO.getAddress(),
                doctorDTO.getDob(),
                specialization,
                doctorDTO.getSlmcRegNo(),
                doctorDTO.getMobileNo(),
                doctorDTO.getPhoneNo(),
                doctorDTO.getEmail(),
                doctorDTO.getVisitingFee(),
                doctorDTO.getDocImage(),
                doctorAvailableDates
        );

        return doctor;
    }

    @Override
    public List<DoctorDTO> getDoctorsByDate(String Date) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            doctorRepository.setSession(session);

            session.beginTransaction();

            List<Doctor> doctors = doctorRepository.findAll();

            session.getTransaction().commit();

            if (doctors != null) {

                List<DoctorDTO> DoctorDTOs = new ArrayList<>();

                for (Doctor doctor : doctors) {

                    DoctorDTO dto = doctorToDoctorDTO(doctor);
                    DoctorAvailableDatesDTO doctorAvailableDates = dto.getDoctorAvailableDates();
                    if (doctorAvailableDates.isMonday() && Date.equalsIgnoreCase("Monday")) {
                        DoctorDTOs.add(dto);
                    }
                    if (doctorAvailableDates.isTuesday() && Date.equalsIgnoreCase("Tuesday")) {
                        DoctorDTOs.add(dto);
                    }
                    if (doctorAvailableDates.isWednesday() && Date.equalsIgnoreCase("Wednesday")) {
                        DoctorDTOs.add(dto);
                    }
                    if (doctorAvailableDates.isThursday() && Date.equalsIgnoreCase("Thursday")) {
                        DoctorDTOs.add(dto);
                    }
                    if (doctorAvailableDates.isFriday() && Date.equalsIgnoreCase("Friday")) {
                        DoctorDTOs.add(dto);
                    }
                    if (doctorAvailableDates.isSaturday() && Date.equalsIgnoreCase("Saturday")) {
                        DoctorDTOs.add(dto);
                    }
                    if (doctorAvailableDates.isSunday() && Date.equalsIgnoreCase("Sunday")) {
                        DoctorDTOs.add(dto);
                    }

                }

                return DoctorDTOs;

            } else {

                return null;
            }

        }
    }

    @Override
    public List<DoctorDTO> getOPDDoctor() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            doctorRepository.setSession(session);

            session.beginTransaction();

            List<Doctor> doctors = doctorRepository.getDoctorBySection("OPD");

            session.getTransaction().commit();

            if (doctors != null) {

                List<DoctorDTO> allDoctorDTOs = new ArrayList<>();

                for (Doctor doctor : doctors) {

                    DoctorDTO dto = doctorToDoctorDTO(doctor);
                    allDoctorDTOs.add(dto);
                }

                return allDoctorDTOs;

            } else {

                return null;
            }

        }
    }

    @Override
    public List<DoctorDTO> getVisitingDoctor() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            doctorRepository.setSession(session);

            session.beginTransaction();

            List<Doctor> doctors = doctorRepository.getDoctorBySection("VISITING");

            session.getTransaction().commit();

            if (doctors != null) {

                List<DoctorDTO> allDoctorDTOs = new ArrayList<>();

                for (Doctor doctor : doctors) {

                    DoctorDTO dto = doctorToDoctorDTO(doctor);
                    allDoctorDTOs.add(dto);
                }

                return allDoctorDTOs;

            } else {

                return null;
            }

        }
    }

    @Override
    public List<DoctorDTO> getOPDDoctorByDate(String Date) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            doctorRepository.setSession(session);

            session.beginTransaction();

            List<DoctorDTO> doctors = getDoctorsByDate(Date);

            session.getTransaction().commit();

            if (doctors != null) {

                List<DoctorDTO> allDoctorDTOs = new ArrayList<>();

                for (DoctorDTO doctor : doctors) {
                    if (doctor.getSection().equalsIgnoreCase("OPD")) {
                        allDoctorDTOs.add(doctor);
                    }
                }

                return allDoctorDTOs;

            } else {

                return null;
            }

        }
    }

    @Override
    public List<DoctorDTO> getVisitingDoctorByDate(String Date) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            doctorRepository.setSession(session);

            session.beginTransaction();

            List<DoctorDTO> doctors = getDoctorsByDate(Date);

            session.getTransaction().commit();

            if (doctors != null) {

                List<DoctorDTO> allDoctorDTOs = new ArrayList<>();

                for (DoctorDTO doctor : doctors) {
                    if (doctor.getSection().equalsIgnoreCase("VISITING")) {
                        allDoctorDTOs.add(doctor);
                    }
                }

                return allDoctorDTOs;

            } else {

                return null;
            }

        }
    }

}

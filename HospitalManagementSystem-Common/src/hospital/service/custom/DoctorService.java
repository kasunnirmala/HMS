/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.service.custom;

import hospital.dto.DoctorDTO;
import hospital.service.SuperService;
import java.util.List;

/**
 *
 * @author Kasun
 */
public interface DoctorService extends SuperService {

    public boolean addDoctor(DoctorDTO doctorDTO) throws Exception;

    public boolean updateDoctor(DoctorDTO doctorDTO) throws Exception;

    public boolean deleteDoctor(int DoctorID) throws Exception;

    public DoctorDTO findDoctorByID(int ID) throws Exception;

    public DoctorDTO findDoctorBySLMC(String regNo) throws Exception;

    public List<DoctorDTO> getOPDDoctor() throws Exception;

    public List<DoctorDTO> getVisitingDoctor() throws Exception;

    public List<DoctorDTO> getOPDDoctorByDate(String Date) throws Exception;

    public List<DoctorDTO> getVisitingDoctorByDate(String Date) throws Exception;

    public List<DoctorDTO> getAllDoctors() throws Exception;

    public List<DoctorDTO> getDoctorsByDate(String Date) throws Exception;

}

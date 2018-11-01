/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.business.custom;

import hospital.business.SuperBO;
import hospital.dto.DoctorDTO;
import java.util.List;

/**
 *
 * @author Kasun
 */
public interface DoctorBO extends SuperBO {

    public boolean addDoctor(DoctorDTO doctorDTO) throws Exception;

    public boolean updateDoctor(DoctorDTO doctorDTO) throws Exception;

    public boolean deleteDoctor(int DoctorID) throws Exception;

    public DoctorDTO findDoctorByID(int ID) throws Exception;

    public List<DoctorDTO> getOPDDoctor() throws Exception;

    public List<DoctorDTO> getVisitingDoctor() throws Exception;

    public List<DoctorDTO> getOPDDoctorByDate(String Date) throws Exception;

    public List<DoctorDTO> getVisitingDoctorByDate(String Date) throws Exception;

    public DoctorDTO findDoctorBySLMC(String regNo) throws Exception;

    public List<DoctorDTO> getAllDoctors() throws Exception;

    public List<DoctorDTO> getDoctorsByDate(String Date) throws Exception;
}

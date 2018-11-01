/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.repository.custom;

import hospital.entity.RoomReserve;
import hospital.repository.SuperRepository;
import java.util.List;

/**
 *
 * @author Kasun
 */
public interface RoomReserveRepository extends SuperRepository<RoomReserve, String> {

    public List<RoomReserve> findAllReserved() throws Exception;

    public List<RoomReserve> findAllNonReserved() throws Exception;

    public RoomReserve findByPatientId(String patientID);
}

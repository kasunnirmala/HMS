/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.controller;

import com.jfoenix.controls.JFXTextField;
import hospital.dto.DoctorDTO;
import hospital.dto.PrescriptionDTO;
import hospital.dto.PrescriptionDetailsDTO;
import hospital.proxy.ProxyHandler;
import hospital.resource.ViewPrescriptionTableDataDTO;
import hospital.service.ServiceFactory;
import hospital.service.custom.PrescriptionService;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Kasun
 */
public class DoctorPrescriptionViewController implements Initializable {

    @FXML
    private TableView<ViewPrescriptionTableDataDTO> tblView;
    @FXML
    private JFXTextField txtFilter;

    private ObservableList<ViewPrescriptionTableDataDTO> tblData;
    private PrescriptionService prescriptionService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            prescriptionService = (PrescriptionService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.PRESCRIPTION);

            tblView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("prescriptionID"));
            tblView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("patientID"));
            tblView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("parientName"));
            tblView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("doctorName"));
            tblView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("docSLMCNo"));
            tblView.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("viewButton"));

            tblData = FXCollections.observableArrayList();
            tblView.setItems(tblData);
            tblData.clear();
            for (PrescriptionDTO prescriptionDTO : prescriptionService.getAllPrescriptions()) {
                tblData.add(new ViewPrescriptionTableDataDTO(
                        prescriptionDTO.getPrescriptionID(),
                        prescriptionDTO.getPatientDTO().getPatientID(),
                        prescriptionDTO.getPatientDTO().getPatientName(),
                        prescriptionDTO.getDoctorDTO().getFirstName() + " " + prescriptionDTO.getDoctorDTO().getLastName(),
                        prescriptionDTO.getDoctorDTO().getSlmcRegNo()
                ));
            }

            FilteredList<ViewPrescriptionTableDataDTO> filteredList = new FilteredList<>(tblData, e -> true);

            txtFilter.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                filteredList.setPredicate((Predicate<? super ViewPrescriptionTableDataDTO>) viewPrescriptionTableDataDTO -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    } else if (viewPrescriptionTableDataDTO.getPatientID().toLowerCase().contains(newValue.toLowerCase())) {
                        return true;
                    } else if (viewPrescriptionTableDataDTO.getPrescriptionID().toLowerCase().contains(newValue.toLowerCase())) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<ViewPrescriptionTableDataDTO> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(tblView.comparatorProperty());
            tblView.setItems(sortedList);

        } catch (Exception ex) {
            Logger.getLogger(DoctorPrescriptionViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

};

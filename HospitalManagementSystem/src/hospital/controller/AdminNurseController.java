/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import hospital.dto.DoctorAvailableTimeDTO;
import hospital.dto.DoctorDTO;
import hospital.dto.NurseDTO;
import hospital.observer.Observer;
import hospital.observer.Subject;
import hospital.proxy.ProxyHandler;
import hospital.resource.AdminAddNurseUtil;
import hospital.resource.CustomMethod;
import hospital.resource.NursePaneDataDTO;
import hospital.service.ServiceFactory;
import hospital.service.custom.NurseService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Kasun
 */
public class AdminNurseController implements Initializable, Observer {
    
    @FXML
    private JFXDrawer drawerAddDoctor;
    @FXML
    private TableView<NurseDTO> tblView;
    @FXML
    private JFXTextField txtSearchByName;
    
    private VBox vBox;
    private NursePaneDataDTO nurse;
    
    private ObservableList<NurseDTO> tblData;
    private NurseService nurseService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            UnicastRemoteObject.exportObject(this, 0);
            nurseService = (NurseService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.NURSE);
            ((Subject) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.NURSE)).registerObserver(this);
            vBox = FXMLLoader.load(getClass().getResource("/hospital/view/AdminAddNurse.fxml"));
            drawerAddDoctor.setSidePane(vBox);
        } catch (Exception ex) {
            Logger.getLogger(AdminNurseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        nurse = AdminAddNurseUtil.getAdminAddNurseUtil(vBox).getAddNurse();
        
        tblView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nurseID"));
        tblView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tblView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("middleName"));
        tblView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("latName"));
        tblView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblView.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("gender"));
        tblView.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("mobileNo"));
        tblView.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        tblView.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("email"));
        
        tblData = FXCollections.observableArrayList();
        tblView.setItems(tblData);
        
        FilteredList<NurseDTO> filteredList = new FilteredList<>(tblData, e -> true);
        
        txtSearchByName.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredList.setPredicate((Predicate<NurseDTO>) nurse -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                } else if (nurse.getFirstName().toLowerCase().contains(newValue.toLowerCase())) {
                    return true;
                } else if (nurse.getMiddleName().toLowerCase().contains(newValue.toLowerCase())) {
                    return true;
                } else if (nurse.getLatName().toLowerCase().contains(newValue.toLowerCase())) {
                    return true;
                } else if ((nurse.getFirstName().toLowerCase() + " " + nurse.getMiddleName().toLowerCase() + " " + nurse.getLatName().toLowerCase()).contains(newValue.toLowerCase())) {
                    return true;
                }
                return false;
            });
        });
        SortedList<NurseDTO> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tblView.comparatorProperty());
        tblView.setItems(sortedList);
        
        try {
            loadAllNurses();
        } catch (Exception ex) {
            Logger.getLogger(AdminDoctorsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void addDoctorOnMouseClicked(MouseEvent event) {
        if (drawerAddDoctor.isShown()) {
            drawerAddDoctor.close();
        } else {
            drawerAddDoctor.open();
            drawerAddDoctor.toFront();
        }
        nurse.getLblTitle().setText("ADD NURSE");
        nurse.getBtnAction().setText("ADD NURSE");
        clearAll();
        enableAll();
    }
    
    @Override
    public void updateObservers() throws Exception {
        loadAllNurses();
    }
    
    private void loadAllNurses() throws Exception {
        tblData.clear();
        List<NurseDTO> allNurses = nurseService.getAllNurses();
        for (NurseDTO nurseDTO : allNurses) {
            tblData.add(nurseDTO);
        }
    }
    
    @FXML
    private void viewHandlerOnAction(ActionEvent event) throws Exception {
        NurseDTO selectedItem = tblView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            
            drawerAddDoctor.open();
            drawerAddDoctor.toFront();
            
            nurse.getLblTitle().setText("VIEW NURSE - " + selectedItem.getFirstName() + " " + selectedItem.getLatName());
            nurse.getBtnAction().setVisible(false);
            
            setValuesFromTable(selectedItem);
            dissableAll();
            
        } else {
            CustomMethod.errorNotification("No Item Selected To View...!");
        }
    }
    
    @FXML
    private void updateHandlerOnAction(ActionEvent event) throws Exception {
        NurseDTO selectedItem = tblView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            
            drawerAddDoctor.open();
            drawerAddDoctor.toFront();
            
            nurse.getLblTitle().setText("UPDATE NURSE - " + selectedItem.getFirstName() + " " + selectedItem.getLatName());
            nurse.getBtnAction().setText("UPDATE");
            
            setValuesFromTable(selectedItem);
            enableAll();
            
        } else {
            CustomMethod.errorNotification("No Item Selected To Update...!");
        }
    }
    
    @FXML
    private void deleteHandlerOnAction(ActionEvent event) throws Exception {
        NurseDTO selectedItem = tblView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            if (nurseService.deleteNurse(selectedItem.getNurseID())) {
                CustomMethod.successNotification("Successfully Deleted...!");
            } else {
                CustomMethod.errorNotification("Error in Deleting...!");
            }
        } else {
            CustomMethod.errorNotification("No Item Selected To Delete...!");
        }
    }
    
    private void clearAll() {
        
        for (JFXDatePicker datePicker : nurse.getAllDatePicker()) {
            datePicker.getEditor().clear();
        }
        
        for (ImageView imageView : nurse.getAllImageView()) {
            imageView.setImage(null);
        }
        for (JFXRadioButton radio : nurse.getAllRadioButtons()) {
            radio.setSelected(false);
        }
        for (JFXTextField textFields : nurse.getAllTextFiels()) {
            textFields.clear();
        }
    }
    
    private void dissableAll() {
        
        for (JFXDatePicker datePicker : nurse.getAllDatePicker()) {
            datePicker.setEditable(false);
        }
        
        for (ImageView imageView : nurse.getAllImageView()) {
            imageView.setDisable(true);
        }
        for (JFXRadioButton radio : nurse.getAllRadioButtons()) {
            radio.setDisable(true);
        }
        for (JFXTextField textFields : nurse.getAllTextFiels()) {
            textFields.setEditable(false);
        }
        
        nurse.getBtnOpenImage().setDisable(true);
    }
    
    private void enableAll() {
        for (JFXDatePicker datePicker : nurse.getAllDatePicker()) {
            datePicker.setEditable(true);
        }
        
        for (ImageView imageView : nurse.getAllImageView()) {
            imageView.setDisable(false);
        }
        for (JFXRadioButton radio : nurse.getAllRadioButtons()) {
            radio.setDisable(false);
        }
        for (JFXTextField textFields : nurse.getAllTextFiels()) {
            textFields.setEditable(true);
        }
        nurse.getBtnOpenImage().setDisable(false);
    }
    
    private void setValuesFromTable(NurseDTO selectedItem) {
        FileOutputStream outputStream = null;
        
        nurse.getTxtNurseID().setText(selectedItem.getNurseID());
        nurse.getTxtFirstName().setText(selectedItem.getFirstName());
        nurse.getTxtMiddleName().setText(selectedItem.getMiddleName());
        nurse.getTxtLastName().setText(selectedItem.getLatName());
        nurse.getTxtAddress().setText(selectedItem.getAddress());
        if (selectedItem.getGender().equalsIgnoreCase("male")) {
            nurse.getRadioMale().setSelected(true);
        } else {
            nurse.getRadioFemale().setSelected(true);
        }
        nurse.getTxtDOB().getEditor().setText(selectedItem.getDob());
        nurse.getTxtMobileNo().setText(selectedItem.getMobileNo());
        nurse.getTxtPhoneNumber().setText(selectedItem.getPhoneNo());
        nurse.getTxtEmail().setText(selectedItem.getEmail());
        
        try {
            byte[] imageInByte = selectedItem.getNurseImage();
            File file = new File("image.jpg");
            outputStream = new FileOutputStream(file);
            outputStream.write(imageInByte);
            Image image = new Image(file.toURI().toString(), 290, 290, true, true);
            nurse.getImgView().setImage(image);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AdminDoctorsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AdminDoctorsController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                outputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(AdminDoctorsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
}

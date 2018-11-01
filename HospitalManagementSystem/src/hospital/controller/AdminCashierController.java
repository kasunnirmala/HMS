/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import hospital.dto.CashierDTO;
import hospital.dto.NurseDTO;
import hospital.observer.Observer;
import hospital.observer.Subject;
import hospital.proxy.ProxyHandler;
import hospital.resource.AdminAddCashierUtil;
import hospital.resource.AdminAddNurseUtil;
import hospital.resource.CashierPaneDataDTO;
import hospital.resource.CustomMethod;
import hospital.resource.NursePaneDataDTO;
import hospital.service.ServiceFactory;
import hospital.service.custom.CashierService;
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
public class AdminCashierController implements Initializable, Observer {

    @FXML
    private JFXDrawer drawerAddDoctor;
    @FXML
    private TableView<CashierDTO> tblView;
    @FXML
    private JFXTextField txtSearchByName;

    private VBox vBox;
    private CashierPaneDataDTO cashier;

    private ObservableList<CashierDTO> tblData;
    private CashierService cashierService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            UnicastRemoteObject.exportObject(this, 0);
            cashierService = (CashierService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.CASHIER);
            ((Subject) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.CASHIER)).registerObserver(this);
            vBox = FXMLLoader.load(getClass().getResource("/hospital/view/AdminAddCashier.fxml"));
            drawerAddDoctor.setSidePane(vBox);
        } catch (Exception ex) {
            Logger.getLogger(AdminCashierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cashier = AdminAddCashierUtil.getAdminAddCashierUtil(vBox).getAddCashier();

        tblView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("cashierID"));
        tblView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tblView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("middleName"));
        tblView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tblView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblView.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("gender"));
        tblView.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("mobileNo"));
        tblView.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        tblView.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("email"));

        tblData = FXCollections.observableArrayList();
        tblView.setItems(tblData);

        FilteredList<CashierDTO> filteredList = new FilteredList<>(tblData, e -> true);

        txtSearchByName.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredList.setPredicate((Predicate<CashierDTO>) cashier -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                } else if (cashier.getFirstName().toLowerCase().contains(newValue.toLowerCase())) {
                    return true;
                } else if (cashier.getMiddleName().toLowerCase().contains(newValue.toLowerCase())) {
                    return true;
                } else if (cashier.getLastName().toLowerCase().contains(newValue.toLowerCase())) {
                    return true;
                } else if ((cashier.getFirstName().toLowerCase() + " " + cashier.getMiddleName().toLowerCase() + " " + cashier.getLastName().toLowerCase()).contains(newValue.toLowerCase())) {
                    return true;
                }
                return false;
            });
        });
        SortedList<CashierDTO> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tblView.comparatorProperty());
        tblView.setItems(sortedList);

        try {
            loadAllCashier();
        } catch (Exception ex) {
            Logger.getLogger(AdminDoctorsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addCashierOnMouseClicked(MouseEvent event) {
        if (drawerAddDoctor.isShown()) {
            drawerAddDoctor.close();
        } else {
            drawerAddDoctor.open();
            drawerAddDoctor.toFront();
        }
        cashier.getLblTitle().setText("ADD CASHIER");
        cashier.getBtnAction().setText("ADD CASHIER");
        clearAll();
        enableAll();
    }

    @Override
    public void updateObservers() throws Exception {
        loadAllCashier();
    }

    private void loadAllCashier() throws Exception {
        tblData.clear();
        List<CashierDTO> allCashiers = cashierService.getAllCashier();
        for (CashierDTO cashierDTO : allCashiers) {
            tblData.add(cashierDTO);
        }
    }

    private void clearAll() {

        for (JFXDatePicker datePicker : cashier.getAllDatePicker()) {
            datePicker.getEditor().clear();
        }

        for (ImageView imageView : cashier.getAllImageView()) {
            imageView.setImage(null);
        }
        for (JFXRadioButton radio : cashier.getAllRadioButtons()) {
            radio.setSelected(false);
        }
        for (JFXTextField textFields : cashier.getAllTextFiels()) {
            textFields.clear();
        }
    }

    private void dissableAll() {

        for (JFXDatePicker datePicker : cashier.getAllDatePicker()) {
            datePicker.setEditable(false);
        }

        for (ImageView imageView : cashier.getAllImageView()) {
            imageView.setDisable(true);
        }
        for (JFXRadioButton radio : cashier.getAllRadioButtons()) {
            radio.setDisable(true);
        }
        for (JFXTextField textFields : cashier.getAllTextFiels()) {
            textFields.setEditable(false);
        }

        cashier.getBtnOpenImage().setDisable(true);
    }

    private void enableAll() {
        for (JFXDatePicker datePicker : cashier.getAllDatePicker()) {
            datePicker.setEditable(true);
        }

        for (ImageView imageView : cashier.getAllImageView()) {
            imageView.setDisable(false);
        }
        for (JFXRadioButton radio : cashier.getAllRadioButtons()) {
            radio.setDisable(false);
        }
        for (JFXTextField textFields : cashier.getAllTextFiels()) {
            textFields.setEditable(true);
        }
        cashier.getBtnOpenImage().setDisable(false);
    }

    private void setValuesFromTable(CashierDTO selectedItem) {
        FileOutputStream outputStream = null;

        cashier.getTxtCashierID().setText(selectedItem.getCashierID());
        cashier.getTxtFirstName().setText(selectedItem.getFirstName());
        cashier.getTxtMiddleName().setText(selectedItem.getMiddleName());
        cashier.getTxtLastName().setText(selectedItem.getLastName());
        cashier.getTxtAddress().setText(selectedItem.getAddress());
        if (selectedItem.getGender().equalsIgnoreCase("male")) {
            cashier.getRadioMale().setSelected(true);
        } else {
            cashier.getRadioFemale().setSelected(true);
        }
        cashier.getTxtDOB().getEditor().setText(selectedItem.getDob());
        cashier.getTxtMobileNo().setText(selectedItem.getMobileNo());
        cashier.getTxtPhoneNumber().setText(selectedItem.getPhoneNo());
        cashier.getTxtEmail().setText(selectedItem.getEmail());

        try {
            byte[] imageInByte = selectedItem.getCashierImage();
            File file = new File("image.jpg");
            outputStream = new FileOutputStream(file);
            outputStream.write(imageInByte);
            Image image = new Image(file.toURI().toString(), 290, 290, true, true);
            cashier.getImgView().setImage(image);
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

    @FXML
    private void viewHandlerOnAction(ActionEvent event) throws Exception {
        CashierDTO selectedItem = tblView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {

            drawerAddDoctor.open();
            drawerAddDoctor.toFront();

            cashier.getLblTitle().setText("VIEW CASHIER " + selectedItem.getFirstName() + " " + selectedItem.getLastName());
            cashier.getBtnAction().setVisible(false);

            setValuesFromTable(selectedItem);
            dissableAll();

        } else {
            CustomMethod.errorNotification("No Item Selected To VIew...!");
        }
    }

    @FXML
    private void updateHandlerOnAction(ActionEvent event) throws Exception {
        CashierDTO selectedItem = tblView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {

            drawerAddDoctor.open();
            drawerAddDoctor.toFront();

            cashier.getLblTitle().setText("UPDATE CASHIER - " + selectedItem.getFirstName() + " " + selectedItem.getLastName());
            cashier.getBtnAction().setText("UPDATE");

            setValuesFromTable(selectedItem);
            enableAll();

        } else {
            CustomMethod.errorNotification("No Item Selected To View...!");
        }
    }

    @FXML
    private void deleteHandlerOnAction(ActionEvent event) throws Exception {
        CashierDTO selectedItem = tblView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            if (cashierService.deleteCashier(selectedItem.getCashierID())) {
                CustomMethod.successNotification("Deleted Successfully...!");
            } else {
                CustomMethod.errorNotification("Error in deleting...!");
            }
        } else {
            CustomMethod.errorNotification("No Item Selected To Delete...!");
        }
    }
}

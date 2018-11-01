/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.resource;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Kasun
 */
public class AdminAddDoctorUtil {

    private static AdminAddDoctorUtil addDoctorUtil;
    private DoctorPaneDataDTO doctorPaneDataDTO;

    public AdminAddDoctorUtil(VBox vBox) {
        doctorPaneDataDTO = new DoctorPaneDataDTO(
                (JFXButton) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#btnCloseAdminAddDoctor"),
                (Label) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#lblTitle"),
                (ImageView) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#imgView"),
                (JFXTextField) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#txtFirstName"),
                (JFXTextField) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#txtMiddleName"),
                (JFXTextField) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#txtLastName"),
                (JFXRadioButton) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#radioMale"),
                (JFXRadioButton) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#radioFemale"),
                (JFXTextField) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#txtAddress"),
                (JFXDatePicker) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#txtDOB"),
                (JFXComboBox) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#cmboSpecialization"),
                (JFXTextField) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#txtSLMCNo"),
                (JFXTextField) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#txtMobileNo"),
                (JFXTextField) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#txtPhoneNumber"),
                (JFXTextField) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#txtEmail"),
                (JFXButton) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#btnOpenImage"),
                (JFXTextField) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#txtVisitingFee"),
                (CheckBox) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#checkMonday"),
                (JFXTimePicker) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#startTimeMonday"),
                (JFXTimePicker) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#endTimeMonday"),
                (CheckBox) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#checkTuesday"),
                (JFXTimePicker) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#startTimeTuesday"),
                (JFXTimePicker) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#endTimeTuesday"),
                (CheckBox) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#checkWednesday"),
                (JFXTimePicker) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#startTimeWednesday"),
                (JFXTimePicker) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#endTimeWednesday"),
                (CheckBox) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#checkThursday"),
                (JFXTimePicker) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#startTimeThursday"),
                (JFXTimePicker) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#endTimeThursday"),
                (CheckBox) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#checkFriday"),
                (JFXTimePicker) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#startTimeFriday"),
                (JFXTimePicker) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#endTimeFriday"),
                (CheckBox) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#checkSaturaday"),
                (JFXTimePicker) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#startTimeSaturday"),
                (JFXTimePicker) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#endTimeSaturday"),
                (CheckBox) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#checkSunday"),
                (JFXTimePicker) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#startTimeSunday"),
                (JFXTimePicker) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#endTimeSunday"),
                (JFXButton) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#btnAction"),
                (JFXComboBox) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#cmbSection")
        );
    }

    public static AdminAddDoctorUtil getAdminAddDoctorUtil(VBox vBox) {
        if (addDoctorUtil == null) {
            addDoctorUtil = new AdminAddDoctorUtil(vBox);
        }
        return addDoctorUtil;
    }

    public DoctorPaneDataDTO getAddDoctor() {
        return doctorPaneDataDTO;
    }
}

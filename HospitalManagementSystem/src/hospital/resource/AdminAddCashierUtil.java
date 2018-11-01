/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.resource;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Kasun
 */
public class AdminAddCashierUtil {

    private static AdminAddCashierUtil adminAddCashierUtil;
    private CashierPaneDataDTO cashierPaneDataDTO;

    public AdminAddCashierUtil(VBox vBox) {
        cashierPaneDataDTO = new CashierPaneDataDTO(
                (Label) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#lblTitle"),
                (ImageView) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#imgView"),
                (JFXTextField) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#txtFirstName"),
                (JFXTextField) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#txtMiddleName"),
                (JFXTextField) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#txtLastName"),
                (JFXRadioButton) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#radioMale"),
                (JFXRadioButton) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#radioFemale"),
                (JFXTextField) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#txtAddress"),
                (JFXDatePicker) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#txtDOB"),
                (JFXTextField) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#txtMobileNo"),
                (JFXTextField) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#txtPhoneNumber"),
                (JFXTextField) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#txtEmail"),
                (JFXButton) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#btnOpenImage"),
                (JFXButton) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#btnAction"),
                (JFXTextField) ((AnchorPane) (vBox.getChildren().get(0))).lookup("#txtCashierID")
        );
    }

    public static AdminAddCashierUtil getAdminAddCashierUtil(VBox vBox) {
        if (adminAddCashierUtil == null) {
            adminAddCashierUtil = new AdminAddCashierUtil(vBox);
        }
        return adminAddCashierUtil;
    }

    public CashierPaneDataDTO getAddCashier() {
        return cashierPaneDataDTO;
    }
}

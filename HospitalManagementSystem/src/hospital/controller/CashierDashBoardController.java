/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Kasun
 */
public class CashierDashBoardController implements Initializable {

    @FXML
    private ImageView imgViewerClose;
    @FXML
    private ImageView imgViewerMinimize;
    @FXML
    private ImageView imgViewerLogout;
    @FXML
    private ImageView imgViewerSettings;
    @FXML
    private Label lblDashBoardDate;
    @FXML
    private Label lblDashBoardTime;
    @FXML
    private JFXButton btnAppointment;
    @FXML
    private JFXButton btnRoom;
    @FXML
    private JFXButton btnBill;
    @FXML
    private AnchorPane showPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setDateTime();
        btnAppointment.fire();
    }

    @FXML
    private void closeOnMouseExited(MouseEvent event) {
        imgViewerClose.setStyle("-fx-image:url(/hospital/assets/Close_Window_96px.png)");
    }

    @FXML
    private void closeOnMouseEntered(MouseEvent event) {
        imgViewerClose.setStyle("-fx-image:url(/hospital/assets/Close_Window_96pxOnMouseOver.png)");
    }

    @FXML
    private void closeOnMouseClicked(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void minimizeOnMouseExited(MouseEvent event) {
        imgViewerMinimize.setStyle("-fx-image:url(/hospital/assets/Minimize_Window_96px.png)");
    }

    @FXML
    private void minimizeOnMouseEntered(MouseEvent event) {
        imgViewerMinimize.setStyle("-fx-image:url(/hospital/assets/Minimize_Window_96pxonMouseOver.png)");
    }

    @FXML
    private void minimizeOnMouseClicked(MouseEvent event) {
        Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        thisStage.setIconified(true);
    }

    @FXML
    private void logoutOnMouseExited(MouseEvent event) {
        imgViewerLogout.setStyle("-fx-image:url(/hospital/assets/Logout_Rounded_Left_96px.png)");
    }

    @FXML
    private void logoutOnMouseEntered(MouseEvent event) {
        imgViewerLogout.setStyle("-fx-image:url(/hospital/assets/Logout_Rounded_Left_96pxonMouseOver.png)");
    }

    @FXML
    private void logoutOnMouseClicked(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/hospital/view/LoginWindow.fxml"));
        Scene thisScene = new Scene(root);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(thisScene);
        mainStage.centerOnScreen();
    }

    @FXML
    private void settingsOnMouseExited(MouseEvent event) {
        imgViewerSettings.setStyle("-fx-image:url(/hospital/assets/Settings_96px.png)");
    }

    @FXML
    private void settingsOnMouseEntered(MouseEvent event) {
        imgViewerSettings.setStyle("-fx-image:url(/hospital/assets/Settings_96pxonMouseOver.png)");
    }

    @FXML
    private void settingsOnMouseClicked(MouseEvent event) {
    }

    private void btnSelected(ActionEvent event) {
        Node btnNode = ((Node) event.getSource());
        Insets curInset = VBox.getMargin(btnNode);
        Node[] btnList = {btnAppointment, btnBill, btnRoom};
        for (Node node : btnList) {
            node.getStyleClass().remove("dashBoardButtonSelected");
            if (!node.getStyleClass().contains("dashBoardButton")) {
                node.getStyleClass().add("dashBoardButton");
            }
        }

        btnNode.getStyleClass().remove("dashBoardButton");
        if (!btnNode.getStyleClass().contains("dashBoardButtonSelected")) {
            btnNode.getStyleClass().add("dashBoardButtonSelected");
        }
    }

    @FXML
    private void btnAppointmentOnAction(ActionEvent event) {
        btnSelected(event);
        try {
            AnchorPane anchorPlaceOrder = FXMLLoader.load(this.getClass().getResource("/hospital/view/CashierAddAppointment.fxml"));
            showPane.getChildren().setAll(anchorPlaceOrder);
        } catch (IOException ex) {
            Logger.getLogger(AdminDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnRoomsOnAction(ActionEvent event) {
        btnSelected(event);
        try {
            AnchorPane anchorPlaceOrder = FXMLLoader.load(this.getClass().getResource("/hospital/view/CashierReserveRoom.fxml"));
            showPane.getChildren().setAll(anchorPlaceOrder);
        } catch (IOException ex) {
            Logger.getLogger(AdminDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnBillOnAction(ActionEvent event) {
        btnSelected(event);
        try {
            AnchorPane anchorPlaceOrder = FXMLLoader.load(this.getClass().getResource("/hospital/view/CashierFormBill.fxml"));
            showPane.getChildren().setAll(anchorPlaceOrder);
        } catch (IOException ex) {
            Logger.getLogger(AdminDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setDateTime() {
        Timeline time = new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                lblDashBoardTime.setText(new SimpleDateFormat("hh:mm:ss a").format(new Date()));
                lblDashBoardDate.setText(new SimpleDateFormat("YYYY-MM-dd").format(new Date()));
            }
        }), new KeyFrame(Duration.seconds(1)));
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

}

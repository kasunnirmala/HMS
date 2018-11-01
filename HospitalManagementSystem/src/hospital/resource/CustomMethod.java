/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.resource;

import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Kasun
 */
public class CustomMethod {

    public static void modalSeet(Parent modalWindow, ActionEvent event) throws IOException {
        Window theStage = ((Node) event.getSource()).getScene().getWindow();
        FadeTransition ft = new FadeTransition(Duration.millis(500), modalWindow);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
        Stage dialog = new Stage();
        Scene scene = new Scene(modalWindow);

        dialog.setScene(scene);
        dialog.initOwner(theStage);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.centerOnScreen();
        dialog.showAndWait();
        scene.setRoot(new Parent() {});

    }

    public static void modalSeet(Parent modalWindow, MouseEvent event) throws IOException {
        Window theStage = ((Node) event.getSource()).getScene().getWindow();
        FadeTransition ft = new FadeTransition(Duration.millis(500), modalWindow);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
        Stage dialog = new Stage();
        Scene scene = new Scene(modalWindow);

        dialog.setScene(scene);
//        dialog.initOwner(theStage);
//        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.centerOnScreen();
        dialog.showAndWait();
       scene.setRoot(new Parent() {});
    }

    public static void successNotification(String text) throws Exception {
        Notifications.create()
                .title("Success!")
                .graphic(new ImageView(new Image("/hospital/assets/success.png")))
                .darkStyle()
                .hideAfter(Duration.seconds(3))
                .text(text)
                .show();
    }

    public static void errorNotification(String text) throws Exception {
        Notifications.create()
                .title("Error!")
                .darkStyle()
                .hideAfter(Duration.seconds(3))
                .text(text)
                .showError();
    }
}

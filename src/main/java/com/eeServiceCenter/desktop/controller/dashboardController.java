package com.eeServiceCenter.desktop.controller;

import com.eeServiceCenter.desktop.Entity.User;
import com.eeServiceCenter.desktop.Services.UserService;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class dashboardController implements Initializable {

    public JFXButton placeOrderBtn;
    public JFXButton orderBtn;
    public JFXButton inventoryBtn;
    public BorderPane pane;

    User user= new UserService().getLoggedInUser();
    public void placeOrderBtnOnPress(ActionEvent actionEvent)  {


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    public void inventoryBtnOnPress(ActionEvent actionEvent) {
        Stage stage=(Stage) pane.getScene().getWindow();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/inventory.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void manageUserBtnOnPress(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage) pane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/userManageForm.fxml"))));
    }
}

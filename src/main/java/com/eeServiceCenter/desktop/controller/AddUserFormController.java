package com.eeServiceCenter.desktop.controller;

import com.eeServiceCenter.desktop.Services.UserService;
import com.eeServiceCenter.desktop.model.UserModel;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddUserFormController implements Initializable {
    public BorderPane pane;

    public JFXTextField usernameTxt;
    public JFXTextField passwordTxt;

    public JFXTextField keyTxt;


    private UserService userService=new UserService();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void addUserBtnOnPress(ActionEvent actionEvent) throws IOException {
        if(validateStuff()){

            Stage thisStage=(Stage) pane.getScene().getWindow();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/AddUserFormPart2.fxml"));
            Parent root=loader.load();

            AddUserFormController2 controller2=loader.getController();


            UserModel newUser=new UserModel();
            newUser.setUserName(usernameTxt.getText());
            newUser.setPassword(passwordTxt.getText());

            controller2.initialize(newUser,keyTxt.getText());
            thisStage.setScene(new Scene(root));

        }

    }

    private boolean validateStuff(){
        String username=usernameTxt.getText();
        if(username.isEmpty()){
            new Alert(Alert.AlertType.WARNING,"insert Username Correctly" +username).show();
            return false;
        }


        String password=passwordTxt.getText();
        if(password.isEmpty()){
            new Alert(Alert.AlertType.WARNING,"insert A password Correctly"+ password).show();
            return false;
        }

        String key=keyTxt.getText();
        if(key.isEmpty() || userService.validateKey(key)){
            new Alert(Alert.AlertType.WARNING,"insert a correct Key Correctly"+key).show();
            return false;
        }

        return true;
    }



}

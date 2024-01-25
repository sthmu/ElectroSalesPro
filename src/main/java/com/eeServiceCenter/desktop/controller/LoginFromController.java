package com.eeServiceCenter.desktop.controller;

import com.eeServiceCenter.desktop.Services.UserService;
import com.eeServiceCenter.desktop.util.ViewFactory;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;


public class LoginFromController implements Initializable {


    public TextField usernameTxtField;
    public PasswordField passwordTxtField;
    public BorderPane pane;
    private UserService userService=new UserService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       /* try {
            boolean ini=new UserService().registerUser(new UserModel("u0001","root","root",10));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        */
    }

    public void loginBtnOnAction(ActionEvent actionEvent) throws NoSuchAlgorithmException {
        boolean isError = false;
        if(usernameTxtField.getText()!=null && passwordTxtField.getText()!=null) {
            isError =userService.login(usernameTxtField.getText(),passwordTxtField.getText());
            if(isError){
                Stage stage =(Stage) pane.getScene().getWindow();

                try {
                    //here it will rediect to the user beloging dashboard
                    String view= ViewFactory.getInstance().getView(UserService.getLoggedInUser().getAuthorityLvl());
                    if(view==null){
                        return;
                    }

                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(view))));
                    stage.setResizable(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }



}

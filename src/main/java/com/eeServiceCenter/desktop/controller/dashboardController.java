package com.eeServiceCenter.desktop.controller;

import com.eeServiceCenter.desktop.Entity.User;
import com.eeServiceCenter.desktop.Services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class dashboardController implements Initializable {

    public JFXButton placeOrderBtn;
    public JFXButton orderBtn;
    public JFXButton inventoryBtn;
    public JFXButton userBtn;
    JFXPopup userPopup;


    public BorderPane pane;

    User user= new UserService().getLoggedInUser();
    public void placeOrderBtnOnPress(ActionEvent actionEvent)  {
        Stage stage=(Stage) pane.getScene().getWindow();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/OrderForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        if(UserService.getLoggedInUser()!=null){
//            if(UserService.getLoggedInUser().getAuthorityLvl()==1){
//                Stage stage=(Stage) pane.getScene().getWindow();
//                try {
//                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/OrderForm.fxml"))));
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//
//        }
        initUserPopup();


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

    private void setButtonAccess(){

    }

    private void gotoLogin() throws IOException {
        Stage stage=(Stage) pane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/login.fxml"))));
    }

    private void initUserPopup(){
        JFXListView<JFXButton> list = new JFXListView<>();



        userPopup=new JFXPopup(list);

        VBox popupInside=new VBox();
        JFXButton loginBtn=new JFXButton("Login");
        list.getItems().add(loginBtn);
        JFXButton logoutBtn=new JFXButton("Logout");
        logoutBtn.setDisable(true);
        list.getItems().add(logoutBtn);

        loginBtn.setOnAction(e -> {
            try {
                gotoLogin();
            } catch (IOException ae) {
                throw new RuntimeException(ae);
            }
        });



    }
    public void userBtnOnPress(ActionEvent actionEvent) {

        userPopup.show(userBtn,JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT);
    }


}

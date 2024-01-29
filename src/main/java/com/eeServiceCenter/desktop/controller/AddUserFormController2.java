package com.eeServiceCenter.desktop.controller;

import com.eeServiceCenter.desktop.Services.UserService;
import com.eeServiceCenter.desktop.model.UserModel;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class AddUserFormController2 {
    public JFXComboBox authorityLvlComboBox;
    public JFXTextArea descriptionTxt;
    public BorderPane pane;
    @Setter
    private UserModel userModel;
    @Setter
    private String key;

    @Setter
    private UserService userService = new UserService();


    public void initialize(UserModel model, String reg_key) {
        this.userModel = model;
        this.key = reg_key;

        int highestAccessLvl = userService.analyseKey(key);

        ObservableList availableAuthorityLvlList = FXCollections.observableArrayList();
        for (int i = 1; i <= highestAccessLvl; i++) {
            availableAuthorityLvlList.add(i);
        }
        authorityLvlComboBox.setItems(availableAuthorityLvlList);
    }


    public void addUserBtnOnPress2(ActionEvent actionEvent) throws NoSuchAlgorithmException, IOException {

        if (validateFields()) {
            userModel.setId(userService.generateId());
            userModel.setAuthorityLvl((int) authorityLvlComboBox.getSelectionModel().getSelectedItem());
            userModel.setDescription(descriptionTxt.getText());
            boolean isSaved= userService.registerUser(userModel);
            if(isSaved){
                new Alert(Alert.AlertType.INFORMATION,"User Registered Successfully").show();
                Stage stage=(Stage) pane.getScene().getWindow();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"))));
            }
        }
    }

    boolean validateFields() {

        if (authorityLvlComboBox.getSelectionModel().getSelectedItem() == null) {
            new Alert(Alert.AlertType.CONFIRMATION, "Select a Authority Level").show();
            return false;

        }
        int authorityLvl = (int) authorityLvlComboBox.getSelectionModel().getSelectedItem();
        if (authorityLvl > 10 || authorityLvl < 1) {
            new Alert(Alert.AlertType.WARNING, "something is wrong here").show();
            return false;
        }
        if (descriptionTxt.getText() == null) {
            new Alert(Alert.AlertType.CONFIRMATION, "Please enter a description for the usage of this accoutn").show();
            return false;
        }
        return true;

    }
}

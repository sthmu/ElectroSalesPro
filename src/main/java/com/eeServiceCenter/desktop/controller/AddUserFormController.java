package com.eeServiceCenter.desktop.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AddUserFormController implements Initializable {
    public BorderPane pane;
    public Label idLbl;
    public JFXTextField usernameTxt;
    public JFXTextField passwordTxt;
    public JFXComboBox statusComboBox;
    public JFXComboBox authorityLvlComboBox;
    public JFXTextArea descriptionTxt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void addUserBtnOnPress(ActionEvent actionEvent) {

    }
}

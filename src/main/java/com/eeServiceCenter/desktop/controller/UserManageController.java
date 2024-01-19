package com.eeServiceCenter.desktop.controller;

import com.eeServiceCenter.desktop.Services.ItemService;
import com.eeServiceCenter.desktop.Services.UserService;
import com.eeServiceCenter.desktop.model.ItemModel;
import com.eeServiceCenter.desktop.model.UserModel;
import com.eeServiceCenter.desktop.model.tm.ItemTm;
import com.eeServiceCenter.desktop.model.tm.UserTm;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserManageController implements Initializable {
    public BorderPane pane;
    public TreeTableColumn userIdCol;
    public TreeTableColumn usernameCol;
    public TreeTableColumn createdDateCol;
    public TreeTableColumn StatusCol;
    public JFXTreeTableView<UserTm> userTbl;
    public JFXTextArea descriptionTxt;
    public JFXComboBox statusComboBox;
    public JFXComboBox categoryComboBox;
    public JFXTextField txtSearch;
    public Label idLbl;
    public JFXTextField usernameTxt;
    public JFXTextField passwordTxt;
    public JFXComboBox authorityLvlComboBox;
    private UserModel selectedUser;

    private UserService userService=new UserService();

    public void backButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"))));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(userService.assignToUserList()){
            System.out.println("got the users list");
        }
        else{
            new Alert(Alert.AlertType.ERROR,"There has been an error while getting the userList from Database");
        }

        userIdCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("Id"));
        usernameCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("userName"));
        createdDateCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("createdAt"));
        StatusCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("status"));
        loadUserTable();


        userTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            setData(newValue.getValue());
        });
    }
    private void setData(UserTm newValue) {
        if (newValue != null) {
            statusComboBox.getItems().addAll("ACTIVE","INNACTIVE");

//REMEMBER TO CHANGE THIS TO WORK WITH CURRENT LOGGED IN USER

            ObservableList<Integer> authorityLvls = FXCollections.observableArrayList();
            for (int i = 1; i <= 10; i++) {
                authorityLvls.add(i);
            }
            authorityLvlComboBox.setItems(authorityLvls);

            selectedUser=userService.getModel(newValue.getId());
            idLbl.setText(selectedUser.getId());
            usernameTxt.setText(selectedUser.getUserName());
            passwordTxt.setText(selectedUser.getPassword());
            statusComboBox.getSelectionModel().select((selectedUser.getStatus().equalsIgnoreCase("Active")?0:1));
            authorityLvlComboBox.getSelectionModel().select(selectedUser.getAuthorityLvl()-1);
        }

    }

    private void loadUserTable() {
        ObservableList<UserTm> tmList = FXCollections.observableArrayList();
        List<UserModel> userModelList = userService.getUserList();
        for (UserModel model : userModelList) {
            UserTm tm = new UserTm(
                    model.getId(),
                    model.getUserName(),
                    model.getCreatedAt(),
                    model.getStatus()
            );
            tmList.add(tm);
        }
        RecursiveTreeItem<UserTm> treeItem = new RecursiveTreeItem<>(tmList, RecursiveTreeObject::getChildren);
        userTbl.setRoot(treeItem);
        userTbl.setShowRoot(false);
    }


    public void addUserBtnOnPress(ActionEvent actionEvent) {
    }

    public void updateButtonOnAction(ActionEvent actionEvent) {
    }

    public void disableBtnOnAction(ActionEvent actionEvent) {
    }

    public void editBtnOnAction(ActionEvent actionEvent) {
    }
}

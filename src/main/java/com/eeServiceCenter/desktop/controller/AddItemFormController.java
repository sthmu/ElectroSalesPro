package com.eeServiceCenter.desktop.controller;

import com.eeServiceCenter.desktop.Services.ItemService;
import com.eeServiceCenter.desktop.model.ItemModel;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class AddItemFormController implements Initializable {
    public ImageView imageItmView;
    public JFXTextField pictureUrlLbl;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtDescription;
    public JFXTextField txtItemCode;
    public AnchorPane pane;

    ItemService itemService=new ItemService();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtItemCode.setText(itemService.getLastCode());

    }
    public void addToInventoryBtnOnPress(ActionEvent actionEvent) {
        boolean isAdded= itemService.saveItem(new ItemModel(
                txtItemCode.getText(),
                txtDescription.getText(),
                Double.parseDouble(txtUnitPrice.getText()),
                0,
                "electronic",
                new Image(pictureUrlLbl.getText())
        ));

        Stage thisStage= (Stage) pane.getScene().getWindow();

        if(isAdded){
            thisStage.close();

        }

    }

    public void addUrlBtnOnPress(ActionEvent actionEvent) {
        Image image=new Image(pictureUrlLbl.getText());
        imageItmView.setImage(image);
    }



}
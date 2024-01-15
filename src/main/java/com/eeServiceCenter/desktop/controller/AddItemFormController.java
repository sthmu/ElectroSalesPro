package com.eeServiceCenter.desktop.controller;

import com.eeServiceCenter.desktop.Services.ItemService;
import com.eeServiceCenter.desktop.model.ItemModel;
import com.eeServiceCenter.desktop.util.LocatedImage;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class AddItemFormController implements Initializable {
    public ImageView imageItmView;
    public JFXTextField pictureUrlLbl;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtDescription;
    public JFXTextField txtItemCode;

    ItemService itemService=new ItemService();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtItemCode.setText(itemService.getLastCode());

    }
    public void addToInventoryBtnOnPress(ActionEvent actionEvent) {
        itemService.saveItem(new ItemModel(
                txtItemCode.getText(),
                txtDescription.getText(),
                Double.parseDouble(txtUnitPrice.getText()),
                0,
                "electronic",
                new Image(pictureUrlLbl.getText())
                ));

    }

    public void addUrlBtnOnPress(ActionEvent actionEvent) {
        Image image=new Image(pictureUrlLbl.getText());
        imageItmView.setImage(image);
    }



}

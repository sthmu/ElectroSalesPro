package com.eeServiceCenter.desktop.controller;

import com.eeServiceCenter.desktop.Services.ItemService;
import com.eeServiceCenter.desktop.model.ItemModel;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.persistence.Column;
import javax.swing.text.Element;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static antlr.build.ANTLR.root;

public class OrderFormController implements Initializable {

    public BorderPane pane;
    public JFXComboBox categoryBomboBox;
    public JFXTextField txtSearch;
    public JFXTreeTableView cartTbl;
    public FlowPane masonryPane;


    private ItemService itemService=new ItemService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadItems();

    }

    private void loadItems() {

        List<ItemModel> itemModelList= ItemService.getItemList();
        for(ItemModel itemModel:itemModelList){
            masonryPane.getChildren().add(createItem(itemModel));
        }


    }

    private VBox createItem(ItemModel itemModel){
        VBox vbox=new VBox();

        ImageView imageView=new ImageView();
        imageView.setImage(itemModel.getImage());
        imageView.setFitHeight(170);
        imageView.setFitWidth(170);

        Label itemCode=new Label(itemModel.getCode());

        Label itemName=new Label(itemModel.getDescription());


        vbox.getChildren().addAll(imageView,itemCode,itemName);

        vbox.setSpacing(10);

        return vbox;

    }



    public void placeOrderBtnOnAction(ActionEvent actionEvent) {
    }

    public void bckBtnOnAction() throws IOException {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"))));
    }


}


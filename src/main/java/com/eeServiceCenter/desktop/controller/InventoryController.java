package com.eeServiceCenter.desktop.controller;

import com.eeServiceCenter.desktop.Services.ItemService;
import com.eeServiceCenter.desktop.model.ItemModel;
import com.eeServiceCenter.desktop.model.tm.ItemTm;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {
    public JFXTextField txtSearch;
    public BorderPane pane;
    public JFXComboBox categoryBomboBox;
    public JFXTreeTableView<ItemTm> itemTbl;
    public ImageView imageBox;
    public Label codeTxt;
    public TreeTableColumn ItemCodeCol;
    public TreeTableColumn ItemDescCol;
    public TreeTableColumn ItemPriceCol;
    public TreeTableColumn ItemQtyCol;
    public JFXTextField descriptionTxt;
    public JFXTextField priceTxt;
    public JFXTextField qtyTxt;

    ItemService itemService=new ItemService();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ItemCodeCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("code"));
        ItemDescCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("description"));
        ItemPriceCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("unitPrice"));
        ItemQtyCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("qty"));
        loadItemTable();


        itemTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            setData(newValue.getValue());
        });
    }

    private void setData(ItemTm newValue) {
        if (newValue != null) {

            codeTxt.setText(newValue.getCode());
            descriptionTxt.setText(newValue.getDescription());
            priceTxt.setText(String.valueOf(newValue.getUnitPrice()));
            qtyTxt.setText(String.valueOf(newValue.getQty()));
            setImage(newValue.getCode());
        }

    }

    private void setImage(String code) {
        imageBox.setImage(ItemService.getImageFromList(code));
    }

    private void loadItemTable() {
        ObservableList<ItemTm> tmList = FXCollections.observableArrayList();
        List<ItemModel> itemModelList = ItemService.getItemList();
        for (ItemModel model : itemModelList) {
            ItemTm tm = new ItemTm(
                    model.getCode(),
                    model.getDescription(),
                    model.getUnitPrice(),
                    model.getQty()
            );
            tmList.add(tm);
        }
        RecursiveTreeItem<ItemTm> treeItem = new RecursiveTreeItem<>(tmList, RecursiveTreeObject::getChildren);
        itemTbl.setRoot(treeItem);
        itemTbl.setShowRoot(false);
    }

    public void updateButtonOnAction(ActionEvent actionEvent) {


    }

    public void addItemBtnOnPress(ActionEvent actionEvent) throws IOException {
        Stage addItemWindow = new Stage();
        addItemWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AddItemForm.fxml"))));
        addItemWindow.show();

        addItemWindow.setOnCloseRequest(windowEvent -> loadItemTable());
    }

    public void backButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"))));
    }



}
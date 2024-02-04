package com.eeServiceCenter.desktop.controller;

import com.eeServiceCenter.desktop.Services.ItemService;
import com.eeServiceCenter.desktop.Services.OrderService;
import com.eeServiceCenter.desktop.model.ItemModel;
import com.eeServiceCenter.desktop.model.tm.BillItemTm;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class OrderFormController implements Initializable {

    public BorderPane pane;
    public JFXComboBox categoryBomboBox;
    public JFXTextField txtSearch;
    public JFXTreeTableView<BillItemTm> cartTbl;
    public FlowPane masonryPane;
    public Label itemCodeLbl;
    public JFXButton addItemBtn;
    public JFXTextField itemQtyTxt;
    public TreeTableColumn ItemCodeCol;
    public TreeTableColumn ItemDescCol;
    public TreeTableColumn ItemPriceCol;
    public TreeTableColumn ItemQtyCol;
    public TreeTableColumn amountCol;
    public Label totalLbl;


    private ItemService itemService = new ItemService();
    //this is the variable to the selected item in the list
    private ItemBox itemSelected = null;

    private OrderService orderService = new OrderService();

    private List<ItemBox> itemBoxList=new LinkedList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ItemCodeCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("code"));
        ItemDescCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("description"));
        ItemPriceCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("unitPrice"));
        ItemQtyCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("qty"));
        amountCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("amount"));
        loadItems();
        addItemBtn.setVisible(false);
        itemCodeLbl.setVisible(false);
        itemQtyTxt.setVisible(false);


        cartTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if(newValue!=null) {
                handleBillItemClick(newValue.getValue());
            }
        });

    }

    private void loadItems() {
        List<ItemModel> itemModelList = ItemService.getItemList();
        for (ItemModel itemModel : itemModelList) {
            ItemBox itemBox=createItemBox(itemModel);
            masonryPane.getChildren().add(itemBox);
            itemBoxList.add(itemBox);
        }
    }

    private ItemBox createItemBox(ItemModel itemModel) {
        ItemBox itemBox = new ItemBox(itemModel);

        ImageView imageView = new ImageView();
        imageView.setImage(itemModel.getImage());
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);

        Label itemCode = new Label(itemModel.getCode());

        Label itemName = new Label(itemModel.getDescription());


        itemBox.getChildren().addAll(imageView, itemCode, itemName);

        itemBox.setSpacing(10);

        //what happenes when clicked
        //--item clicked background becomes dark AND THE BEFORE CLICKED ONE BECOMES NORMAL
        //--item clicked triggers
        itemBox.setOnMouseClicked(mouseEvent -> handleItemClick(itemBox));

        addPressAndHoldHandler(itemBox, Duration.seconds(1),
                event -> {
                    orderService.addToCart(itemModel, orderService.getQtyInCart(itemModel)+1);
                    cartUpdate();
                });


        return itemBox;

    }

    private void addPressAndHoldHandler(ItemBox node, Duration holdTime,
                                        EventHandler<MouseEvent> handler) {

        class Wrapper<T> {
            T content;
        }
        Wrapper<MouseEvent> eventWrapper = new Wrapper<>();

        PauseTransition holdTimer = new PauseTransition(holdTime);
        holdTimer.setOnFinished(event -> handler.handle(eventWrapper.content));


        node.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            eventWrapper.content = event;
            holdTimer.playFromStart();
        });
        node.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> holdTimer.stop());
        node.addEventHandler(MouseEvent.DRAG_DETECTED, event -> holdTimer.stop());
    }

    private void handleItemClick(ItemBox itemClicked) {
        selectAnItem(itemClicked);
    }

    void handleBillItemClick(BillItemTm billItem){
        for(ItemBox itemBox:itemBoxList){
            if( billItem.getCode().equalsIgnoreCase(itemBox.getItem().getCode())){
                selectAnItem(itemBox);
                return;
            }
        }

    }


//SET ITEMBOX BACKGROUND COLOR
    void selectAnItem(ItemBox itemClicked){
        if (itemSelected != null) {
            itemSelected.setStyle("-fx-background-color: white;");

        }
        itemClicked.setStyle("-fx-background-color: gray;");
        itemSelected = itemClicked;

        itemCodeLbl.setVisible(true);
        itemCodeLbl.setText(itemClicked.getItem().getCode());
        itemQtyTxt.setVisible(true);
        Integer qty = orderService.getQtyInCart(itemClicked.getItem());
        itemQtyTxt.setText(String.valueOf(qty));
        addItemBtn.setVisible(true);
    }


    void cartUpdate() {
        ObservableList<BillItemTm> tmList = FXCollections.observableArrayList();
        double total=0d;

        Iterator<Map.Entry<ItemModel, Integer>> iterator = orderService.getCart().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<ItemModel, Integer> entry = iterator.next();
            ItemModel itemModel = entry.getKey();
            int quantity = entry.getValue();
            tmList.add(new BillItemTm(itemModel, quantity));
            total+=quantity*itemModel.getUnitPrice();
        }
        RecursiveTreeItem<BillItemTm> treeItem = new RecursiveTreeItem<>(tmList, RecursiveTreeObject::getChildren);
        if(cartTbl.getSelectionModel()!=null) {
            cartTbl.getSelectionModel().clearSelection();
        }
        cartTbl.setRoot(treeItem);
        cartTbl.setShowRoot(false);
        totalLbl.setText(String.valueOf(total));
    }



    public void bckBtnOnAction() throws IOException {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"))));
    }


    public void placeOrderBtnOnAction(ActionEvent actionEvent) {
    }

    public void addToCartBtnOnPress(ActionEvent actionEvent) {
        orderService.addToCart(itemSelected.getItem(), Integer.parseInt(itemQtyTxt.getText()));
        cartUpdate();
    }
}

class ItemBox extends VBox {
    @Getter
    @Setter
    private ItemModel item;

    ItemBox(ItemModel itemModel) {
        super();
        this.item = itemModel;
    }

}


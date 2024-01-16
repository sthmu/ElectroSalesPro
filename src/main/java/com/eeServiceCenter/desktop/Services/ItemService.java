package com.eeServiceCenter.desktop.Services;

import com.eeServiceCenter.desktop.Entity.Item;
import com.eeServiceCenter.desktop.model.ItemModel;
import com.eeServiceCenter.desktop.persistence.ItemPersistence;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ItemService {

    private static List<ItemModel> itemList = createItemList();
    ItemPersistence itemPersistent = new ItemPersistence();

    private static List<ItemModel> createItemList() {

        List<Item> entityList = ItemPersistence.getAll();
        List<ItemModel> tempModelList = new LinkedList<>();
        for (Item item : entityList) {

            tempModelList.add(new ItemModel(
                    item.getCode(),
                    item.getDescription(),
                    item.getUnitPrice(),
                    item.getQty(),
                    item.getCategory(),
                    new Image(item.getImgUrl())
            ));
        }
        return tempModelList;

    }

    public boolean saveItem(ItemModel item) {
        boolean isSaved = itemPersistent.save(new Item(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQty(), item.getCategory(), item.getImage().getUrl()));
        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Item Successfully Saved!").show();

            updateItemList(item);

            return true;
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Item Failed to be Saved!").show();
            return false;
        }
    }

    private void updateItemList(ItemModel item) {
        ItemModel itemFromList= isInList(item);
       if(itemFromList==null){
           itemList.add(item);
       }
       else{
           itemFromList.setDescription(item.getDescription());
           itemFromList.setUnitPrice(item.getUnitPrice());
           itemFromList.setQty(item.getQty());
           itemFromList.setImage(item.getImage());
           itemFromList.setCategory(item.getCategory());
        }

    }

    private ItemModel isInList(ItemModel item) {

        for(ItemModel itemInList:itemList){
            if(item.getCode().equalsIgnoreCase(itemInList.getCode())){
                return itemInList;
            }
        }
        return null;
    }


    public static List<ItemModel> getItemList() {
        return itemList;
    }

    public String getLastCode(){
        int lastCodeNum=0;
        for(ItemModel item:itemList){
            int curCode=Integer.parseInt(item.getCode().substring(1,5));
            if(curCode>lastCodeNum){
                lastCodeNum=curCode;
            }
            System.out.println(curCode);
        }
        String LastCode=(++lastCodeNum)+"";

        for(int i=(LastCode).length();i<4;i++){
            LastCode="0"+LastCode;
        }

        return "P"+LastCode;
    }

    public static Image getImageFromList(String code){
        for(ItemModel item:itemList){
            if(item.getCode().equalsIgnoreCase(code)){
                return item.getImage();
            }
        }
        return null;
    }

    private void removeFromItemList(String code){
        for(ItemModel itemInList:itemList){
            if(code.equalsIgnoreCase(itemInList.getCode())){
                itemList.remove(itemInList);
            }
        }

    }


    public boolean deleteItem(String text) {


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setContentText("Are You Sure To Delete this Item");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
           boolean isDeleted=itemPersistent.delete(text);
            if(isDeleted){
                removeFromItemList(text);
                return true;
            }
            else {
                return false;
            }
        } else {
            alert.close();
            return false;
        }
    }
}
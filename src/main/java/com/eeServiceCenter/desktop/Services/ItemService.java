package com.eeServiceCenter.desktop.Services;

import com.eeServiceCenter.desktop.Entity.Item;
import com.eeServiceCenter.desktop.model.ItemModel;
import com.eeServiceCenter.desktop.persistence.ItemPersistence;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;

import java.util.LinkedList;
import java.util.List;

public class ItemService {

    private static List<ItemModel> itemList = createItemList();
    ItemPersistence itemPersistent = new ItemPersistence();

    private static List<ItemModel> createItemList() {

        List<Item> entityList = ItemPersistence.getAll();
        List<ItemModel> tempModelList = new LinkedList<>();
        for (Item item : entityList) {
            System.out.println(item.getImgUrl()+"sadddddddddddddddddd");
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
            itemList.add(item);
            return true;
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Item Failed to be Saved!").show();
            return false;
        }
    }

    public static List<ItemModel> getItemList() {
        return itemList;
    }

    public String getLastCode(){
        int lastCodeNum=0;
        for(ItemModel item:itemList){
            int curCode=Integer.parseInt(item.getCode().substring(1,4));
            if(curCode>lastCodeNum){
                lastCodeNum=curCode;
            }
        }
        String LastCode=lastCodeNum+"";

        for(int i=(LastCode).length();i<5;i++){
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
}
package com.eeServiceCenter.desktop.Services;

import com.eeServiceCenter.desktop.model.ItemModel;
import com.eeServiceCenter.desktop.model.tm.BillItemTm;
import lombok.Getter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class OrderService {

    //---CART ITEM
    @Getter
    private HashMap<ItemModel, Integer> cart = new HashMap<>();

    public boolean addToCart(ItemModel itemModel,int qty){
        System.out.println("AddToCart " +itemModel);
        cart.put(itemModel,qty);
        updateCart();
        return true;
    }

    private void updateCart(){
        Iterator<Map.Entry<ItemModel, Integer>> iterator = cart.entrySet().iterator();
        System.out.println("Update cart Running");

        while (iterator.hasNext()) {
            Map.Entry<ItemModel, Integer> entry = iterator.next();
            if(entry.getValue()==0){
                iterator.remove();
            }
        }
        System.out.println(cart);
    }

    public int getQtyInCart(ItemModel itemModel){
        Integer itemQtyInCart=cart.get(itemModel);
       return itemQtyInCart==null?0:itemQtyInCart;

    }
}

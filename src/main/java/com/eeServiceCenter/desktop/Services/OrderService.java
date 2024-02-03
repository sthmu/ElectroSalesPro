package com.eeServiceCenter.desktop.Services;

import com.eeServiceCenter.desktop.model.ItemModel;
import lombok.Getter;

import java.util.HashMap;

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
        System.out.println(cart);
    }

    public int getQtyInCart(ItemModel itemModel){
        Integer itemQtyInCart=cart.get(itemModel);
       return itemQtyInCart==null?0:itemQtyInCart;

    }
}

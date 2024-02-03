package com.eeServiceCenter.desktop.model.tm;

import com.eeServiceCenter.desktop.model.ItemModel;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class BillItemTm extends RecursiveTreeObject<BillItemTm> {
    private String code;
    private String description;
    private double unitPrice;
    private int qty;
    private double amount;


     public BillItemTm(ItemModel model,int qty){
        this.code=model.getCode();
        this.description=model.getDescription();
        this.unitPrice=model.getUnitPrice();
        this.qty=qty;
        this.amount=qty*unitPrice;
    }
}

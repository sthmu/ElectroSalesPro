package com.eeServiceCenter.desktop.model.tm;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
public class ItemTm extends RecursiveTreeObject<ItemTm> {
    private String code;
    private String description;
    private double unitPrice;
    private int qty;


}

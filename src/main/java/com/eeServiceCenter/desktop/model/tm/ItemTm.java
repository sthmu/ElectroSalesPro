package com.eeServiceCenter.desktop.model.tm;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import lombok.*;


@Data
@ToString
@Getter
@Setter
@AllArgsConstructor
public class ItemTm extends RecursiveTreeObject<ItemTm> {
    private String code;
    private String description;
    private double unitPrice;
    private int qty;


}

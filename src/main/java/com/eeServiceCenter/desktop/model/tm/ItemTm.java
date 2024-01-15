package com.eeServiceCenter.desktop.model.tm;

import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.util.Callback;
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

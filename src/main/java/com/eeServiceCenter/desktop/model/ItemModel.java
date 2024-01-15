package com.eeServiceCenter.desktop.model;

import com.eeServiceCenter.desktop.util.LocatedImage;
import javafx.scene.image.Image;
import lombok.*;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class ItemModel {
    private String code;
    private String description;
    private double unitPrice;
    private int qty;
    private String category;
    private Image image;
}

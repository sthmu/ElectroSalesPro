package com.eeServiceCenter.desktop.Entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@AllArgsConstructor
@Getter
@NoArgsConstructor
@ToString
@Entity
public class Item {
    @Id
    private String code;

    private String description;
    private double unitPrice;
    private int qty;
    @Column(name = "Category")
    private String category;
    @Column(name="imgUrl")
    private String imgUrl;

}
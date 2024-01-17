package com.eeServiceCenter.desktop.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserModel {
    private String Id;
    private String userName;
    private String Password;
    private String description;
    private String createdAt;
    private String status;
    private int authorityLvl;
}

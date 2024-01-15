package com.eeServiceCenter.desktop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserModel {
    private String userId;
    private String userName;
    private String Password;
    private int accessLevel;
}

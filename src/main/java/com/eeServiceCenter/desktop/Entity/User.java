package com.eeServiceCenter.desktop.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class User {
    private String userId;
    private String userName;
    private String Password;
    private boolean status =true;
    private int accessLevel;


}

package com.eeServiceCenter.desktop.model.tm;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserTm extends RecursiveTreeObject<UserTm> {
    private String Id;
    private String userName;
    private String createdAt;
    private String status;

}

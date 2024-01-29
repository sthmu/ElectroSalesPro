package com.eeServiceCenter.desktop.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    private String Id;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String Password;
    @Column(name = "status")
    private boolean status =true;
    @Column(name = "Description")
    private String description;
    @Column(name = "created_at", nullable = false, updatable = false, insertable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "AuthorityLVL")
    private int authorityLvl;

    public User(String id, String userName, String password, boolean status, String description, int authorityLvl) {
        Id = id;
        this.userName = userName;
        Password = password;
        this.status = status;
        this.description = description;
        this.authorityLvl = authorityLvl;
    }
}

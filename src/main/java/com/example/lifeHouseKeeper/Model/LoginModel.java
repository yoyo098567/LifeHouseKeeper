package com.example.lifeHouseKeeper.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "main_login")
public class LoginModel {

    public LoginModel(AccountModel accountModel) {
        this.accountModel = accountModel;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;


    private String grade;

    private Date lastTime = null;

    @OneToOne(targetEntity = AccountModel.class ,fetch = FetchType.LAZY)
    @JoinColumn(name = "AccountId",nullable = false)
    private AccountModel accountModel;
}

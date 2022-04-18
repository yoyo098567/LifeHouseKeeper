package com.example.lifeHouseKeeper.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter @Getter
@NoArgsConstructor
@Entity
public class PasswordAndToken {

    public PasswordAndToken(String token, AccountModel accountModel) {
        this.token = token;
        this.accountModel = accountModel;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String token;

    private Date upDate;


    @OneToOne(targetEntity = AccountModel.class ,fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,name = "AccountId")
    private AccountModel accountModel;
}

package com.example.lifeHouseKeeper.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "AccountModel")
public class AccountModel {

    public AccountModel(Integer userId) {
        this.userId = userId;
    }

    public AccountModel(String account) {
        this.account = account;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(unique = true, nullable = false , length = 30)
    private String account="";

    private String password="";

    private String Grade="";

    private Date lastTime = null;

    @OneToMany
    private List<CalMonthModel> calMonthModels = new ArrayList<>();

    @OneToMany
    private List<EventModel> day = new ArrayList<>();

}

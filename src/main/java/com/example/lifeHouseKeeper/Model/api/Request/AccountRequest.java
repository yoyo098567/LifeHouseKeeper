package com.example.lifeHouseKeeper.Model.api.Request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class AccountRequest {
    private String account = "";

    private String password = "";

    private Date UpDate= null;
}

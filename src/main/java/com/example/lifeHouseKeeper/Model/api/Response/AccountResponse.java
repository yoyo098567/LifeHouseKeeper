package com.example.lifeHouseKeeper.Model.api.Response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class AccountResponse {
    private String isLogin;

    private Date UpDate = null;
}

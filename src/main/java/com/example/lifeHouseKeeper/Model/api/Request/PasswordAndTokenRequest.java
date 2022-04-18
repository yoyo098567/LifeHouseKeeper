package com.example.lifeHouseKeeper.Model.api.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordAndTokenRequest {

    private String token;

    private String newPassword;
}

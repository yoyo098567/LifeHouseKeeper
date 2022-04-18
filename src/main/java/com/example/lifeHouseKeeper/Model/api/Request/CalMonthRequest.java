package com.example.lifeHouseKeeper.Model.api.Request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CalMonthRequest {

    private String howMonth;

    private long totalCost;
}

package com.example.lifeHouseKeeper.Model.api.Response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CalMonthResponse {

    private String month;

    private long TotalCost;

    private String des;
}

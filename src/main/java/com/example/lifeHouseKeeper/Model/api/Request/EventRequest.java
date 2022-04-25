package com.example.lifeHouseKeeper.Model.api.Request;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EventRequest {

    private long id;

    private String subject;

    private String event;

    private String cost;
}

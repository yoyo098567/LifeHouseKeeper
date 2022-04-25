package com.example.lifeHouseKeeper.Model.api.Response;

import lombok.Getter;
import lombok.Setter;


@Setter @Getter
public class EventResponse {

    private long eventId;

    private String event;

    private String date;

    private String time;

    private String subject;
}

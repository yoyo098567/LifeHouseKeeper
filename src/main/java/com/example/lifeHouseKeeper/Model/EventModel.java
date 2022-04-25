package com.example.lifeHouseKeeper.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Event")
@Getter @Setter
@NoArgsConstructor
public class EventModel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long eventId;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String event;

    @Column
    private String date;

    @Column
    private String Time;

    @Column
    private String cost;

    @ManyToOne
    @JoinColumn(name = "id",nullable = false)
    private AccountModel accountModel;

}

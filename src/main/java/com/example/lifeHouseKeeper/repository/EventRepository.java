package com.example.lifeHouseKeeper.repository;

import com.example.lifeHouseKeeper.Model.CalMonthModel;
import com.example.lifeHouseKeeper.Model.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<EventModel, String> {
    EventModel findByEventId (long id);
    EventModel findByEvent (String event);
}

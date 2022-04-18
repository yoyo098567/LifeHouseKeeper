package com.example.lifeHouseKeeper.Service;

import com.example.lifeHouseKeeper.Model.CalMonthModel;
import com.example.lifeHouseKeeper.Model.EventModel;
import com.example.lifeHouseKeeper.Model.api.Request.EventRequest;
import com.example.lifeHouseKeeper.Model.api.Response.EventResponse;
import com.example.lifeHouseKeeper.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    public void addEvent(EventRequest eventRequest){

        Date date = new Date();
        EventModel eventModel = new EventModel();
        eventModel.setDate(dateFormat(date));
        eventModel.setTime(timeFormat(date));
        eventModel.setEvent(eventRequest.getEvent());
        eventModel.setCalMonthModel(new CalMonthModel(eventRequest.getId()));
        if (eventRequest.getCost()==0){
            eventModel.setCost(0);
        }else{
            eventModel.setCost(eventRequest.getCost());
        }
        eventModel.setCalMonthModel(new CalMonthModel(eventRequest.getId()));

        eventRepository.save(eventModel);
    }

    public EventResponse findID(Integer id){
        EventModel eventModel = eventRepository.findByEventId(id);

        Date date = new Date();
        EventResponse response = new EventResponse();
        response.setEvent(eventModel.getEvent());
        response.setDate(dateFormat(date));
        response.setTime(timeFormat(date));
        response.setEventId(eventModel.getEventId());
       return response;
    }

    public EventModel findEventID(Integer id){
        return eventRepository.findByEventId(id);
    }

    public EventModel findEvent(String event){

        return eventRepository.findByEvent(event);
    }

    public void deleteData(EventRequest request){

        eventRepository.deleteById(String.valueOf(request.getId()));
    }

    public String dateFormat(Date date){
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Taipei"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateF = format.format(date);
        return dateF;
    }

    public String timeFormat(Date time){
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Taipei"));
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String timeF = format.format(time);
        return  timeF;
    }

}

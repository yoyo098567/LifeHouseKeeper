package com.example.lifeHouseKeeper.Service;

import com.example.lifeHouseKeeper.Model.AccountModel;
import com.example.lifeHouseKeeper.Model.CalMonthModel;
import com.example.lifeHouseKeeper.Model.EventModel;
import com.example.lifeHouseKeeper.Model.api.Request.EventRequest;
import com.example.lifeHouseKeeper.Model.api.Response.EventResponse;
import com.example.lifeHouseKeeper.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
        eventModel.setSubject(eventRequest.getSubject());
        eventModel.setEvent(eventRequest.getEvent());
        eventModel.setAccountModel(new AccountModel(Math.toIntExact(eventRequest.getId())));
        if (eventRequest.getCost().equals("0")){
            eventModel.setCost("0");
        }else{
            eventModel.setCost(eventRequest.getCost());
        }
        eventRepository.save(eventModel);
    }

    public  List<EventModel> findAllEvent(){
        List<EventModel> eventModel = eventRepository.findAll();

//        EventResponse response = new EventResponse();
//        Date date = new Date();
//        for (EventModel model:eventModel) {
//            response.setEvent(model.getEvent());
//            response.setSubject(model.getSubject());
//            response.setDate(dateFormat(date));
//            response.setTime(timeFormat(date));
//            System.out.println(response.getEvent());
//        }
//       return response;
        return  eventModel;
    }

    public EventModel findEventID(Integer id){
        return eventRepository.findByEventId(id);
    }

    public EventModel findEvent(String event){

        return eventRepository.findByEvent(event);
    }

    public void deleteData(EventRequest request){
        Integer id = eventRepository.findBySubjectAndEvent(request.getSubject(),request.getEvent());
        eventRepository.deleteById(String.valueOf(id));
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

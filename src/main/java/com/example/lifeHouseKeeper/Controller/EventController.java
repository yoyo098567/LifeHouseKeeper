package com.example.lifeHouseKeeper.Controller;


import com.example.lifeHouseKeeper.Model.api.Request.EventRequest;
import com.example.lifeHouseKeeper.Model.api.Response.EventResponse;
import com.example.lifeHouseKeeper.Service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
@Api(tags = "EventController",description = "事件類別處理")
@RestController
public class EventController {

    @Autowired
    EventService eventService;

    @ApiOperation(value = "新增事件")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = " Add New Item Success "),
            @ApiResponse(code = 404, message = " The resource you were trying to reach is not found " )})
    @PostMapping("/lifeHouseKeeper/addEvent")
    public ResponseEntity<?> saveEvent(@RequestBody EventRequest request){
        System.out.println(request.getEvent());
        try {
            eventService.addEvent(request);
            return ResponseEntity.ok(request);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @ApiOperation(value = "查詢事件")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = " Search Item Success "),
            @ApiResponse(code = 404, message = " The resource you were trying to reach is not found ")})
    @GetMapping("/lifeHouseKeeper/searchEvent/{id}")
    public ResponseEntity<?> searchEvent(@PathVariable Integer id){
        EventResponse response = eventService.findID(id);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return ResponseEntity.ok(response);
        }
    }
    @ApiOperation(value = "刪除事件")
    @DeleteMapping("/lifeHouseKeeper/deleteData")
    public void deleteEvent(@RequestBody EventRequest request){
        eventService.deleteData(request);

    }
}

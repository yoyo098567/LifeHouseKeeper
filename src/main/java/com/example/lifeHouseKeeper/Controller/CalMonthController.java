package com.example.lifeHouseKeeper.Controller;


import com.example.lifeHouseKeeper.Model.api.Request.CalMonthRequest;
import com.example.lifeHouseKeeper.Model.api.Response.CalMonthResponse;
import com.example.lifeHouseKeeper.Service.CalMonthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
@Api(tags = "CalMonthController",description = "紀錄每月事件")
@RestController
public class CalMonthController {

    @Autowired
    CalMonthService calMonthService;

    @ApiOperation(value = "加入每月事件")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = " Add month Item Success "),
            @ApiResponse(code = 404, message = " The resource you were trying to reach is not found ")})
    @PostMapping("/lifeHouseKeeper/calMonth")
    public ResponseEntity<?> addCalMoth(@RequestBody CalMonthRequest request){

        try {
            calMonthService.addData(request);
            return ResponseEntity.ok(request);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "查詢每月花費")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Success")})
    @GetMapping("/lifeHouseKeeper/searchMonthCost")
    public ResponseEntity<?> findMonthId(@RequestBody CalMonthRequest request){
        CalMonthResponse response = calMonthService.findData(request);

        return ResponseEntity.ok(response);
    }

}

package com.example.lifeHouseKeeper.Controller;

import com.example.lifeHouseKeeper.Model.api.Request.LoginRequest;
import com.example.lifeHouseKeeper.Model.api.Response.LoginResponse;
import com.example.lifeHouseKeeper.Service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;
@RestController
@Api(tags = "LoginController",description = "登入類別處理")

public class LoginController {

    @Autowired
    LoginService service;

    @ApiOperation(value = "登入帳戶")
    @PostMapping("/lifeHouseKeeper/login")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = " Account Login  Success "),
            @ApiResponse(code = 404, message = " The resource you were trying to reach is not found ")})
    public ResponseEntity<?> login (@RequestBody LoginRequest loginRequest){
        try {
            LoginResponse response = service.findAccountLoginState(loginRequest.getAccount(),loginRequest.getPassword());
            if (response.getIsLogin().equals(loginRequest.getAccount() + "  登入成功  ")){
                service.findAccount(loginRequest);
            }
            return ResponseEntity.ok("訊息: "+response.getIsLogin());
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

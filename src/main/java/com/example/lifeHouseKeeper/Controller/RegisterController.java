package com.example.lifeHouseKeeper.Controller;

import com.example.lifeHouseKeeper.Model.api.Request.AccountRequest;
import com.example.lifeHouseKeeper.Model.api.Response.AccountResponse;
import com.example.lifeHouseKeeper.Service.RegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "RegisterController",description = "註冊類別處理")
public class RegisterController {

    @Autowired
    RegisterService service;

    @ApiOperation(value = "註冊帳戶")
    @PostMapping("/lifeHouseKeeper/user/register")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = " Register Account Success ")})
    public ResponseEntity<?> registerAccount(@RequestBody AccountRequest accountRequest){
        AccountResponse response=service.registerAccount(accountRequest);

        return ResponseEntity.ok(response);
    }
}

package com.example.lifeHouseKeeper.Controller;

import com.example.lifeHouseKeeper.Model.AccountModel;
import com.example.lifeHouseKeeper.Model.LoginModel;
import com.example.lifeHouseKeeper.Model.api.Request.AccountRequest;
import com.example.lifeHouseKeeper.Model.api.Request.PasswordAndTokenRequest;
import com.example.lifeHouseKeeper.Service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "AccountController",description = "帳戶類別處理")
@RestController
public class AccountController {

    @Autowired
    AccountService accountService;


    @ApiOperation(value = "重設密碼")
    @PostMapping("/lifeHouseKeeper/ResetPassword")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = " ResetPassword Success "),
            @ApiResponse(code = 404, message = " The resource you were trying to find user is not found ")})
    public String resetPassword(@RequestBody AccountRequest accountRequest){

        Integer id  = accountService.findByAccountId(accountRequest.getAccount());
        AccountModel accountModel = accountService.findById(id);
        if(accountModel !=null) {
            accountService.changeUserPassword(accountRequest.getPassword(),accountModel);

            return "change password success!";
        } else {
            return "can't not find user";
        }
    }
}

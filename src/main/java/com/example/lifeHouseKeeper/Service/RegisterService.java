package com.example.lifeHouseKeeper.Service;

import com.example.lifeHouseKeeper.Model.AccountModel;
import com.example.lifeHouseKeeper.Model.LoginModel;
import com.example.lifeHouseKeeper.Model.api.Request.AccountRequest;
import com.example.lifeHouseKeeper.Model.api.Request.LoginRequest;
import com.example.lifeHouseKeeper.Model.api.Response.AccountResponse;
import com.example.lifeHouseKeeper.Model.api.Response.LoginResponse;
import com.example.lifeHouseKeeper.repository.AccountRepository;
import com.example.lifeHouseKeeper.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.TimeZone;

@Service
public class RegisterService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    LoginRepository loginRepository;

    public AccountResponse registerAccount(AccountRequest request){
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Taipei"));
        Date date = new Date();
        AccountModel accountModel = accountRepository.findByAccount(request.getAccount());
        AccountResponse accountResponse = new AccountResponse();
        if(accountModel == null){
            AccountModel model = new AccountModel();
            model.setAccount(request.getAccount());
            model.setGrade("b");
            model.setPassword(request.getPassword());
            model.setLastTime(date);
            accountRepository.save(model);

            accountResponse.setIsLogin("帳戶註冊成功");
            return accountResponse;

        }else {
            accountResponse.setIsLogin("此帳戶名已註冊過，請重新註冊");
            return accountResponse;
        }
    }
}

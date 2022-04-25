package com.example.lifeHouseKeeper.Service;

import com.example.lifeHouseKeeper.Model.AccountModel;
import com.example.lifeHouseKeeper.Model.LoginModel;
import com.example.lifeHouseKeeper.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.TimeZone;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;


    public void changeUserPassword(String password, AccountModel accountModel){
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Taipei"));
        Date date = new Date();
        accountModel.setPassword(password);
        accountModel.setLastTime(date);
        accountRepository.save(accountModel);
    }

    public AccountModel findById(Integer id){
        AccountModel accountModel = accountRepository.findByUserId(id);

        return accountModel;
    }

    public Integer findByAccountId(String account){
        AccountModel accountModel = accountRepository.findByAccount(account);
        return accountModel.getUserId();
    }
    public AccountModel findAccount(String account){
        AccountModel accountModel = accountRepository.findByAccount(account);
        return accountModel;
    }
}

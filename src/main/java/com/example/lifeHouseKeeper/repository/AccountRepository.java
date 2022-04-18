package com.example.lifeHouseKeeper.repository;

import com.example.lifeHouseKeeper.Model.AccountModel;
import com.example.lifeHouseKeeper.Model.LoginModel;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<AccountModel,Integer> {
    AccountModel findByAccount (String account);
    AccountModel findByUserId (Integer id);

    AccountModel findByAccountAndPassword(String account , String password);

}

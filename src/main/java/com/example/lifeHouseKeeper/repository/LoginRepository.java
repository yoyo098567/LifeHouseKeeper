package com.example.lifeHouseKeeper.repository;

import com.example.lifeHouseKeeper.Model.LoginModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LoginRepository  extends JpaRepository<LoginModel,Integer> {

    LoginModel findByUserId(Integer ID);



}

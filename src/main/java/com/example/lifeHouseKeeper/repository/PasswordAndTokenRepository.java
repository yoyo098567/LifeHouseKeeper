package com.example.lifeHouseKeeper.repository;

import com.example.lifeHouseKeeper.Model.PasswordAndToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordAndTokenRepository extends JpaRepository<PasswordAndToken,Integer> {
    PasswordAndToken findByToken(String token);

}

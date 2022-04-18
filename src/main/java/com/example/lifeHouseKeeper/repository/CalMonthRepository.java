package com.example.lifeHouseKeeper.repository;

import com.example.lifeHouseKeeper.Model.CalMonthModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalMonthRepository extends JpaRepository<CalMonthModel,Integer> {

    CalMonthModel findByHowMonth(String month);
}

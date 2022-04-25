package com.example.lifeHouseKeeper.Service;

import com.example.lifeHouseKeeper.Model.AccountModel;
import com.example.lifeHouseKeeper.Model.CalMonthModel;
import com.example.lifeHouseKeeper.Model.api.Request.CalMonthRequest;
import com.example.lifeHouseKeeper.Model.api.Response.CalMonthResponse;
import com.example.lifeHouseKeeper.repository.CalMonthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalMonthService {

    @Autowired
    CalMonthRepository repository;

    public void addData(CalMonthRequest request){
        CalMonthModel calMonthModel = new CalMonthModel();
        calMonthModel.setHowMonth(request.getHowMonth());
        calMonthModel.setTotalCost(request.getTotalCost());
        calMonthModel.setAccountModel(new AccountModel(Math.toIntExact(request.getId())));
        repository.save(calMonthModel);
    }

    public CalMonthResponse findData(CalMonthRequest request){
        CalMonthModel model = repository.findByHowMonth(request.getHowMonth());
        CalMonthResponse response = new CalMonthResponse();
        if (model==null){
            response.setDes("資料未存在，請確認查詢日期是否正確");
            return response;
        }else{
            response.setDes("  ");
            response.setMonth(request.getHowMonth());
            response.setTotalCost(request.getTotalCost());
            return response;
        }
    }
}

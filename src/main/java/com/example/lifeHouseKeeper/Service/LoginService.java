package com.example.lifeHouseKeeper.Service;

import com.example.lifeHouseKeeper.Model.AccountModel;
import com.example.lifeHouseKeeper.Model.LoginModel;
import com.example.lifeHouseKeeper.Model.PasswordAndToken;
import com.example.lifeHouseKeeper.Model.api.Request.LoginRequest;
import com.example.lifeHouseKeeper.Model.api.Response.LoginResponse;
import com.example.lifeHouseKeeper.repository.AccountRepository;
import com.example.lifeHouseKeeper.repository.LoginRepository;
import com.example.lifeHouseKeeper.repository.PasswordAndTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Service
public class LoginService {
    @Autowired
    LoginRepository loginRepository;

    @Autowired
    PasswordAndTokenRepository passwordAndTokenRepository;

    @Autowired
    AccountRepository accountRepository;

    public LoginResponse findAccountLoginState(String account , String password){
        AccountModel accountModel = accountRepository.findByAccountAndPassword(account,password);
        AccountModel model = accountRepository.findByAccount(account);

        LoginResponse response = new LoginResponse();
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Taipei"));
        Date date = new Date();

        if (accountModel==null){
            response.setIsLogin("沒有此帳戶，請先註冊");
            return response;
        }else{
            if(!accountModel.getGrade().equals("c")) {
                response.setIsLogin("此帳戶還未驗證，請先進行驗證");
                return response;
            }else {
                if (!model.getPassword().equals(password)){
                    response.setIsLogin("密碼輸入有誤");
                    return response;
                }
                else{
                    response.setIsLogin(accountModel.getAccount() + "  登入成功  ");
                    response.setUpDate(date);

                    return response;
                }
            }
        }
    }

    public void findAccount(LoginRequest loginRequest){
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Taipei"));
        Date date = new Date();
        AccountModel accountModel = accountRepository.findByAccount(loginRequest.getAccount());
        //if (accountModel.getAccount().substring(1,3).equals(a)){
        LoginModel loginModel = new LoginModel(accountModel);
            loginModel.setGrade("c");
            loginModel.setLastTime(date);
            loginRepository.save(loginModel);

    }



    public String validatePasswordResetToken(String token) {
        final PasswordAndToken passToken = passwordAndTokenRepository.findByToken(token);

        if (isTokenFound(passToken) && !isTokenExpired(passToken)){
            return "ok";
        }
        else {
            if (!isTokenFound(passToken)){
                return "invalidToken";
            }
            if ( isTokenExpired(passToken)){
                return "expired";
            }
        }
        return null;
    }

    public AccountModel findById(Integer id){
        AccountModel accountModel = accountRepository.findByUserId(id);
        if (accountModel.getGrade().equals("b")){
            accountModel.setGrade("c");
            accountRepository.save(accountModel);
        }
        return accountModel;
    }


    public PasswordAndToken findByToken(String token){
        PasswordAndToken passwordAndToken = passwordAndTokenRepository.findByToken(token);

        return passwordAndToken;
    }

    public void createPasswdAndToken(AccountModel user, String token){
        PasswordAndToken passwordAndToken = new PasswordAndToken(token,user);
        Calendar date = Calendar.getInstance();
        long sec = date.getTimeInMillis();

        Date after10MIn = new Date(sec);
        passwordAndToken.setUpDate(after10MIn);
        passwordAndTokenRepository.save(passwordAndToken);
    }

    public void deleteToken(String token){
        PasswordAndToken passwordAndToken = passwordAndTokenRepository.findByToken(token);
        passwordAndTokenRepository.delete(passwordAndToken);
    }

    private boolean isTokenFound(PasswordAndToken passToken) {
        return passToken != null;
    }

    private boolean isTokenExpired(PasswordAndToken passToken) {
        final Calendar cal = Calendar.getInstance();
        System.out.println("bool    :   "+passToken.getUpDate().before(cal.getTime()));
        return passToken.getUpDate().before(cal.getTime());
    }

}

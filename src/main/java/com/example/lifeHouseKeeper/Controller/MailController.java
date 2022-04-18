package com.example.lifeHouseKeeper.Controller;

import com.example.lifeHouseKeeper.Model.AccountModel;

import com.example.lifeHouseKeeper.Model.PasswordAndToken;
import com.example.lifeHouseKeeper.Service.AccountService;
import com.example.lifeHouseKeeper.Service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

@Api(tags = "MailController",description = "信件類別處理")
@RestController
public class MailController {
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    LoginService loginService;
    @Autowired
    AccountService accountService;

    @ApiOperation(value = "發送驗證信件")
    @PostMapping("/lifeHouseKeeper/email/verifyPassword")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = " Mail Send Success "),
            @ApiResponse(code = 404, message = " The resource you were trying to verifyPassword is not found ")})
    public String verifyPassword(@RequestParam("email") String userMail) throws Exception{
        AccountModel accountModel = accountService.findAccount(userMail);

        if(accountModel == null){
            throw new Exception("user not found");
        }
        String token = UUID.randomUUID().toString();
        loginService.createPasswdAndToken(accountModel , token);
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Taipei"));
        Date date = new Date();

        mailSender.send(verifyUrl(date.getTime() , token , accountModel));

        return "驗證信已送出";
    }

    @ApiOperation(value = "驗證帳戶")
    @GetMapping("/lifeHouseKeeper/email/verify")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = " Verify Success"),
            @ApiResponse(code = 404, message = " The resource you were trying to verify is not found ")})
    public String verify(@RequestParam("Token") String token) {
        PasswordAndToken passwordAndToken = loginService.findByToken(token);
        AccountModel accountModel = loginService.findById(passwordAndToken.getAccountModel().getUserId());

        if (passwordAndToken==null){
            return "未找到帳戶";
        }else {
            if (accountModel.getGrade().equals("C")){
                loginService.deleteToken(passwordAndToken.getToken());
                return "此帳戶已驗證過";
            }else{
                loginService.deleteToken(passwordAndToken.getToken());
                return "驗證成功";
            }
        }
    }

    private SimpleMailMessage verifyUrl(Long locale, String token , AccountModel user){
        String url = "http://localhost:8080" + "/lifeHouseKeeper/email/verify?Token=" + token;
        System.out.println("now time: " +locale);
        return emailFormat("帳號驗證信", "請點擊下方連結進行帳號驗證:\r\n" +url , user);
    }

    private SimpleMailMessage emailFormat(String subject,String text, AccountModel user){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getAccount());
        message.setSubject(subject);
        message.setText(text);

        return message;
    }
}

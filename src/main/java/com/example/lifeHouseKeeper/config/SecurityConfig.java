package com.example.lifeHouseKeeper.config;


import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET).permitAll()
                .antMatchers(HttpMethod.POST,"/lifeHouseKeeper/**").permitAll()
                .antMatchers(HttpMethod.DELETE).permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()//關閉對 CSRF（跨站請求偽造）攻擊的防護。
                .formLogin();
                // 這樣 Security 機制才不會拒絕外部直接對 API 發出的請求，如 Postman 與前端。


       // http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());

    }
}

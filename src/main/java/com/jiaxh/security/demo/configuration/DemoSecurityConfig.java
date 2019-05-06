package com.jiaxh.security.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * WebSecurityConfigurerAdapter 是Spring Security提供的一个安全适配器类
 * @Auther: jiaxh
 * @Date: 2019/5/6 10:42
 */
@Configuration
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
//        http.httpBasic()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }
}

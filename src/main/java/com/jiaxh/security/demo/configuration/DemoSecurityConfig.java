package com.jiaxh.security.demo.configuration;

import com.jiaxh.security.demo.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * WebSecurityConfigurerAdapter 是Spring Security提供的一个安全适配器类
 * @Auther: jiaxh
 * @Date: 2019/5/6 10:42
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 自定义登录成功处理器
     */
    @Autowired
    private AuthenticationSuccessHandler jiaAuthenticationSuccessHandler;

    /**
     * 自定义登录失败处理器
     */
    @Autowired
    private AuthenticationFailureHandler jiaAuthenticationFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.httpBasic()
        http.formLogin()
                //使用自定义的登录页面的url，如果不加该方法则使用security默认的登录页
                .loginPage("/authentication/require")
                //登录页提交时的url
                .loginProcessingUrl("/authentication/form")
                //使用自定义的成功处理器
                .successHandler(jiaAuthenticationSuccessHandler)
                //使用自定义的失败处理器
                .failureHandler(jiaAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                //允许所有请求访问的url，即该url不设权限，所有请求均可访问，否则会报 重定向次数过多 的错误
                .antMatchers("/authentication/require",securityProperties.getBrowser().getLoginPage()).permitAll()
                .anyRequest()
                .authenticated()
        .and()
         //关闭csrf验证
        .csrf().disable();
    }
}

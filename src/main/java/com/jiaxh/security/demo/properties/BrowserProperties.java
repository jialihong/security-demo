package com.jiaxh.security.demo.properties;

import org.springframework.stereotype.Component;

/**
 * @Auther: jiaxh
 * @Date: 2019/5/7 15:27
 */
public class BrowserProperties {
    //默认值
    private String loginPage = "/jia-login.html";

    private LoginType loginType = LoginType.JSON;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}

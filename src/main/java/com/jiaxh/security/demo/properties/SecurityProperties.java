package com.jiaxh.security.demo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Auther: jiaxh
 * @Date: 2019/5/7 15:26
 */
@Component
@ConfigurationProperties(prefix = "jiaxh.security")
public class SecurityProperties {
    /**
     * 坑坑
     * field名字必须与配置文件中的 jiaxh.security.browser.loginPage完全相同才能匹配到
     */
    private BrowserProperties browser = new BrowserProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}

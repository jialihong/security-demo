package com.jiaxh.security.demo.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * @Auther: jiaxh
 * @Date: 2019/7/5 17:00
 */
@Component
public class DemoConnectionSignUp implements ConnectionSignUp {

    /**
     * connection 为第三方登录返回的用户信息
     * @param connection
     * @return
     */
    @Override
    public String execute(Connection<?> connection) {
        //根据社交用户信息默认创建用户并返回用户唯一标识
        return connection.getDisplayName();
    }
}

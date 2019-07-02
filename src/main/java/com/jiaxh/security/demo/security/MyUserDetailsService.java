package com.jiaxh.security.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @Auther: jiaxh
 * @Date: 2019/5/6 15:07
 */
@Component
public class MyUserDetailsService implements UserDetailsService, SocialUserDetailsService {

    //注入用户service

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("表单登录用户名："+username);
        String password = passwordEncoder.encode("123456");
        logger.info("password：" + password);
        //根据用户名到数据库查找用户信息
        return buildUser(username);
    }


    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        //此处的userId不一定就是数据库的主键，只要是唯一标识即可
        logger.info("社交登录用户id："+userId);
        return buildUser(userId);
    }

    private SocialUserDetails buildUser(String userId) {
        //根据用户id查找用户信息
        //根据查找到的用户信息判断用户是否被冻结
        String password = passwordEncoder.encode("123456");
        logger.info("数据库密码是：" + password);
        return new SocialUser(userId,password,true,true,true,true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}

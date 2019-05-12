package com.jiaxh.security.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.jiaxh.security.demo.domain.User;
import com.jiaxh.security.demo.domain.UserQuery;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: jiaxh
 * @Date: 2019/4/27 14:40
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 返回用户信息给前端
     * @param userDetails
     * @return
     */
    @GetMapping("/me")
    /**
     * 方式一
     */
//    public Object getCurrentUserInfo(){
//        return SecurityContextHolder.getContext().getAuthentication();
//    }
    /**
     * 方式二
     */
//    public Object getCurrentUserInfo(Authentication authentication){
//        return authentication;
//    }
    /**
     * 方式三：只显示用户信息
     */
    public Object getCurrentUserInfo(@AuthenticationPrincipal UserDetails userDetails){
        return userDetails;
    }

    @DeleteMapping("/{id:\\d+}")
    public boolean deleteUser(@PathVariable String id){
        System.out.println(id);
        return true;
    }

    @PutMapping("/{id:\\d+}")
    public User updateUser(@Valid @RequestBody User user,BindingResult errors){
        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(objectError -> {
                FieldError fieldError = (FieldError)objectError;
                String message = fieldError.getField()+":::"+fieldError.getDefaultMessage();
                System.out.println(message);
            });
        }
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getAge());
        System.out.println(user.getBirthday());
        System.out.println(user.getId());

        return user;
    }

//    @RequestParam(name = "nickname",required = false,defaultValue = "tom") String username
    @GetMapping
    @JsonView(value = User.UserSimpleView.class)
    public List<User> searchUser(UserQuery userQuery){

        System.out.println("useController searchUser() ... ");

        System.out.println(ReflectionToStringBuilder.toString(userQuery, ToStringStyle.MULTI_LINE_STYLE));
        System.out.println(userQuery.toString());
        List<User> list = new ArrayList<>();
        list.add(new User());
        list.add(new User());
        list.add(new User());
        return list;
    }

    @GetMapping("/{id:\\d+}")
    @JsonView(value = User.UserDetailView.class)
    public User getUserInfo(@PathVariable @ApiParam(value = "用户编号") String id){
//        throw new UserNotExistException(id);
        System.out.println("use getUserInfo() ... ");
        User user = new User();
        user.setUsername("tom");
        user.setPassword("123456");
        return user;
    }

    @PostMapping
    public User createUser(@RequestBody @Valid User user){
//        if(errors.hasErrors()){
//            errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
//        }

        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getAge());
        user.setId("1");
        return user;
    }
}

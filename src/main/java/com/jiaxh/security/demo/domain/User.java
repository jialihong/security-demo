package com.jiaxh.security.demo.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.jiaxh.security.demo.annotation.MyAnnotation;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @Auther: jiaxh
 * @Date: 2019/4/27 14:45
 */
public class User {

    public interface UserSimpleView{};

    public interface UserDetailView extends UserSimpleView{};

    private String id;

    @MyAnnotation(message = "自定义注解测试")
    private String username;

    private Integer age;

    @Past(message = "birthday必须为过去时间")
    private Date birthday;

    @NotBlank(message = "password不能为空")
    private String password;

    @JsonView(value = UserSimpleView.class)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @JsonView(value = UserDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonView(value = UserSimpleView.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonView(value = UserSimpleView.class)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

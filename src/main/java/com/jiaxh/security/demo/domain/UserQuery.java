package com.jiaxh.security.demo.domain;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: jiaxh
 * @Date: 2019/4/27 15:15
 */
public class UserQuery {
    private String id;
    private String username;

    @ApiModelProperty(value = "年龄起始值")
    private Integer age;

    @ApiModelProperty(value = "年龄终止值")
    private Integer ageTo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAgeTo() {
        return ageTo;
    }

    public void setAgeTo(Integer ageTo) {
        this.ageTo = ageTo;
    }
}

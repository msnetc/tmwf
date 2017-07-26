package com.taimeitech.pass.entity.member;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by yanjie.miao on 2017/7/24.
 */
public class WfUser {
    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "用户名称")
    private String firstName;
    @ApiModelProperty(value = "用户姓氏")
    private String lastName;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "密码")
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

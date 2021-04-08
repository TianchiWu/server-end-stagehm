package com.example.web;

import org.hibernate.validator.constraints.Length;

public class LoginInfo {

    @Length(min=3, max=6, message = "用户名3到6字符")
    private String userName;

    @Length(min=6, max=6, message = "口令长度为6个字符")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

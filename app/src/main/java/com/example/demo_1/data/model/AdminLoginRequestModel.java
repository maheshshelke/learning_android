package com.example.demo_1.data.model;

public class AdminLoginRequestModel {
    private String mobile;
    private String password;

    public AdminLoginRequestModel(){};

    public AdminLoginRequestModel(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AdminLoginRequestModel{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
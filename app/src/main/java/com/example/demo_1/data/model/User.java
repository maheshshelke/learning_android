package com.example.demo_1.data.model;

public class User {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String mobile;
    private String role;

    public User() {}

    public User(Integer userId, String firstName, String lastName, String mobile, String role) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.role = role;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

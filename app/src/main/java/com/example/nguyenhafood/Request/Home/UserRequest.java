package com.example.nguyenhafood.Request.Home;

public class UserRequest {
    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String UserName;
    public String Password;
}

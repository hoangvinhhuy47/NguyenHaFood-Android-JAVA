package com.example.nguyenhafood.Request.Order;

public class ReBuyRequest {
    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String UserID;
    public String OrderID;
}

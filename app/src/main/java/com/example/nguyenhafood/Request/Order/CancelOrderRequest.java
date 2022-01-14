package com.example.nguyenhafood.Request.Order;

public class CancelOrderRequest {
    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public String OrderID;
    public String Reason;
}

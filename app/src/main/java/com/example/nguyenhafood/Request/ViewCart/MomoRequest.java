package com.example.nguyenhafood.Request.ViewCart;

public class MomoRequest {
   private String OrderID;

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getOrderInfo() {
        return OrderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        OrderInfo = orderInfo;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public String getRedirectUrl() {
        return RedirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        RedirectUrl = redirectUrl;
    }

    private String OrderInfo;
    private int Amount;
    private String RedirectUrl;
}

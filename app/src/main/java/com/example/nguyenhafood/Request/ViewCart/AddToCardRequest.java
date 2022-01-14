package com.example.nguyenhafood.Request.ViewCart;

public class AddToCardRequest {
    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    private String UserID;
    private String IsLogin;
    private String ProductID;


    public String getIsLogin() {
        return IsLogin;
    }

    public void setIsLogin(String isLogin) {
        IsLogin = isLogin;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getSkuID() {
        return SkuID;
    }

    public void setSkuID(String skuID) {
        SkuID = skuID;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    private String SkuID;
    private String Quantity;
    private String Price;
}

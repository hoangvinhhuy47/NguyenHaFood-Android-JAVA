package com.example.nguyenhafood.Request.Acount_Login;

public class GetProductDetailRequest {
    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    private String ProductID;

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    private String UserID;
}

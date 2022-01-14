package com.example.nguyenhafood.Request.ViewCart;

public class UpdateUserInCartRequest {
    public String getCartID() {
        return CartID;
    }

    public void setCartID(String cartID) {
        CartID = cartID;
    }



    private String CartID;

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    private String UserID;
}

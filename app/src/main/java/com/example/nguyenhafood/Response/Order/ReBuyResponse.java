package com.example.nguyenhafood.Response.Order;

import com.example.nguyenhafood.Response.BaseResponse;

public class ReBuyResponse extends BaseResponse {
    public String getCartID() {
        return CartID;
    }

    public void setCartID(String cartID) {
        CartID = cartID;
    }

    public String CartID;
}

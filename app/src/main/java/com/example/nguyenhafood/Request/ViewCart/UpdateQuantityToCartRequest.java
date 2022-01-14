package com.example.nguyenhafood.Request.ViewCart;

public class UpdateQuantityToCartRequest {
    public String getCartDetailID() {
        return CartDetailID;
    }

    public void setCartDetailID(String cartDetailID) {
        CartDetailID = cartDetailID;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    private String CartDetailID;
    private String Quantity;
}

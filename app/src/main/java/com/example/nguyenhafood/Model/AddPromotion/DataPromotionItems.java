package com.example.nguyenhafood.Model.AddPromotion;

public class DataPromotionItems {
    private String PromotionID;
    private String ProductID;

    public String getPromotionID() {
        return PromotionID;
    }

    public void setPromotionID(String promotionID) {
        PromotionID = promotionID;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public double getDiscountPercent() {
        return DiscountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        DiscountPercent = discountPercent;
    }

    public double getDiscountAmount() {
        return DiscountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        DiscountAmount = discountAmount;
    }

    private int Quantity;
    private double DiscountPercent;
    private double DiscountAmount;

}

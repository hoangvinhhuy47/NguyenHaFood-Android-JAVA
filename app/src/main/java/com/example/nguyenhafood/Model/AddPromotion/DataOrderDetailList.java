package com.example.nguyenhafood.Model.AddPromotion;

public class DataOrderDetailList {
    private String SkuProductID;
    private String Quantity;

    public String getSkuProductID() {
        return SkuProductID;
    }

    public void setSkuProductID(String skuProductID) {
        SkuProductID = skuProductID;
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

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    private String Price;
    private String Amount;


}

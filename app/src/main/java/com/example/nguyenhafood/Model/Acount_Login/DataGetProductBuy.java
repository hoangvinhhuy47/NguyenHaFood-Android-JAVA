package com.example.nguyenhafood.Model.Acount_Login;

public class DataGetProductBuy {
    private String CustomerID;

    public String getItemID() {
        return ItemID;
    }

    public void setItemID(String itemID) {
        ItemID = itemID;
    }

    private String ItemID;
    private String Code;
    private String ItemName;
    private String SkuID;

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }



    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getSkuID() {
        return SkuID;
    }

    public void setSkuID(String skuID) {
        SkuID = skuID;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getSalePrice() {
        return SalePrice;
    }

    public void setSalePrice(double salePrice) {
        SalePrice = salePrice;
    }

    public double getSpecialPrice() {
        return SpecialPrice;
    }

    public void setSpecialPrice(double specialPrice) {
        SpecialPrice = specialPrice;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getReviewCount() {
        return ReviewCount;
    }

    public void setReviewCount(int reviewCount) {
        ReviewCount = reviewCount;
    }

    public double getReviewValue() {
        return ReviewValue;
    }

    public void setReviewValue(double reviewValue) {
        ReviewValue = reviewValue;
    }

    private double Price;
    private double SalePrice;
    private double SpecialPrice;
    private String Image;
    private int ReviewCount;
    private double ReviewValue;
}

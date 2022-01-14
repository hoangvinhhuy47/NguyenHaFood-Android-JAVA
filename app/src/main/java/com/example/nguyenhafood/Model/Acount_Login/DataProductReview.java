package com.example.nguyenhafood.Model.Acount_Login;

public class DataProductReview {
    private String FavoriteID;
    private String UserID;
    private String ItemID;
    private String Code;
    private String ItemName;
    private String SkuID;

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    private int Quantity;
    public String getFavoriteID() {
        return FavoriteID;
    }

    public void setFavoriteID(String favoriteID) {
        FavoriteID = favoriteID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getItemID() {
        return ItemID;
    }

    public void setItemID(String itemID) {
        ItemID = itemID;
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

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public int getFavoriteType() {
        return FavoriteType;
    }

    public void setFavoriteType(int favoriteType) {
        FavoriteType = favoriteType;
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

    private String CreatedDate;
    private int FavoriteType;
    private double Price;
    private double SalePrice;
    private double SpecialPrice;
    private String Image;
}

package com.example.nguyenhafood.Model.Home;

public class vw_Favorite {
    private String FavoriteID;
    private String UserID;
    private String ItemID;
    private double Price;
    private double SalePrice;

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

    private double SpecialPrice;
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

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    private String Code;
    private String ItemName;
    private String SkuID;
    private String CreatedDate;
    private String Image;
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

    private int ReviewCount;
    private double ReviewValue;
}

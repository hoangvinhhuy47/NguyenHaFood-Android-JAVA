package com.example.nguyenhafood.Model.ImfomationProduct;

public class DataWebItemRelation {
    private String ItemID;
    private String Name;
    private double Price;
    private int SpecialPrice;

    public String getItemID() {
        return ItemID;
    }

    public void setItemID(String itemID) {
        ItemID = itemID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getSpecialPrice() {
        return SpecialPrice;
    }

    public void setSpecialPrice(int specialPrice) {
        SpecialPrice = specialPrice;
    }

    public int getBrandID() {
        return BrandID;
    }

    public void setBrandID(int brandID) {
        BrandID = brandID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public double getSalePrice() {
        return SalePrice;
    }

    public void setSalePrice(double salePrice) {
        SalePrice = salePrice;
    }

    public double getOrgPrice() {
        return OrgPrice;
    }

    public void setOrgPrice(double orgPrice) {
        OrgPrice = orgPrice;
    }

    private int BrandID;
    private String Description;
    private String Image;
    private double SalePrice;
    private double OrgPrice;

    public String getSkuID() {
        return SkuID;
    }

    public void setSkuID(String skuID) {
        SkuID = skuID;
    }

    private String SkuID;

    public double getReviewCount() {
        return ReviewCount;
    }

    public void setReviewCount(double reviewCount) {
        ReviewCount = reviewCount;
    }

    public double getReviewValue() {
        return ReviewValue;
    }

    public void setReviewValue(double reviewValue) {
        ReviewValue = reviewValue;
    }

    private double ReviewCount;
    private double ReviewValue;
}

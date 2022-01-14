package com.example.nguyenhafood.Model.Home;

public class ItemListProMotion {
    private String PromotionName;
    private String ItemID;
    private String Name;
    private double Price;

    public String getPromotionName() {
        return PromotionName;
    }

    public void setPromotionName(String promotionName) {
        PromotionName = promotionName;
    }

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

    public double getSalePrice() {
        return SalePrice;
    }

    public void setSalePrice(double salePrice) {
        SalePrice = salePrice;
    }

    public String getOrgPrice() {
        return OrgPrice;
    }

    public void setOrgPrice(String orgPrice) {
        OrgPrice = orgPrice;
    }

    public String getProductCategoryID() {
        return ProductCategoryID;
    }

    public void setProductCategoryID(String productCategoryID) {
        ProductCategoryID = productCategoryID;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    private double SalePrice;
    private String OrgPrice;
    private String ProductCategoryID;
    private String Image;

    public String getSkuID() {
        return SkuID;
    }

    public void setSkuID(String skuID) {
        SkuID = skuID;
    }

    private String SkuID;

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

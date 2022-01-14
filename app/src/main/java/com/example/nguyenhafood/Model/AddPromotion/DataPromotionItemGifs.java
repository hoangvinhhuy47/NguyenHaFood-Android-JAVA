package com.example.nguyenhafood.Model.AddPromotion;

public class DataPromotionItemGifs {
    private String PromotionBenefitID;
    private String PromotionID;
    private String ProductID;

    public String getPromotionBenefitID() {
        return PromotionBenefitID;
    }

    public void setPromotionBenefitID(String promotionBenefitID) {
        PromotionBenefitID = promotionBenefitID;
    }

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

    public int getDiscountQuantity() {
        return DiscountQuantity;
    }

    public void setDiscountQuantity(int discountQuantity) {
        DiscountQuantity = discountQuantity;
    }

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getStore_ID() {
        return Store_ID;
    }

    public void setStore_ID(String store_ID) {
        Store_ID = store_ID;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public int getAppID() {
        return AppID;
    }

    public void setAppID(int appID) {
        AppID = appID;
    }

    private int DiscountQuantity ;
    private String Barcode;
    private String ProductName;
    private String Store_ID;
    private String ItemName;
    private int AppID;

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    private String Image;
}

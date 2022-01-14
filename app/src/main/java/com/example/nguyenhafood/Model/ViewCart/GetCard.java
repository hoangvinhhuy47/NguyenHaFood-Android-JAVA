package com.example.nguyenhafood.Model.ViewCart;

public class GetCard {
   private long CartID;
   private long DetailID;
   private String ItemID;
   private String Code;
   private String ItemName;
   private int Quantity;
   private double Price;
   private double Amount;
   private String SkuID;

    public double getOrgPrice() {
        return OrgPrice;
    }

    public void setOrgPrice(double orgPrice) {
        OrgPrice = orgPrice;
    }

    private double OrgPrice;
    public double getSalePrice() {
        return SalePrice;
    }

    public void setSalePrice(double salePrice) {
        SalePrice = salePrice;
    }

    private double SalePrice;
    public double getSpecialPrice() {
        return SpecialPrice;
    }

    public void setSpecialPrice(double specialPrice) {
        SpecialPrice = specialPrice;
    }

    private double SpecialPrice;
    public long getCartID() {
        return CartID;
    }

    public void setCartID(long cartID) {
        CartID = cartID;
    }

    public long getDetailID() {
        return DetailID;
    }

    public void setDetailID(long detailID) {
        DetailID = detailID;
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

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public String getSkuID() {
        return SkuID;
    }

    public void setSkuID(String skuID) {
        SkuID = skuID;
    }

    public String getSkuProductID() {
        return SkuProductID;
    }

    public void setSkuProductID(String skuProductID) {
        SkuProductID = skuProductID;
    }

    public String getSkuProductCode() {
        return SkuProductCode;
    }

    public void setSkuProductCode(String skuProductCode) {
        SkuProductCode = skuProductCode;
    }

    public String getSkuProductName() {
        return SkuProductName;
    }

    public void setSkuProductName(String skuProductName) {
        SkuProductName = skuProductName;
    }

    public double getSkuProductPrice() {
        return SkuProductPrice;
    }

    public void setSkuProductPrice(double skuProductPrice) {
        SkuProductPrice = skuProductPrice;
    }

    public long getSkuSortOrder() {
        return SkuSortOrder;
    }

    public void setSkuSortOrder(long skuSortOrder) {
        SkuSortOrder = skuSortOrder;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        TotalAmount = totalAmount;
    }

    public String getAttributeID1() {
        return AttributeID1;
    }

    public void setAttributeID1(String attributeID1) {
        AttributeID1 = attributeID1;
    }

    public String getAttributeName1() {
        return AttributeName1;
    }

    public void setAttributeName1(String attributeName1) {
        AttributeName1 = attributeName1;
    }

    public String getAttributeID2() {
        return AttributeID2;
    }

    public void setAttributeID2(String attributeID2) {
        AttributeID2 = attributeID2;
    }

    public String getAttributeName2() {
        return AttributeName2;
    }

    public void setAttributeName2(String attributeName2) {
        AttributeName2 = attributeName2;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    private String SkuProductID;
   private String SkuProductCode;
   private String SkuProductName;
   private double SkuProductPrice;
   private long SkuSortOrder;
   private String UserName;
   private double TotalAmount;
   private String AttributeID1;
   private String AttributeName1;
   private String AttributeID2;
   private String AttributeName2;
   private String Image;
   private int SiteID;
   private String StoreID;

    public int getSiteID() {
        return SiteID;
    }

    public void setSiteID(int siteID) {
        SiteID = siteID;
    }

    public String getStoreID() {
        return StoreID;
    }

    public void setStoreID(String storeID) {
        StoreID = storeID;
    }

    public int getCartType() {
        return CartType;
    }

    public void setCartType(int cartType) {
        CartType = cartType;
    }

    private int CartType;
}

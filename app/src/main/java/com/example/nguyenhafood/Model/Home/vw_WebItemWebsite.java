package com.example.nguyenhafood.Model.Home;

public class vw_WebItemWebsite {
    private String ItemID;
    private String Name;
    private double Price;
    private int SpecialPrice;
    private String Code;
    private String Description;
    private String CategoryID;
    private String SearchString;
    private int SortOrder;

    public boolean isFavorite() {
        return IsFavorite;
    }

    public void setFavorite(boolean favorite) {
        IsFavorite = favorite;
    }

    private boolean IsFavorite;

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

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(String categoryID) {
        CategoryID = categoryID;
    }

    public String getSearchString() {
        return SearchString;
    }

    public void setSearchString(String searchString) {
        SearchString = searchString;
    }

    public int getSortOrder() {
        return SortOrder;
    }

    public void setSortOrder(int sortOrder) {
        SortOrder = sortOrder;
    }

    public String getTagName() {
        return TagName;
    }

    public void setTagName(String tagName) {
        TagName = tagName;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getSecondImage() {
        return SecondImage;
    }

    public void setSecondImage(String secondImage) {
        SecondImage = secondImage;
    }

    public String getSkuID() {
        return SkuID;
    }

    public void setSkuID(String skuID) {
        SkuID = skuID;
    }

    public double getSalePrice() {
        return SalePrice;
    }

    public void setSalePrice(double salePrice) {
        SalePrice = salePrice;
    }

    public int getOrgPrice() {
        return OrgPrice;
    }

    public void setOrgPrice(int orgPrice) {
        OrgPrice = orgPrice;
    }

    private String TagName;
    private String Image;
    private String SecondImage;
    private String SkuID;
    private double SalePrice;
    private int OrgPrice;

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

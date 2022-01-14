package com.example.nguyenhafood.Model.ImfomationProduct;

public class WebItemPhotoList {
    private String ItemPhotoID;

    public String getItemPhotoID() {
        return ItemPhotoID;
    }

    public void setItemPhotoID(String itemPhotoID) {
        ItemPhotoID = itemPhotoID;
    }

    public String getItemID() {
        return ItemID;
    }

    public void setItemID(String itemID) {
        ItemID = itemID;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getSortOrder() {
        return SortOrder;
    }

    public void setSortOrder(int sortOrder) {
        SortOrder = sortOrder;
    }

    private String ItemID;
    private String Image;
    private int SortOrder;
}

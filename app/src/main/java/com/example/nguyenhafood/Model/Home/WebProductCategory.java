package com.example.nguyenhafood.Model.Home;

import java.util.List;

public class WebProductCategory {
    private String ProductCategoryID;

    public String getProductCategoryID() {
        return ProductCategoryID;
    }

    public void setProductCategoryID(String productCategoryID) {
        ProductCategoryID = productCategoryID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getParentID() {
        return ParentID;
    }

    public void setParentID(String parentID) {
        ParentID = parentID;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }


    private String CategoryName;
    private String Description;
    private String ParentID;
    private String Photo;

    public String getIconApp() {
        return IconApp;
    }

    public void setIconApp(String iconApp) {
        IconApp = iconApp;
    }

    private String IconApp;


}

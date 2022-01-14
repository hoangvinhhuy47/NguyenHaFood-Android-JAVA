package com.example.nguyenhafood.Model.Home;

import java.util.List;

public class Item {
    WebProductCategory Category;

    public WebProductCategory   getCategory() {
        return Category;
    }

    public void setCategory(WebProductCategory category) {
        Category = category;
    }

    public List<vw_WebItemWebsite> getProductList() {
        return ProductList;
    }

    public void setProductList(List<vw_WebItemWebsite> productList) {
        ProductList = productList;
    }

    List<vw_WebItemWebsite> ProductList;

    public int getPageindex() {
        return pageindex;
    }

    public void setPageindex(int pageindex) {
        this.pageindex = pageindex;
    }

    public int pageindex=-1;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isActive;

}

package com.example.nguyenhafood.Request.Home;

public class GetMoreProductOnCategoryRequest {
    public String getProductCategoryID() {
        return ProductCategoryID;
    }

    public void setProductCategoryID(String productCategoryID) {
        ProductCategoryID = productCategoryID;
    }

    public String getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(String pageIndex) {
        PageIndex = pageIndex;
    }

    private String ProductCategoryID;
    private String PageIndex;
}

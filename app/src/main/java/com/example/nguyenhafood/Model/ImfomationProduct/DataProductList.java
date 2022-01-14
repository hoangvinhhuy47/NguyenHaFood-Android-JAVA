package com.example.nguyenhafood.Model.ImfomationProduct;

import com.example.nguyenhafood.Model.AcountUser.DataAddressBook;

import java.util.List;

public class DataProductList {
    WebItem WebItem;
    List<WebItemPhotoList> WebItemPhotoList;

    public WebItem getWebItem() {
        return WebItem;
    }
    public void setWebItem(WebItem WebItem) {
        this.WebItem = WebItem;
    }

    public List<com.example.nguyenhafood.Model.ImfomationProduct.WebItemPhotoList> getWebItemPhotoList() {
        return WebItemPhotoList;
    }

    public void setWebItemPhotoList(List<com.example.nguyenhafood.Model.ImfomationProduct.WebItemPhotoList> webItemPhotoList) {
        WebItemPhotoList = webItemPhotoList;
    }

    public List<com.example.nguyenhafood.Model.ImfomationProduct.ProductRelationList> getProductRelationList() {
        return ProductRelationList;
    }

    public void setProductRelationList(List<com.example.nguyenhafood.Model.ImfomationProduct.ProductRelationList> productRelationList) {
        ProductRelationList = productRelationList;
    }

    List<ProductRelationList> ProductRelationList;

    public List<DataWebItemRelation> getWebItemRelation() {
        return WebItemRelation;
    }

    public void setWebItemRelation(List<DataWebItemRelation> webItemRelation) {
        WebItemRelation = webItemRelation;
    }

    List<DataWebItemRelation> WebItemRelation;

    public DataAddressBook getAddressBook() {
        return AddressBook;
    }

    public void setAddressBook(DataAddressBook addressBook) {
        AddressBook = addressBook;
    }

    DataAddressBook AddressBook;

    public List<DataReviewTotalList> getReviewTotalList() {
        return ReviewTotalList;
    }

    public void setReviewTotalList(List<DataReviewTotalList> reviewTotalList) {
        ReviewTotalList = reviewTotalList;
    }

    List<DataReviewTotalList> ReviewTotalList;

}

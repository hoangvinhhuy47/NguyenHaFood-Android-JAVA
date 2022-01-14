package com.example.nguyenhafood.Response.Acount_Login;

import com.example.nguyenhafood.Model.Acount_Login.DataProductReview;
import com.example.nguyenhafood.Response.BaseResponse;

import java.util.List;

public class GetProductFavoriteResponse  extends BaseResponse {


    public List<DataProductReview> getProductFavoriteList() {
        return ProductFavoriteList;
    }

    public void setProductFavoriteList(List<DataProductReview> productFavoriteList) {
        ProductFavoriteList = productFavoriteList;
    }

    List<DataProductReview> ProductFavoriteList;
}

package com.example.nguyenhafood.Response.Order;

import com.example.nguyenhafood.Model.ImfomationProduct.DataProductList;
import com.example.nguyenhafood.Model.ImfomationProduct.ProductRelationList;
import com.example.nguyenhafood.Model.ImfomationProduct.WebItem;
import com.example.nguyenhafood.Model.ImfomationProduct.WebItemPhotoList;
import com.example.nguyenhafood.Response.BaseResponse;

import java.util.List;

public class GetProductDetailResponse extends BaseResponse {
    public DataProductList getHomeData() {
        return HomeData;
    }

    public void setHomeData(DataProductList homeData) {
        HomeData = homeData;
    }

    DataProductList HomeData;
}

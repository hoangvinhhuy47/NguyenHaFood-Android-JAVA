package com.example.nguyenhafood.Response.Acount_Login;

import com.example.nguyenhafood.Model.Acount_Login.DataProductReview;
import com.example.nguyenhafood.Response.BaseResponse;

import java.util.List;

public class GetProductViewResponse extends BaseResponse {
    public List<DataProductReview> getProductViewList() {
        return ProductViewList;
    }

    public void setProductViewList(List<DataProductReview> productViewList) {
        ProductViewList = productViewList;
    }

    List<DataProductReview> ProductViewList;
}

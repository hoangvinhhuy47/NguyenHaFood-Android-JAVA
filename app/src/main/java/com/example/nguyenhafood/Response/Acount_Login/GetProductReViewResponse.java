package com.example.nguyenhafood.Response.Acount_Login;

import com.example.nguyenhafood.Model.Acount_Login.DataProductReview;
import com.example.nguyenhafood.Response.BaseResponse;

import java.util.List;

public class GetProductReViewResponse  extends BaseResponse {
    public List<DataProductReview> getProductReViewList() {
        return ProductReviewList;
    }

    public void setProductReViewList(List<DataProductReview> productReViewList) {
        ProductReviewList = productReViewList;
    }

    private List<DataProductReview> ProductReviewList;
}

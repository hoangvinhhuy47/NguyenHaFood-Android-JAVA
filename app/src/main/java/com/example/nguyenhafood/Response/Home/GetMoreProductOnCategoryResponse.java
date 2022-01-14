package com.example.nguyenhafood.Response.Home;

import com.example.nguyenhafood.Model.ImfomationProduct.ProductRelationList;
import com.example.nguyenhafood.Response.BaseResponse;

import java.util.List;

public class GetMoreProductOnCategoryResponse extends BaseResponse {
    public List<ProductRelationList> getProductRelation() {
        return ProductRelation;
    }

    public void setProductRelation(List<ProductRelationList> productRelation) {
        ProductRelation = productRelation;
    }

    List<ProductRelationList> ProductRelation;
}

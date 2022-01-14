package com.example.nguyenhafood.Response.Acount_Login;

import com.example.nguyenhafood.Model.ImfomationProduct.DataWebItemRelation;
import com.example.nguyenhafood.Response.BaseResponse;

import java.util.List;

public class GetMoreItemRelationResponse  extends BaseResponse {
    public List<DataWebItemRelation> getWebItemRelation() {
        return WebItemRelation;
    }

    public void setWebItemRelation(List<DataWebItemRelation> webItemRelation) {
        WebItemRelation = webItemRelation;
    }

    List<DataWebItemRelation> WebItemRelation;
}

package com.example.nguyenhafood.Response.Acount_Login;

import com.example.nguyenhafood.Model.Home.DataProductViewList;
import com.example.nguyenhafood.Response.BaseResponse;

import java.util.List;

public class GetProductLaterBuyResponse  extends BaseResponse {
    public List<DataProductViewList> getHomeData() {
        return HomeData;
    }

    public void setHomeData(List<DataProductViewList> homeData) {
        HomeData = homeData;
    }

    List<DataProductViewList> HomeData;
}

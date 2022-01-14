package com.example.nguyenhafood.Response.Home;

import com.example.nguyenhafood.Model.Home.HomeDTO;
import com.example.nguyenhafood.Response.BaseResponse;


public class MenuResponse  extends BaseResponse {
    public HomeDTO getHomeData() {
        return HomeData;
    }

    public void setHomeData(HomeDTO homeData) {
        HomeData = homeData;
    }

    HomeDTO HomeData;
}

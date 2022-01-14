package com.example.nguyenhafood.Response.Home;

import com.example.nguyenhafood.Model.LoadMore.HomeLoadMore;
import com.example.nguyenhafood.Response.BaseResponse;

public class LoadMoreProductResponse  extends BaseResponse {
    public HomeLoadMore getHomeData() {
        return HomeData;
    }

    public void setHomeData(HomeLoadMore homeData) {
        HomeData = homeData;
    }

    HomeLoadMore HomeData;
}

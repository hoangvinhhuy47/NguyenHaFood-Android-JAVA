package com.example.nguyenhafood.Response.Home;

import com.example.nguyenhafood.Model.Search.SearchDTO;
import com.example.nguyenhafood.Response.BaseResponse;

public class SearchResponse extends BaseResponse {
    public SearchDTO getHomeData() {
        return HomeData;
    }

    public void setSearchData(SearchDTO homeData) {
        HomeData = homeData;
    }

    SearchDTO HomeData;
    }

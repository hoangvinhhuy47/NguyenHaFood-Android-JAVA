package com.example.nguyenhafood.Response.Home;

import com.example.nguyenhafood.Model.Acount_Login.DataOrderDetail;
import com.example.nguyenhafood.Model.Notification.DataGetNewsDetail;
import com.example.nguyenhafood.Response.BaseResponse;

public class GetNewsDetailResponse extends BaseResponse {
    public DataGetNewsDetail getNews() {
        return News;
    }

    public void setNews(DataGetNewsDetail news) {
        News = news;
    }

    DataGetNewsDetail News;
}

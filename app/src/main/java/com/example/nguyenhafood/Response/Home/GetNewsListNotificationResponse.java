package com.example.nguyenhafood.Response.Home;

import com.example.nguyenhafood.Model.Notification.DataNotification;
import com.example.nguyenhafood.Response.BaseResponse;

import java.util.List;

public class GetNewsListNotificationResponse  extends BaseResponse {
    public List<DataNotification> getNewsList() {
        return NewsList;
    }

    public void setNewsList(List<DataNotification> newsList) {
        NewsList = newsList;
    }

    List<DataNotification> NewsList;

}

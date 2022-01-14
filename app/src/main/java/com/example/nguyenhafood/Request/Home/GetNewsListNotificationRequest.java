package com.example.nguyenhafood.Request.Home;

public class GetNewsListNotificationRequest {
    public String NewsType;

    public String getNewsType() {
        return NewsType;
    }

    public void setNewsType(String newsType) {
        NewsType = newsType;
    }

    public String getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(String pageIndex) {
        PageIndex = pageIndex;
    }

    public String PageIndex;
}

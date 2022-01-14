package com.example.nguyenhafood.Request.ViewCart;

public class GetCardRequest {


    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    private String UserID;

    public String getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(String pageIndex) {
        PageIndex = pageIndex;
    }

    private String PageIndex;
}

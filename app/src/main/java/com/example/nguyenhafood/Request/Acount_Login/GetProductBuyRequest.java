package com.example.nguyenhafood.Request.Acount_Login;

public class GetProductBuyRequest {
    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(String pageIndex) {
        PageIndex = pageIndex;
    }

    private String UserID;
    private String PageIndex;
}

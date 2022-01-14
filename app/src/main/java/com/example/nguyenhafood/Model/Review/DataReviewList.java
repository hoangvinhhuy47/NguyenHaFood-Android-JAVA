package com.example.nguyenhafood.Model.Review;

public class DataReviewList {
    private String ReviewID;
    private String ReviewDate;
    private String CustomerID;

    public String getReviewID() {
        return ReviewID;
    }

    public void setReviewID(String reviewID) {
        ReviewID = reviewID;
    }

    public String getReviewDate() {
        return ReviewDate;
    }

    public void setReviewDate(String reviewDate) {
        ReviewDate = reviewDate;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String getItemID() {
        return ItemID;
    }

    public void setItemID(String itemID) {
        ItemID = itemID;
    }

    public String getReviewTitle() {
        return ReviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        ReviewTitle = reviewTitle;
    }

    public String getReviewContent() {
        return ReviewContent;
    }

    public void setReviewContent(String reviewContent) {
        ReviewContent = reviewContent;
    }

    public int getReviewValue() {
        return ReviewValue;
    }

    public void setReviewValue(int reviewValue) {
        ReviewValue = reviewValue;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    private String ItemID;
    private String ReviewTitle;
    private String ReviewContent;
    private int ReviewValue;
    private String FullName;
}

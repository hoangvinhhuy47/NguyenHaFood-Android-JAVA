package com.example.nguyenhafood.Model.Review;

public class DataWriteReview {
    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String CustomerID;
    public String ItemID;
    public String ReviewTitle;
    public String ReviewContent;



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

    public String getReviewValue() {
        return ReviewValue;
    }

    public void setReviewValue(String reviewValue) {
        ReviewValue = reviewValue;
    }

    public String ReviewValue;

    public String getReviewStatus() {
        return ReviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        ReviewStatus = reviewStatus;
    }

    public String ReviewStatus;
}

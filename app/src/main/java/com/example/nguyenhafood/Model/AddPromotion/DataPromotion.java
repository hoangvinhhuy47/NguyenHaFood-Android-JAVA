package com.example.nguyenhafood.Model.AddPromotion;

public class DataPromotion {
    private String PromotionID;
    private double DiscountAmount;

    public String getPromotionID() {
        return PromotionID;
    }

    public void setPromotionID(String promotionID) {
        PromotionID = promotionID;
    }

    public double getDiscountAmount() {
        return DiscountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        DiscountAmount = discountAmount;
    }

    public double getDiscountPercent() {
        return DiscountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        DiscountPercent = discountPercent;
    }

    public boolean isRecurrence() {
        return IsRecurrence;
    }

    public void setRecurrence(boolean recurrence) {
        IsRecurrence = recurrence;
    }

    public boolean isMembership() {
        return IsMembership;
    }

    public void setMembership(boolean membership) {
        IsMembership = membership;
    }

    private double DiscountPercent;
    private boolean IsRecurrence;
    private boolean IsMembership;
}

package com.example.nguyenhafood.Response.Home;

import com.example.nguyenhafood.Model.Review.DataReViewToTal;
import com.example.nguyenhafood.Model.Review.DataReviewList;
import com.example.nguyenhafood.Response.BaseResponse;

import java.util.List;

public class ReViewProductResponse extends BaseResponse {
    List<DataReViewToTal> ReviewTotal;

    public List<DataReViewToTal> getReviewToTal() {
        return ReviewTotal;
    }

    public void setReviewToTal(List<DataReViewToTal> reviewToTal) {
        ReviewTotal = reviewToTal;
    }

    public List<DataReviewList> getReviewList() {
        return ReviewList;
    }

    public void setReviewList(List<DataReviewList> reviewList) {
        ReviewList = reviewList;
    }

    List<DataReviewList> ReviewList;
}

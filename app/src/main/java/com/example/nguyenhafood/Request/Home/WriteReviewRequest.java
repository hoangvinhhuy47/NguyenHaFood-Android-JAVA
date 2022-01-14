package com.example.nguyenhafood.Request.Home;

import com.example.nguyenhafood.Model.Review.DataWriteReview;

public class WriteReviewRequest {
    public DataWriteReview getReview() {
        return Review;
    }

    public void setReview(DataWriteReview review) {
        Review = review;
    }

    public DataWriteReview Review;
}

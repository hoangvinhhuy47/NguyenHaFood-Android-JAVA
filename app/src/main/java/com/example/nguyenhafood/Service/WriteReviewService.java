package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.Home.WriteReviewRequest;
import com.example.nguyenhafood.Response.Home.WriteReviewReponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WriteReviewService {
    @POST("WriteReview")
    Call<WriteReviewReponse> WriteReview(@Query("GUIID") String GUIID, @Body WriteReviewRequest writeReviewRequest);
}

package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.Home.ReViewProductRequest;
import com.example.nguyenhafood.Response.Home.ReViewProductResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ReViewProductService {
    @POST("GetReviewList")
    Call<ReViewProductResponse> GetReviewList(@Query("GUIID") String GUIID, @Body ReViewProductRequest reViewProductRequest);
}

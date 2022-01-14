package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.Home.LoadMoreProductRequest;
import com.example.nguyenhafood.Response.Home.LoadMoreProductResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoadMoreProductSerVice {
    @POST("GetProductByCategory")
    Call<LoadMoreProductResponse> LoadMore(@Query("GUIID") String GUIID, @Body LoadMoreProductRequest loadMoreProductRequest);
}

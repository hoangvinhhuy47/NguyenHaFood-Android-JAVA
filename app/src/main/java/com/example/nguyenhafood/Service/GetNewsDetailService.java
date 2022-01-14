package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.Home.GetNewsDetailRequest;
import com.example.nguyenhafood.Response.Home.GetNewsDetailResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetNewsDetailService {
    @POST("GetNewsDetail")
    Call<GetNewsDetailResponse> GetNewsDetail(@Query("GUIID") String GUIID, @Body GetNewsDetailRequest getNewsDetailRequest);
}

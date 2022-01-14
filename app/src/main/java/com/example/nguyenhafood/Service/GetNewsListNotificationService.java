package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.Home.GetNewsListNotificationRequest;
import com.example.nguyenhafood.Response.Home.GetNewsListNotificationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetNewsListNotificationService {
    @POST("GetNewsList")
    Call<GetNewsListNotificationResponse> GetNewsList(@Query("GUIID") String GUIID, @Body GetNewsListNotificationRequest getNewsListNotificationRequest);

}

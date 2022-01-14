package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.Order.ReBuyRequest;
import com.example.nguyenhafood.Response.Order.ReBuyResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ReBuyService {
    @POST("ReBuy")
    Call<ReBuyResponse>ReBuy(@Query("GUIID") String GUIID, @Body ReBuyRequest reBuyRequest);
}

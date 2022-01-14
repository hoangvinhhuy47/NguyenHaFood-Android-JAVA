package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.Order.CancelOrderRequest;
import com.example.nguyenhafood.Response.Order.CancelOrderResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CancelOrderSerVice {
    @POST("CancelOrder")
    Call<CancelOrderResponse> CancelOrder(@Query("GUIID") String GUIID, @Body CancelOrderRequest cancelOrderRequest);
}

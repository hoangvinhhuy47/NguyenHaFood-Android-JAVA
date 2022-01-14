package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.ViewCart.CreateOrderRequest;
import com.example.nguyenhafood.Response.Order.CreateOrderResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CreateOrderSerVice {
    @POST("CreateOrder")
    Call<CreateOrderResponse> CreateOrder(@Query("GUIID") String GUIID, @Body CreateOrderRequest createOrderRequest );
}

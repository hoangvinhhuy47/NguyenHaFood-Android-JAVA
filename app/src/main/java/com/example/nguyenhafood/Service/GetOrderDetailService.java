package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.Order.GetOrderDetailRequest;
import com.example.nguyenhafood.Response.Order.GetOrderDetailResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetOrderDetailService {
    @POST("GetOrderDetail")
    Call<GetOrderDetailResponse> GetOrderDetail(@Query("GUIID") String GUIID, @Body GetOrderDetailRequest getOrderDetailRequest);
}

package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.Order.GetOderRequest;
import com.example.nguyenhafood.Response.Order.GetOderResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetOderService {
    @POST("GetOrder")
    Call<GetOderResponse> GetOder(@Query("GUIID") String GUIID, @Body GetOderRequest getOderRequest);
}

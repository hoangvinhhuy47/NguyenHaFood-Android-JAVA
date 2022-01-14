package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.ViewCart.MomoRequest;
import com.example.nguyenhafood.Response.ViewCart.MomoResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MomoService {
    @POST("RequestMomoPayment")
    Call<MomoResponse> MomoPayment(@Query("GUIID") String GUIID, @Body MomoRequest momoRequest);
}

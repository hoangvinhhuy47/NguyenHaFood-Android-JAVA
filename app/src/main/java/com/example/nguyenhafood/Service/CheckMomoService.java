package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.ViewCart.CheckMoMoRequest;
import com.example.nguyenhafood.Response.ViewCart.CheckMomoResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CheckMomoService {
    @POST("CheckSignature")
    Call<CheckMomoResponse> CheckSignature(@Query("GUIID") String GUIID, @Body CheckMoMoRequest checkMoMoRequest );
}

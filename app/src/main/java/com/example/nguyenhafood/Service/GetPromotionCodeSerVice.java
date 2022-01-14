package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.ViewCart.GetPromotionCodeRequest;
import com.example.nguyenhafood.Response.ViewCart.GetPromotionCodeResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetPromotionCodeSerVice {
    @POST("GetPromotionCode")
    Call<GetPromotionCodeResponse> GetPromotionCode(@Query("GUIID") String GUIID, @Body GetPromotionCodeRequest getPromotionCodeRequest);
}

package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.Acount_Login.GetProductLaterBuyRequest;
import com.example.nguyenhafood.Response.Acount_Login.GetProductLaterBuyResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetProductLaterBuyService {
    @POST("GetProductLaterBuy")
    Call<GetProductLaterBuyResponse> GetProductLaterBuy(@Query("GUIID") String GUIID, @Body GetProductLaterBuyRequest getProductLaterBuyRequest);
}

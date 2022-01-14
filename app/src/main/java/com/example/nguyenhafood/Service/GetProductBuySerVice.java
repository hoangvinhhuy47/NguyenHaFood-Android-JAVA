package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.Acount_Login.GetProductBuyRequest;
import com.example.nguyenhafood.Response.Acount_Login.GetProductBuyResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetProductBuySerVice {
    @POST("GetProductBuy")
    Call<GetProductBuyResponse> GetProductBuy(@Query("GUIID") String GUIID, @Body GetProductBuyRequest getProductBuyRequest);
}

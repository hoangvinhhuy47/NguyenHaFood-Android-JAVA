package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.ViewCart.AddProductBuyLaterRequest;
import com.example.nguyenhafood.Response.ViewCart.AddProductBuyLaterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AddProductLaterBuySerVice {
    @POST("AddProductLaterBuy")
    Call<AddProductBuyLaterResponse> AddProductLaterBuy(@Query("GUIID") String GUIID, @Body AddProductBuyLaterRequest addProductBuyLaterRequest);

}

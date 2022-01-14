package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.Acount_Login.ChangeProductLaterBuyToCartRequest;
import com.example.nguyenhafood.Response.Acount_Login.ChangeProductLaterBuyToCartResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ChangeProductLaterBuyToCartService {
    @POST("ChangeProductLaterBuyToCart")
    Call<ChangeProductLaterBuyToCartResponse> ChangeProductLaterBuyToCart(@Query("GUIID") String GUIID, @Body ChangeProductLaterBuyToCartRequest changeProductLaterBuyToCartRequest);

}

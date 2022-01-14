package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.ViewCart.AddToCardRequest;
import com.example.nguyenhafood.Response.ViewCart.AddToCardResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AddToCardSerVice {
    @POST("AddToCart")
    Call<AddToCardResponse> AddToCart(@Query("GUIID") String GUIID, @Body AddToCardRequest addToCardRequest);
}

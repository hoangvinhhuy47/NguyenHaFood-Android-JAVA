package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.ViewCart.GetCardRequest;
import com.example.nguyenhafood.Response.ViewCart.GetCardResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetCardSerVice {
    @POST("GetCart")
    Call<GetCardResponse> GetCard(@Query("GUIID") String GUIID, @Body GetCardRequest getCardRequest);
}

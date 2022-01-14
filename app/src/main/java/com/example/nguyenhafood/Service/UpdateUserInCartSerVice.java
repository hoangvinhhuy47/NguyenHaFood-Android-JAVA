package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.ViewCart.UpdateUserInCartRequest;
import com.example.nguyenhafood.Response.ViewCart.UpdateUserInCartResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UpdateUserInCartSerVice {
    @POST("UpdateUserInCart")
    Call<UpdateUserInCartResponse> UDUserInCart(@Query("GUIID") String GUIID, @Body UpdateUserInCartRequest updateUserInCartRequest);
}

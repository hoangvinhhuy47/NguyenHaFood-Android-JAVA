package com.example.nguyenhafood.Service;


import com.example.nguyenhafood.Request.ViewCart.UpdateQuantityToCartRequest;
import com.example.nguyenhafood.Response.ViewCart.UpdateQuanTityToCartResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UpdateQuantityToCartSerVice {
    @POST("UpdateQuantityToCart")
    Call<UpdateQuanTityToCartResponse> UDQuantityToCart(@Query("GUIID") String GUIID, @Body UpdateQuantityToCartRequest updateQuantityToCartRequest);
}

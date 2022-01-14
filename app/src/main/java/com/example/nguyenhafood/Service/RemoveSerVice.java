package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.Acount_Login.RemoveProdcutRequest;
import com.example.nguyenhafood.Response.ViewCart.RemoveProductResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RemoveSerVice {
    @POST("RemoveProductOnCart")
    Call<RemoveProductResponse> RemoveProduct(@Query("GUIID") String GUIID, @Body RemoveProdcutRequest  removeProdcutRequest);
}

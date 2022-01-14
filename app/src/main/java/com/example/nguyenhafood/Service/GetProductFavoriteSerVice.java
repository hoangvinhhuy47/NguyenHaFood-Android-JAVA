package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.Acount_Login.GetProductFavoriteRequest;
import com.example.nguyenhafood.Response.Acount_Login.GetProductFavoriteResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetProductFavoriteSerVice {
    @POST("GetProductFavorite")
    Call<GetProductFavoriteResponse> GetProductFavorite(@Query("GUIID") String GUIID, @Body GetProductFavoriteRequest getProductFavoriteRequest);

}

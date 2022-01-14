package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.Acount_Login.AddRemoveFavoriteRequest;
import com.example.nguyenhafood.Response.Acount_Login.AddRemoveFavoriteReponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AddRemoveFavoriteSerVice {
        @POST("AddRemoveFavorite")
        Call<AddRemoveFavoriteReponse> AddRemoveFavorite(@Query("GUIID") String GUIID, @Body AddRemoveFavoriteRequest addRemoveFavoriteRequest);
}

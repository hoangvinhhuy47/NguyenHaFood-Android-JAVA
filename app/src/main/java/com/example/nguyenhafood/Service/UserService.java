package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.Home.UserRequest;
import com.example.nguyenhafood.Response.Home.UserReponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {
        @POST("CheckLogin")
        Call<UserReponse> UserLogin(  @Query("GUIID") String GUIID, @Body UserRequest userRequest);
}

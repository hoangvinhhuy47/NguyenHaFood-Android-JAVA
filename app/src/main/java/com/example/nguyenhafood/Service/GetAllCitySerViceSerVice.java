package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.Acount_Login.GetAllCityRequest;
import com.example.nguyenhafood.Response.Acount_Login.GetAllCityResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetAllCitySerViceSerVice {
    @POST("GetAllCity")
    Call<GetAllCityResponse> GetAllCity(@Query("GUIID") String GUIID, @Body GetAllCityRequest getAllCityRequest);
}

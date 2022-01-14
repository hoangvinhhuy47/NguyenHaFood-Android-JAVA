package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.Home.HomeRequest;
import com.example.nguyenhafood.Response.Home.HomeResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HomeDTOSerVice {
    @POST("GetHomeData")
    Call<HomeResponse> HomeDTO(@Query("GUIID") String GUIID, @Body HomeRequest homeRequest);
}

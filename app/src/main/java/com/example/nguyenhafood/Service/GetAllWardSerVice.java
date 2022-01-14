package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.Acount_Login.GetAllDistrictRequest;
import com.example.nguyenhafood.Request.Acount_Login.GetAllWardRequest;
import com.example.nguyenhafood.Response.Acount_Login.GetAllDistrictResponse;
import com.example.nguyenhafood.Response.Acount_Login.GetAllWardResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetAllWardSerVice {
    @POST("GetAllWard")
    Call<GetAllWardResponse> GetAllWard(@Query("GUIID") String GUIID, @Body GetAllWardRequest getAllWardRequest);
}

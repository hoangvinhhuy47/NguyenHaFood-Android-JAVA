package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.Acount_Login.GetAllDistrictRequest;
import com.example.nguyenhafood.Response.Acount_Login.GetAllDistrictResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetAllDistrictSerVice {
    @POST("GetAllDistrict")
    Call<GetAllDistrictResponse> GetAllDistrict(@Query("GUIID") String GUIID, @Body GetAllDistrictRequest getAllDistrictRequest);
}

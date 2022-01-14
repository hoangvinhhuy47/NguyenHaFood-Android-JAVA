package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.Acount_Login.GetMoreItemRelationRequest;
import com.example.nguyenhafood.Response.Acount_Login.GetMoreItemRelationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetMoreWebItemRelationService {
    @POST("GetMoreItemRelation")
    Call<GetMoreItemRelationResponse> GetMoreItemRelation(@Query("GUIID") String GUIID, @Body GetMoreItemRelationRequest getMoreItemRelationRequest);
}

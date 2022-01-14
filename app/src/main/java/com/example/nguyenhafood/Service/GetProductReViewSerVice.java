package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.Acount_Login.GetProductReViewRequest;
import com.example.nguyenhafood.Response.Acount_Login.GetProductReViewResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetProductReViewSerVice {
    @POST("GetProductReView")
    Call<GetProductReViewResponse> GetProductReView(@Query("GUIID") String GUIID, @Body GetProductReViewRequest getProductReViewRequest);
}

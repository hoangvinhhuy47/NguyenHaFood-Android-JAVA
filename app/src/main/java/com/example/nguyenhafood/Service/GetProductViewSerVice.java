package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.Acount_Login.GetProductViewRequest;
import com.example.nguyenhafood.Response.Acount_Login.GetProductViewResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetProductViewSerVice {
    @POST("GetProductView")
    Call<GetProductViewResponse> GetProDuctView(@Query("GUIID") String GUIID, @Body GetProductViewRequest getProductViewRequest);

}

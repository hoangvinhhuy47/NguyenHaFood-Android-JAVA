package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.Acount_Login.GetProductDetailRequest;
import com.example.nguyenhafood.Response.Order.GetProductDetailResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetProductDetailService {
    @POST("GetProductDetail")
    Call<GetProductDetailResponse> GetProductDetail(@Query("GUIID") String GUIID, @Body GetProductDetailRequest getProductDetailRequest);
}

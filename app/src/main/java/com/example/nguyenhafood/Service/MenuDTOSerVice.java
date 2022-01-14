package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.Home.MenuRequest;
import com.example.nguyenhafood.Response.Home.MenuResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MenuDTOSerVice {
    @POST("GetCategoryData")
    Call<MenuResponse> MenuDTO(@Query("GUIID") String GUIID, @Body MenuRequest menuRequest);
}

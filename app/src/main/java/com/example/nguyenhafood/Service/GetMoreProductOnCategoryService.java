package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.Home.GetMoreProductOnCategoryRequest;
import com.example.nguyenhafood.Response.Home.GetMoreProductOnCategoryResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetMoreProductOnCategoryService {
    @POST("GetMoreProductOnCategory")
    Call<GetMoreProductOnCategoryResponse> GetMoreProductOnCategory(@Query("GUIID") String GUIID, @Body GetMoreProductOnCategoryRequest getMoreProductOnCategoryRequest);
}

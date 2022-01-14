package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.Home.SearchRequest;
import com.example.nguyenhafood.Response.Home.SearchResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SearchDTOSerVice {
    @POST("SearchProduct")
    Call<SearchResponse> SearchDTO(@Query("GUIID") String GUIID, @Body SearchRequest searchRequest);
}

package com.example.nguyenhafood.Service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DeleteCartSerVice {
    @POST("DeleteCart")
    Call<DeleteCartSerVice> DeleteCart(@Query("GUIID") String GUIID, @Body DeleteCartSerVice deleteCartSerVice);
}

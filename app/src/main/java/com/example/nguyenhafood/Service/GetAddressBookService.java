package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.Acount_Login.GetAddressBookRequest;
import com.example.nguyenhafood.Response.Acount_Login.GetAddressBookResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetAddressBookService {
    @POST("GetAddressBook")
    Call<GetAddressBookResponse> GetAddressBook(@Query("GUIID") String GUIID, @Body GetAddressBookRequest getAddressBookRequest);

}

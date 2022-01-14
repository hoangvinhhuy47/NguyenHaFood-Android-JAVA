package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.Acount_Login.AddAddressBookRequest;
import com.example.nguyenhafood.Response.Acount_Login.AddAddressBookResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AddAddressBookSerVice {
    @POST("AddAddressBook")
    Call<AddAddressBookResponse> AddAddressBook(@Query("GUIID") String GUIID, @Body AddAddressBookRequest addAddressBookRequest);
}

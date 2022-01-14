package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.Acount_Login.UpdateAddressBookRequest;
import com.example.nguyenhafood.Response.Acount_Login.UpdateAddressBookResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UpdateAddressBookSerVice {
    @POST("UpdateAddressBook")
    Call<UpdateAddressBookResponse> updateAddressBook(@Query("GUIID") String GUIID, @Body UpdateAddressBookRequest updateAddressBookRequest);
}

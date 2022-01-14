package com.example.nguyenhafood.Service;

import com.example.nguyenhafood.Request.Acount_Login.DeleteAddressBookRequest;
import com.example.nguyenhafood.Response.Acount_Login.DeleteAddressBookResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DeleteAddressBookSerVice {
    @POST("DeleteAddressBook")
    Call<DeleteAddressBookResponse> DeleteAddressBook(@Query("GUIID") String GUIID, @Body DeleteAddressBookRequest deleteAddressBookRequest);
}

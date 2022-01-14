package com.example.nguyenhafood.Response.Acount_Login;

import com.example.nguyenhafood.Model.AcountUser.DataAddressBook;
import com.example.nguyenhafood.Response.BaseResponse;

import java.util.List;

public class GetAddressBookResponse  extends BaseResponse {


    public List<DataAddressBook> getAddressBook() {
        return AddressBook;
    }

    public void setAddressBook(List<DataAddressBook> addressBook) {
        AddressBook = addressBook;
    }

    List<DataAddressBook> AddressBook;
}

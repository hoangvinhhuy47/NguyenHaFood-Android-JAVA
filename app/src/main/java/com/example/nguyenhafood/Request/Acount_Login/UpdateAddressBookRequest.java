package com.example.nguyenhafood.Request.Acount_Login;

import com.example.nguyenhafood.Model.AcountUser.DataUpdateAddressBook;

public class UpdateAddressBookRequest {
    public DataUpdateAddressBook getAddressBook() {
        return AddressBook;
    }

    public void setAddressBook(DataUpdateAddressBook addressBook) {
        AddressBook = addressBook;
    }

    DataUpdateAddressBook AddressBook;
}

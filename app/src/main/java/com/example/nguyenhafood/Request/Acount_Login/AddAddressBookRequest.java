package com.example.nguyenhafood.Request.Acount_Login;

import com.example.nguyenhafood.Model.AcountUser.DataAddressBook;

public class AddAddressBookRequest {
    public DataAddressBook getAddressBook() {
        return AddressBook;
    }

    public void setAddressBook(DataAddressBook addressBook) {
        AddressBook = addressBook;
    }

    DataAddressBook AddressBook;
}

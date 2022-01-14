package com.example.nguyenhafood.Response.Home;

import com.example.nguyenhafood.Model.LoginUser.Customer;
import com.example.nguyenhafood.Response.BaseResponse;

public class UserReponse extends BaseResponse {
    public com.example.nguyenhafood.Model.LoginUser.Customer getCustomer() {
        return Customer;
    }

    public void setCustomer(com.example.nguyenhafood.Model.LoginUser.Customer customer) {
        Customer = customer;
    }

    Customer Customer;
}

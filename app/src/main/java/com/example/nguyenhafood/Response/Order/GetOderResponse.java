package com.example.nguyenhafood.Response.Order;

import com.example.nguyenhafood.Model.Acount_Login.DataOder;
import com.example.nguyenhafood.Response.BaseResponse;

import java.util.List;

public class GetOderResponse extends BaseResponse {
    public List<DataOder> getOrderList() {
        return OrderList;
    }

    public void setOderlist(List<DataOder> orderList) {
        OrderList = orderList;
    }

    public List<DataOder> OrderList;
}
package com.example.nguyenhafood.Response.ViewCart;

import com.example.nguyenhafood.Model.Momo.DataMomo;
import com.example.nguyenhafood.Response.BaseResponse;

public class MomoResponse extends BaseResponse {
    public DataMomo getData() {
        return Data;
    }

    public void setData(DataMomo data) {
        Data = data;
    }

    DataMomo Data;
}

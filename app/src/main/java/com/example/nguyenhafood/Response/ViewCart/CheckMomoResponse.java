package com.example.nguyenhafood.Response.ViewCart;

import com.example.nguyenhafood.Model.Momo.DatamomoResponse;
import com.example.nguyenhafood.Response.BaseResponse;

public class CheckMomoResponse  extends BaseResponse {
    public DatamomoResponse getData() {
        return Data;
    }

    public void setData(DatamomoResponse data) {
        Data = data;
    }

    DatamomoResponse Data;
}

package com.example.nguyenhafood.Response.Acount_Login;

import com.example.nguyenhafood.Model.AcountUser.DataAllWard;
import com.example.nguyenhafood.Response.BaseResponse;

import java.util.List;

public class GetAllWardResponse extends BaseResponse {
    public List<DataAllWard> getWardList() {
        return WardList;
    }

    public void setWardList(List<DataAllWard> wardList) {
        WardList = wardList;
    }

    List<DataAllWard> WardList;
}

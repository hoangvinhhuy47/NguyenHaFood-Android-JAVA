package com.example.nguyenhafood.Response.Acount_Login;

import com.example.nguyenhafood.Model.AcountUser.DataAllDistrict;
import com.example.nguyenhafood.Response.BaseResponse;

import java.util.List;

public class GetAllDistrictResponse  extends BaseResponse {
    public List<DataAllDistrict> getDistrictList() {
        return DistrictList;
    }

    public void setDistrictList(List<DataAllDistrict> districtList) {
        DistrictList = districtList;
    }

    List<DataAllDistrict> DistrictList;
}

package com.example.nguyenhafood.Response.Acount_Login;

import com.example.nguyenhafood.Model.AcountUser.DataAllCity;
import com.example.nguyenhafood.Response.BaseResponse;

import java.util.List;

public class GetAllCityResponse  extends BaseResponse {
    public List<DataAllCity> getCityList() {
        return CityList;
    }

    public void setCityList(List<DataAllCity> cityList) {
        CityList = cityList;
    }

    List<DataAllCity> CityList;
}

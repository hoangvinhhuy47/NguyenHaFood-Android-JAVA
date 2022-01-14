package com.example.nguyenhafood.Model.AcountUser;

import androidx.annotation.NonNull;

public class DataAllDistrict {
    private String DistrictID;
    private String DistrictName;
    private String CityID;
    private int SortOrder;

    public String getDistrictID() {
        return DistrictID;
    }

    public void setDistrictID(String districtID) {
        DistrictID = districtID;
    }

    public String getDistrictName() {
        return DistrictName;
    }

    public void setDistrictName(String districtName) {
        DistrictName = districtName;
    }

    public String getCityID() {
        return CityID;
    }

    public void setCityID(String cityID) {
        CityID = cityID;
    }

    public int getSortOrder() {
        return SortOrder;
    }

    public void setSortOrder(int sortOrder) {
        SortOrder = sortOrder;
    }

    @NonNull
    @Override
    public String toString() {
        return this.getDistrictName();
    }
}

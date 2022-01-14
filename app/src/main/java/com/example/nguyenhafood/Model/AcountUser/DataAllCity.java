package com.example.nguyenhafood.Model.AcountUser;

import androidx.annotation.NonNull;

public class DataAllCity {
    private String CityID;
    private String CityName;

    public String getCityID() {
        return CityID;
    }

    public void setCityID(String cityID) {
        CityID = cityID;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getCountryID() {
        return CountryID;
    }

    public void setCountryID(String countryID) {
        CountryID = countryID;
    }

    public int getSortOrder() {
        return SortOrder;
    }

    public void setSortOrder(int sortOrder) {
        SortOrder = sortOrder;
    }

    private String CountryID;
    private int SortOrder;

    @NonNull
    @Override
    public String toString() {
        return this.getCityName();
    }
}

package com.example.nguyenhafood.Model.AcountUser;

import androidx.annotation.NonNull;

public class DataAllWard {
  public String WardID;

    public String getWardID() {
        return WardID;
    }

    public void setWardID(String wardID) {
        WardID = wardID;
    }

    public String getWardName() {
        return WardName;
    }

    public void setWardName(String wardName) {
        WardName = wardName;
    }

    public String getDistrictID() {
        return DistrictID;
    }

    public void setDistrictID(String districtID) {
        DistrictID = districtID;
    }

    public String WardName;
  public String DistrictID;
    @NonNull
    @Override
    public String toString() {
        return this.getWardName();
    }
}

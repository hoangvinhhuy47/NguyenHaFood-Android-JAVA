package com.example.nguyenhafood.Model.Acount_Login;

public class DataTapAllOder {
    public DataTapAllOder(String NametapAllOder, boolean iSActive){
        this.NametapAllOder = NametapAllOder;
        this.iSActive = iSActive;
    }

    public String getNametapAllOder() {
        return NametapAllOder;
    }

    public void setNametapAllOder(String nametapAllOder) {
        NametapAllOder = nametapAllOder;
    }

    private String NametapAllOder;

    public boolean isiSActive() {
        return iSActive;
    }

    public void setiSActive(boolean iSActive) {
        this.iSActive = iSActive;
    }

    public boolean iSActive ;
}

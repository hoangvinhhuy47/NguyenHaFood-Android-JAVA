package com.example.nguyenhafood.Request.ViewCart;

public class CheckMoMoRequest {
    private String ParamString;

    public String getParamString() {
        return ParamString;
    }

    public void setParamString(String paramString) {
        ParamString = paramString;
    }

    public String getSignature() {
        return Signature;
    }

    public void setSignature(String signature) {
        Signature = signature;
    }

    private String Signature;
}

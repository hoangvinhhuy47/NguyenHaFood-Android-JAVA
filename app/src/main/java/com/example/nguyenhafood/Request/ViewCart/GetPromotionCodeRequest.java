package com.example.nguyenhafood.Request.ViewCart;

import com.example.nguyenhafood.Model.AddPromotion.DataOrderDetailList;

import java.util.List;

public class GetPromotionCodeRequest {
    public String getPromotionCode() {
        return PromotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        PromotionCode = promotionCode;
    }

    public List<DataOrderDetailList> getOrderDetailList() {
        return OrderDetailList;
    }

    public void setOrderDetailList(List<DataOrderDetailList> orderDetailList) {
        OrderDetailList = orderDetailList;
    }

    private String PromotionCode;
    public List<DataOrderDetailList> OrderDetailList;
}

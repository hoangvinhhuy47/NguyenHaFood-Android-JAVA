package com.example.nguyenhafood.Request.ViewCart;

import com.example.nguyenhafood.Model.Orders.DataCreateOrder;
import com.example.nguyenhafood.Model.ViewCart.GetCard;

import java.util.List;

public class CreateOrderRequest {






    public DataCreateOrder getOrder() {
        return Order;
    }

    public void setOrder(DataCreateOrder order) {
        Order = order;
    }

    DataCreateOrder Order;


    public List<GetCard> getOrderDetailList() {
        return OrderDetailList;
    }

    public void setOrderDetailList(List<GetCard> orderDetailList) {
        OrderDetailList = orderDetailList;
    }

    List<GetCard> OrderDetailList;
    public String getPromotionCode() {
        return PromotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        PromotionCode = promotionCode;
    }

    public String PromotionCode;
}

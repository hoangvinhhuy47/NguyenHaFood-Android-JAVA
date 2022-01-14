package com.example.nguyenhafood.Response.ViewCart;

import com.example.nguyenhafood.Model.ViewCart.GetCard;
import com.example.nguyenhafood.Response.BaseResponse;

import java.util.List;

public class GetCardResponse  extends BaseResponse {
    public List<GetCard> getHomeData() {
        return HomeData;
    }

    public void setHomeData(List<GetCard> homeData) {
        HomeData = homeData;
    }

    List<GetCard> HomeData;
}

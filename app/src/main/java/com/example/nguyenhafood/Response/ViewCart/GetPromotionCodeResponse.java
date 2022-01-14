package com.example.nguyenhafood.Response.ViewCart;

import com.example.nguyenhafood.Model.AddPromotion.DataGetPromotionCode;
import com.example.nguyenhafood.Model.AddPromotion.DataPromotion;
import com.example.nguyenhafood.Model.AddPromotion.DataPromotionItemGifs;
import com.example.nguyenhafood.Model.AddPromotion.DataPromotionItems;
import com.example.nguyenhafood.Response.BaseResponse;

import java.util.List;

public class GetPromotionCodeResponse extends BaseResponse {
    public DataGetPromotionCode getPromotion() {
        return Promotion;
    }

    public void setPromotion(DataGetPromotionCode promotion) {
        Promotion = promotion;
    }

    DataGetPromotionCode Promotion;
}

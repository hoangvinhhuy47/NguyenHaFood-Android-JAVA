package com.example.nguyenhafood.Model.AddPromotion;

import java.util.List;

public class DataGetPromotionCode {
    DataPromotion Promotion;

    public DataPromotion getPromotion() {
        return Promotion;
    }

    public void setPromotion(DataPromotion promotion) {
        Promotion = promotion;
    }

    public List<DataPromotionItems> getPromotionItems() {
        return PromotionItems;
    }

    public void setPromotionItems(List<DataPromotionItems> promotionItems) {
        PromotionItems = promotionItems;
    }

    public List<DataPromotionItemGifs> getPromotionItemGifs() {
        return PromotionItemGifs;
    }

    public void setPromotionItemGifs(List<DataPromotionItemGifs> promotionItemGifs) {
        PromotionItemGifs = promotionItemGifs;
    }

    public int getPromotionType() {
        return PromotionType;
    }

    public void setPromotionType(int promotionType) {
        PromotionType = promotionType;
    }

    List<DataPromotionItems> PromotionItems;
    List<DataPromotionItemGifs> PromotionItemGifs;
    private int  PromotionType;
}

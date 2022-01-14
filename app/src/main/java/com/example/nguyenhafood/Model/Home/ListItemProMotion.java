package com.example.nguyenhafood.Model.Home;

import java.util.List;

public class ListItemProMotion {
    public List<ItemListProMotion> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemListProMotion> itemList) {
        this.itemList = itemList;
    }

    public CatoryPromotion getCategory() {
        return category;
    }

    public void setCategory(CatoryPromotion category) {
        this.category = category;
    }
    List<ItemListProMotion> itemList;
    CatoryPromotion category;
}

package com.example.nguyenhafood.Model.Home;

import java.util.List;

public class HomeDTO {
    List<vw_SlideDetail> SlideDetailList;
    public List<vw_Favorite> Favoritelist;

    public List<vw_SlideDetail> getSlideDetailList() {
        return SlideDetailList;
    }

    public void setSlideDetailList(List<vw_SlideDetail> slideDetailList) {
        SlideDetailList = slideDetailList;
    }

    public List<vw_Favorite> getFavoriteList() {
        return Favoritelist;
    }

    public void setFavoriteList(List<vw_Favorite> favoriteList) {
        Favoritelist = favoriteList;
    }

    public List<ItemDTO> getItemDTOList() {
        return ItemDTOList;
    }

    public void setItemDTOList(List<ItemDTO> itemDTOList) {
        ItemDTOList = itemDTOList;
    }

    public List<ItemDTO> ItemDTOList;
    public List<ListItemProMotion> getWebItemPromotionDTOList() {
        return WebItemPromotionDTOList;
    }

    public void setWebItemPromotionDTOList(List<ListItemProMotion> webItemPromotionDTOList) {
        WebItemPromotionDTOList = webItemPromotionDTOList;
    }

    List<ListItemProMotion> WebItemPromotionDTOList;

    public List<vw_Favorite> getFavoritelist() {
        return Favoritelist;
    }

    public void setFavoritelist(List<vw_Favorite> favoritelist) {
        Favoritelist = favoritelist;
    }

    public List<DataProductViewList> getProductViewList() {
        return ProductViewList;
    }

    public void setProductViewList(List<DataProductViewList> productViewList) {
        ProductViewList = productViewList;
    }

    List<DataProductViewList> ProductViewList;
}

package com.example.nguyenhafood.Interface;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.nguyenhafood.Model.Home.vw_WebItemWebsite;

import java.util.List;

public interface UpdateViewCard  extends SwipeRefreshLayout.OnRefreshListener {
    public void CallBackViewCard(int Totalpirce,boolean TilePromotionCode);
    public void CallBackProductItem(double price,boolean calculation,boolean TilePromotionCode);
    public void UpdatePromotion(double price);
}

package com.example.nguyenhafood.Interface;

import androidx.viewpager.widget.ViewPager;

import com.example.nguyenhafood.Model.Home.ItemDTO;
import com.example.nguyenhafood.Model.Home.ItemListProMotion;
import com.example.nguyenhafood.Model.Home.ListItemProMotion;
import com.example.nguyenhafood.Model.Home.vw_WebItemWebsite;

import java.util.List;

public interface UpdatePromotion  extends UpdateListSeaRecycleView {
    public void CallBackPromotion(int position, List<ItemListProMotion> datalist);

}

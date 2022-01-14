package com.example.nguyenhafood.Interface;

import androidx.viewpager.widget.ViewPager;

import com.example.nguyenhafood.Model.Home.Item;
import com.example.nguyenhafood.Model.Home.vw_WebItemWebsite;

import java.util.List;

public interface UpdateListSeaRecycleView extends UpdateSizeCart  {
    public void CallBackProductID(int position, String ProductID,String PageIndex);
    public void CallBackAdapter(int position1,List<Item> list);
    public void CallBackView(int position2,String CatoryRootID);
}

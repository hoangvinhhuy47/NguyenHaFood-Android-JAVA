package com.example.nguyenhafood.Interface;

import com.example.nguyenhafood.Model.Home.Item;
import com.example.nguyenhafood.Model.Home.ItemDTO;
import com.example.nguyenhafood.Model.Home.vw_WebItemWebsite;

import java.util.List;

public interface UpdateListMenuRecycleView extends UpdateSizeCart {
public void UpdateCatoryMenu(int position,String CatoryItem,String ID);
public void UpdateCatoryItemMenu(int position,String CatoryMenuDTO,String ID,String IMG);
}

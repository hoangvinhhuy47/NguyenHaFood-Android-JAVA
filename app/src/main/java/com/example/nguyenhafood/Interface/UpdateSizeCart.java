package com.example.nguyenhafood.Interface;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.nguyenhafood.Model.Home.Item;

import java.util.List;

public interface UpdateSizeCart extends SwipeRefreshLayout.OnRefreshListener  {
    public void UpdateSize(int position1);
}

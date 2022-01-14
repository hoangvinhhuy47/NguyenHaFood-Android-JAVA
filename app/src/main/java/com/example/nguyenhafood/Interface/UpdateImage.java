package com.example.nguyenhafood.Interface;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.nguyenhafood.Model.Home.Item;

import java.util.List;

public interface UpdateImage  extends SwipeRefreshLayout.OnRefreshListener {
    public void UpdateImage(int position, String Image);
}

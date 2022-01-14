package com.example.nguyenhafood.Interface;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public interface UpdateProductView extends SwipeRefreshLayout.OnRefreshListener {
    public void UpDateview(int position);
}

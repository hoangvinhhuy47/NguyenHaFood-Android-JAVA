package com.example.nguyenhafood.Interface;

import androidx.viewpager.widget.ViewPager;

public interface IAllOder  extends ViewPager.OnPageChangeListener {
    void onItemClick(int position);
    void onItemClick1(int position);
}
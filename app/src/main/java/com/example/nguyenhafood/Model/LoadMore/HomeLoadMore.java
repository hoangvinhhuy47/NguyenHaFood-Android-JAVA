package com.example.nguyenhafood.Model.LoadMore;

import com.example.nguyenhafood.Model.Home.vw_WebItemWebsite;

import java.util.List;

public class HomeLoadMore {
    public List<vw_WebItemWebsite> getProductList() {
        return ProductList;
    }

    public void setProductList(List<vw_WebItemWebsite> productList) {
        ProductList = productList;
    }

    List<vw_WebItemWebsite> ProductList;
}

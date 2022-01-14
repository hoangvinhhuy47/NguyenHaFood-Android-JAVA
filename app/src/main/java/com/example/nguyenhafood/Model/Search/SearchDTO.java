package com.example.nguyenhafood.Model.Search;

import com.example.nguyenhafood.Model.Home.ItemDTO;
import com.example.nguyenhafood.Model.Home.vw_Favorite;
import com.example.nguyenhafood.Model.Home.vw_SlideDetail;
import com.example.nguyenhafood.Model.Home.vw_WebItemWebsite;

import java.util.List;

public class SearchDTO {
    public List<vw_WebItemWebsite> getProductList() {
        return ProductList;
    }

    public void setProductList(List<vw_WebItemWebsite> productList) {
        ProductList = productList;
    }

    List<vw_WebItemWebsite> ProductList;
}

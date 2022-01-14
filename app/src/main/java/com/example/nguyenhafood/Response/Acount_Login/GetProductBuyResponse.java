package com.example.nguyenhafood.Response.Acount_Login;

import com.example.nguyenhafood.Model.Acount_Login.DataGetProductBuy;
import com.example.nguyenhafood.Response.BaseResponse;

import java.util.List;

public class GetProductBuyResponse extends BaseResponse {
    public List<DataGetProductBuy> getProductBuyList() {
        return ProductBuyList;
    }

    public void setProductBuyList(List<DataGetProductBuy> productBuyList) {
        ProductBuyList = productBuyList;
    }

    public List<DataGetProductBuy> ProductBuyList;
}

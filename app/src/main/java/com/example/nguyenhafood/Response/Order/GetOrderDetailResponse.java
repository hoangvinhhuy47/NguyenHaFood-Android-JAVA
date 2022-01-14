package com.example.nguyenhafood.Response.Order;

import com.example.nguyenhafood.Model.Acount_Login.DataDetailList;
import com.example.nguyenhafood.Model.Acount_Login.DataOrderDetail;
import com.example.nguyenhafood.Response.BaseResponse;

import java.util.List;

public class GetOrderDetailResponse extends BaseResponse {
    public List<DataDetailList> getDetailList() {
        return DetailList;
    }

    public void setDetailList(List<DataDetailList> detailList) {
        DetailList = detailList;
    }

    public DataOrderDetail getOrder() {
        return Order;
    }

    public void setOrder(DataOrderDetail order) {
        Order = order;
    }

    List<DataDetailList> DetailList;
    DataOrderDetail Order;
}

package com.example.nguyenhafood.Activity.Acount_login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenhafood.API.APIClient;
import com.example.nguyenhafood.Activity.BaseActivity.BaseActivity;
import com.example.nguyenhafood.Activity.ViewCartsAcivity;
import com.example.nguyenhafood.Apdater.Acount_Login.ListProductOrderdetailAdapter;
import com.example.nguyenhafood.Fragment.SearchFragment;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Model.Acount_Login.DataDetailList;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.Order.GetOrderDetailRequest;
import com.example.nguyenhafood.Response.Order.GetOrderDetailResponse;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityOrderDetail extends BaseActivity {
    TextView text_maDH_OrderDetail, text_Ngaydathang_orderdetail, text_Namenguoinhan_orderdetail, text_phonecustumer_orderdetail, text_ShipAddress_orderdetail;
    TextView text_trangthaiDH_orderdetail, txt_Payment_Orderdetail, txt_tamtinh_orderdetail, txt_tongcong_orderdetail, txt_button_orderdetail, txt_masothue_orderdetail;
    RecyclerView rcv_listproduct_orderdetail;
    ImageView btn_back_orderdetail, view_cart;
    TextView txt_trangthaiHD_orderdetail, txt_nameconty_orderdetail, txt_emailcongty_orderdetail, txt_diachicongty_orderdetail, txt_TransportType_Orderdetail, txt_Note_Orderdetail;
    RelativeLayout groud_trangthaiHD, groud_thongtincongty;
    boolean Showgroud_thongtincongty = false;
    ShimmerFrameLayout shimmerFrameLayoutorderdetail;
    ListProductOrderdetailAdapter listProductOrderdetailAdapter;
    List<DataDetailList> dataDetailLists = new ArrayList<>();
    RelativeLayout group_CancelReason_Orderdetail;
    TextView txt_CancelReason_Orderdetail, cart_badge;
    EditText edt_CancelReason_Orderdetail;
    ProgressBar progressBar_orderdetail;
    ScrollView scl_orderdetail;
    RelativeLayout search;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetails);
        text_maDH_OrderDetail = findViewById(R.id.text_maDH_OrderDetail);
        text_Ngaydathang_orderdetail = findViewById(R.id.text_Ngaydathang_orderdetail);
        text_Namenguoinhan_orderdetail = findViewById(R.id.text_Namenguoinhan_orderdetail);
        text_phonecustumer_orderdetail = findViewById(R.id.text_phonecustumer_orderdetail);
        text_ShipAddress_orderdetail = findViewById(R.id.text_ShipAddress_orderdetail);
        text_trangthaiDH_orderdetail = findViewById(R.id.text_trangthaiDH_orderdetail);
        rcv_listproduct_orderdetail = findViewById(R.id.rcv_listproduct_orderdetail);
        rcv_listproduct_orderdetail.setNestedScrollingEnabled(false);
        txt_Payment_Orderdetail = findViewById(R.id.txt_Payment_Orderdetail);
        txt_tongcong_orderdetail = findViewById(R.id.txt_tongcong_orderdetail);
        txt_tamtinh_orderdetail = findViewById(R.id.txt_tamtinh_orderdetail);
        txt_button_orderdetail = findViewById(R.id.txt_button_orderdetail);
        btn_back_orderdetail = findViewById(R.id.btn_back_orderdetail);
        txt_trangthaiHD_orderdetail = findViewById(R.id.txt_trangthaiHD_orderdetail);
        txt_nameconty_orderdetail = findViewById(R.id.txt_nameconty_orderdetail);
        txt_emailcongty_orderdetail = findViewById(R.id.txt_emailcongty_orderdetail);
        txt_diachicongty_orderdetail = findViewById(R.id.txt_diachicongty_orderdetail);
        txt_TransportType_Orderdetail = findViewById(R.id.txt_TransportType_Orderdetail);
        txt_Note_Orderdetail = findViewById(R.id.txt_Note_Orderdetail);
        groud_trangthaiHD = findViewById(R.id.groud_trangthaiHD);
        groud_thongtincongty = findViewById(R.id.groud_thongtincongty);
        txt_masothue_orderdetail = findViewById(R.id.txt_masothue_orderdetail);
        group_CancelReason_Orderdetail = findViewById(R.id.group_CancelReason_Orderdetail);
        shimmerFrameLayoutorderdetail = findViewById(R.id.shimmerFrameLayoutorderdetail);
        edt_CancelReason_Orderdetail = findViewById(R.id.edt_CancelReason_Orderdetail);
        txt_CancelReason_Orderdetail = findViewById(R.id.txt_CancelReason_Orderdetail);
        progressBar_orderdetail = findViewById(R.id.progressBar_orderdetail);
        cart_badge = findViewById(R.id.cart_badge);
        view_cart = findViewById(R.id.view_cart);
        search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(SearchFragment.class);
            }
        });
        view_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), ViewCartsAcivity.class);
                startActivity(intent);
            }
        });
        scl_orderdetail = findViewById(R.id.scl_orderdetail);
        shimmerFrameLayoutorderdetail.startShimmer();
        scl_orderdetail.setVisibility(View.GONE);
        btn_back_orderdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = this.getIntent();
        GetOrderDetailRequest getOrderDetailRequest = new GetOrderDetailRequest();
        getOrderDetailRequest.setOrderID(intent.getStringExtra("OrderID"));
        LoadOrderdetail(getOrderDetailRequest);
    }
    @Override
    protected void onStart() {
        super.onStart();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cart_badge.setText(String.valueOf(Gobal.SizeProduct));
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    public void LoadOrderdetail(GetOrderDetailRequest getOrderDetailRequest) {
        Call<GetOrderDetailResponse> getOrderDetailResponseCall = APIClient.getOrderDetailService().GetOrderDetail(Gobal.GuiID, getOrderDetailRequest);
        getOrderDetailResponseCall.enqueue(new Callback<GetOrderDetailResponse>() {
            @Override
            public void onResponse(Call<GetOrderDetailResponse> call, Response<GetOrderDetailResponse> response) {
                if (response.isSuccessful()) {
                    GetOrderDetailResponse getOrderDetailResponse = response.body();
                    if (getOrderDetailResponse.getStatusID() == 1) {
                        //get list detail
                        dataDetailLists.addAll(getOrderDetailResponse.getDetailList());
                        listProductOrderdetailAdapter = new ListProductOrderdetailAdapter(getApplication(), getOrderDetailResponse.getDetailList());
                        rcv_listproduct_orderdetail.setAdapter(listProductOrderdetailAdapter);
                        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
                        rcv_listproduct_orderdetail.setLayoutManager(linearLayoutManager2);
                        //
                        text_maDH_OrderDetail.setText(getOrderDetailResponse.getOrder().getOrderCode());
                        text_Ngaydathang_orderdetail.setText(Model.convertDateToString(Model.convertStringToDate(getOrderDetailResponse.getOrder().getCreatedDate())));
                        text_trangthaiDH_orderdetail.setText(Model.ChangeStatusOrder(getOrderDetailResponse.getOrder().getStatus(), text_trangthaiDH_orderdetail));
                        text_Namenguoinhan_orderdetail.setText(getOrderDetailResponse.getOrder().getCustomerName());
                        text_phonecustumer_orderdetail.setText(getOrderDetailResponse.getOrder().getCustomerPhone());
                        text_ShipAddress_orderdetail.setText(getOrderDetailResponse.getOrder().getShipAddress());
                        txt_Payment_Orderdetail.setText(Model.ChangePaymentOrder(getOrderDetailResponse.getOrder().getPaymentMethodID()));
                        txt_tamtinh_orderdetail.setText(Model.ChangeDecimal(getOrderDetailResponse.getOrder().getAmount()));
                        txt_tongcong_orderdetail.setText(Model.ChangeDecimal(getOrderDetailResponse.getOrder().getTotalAmount()));
                        txt_button_orderdetail.setText(Model.ChangeStatusButtonOrder(getOrderDetailResponse.getOrder().getStatus()));
                        txt_CancelReason_Orderdetail.setText(getOrderDetailResponse.getOrder().getCancelReason());
                        //xử lý nút
                        Model.showbutton(getOrderDetailResponse.getOrder().getStatus(), txt_button_orderdetail, getApplicationContext(),getOrderDetailResponse.getOrder().getOrderID(), group_CancelReason_Orderdetail, edt_CancelReason_Orderdetail, txt_CancelReason_Orderdetail, progressBar_orderdetail);
                        //
                        txt_trangthaiHD_orderdetail.setText(Model.ChangeStatusVATOrder(getOrderDetailResponse.getOrder().getVATMethodID()));
                        //show thông tin VAT
                        groud_trangthaiHD.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (getOrderDetailResponse.getOrder().getVATMethodID() == 1) {
                                } else {
                                    if (Showgroud_thongtincongty == false) {
                                        Showgroud_thongtincongty = true;
                                        groud_thongtincongty.setVisibility(View.GONE);
                                    } else {
                                        groud_thongtincongty.setVisibility(View.VISIBLE);
                                        Showgroud_thongtincongty = false;
                                    }

                                }
                            }
                        });
                        //
                        txt_nameconty_orderdetail.setText(getOrderDetailResponse.getOrder().getTaxCompanyName());
                        txt_emailcongty_orderdetail.setText(getOrderDetailResponse.getOrder().getTaxCompanyEmail());
                        txt_diachicongty_orderdetail.setText(getOrderDetailResponse.getOrder().getTaxCompanyAddress());
                        txt_masothue_orderdetail.setText(getOrderDetailResponse.getOrder().getTaxCode());
                        //Notes
                        txt_Note_Orderdetail.setText(getOrderDetailResponse.getOrder().getNotes());
                        //TransportType
                        txt_TransportType_Orderdetail.setText(Model.ChangeStatusHTgiaohangOrder(getOrderDetailResponse.getOrder().getTransportTypeID()));
                        shimmerFrameLayoutorderdetail.stopShimmer();
                        shimmerFrameLayoutorderdetail.setVisibility(View.GONE);
                        scl_orderdetail.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetOrderDetailResponse> call, Throwable t) {
            }
        });

    }
}
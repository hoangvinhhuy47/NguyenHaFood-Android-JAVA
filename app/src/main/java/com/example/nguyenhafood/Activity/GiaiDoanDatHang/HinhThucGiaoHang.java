package com.example.nguyenhafood.Activity.GiaiDoanDatHang;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.nguyenhafood.Activity.Address.AddressActivity;
import com.example.nguyenhafood.Activity.BaseActivity.BaseActivity;
import com.example.nguyenhafood.Activity.ViewCartsAcivity;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.R;

public class HinhThucGiaoHang extends BaseActivity {
    Button btn_tieptuc, btn_tieptucmuahang;
    TextView change_address_delivery, txt_address_delivery;
    RadioButton radio_fast_delivery, radio_standard_delivery;
    LinearLayout lne_delivery, lne_delivery1;
    ImageView back_hinhthucgiaohang, ic_backviewcart;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formofdelivery);
        findID();
        ic_backviewcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(ViewCartsAcivity.class);
            }
        });
        back_hinhthucgiaohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToActivity(ViewCartsAcivity.class);

            }
        });
        change_address_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), AddressActivity.class);
                intent.putExtra("Show", "True");
                startActivity(intent);
            }
        });
        btn_tieptucmuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToActivity(ViewCartsAcivity.class);
            }
        });
        radio_standard_delivery.setChecked(true);
        Gobal.dataOrders.TransportTypeID = 1;
        lne_delivery1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radio_fast_delivery.setChecked(true);
                Gobal.dataOrders.TransportTypeID = 2;
                radio_standard_delivery.setChecked(false);
            }
        });
        radio_fast_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radio_fast_delivery.setChecked(true);
                Gobal.dataOrders.TransportTypeID = 2;
                radio_standard_delivery.setChecked(false);
            }
        });
        lne_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radio_fast_delivery.setChecked(false);
                Gobal.dataOrders.TransportTypeID = 1;
                radio_standard_delivery.setChecked(true);
            }
        });
        radio_standard_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radio_fast_delivery.setChecked(false);
                Gobal.dataOrders.TransportTypeID = 1;
                radio_standard_delivery.setChecked(true);
            }
        });

    }

    public void findID() {
        change_address_delivery = findViewById(R.id.change_address_delivery);
        btn_tieptuc = findViewById(R.id.btn_tieptuc);
        txt_address_delivery = findViewById(R.id.txt_address_delivery);
        radio_standard_delivery = findViewById(R.id.radio_standard_delivery);
        radio_fast_delivery = findViewById(R.id.radio_fast_delivery);
        lne_delivery1 = findViewById(R.id.lne_delivery1);
        lne_delivery = findViewById(R.id.lne_delivery);
        ic_backviewcart = findViewById(R.id.ic_backviewcart);
        btn_tieptucmuahang = findViewById(R.id.btn_tieptucmuahang);
        back_hinhthucgiaohang = findViewById(R.id.back_hinhthucgiaohang);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Gobal.ShowAddress.equals("") == false) {
            txt_address_delivery.setText(Gobal.ShowAddress);
            btn_tieptuc.setBackground(btn_tieptuc.getContext().getResources().getDrawable(R.drawable.button_muahang));
            btn_tieptuc.setTextColor(Color.parseColor("#FFFEFE"));
            btn_tieptuc.setVisibility(View.VISIBLE);
            btn_tieptuc.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("NewApi")
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplication(), HinhThucThanhToan.class);
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(HinhThucGiaoHang.this).toBundle());
                }
            });
        } else {
            btn_tieptuc.setBackground(btn_tieptuc.getContext().getResources().getDrawable(R.drawable.button));
            btn_tieptuc.setTextColor(Color.parseColor("#000000"));
            btn_tieptuc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplication(), "Vui Lòng Chọn Địa Chỉ Giao Hàng", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

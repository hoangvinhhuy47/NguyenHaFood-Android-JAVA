package com.example.nguyenhafood.Activity.GiaiDoanDatHang;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.nguyenhafood.Activity.BaseActivity.BaseActivity;
import com.example.nguyenhafood.Activity.ViewCartsAcivity;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.R;

public class HinhThucThanhToan extends BaseActivity {
    Button btn_tieptuchththanhtoan;
    RadioButton radio_paybymoney, radio_paybycard,radio_payment;
    TextView provisional_price, provisional_totalprice, delivery_form,title_tt,title_promotion;
    ImageView back_hinhthucthanhtoan, ic_backform_delivery, ic_backviewcart;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);
        findID();
        title_promotion.setText(Gobal.TitlePromotionCode);
        title_promotion.setPaintFlags(title_promotion.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        title_promotion.setTextColor(Color.parseColor("#000000"));
        title_promotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(ViewCartsAcivity.class);
            }
        });
        ic_backviewcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(ViewCartsAcivity.class);
            }
        });
        ic_backform_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToActivity(HinhThucGiaoHang.class);

            }
        });
        back_hinhthucthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToActivity(HinhThucGiaoHang.class);
            }
        });
        radio_paybymoney.setChecked(true);
        Gobal.dataOrders.PaymentMethodID = 1;
        Gobal.dataOrders.VATMethodID = 1;
        provisional_price.setText(Gobal.ToTalPrice);
        provisional_totalprice.setText(Gobal.ToTalPrice);
        radio_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gobal.dataOrders.PaymentMethodID = 3;
                radio_paybymoney.setChecked(false);
                radio_paybycard.setChecked(false);
                radio_payment.setChecked(true);
            }
        });
        radio_paybycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gobal.dataOrders.PaymentMethodID = 2;
                radio_paybycard.setChecked(true);
                radio_paybymoney.setChecked(false);
                radio_payment.setChecked(false);
            }
        });
        delivery_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(HinhThucGiaoHang.class);
            }
        });
        radio_paybymoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gobal.dataOrders.PaymentMethodID = 1;
                radio_paybymoney.setChecked(true);
                radio_paybycard.setChecked(false);
                radio_payment.setChecked(false);
            }
        });
        btn_tieptuchththanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), XacNhanDatHang.class);
                intent.putExtra("Key", "false");
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(HinhThucThanhToan.this).toBundle());
            }
        });
    }

    public void findID() {
        title_tt=findViewById(R.id.title_tt);
        title_promotion=findViewById(R.id.title_promotion);
        radio_payment=findViewById(R.id.radio_payment);
        delivery_form = findViewById(R.id.delivery_form);
        btn_tieptuchththanhtoan = findViewById(R.id.btn_tieptuchththanhtoan);
        radio_paybycard = findViewById(R.id.radio_paybycard);
        radio_paybymoney = findViewById(R.id.radio_paybymoney);
        provisional_price = findViewById(R.id.provisional_price);
        provisional_totalprice = findViewById(R.id.provisional_totalprice);
        back_hinhthucthanhtoan = findViewById(R.id.back_hinhthucthanhtoan);
        ic_backviewcart = findViewById(R.id.ic_backviewcart);
        ic_backform_delivery = findViewById(R.id.ic_backform_delivery);
    }
}

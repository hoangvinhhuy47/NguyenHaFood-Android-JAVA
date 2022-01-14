package com.example.nguyenhafood.Activity.Acount_login;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nguyenhafood.Activity.BaseActivity.BaseActivity;
import com.example.nguyenhafood.Activity.ViewCartsAcivity;
import com.example.nguyenhafood.Database.DBHelper;
import com.example.nguyenhafood.Fragment.LoginFragment;
import com.example.nguyenhafood.Fragment.SearchFragment;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.R;

public class Activity_Customerinformation extends BaseActivity {
    TextView txt_hovaten, txt_username_tt, txt_passwordtt, txt_address,txt_email_tt,txt_phonenumber_tt,cart_badge;
    DBHelper db;
    ImageView icon_eyes_password,iconbackalloder;
    Boolean ShowpassInformation = false;
    RelativeLayout search,viewcart;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerinformation);
        txt_address = findViewById(R.id.txt_address);
        txt_passwordtt = findViewById(R.id.txt_passwordtt);
        txt_username_tt = findViewById(R.id.txt_username_tt);
        txt_hovaten = findViewById(R.id.txt_hovaten);
        icon_eyes_password = findViewById(R.id.icon_eyes_password);
        txt_email_tt=findViewById(R.id.txt_email_tt);
        txt_phonenumber_tt=findViewById(R.id.txt_phonenumber_tt);
        cart_badge=findViewById(R.id.cart_badge);
        search=findViewById(R.id.search);
        viewcart=findViewById(R.id.viewcart);
        iconbackalloder=findViewById(R.id.iconbackalloder);
        iconbackalloder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //
        cart_badge.setText(String.valueOf(Gobal.SizeProduct));
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(SearchFragment.class);
            }
        });
        viewcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(ViewCartsAcivity.class);
            }
        });
        ///
        txt_hovaten.setText(LoginFragment.GobalLogin.getFullName());
        txt_username_tt.setText(LoginFragment.GobalLogin.getUserID());
        txt_email_tt.setText(Gobal.Gmail);
        txt_passwordtt.setText(LoginFragment.GobalLogin.getPassWord());
        txt_phonenumber_tt.setText(LoginFragment.GobalLogin.getCellPhone());
        txt_address.setText(Gobal.AdressAcount);

        icon_eyes_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ShowpassInformation == false){
                    ShowpassInformation = true;
                    txt_passwordtt.setInputType(InputType.TYPE_CLASS_TEXT);
                    icon_eyes_password.setImageResource(R.drawable.eye_hide);
                }else {
                    ShowpassInformation = false;
                    txt_passwordtt.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                    icon_eyes_password.setImageResource(R.drawable.eye_showpass);

                }
            }
        });
    }
}
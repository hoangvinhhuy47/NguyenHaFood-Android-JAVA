package com.example.nguyenhafood.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.nguyenhafood.Activity.Acount_login.ActivityAllOder;
import com.example.nguyenhafood.Activity.Acount_login.ActivityProductBuy;
import com.example.nguyenhafood.Activity.Acount_login.ActivityProductReView;
import com.example.nguyenhafood.Activity.Acount_login.CustomerSupportActivity;
import com.example.nguyenhafood.Activity.Acount_login.ActivityConnect;
import com.example.nguyenhafood.Activity.Acount_login.ActivityFavoriteproduct;
import com.example.nguyenhafood.Activity.Acount_login.ActivityProductlater;
import com.example.nguyenhafood.Activity.Acount_login.ActivityViewproduct;
import com.example.nguyenhafood.Activity.Acount_login.Activity_Customerinformation;
import com.example.nguyenhafood.Activity.Address.AddressActivity;
import com.example.nguyenhafood.Activity.ViewCartsAcivity;
import com.example.nguyenhafood.Database.DBHelper;
import com.example.nguyenhafood.Fragment.BaseFragment.BaseFragment;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.R;

import java.util.UUID;

public class Acount_LoginFragment extends BaseFragment {
    TextView txt_ketnoi, txt_QLdonhang, txt_sodc,hellp_acountlogin, txt_fullname, txt_dangxuat, cart_badge;
    ImageView img_xemtt, view_cart;
    TextView txt_spmuasau, txt_spdamua, txt_spdaxem, txt_spyeuthich;
    TextView sp_dadanhgia;
    DBHelper db;
    RelativeLayout ln_Oder_dahuy, ln_Oder_dathanhcong, ln_Oder_dangvanchuyen, ln_Oder_dangxuly;
    TextView acount_createacount,addgroup_nguyenhafood;
    ImageView img_fb,img_gg,img_zalo,img_youtube,img_twitter,img_instagram;
    String linkgroup = "https://www.facebook.com/groups/791878421562215";
    String linkfb="https://www.facebook.com/nguyenhafood.vn.video";
    String linkgg="https://www.google.com/maps/place//data=!4m2!3m1!1s0x31752f2eb132daeb:0xd2b5280c8fbca374?source=g.page.m";
    String linkzalo="https://zaloshop.me/store?id=f692c631cf7826267f69";
    String twitter="https://twitter.com/nguyenhafood";
    String linkyb = "https://www.youtube.com/channel/UClpibeSzfocURe-6PqE_0bg?view_as=subscriber";
    String linkinsta = "https://www.instagram.com/nguyenhafood/";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_acount_dangnhap, container, false);
        txt_ketnoi = view.findViewById(R.id.txt_ketnoi);
        view_cart = view.findViewById(R.id.view_cart);
        cart_badge = view.findViewById(R.id.cart_badge);
        cart_badge.setText(String.valueOf(Gobal.SizeProduct));
        addgroup_nguyenhafood=view.findViewById(R.id.addgroup_nguyenhafood);
        acount_createacount=view.findViewById(R.id.acount_createacount);
        hellp_acountlogin=view.findViewById(R.id.hellp_acountlogin);
        img_fb=view.findViewById(R.id.img_fb);
        img_gg=view.findViewById(R.id.img_gg);
        img_zalo=view.findViewById(R.id.img_zalo);
        img_youtube=view.findViewById(R.id.img_youtube);
        img_twitter=view.findViewById(R.id.img_twitter);
        img_instagram=view.findViewById(R.id.img_instagram);
        acount_createacount.setText("Thành Viên : " + Model.convertDateToString(Model.convertStringToDate(Gobal.CreateDay)));
        view_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               gotoActivity(ViewCartsAcivity.class);
            }
        });
        txt_QLdonhang = view.findViewById(R.id.txt_QLdonhang);
        txt_sodc = view.findViewById(R.id.txt_sodc);
        txt_spmuasau = view.findViewById(R.id.txt_spmuasau);
        db = new DBHelper(getActivity());
        txt_spdamua = view.findViewById(R.id.txt_spdamua);
        txt_spdaxem = view.findViewById(R.id.txt_spdaxem);
        txt_spyeuthich = view.findViewById(R.id.txt_spyeuthich);
        txt_fullname = view.findViewById(R.id.txt_fullname);
        txt_fullname.setText(LoginFragment.GobalLogin.getFullName());
        img_xemtt = view.findViewById(R.id.img_xemtt);
        sp_dadanhgia=view.findViewById(R.id.sp_dadanhgia);
        img_zalo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToView_Link(linkzalo);
            }
        });
        img_youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToView_Link(linkyb);
            }
        });
        img_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToView_Link(twitter);
            }
        });
        img_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToView_Link(linkfb);
            }
        });
        img_gg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToView_Link(linkgg);
            }
        });
        img_instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToView_Link(linkinsta);
            }
        });

        addgroup_nguyenhafood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToView_Link(linkgroup);
            }
        });
        hellp_acountlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(CustomerSupportActivity.class);
            }
        });
        img_xemtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gotoActivity(Activity_Customerinformation.class);
            }
        });
        txt_dangxuat = view.findViewById(R.id.txt_dangxuat);
        txt_dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gobal.setLoginStatus(0);
                db.DeleteUser(1);
                UUID ui = UUID.randomUUID();
                db.InsertUserER(ui.toString());
                Gobal.UserID = ui.toString();
                gotoActivity(MainActivity.class);
            }
        });
        ln_Oder_dangxuly = view.findViewById(R.id.ln_Oder_dangxuly);
        ln_Oder_dangvanchuyen = view.findViewById(R.id.ln_Oder_dangvanchuyen);
        ln_Oder_dathanhcong = view.findViewById(R.id.ln_Oder_dathanhcong);
        ln_Oder_dahuy = view.findViewById(R.id.ln_Oder_dahuy);
        Itent();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        cart_badge.setText(String.valueOf(Gobal.SizeProduct));
    }

    public void Itent() {
        txt_spdamua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(ActivityProductBuy.class);
            }
        });
        sp_dadanhgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(ActivityProductReView.class);
            }
        });
        txt_ketnoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoActivity(ActivityConnect.class);
            }
        });
        txt_sodc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gotoActivity(AddressActivity.class);
            }
        });
        txt_spmuasau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoActivity(ActivityProductlater.class);
            }
        });

        txt_spdaxem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gotoActivity(ActivityViewproduct.class);
            }
        });
        txt_spyeuthich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gotoActivity(ActivityFavoriteproduct.class);
            }
        });
        ln_Oder_dangxuly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityAllOder.class);
                intent.putExtra("key1", "0");
                startActivity(intent);
            }
        });
        ln_Oder_dangvanchuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityAllOder.class);
                intent.putExtra("key1", "1");
                startActivity(intent);
            }
        });
        ln_Oder_dahuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityAllOder.class);
                intent.putExtra("key1", "2");
                startActivity(intent);
            }
        });
        ln_Oder_dathanhcong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityAllOder.class);
                intent.putExtra("key1", "3");
                startActivity(intent);
            }
        });
    }
}
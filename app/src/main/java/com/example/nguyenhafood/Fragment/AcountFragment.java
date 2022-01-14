package com.example.nguyenhafood.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nguyenhafood.Activity.Acount_login.AccountActivity;
import com.example.nguyenhafood.Activity.Acount_login.CustomerSupportActivity;
import com.example.nguyenhafood.Activity.Acount_login.SettingActivity;
import com.example.nguyenhafood.Fragment.BaseFragment.BaseFragment;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.R;

public class AcountFragment extends BaseFragment {
    LinearLayout quanliDh, sanphamDM, sanphamDX, sanphamYT, sanphamDDG,sanphammuasau,join_ground,customer_support,setting;
    TextView txtAcount;
    TextView cart_badge;
    String linkgroup = "https://www.facebook.com/groups/791878421562215";
    String linkfb="https://www.facebook.com/nguyenhafood.vn.video";
    String linkgg="https://www.google.com/maps/place//data=!4m2!3m1!1s0x31752f2eb132daeb:0xd2b5280c8fbca374?source=g.page.m";
    String linkzalo="https://zaloshop.me/store?id=f692c631cf7826267f69";
    String twitter="https://twitter.com/nguyenhafood";
    String linkyb = "https://www.youtube.com/channel/UClpibeSzfocURe-6PqE_0bg?view_as=subscriber";
    String linkinsta = "https://www.instagram.com/nguyenhafood/";
    ImageView img_fb,img_gg,img_zalo,img_youtube,img_twitter,img_instagram;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_acount, container, false);
        txtAcount = view.findViewById(R.id.txtAcount);
        quanliDh  = view.findViewById(R.id.quanliDh);
        cart_badge=view.findViewById(R.id.cart_badge);
        sanphamDM = view.findViewById(R.id.sanphamDM);
        sanphamDX = view.findViewById(R.id.sanphamDX);
        sanphamYT = view.findViewById(R.id.sanphamYT);
        sanphamDDG = view.findViewById(R.id.sanphamDDG);
        sanphammuasau = view.findViewById(R.id.sanphammuasau);
        join_ground=view.findViewById(R.id.join_ground);
        customer_support=view.findViewById(R.id.customer_support);
        img_fb=view.findViewById(R.id.img_fb);
        img_gg=view.findViewById(R.id.img_gg);
        img_zalo=view.findViewById(R.id.img_zalo);
        img_youtube=view.findViewById(R.id.img_youtube);
        img_twitter=view.findViewById(R.id.img_twitter);
        img_instagram=view.findViewById(R.id.img_instagram);
        setting=view.findViewById(R.id.setting);

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(SettingActivity.class);
            }
        });
        customer_support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(CustomerSupportActivity.class);
            }
        });
        join_ground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToView_Link(linkgroup);
            }
        });
        sanphammuasau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AccountActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "Bạn hãy đăng nhập !!!", Toast.LENGTH_SHORT).show();
            }
        });
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

        txtAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AccountActivity.class);
                startActivity(intent);
            }
        });
        quanliDh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AccountActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "Bạn hãy đăng nhập ", Toast.LENGTH_SHORT).show();
            }
        });
        sanphamDM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AccountActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "Bạn hãy đăng nhập ", Toast.LENGTH_SHORT).show();
            }
        });
        sanphamDX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AccountActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "Bạn hãy đăng nhập ", Toast.LENGTH_SHORT).show();
            }
        });
        sanphamYT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AccountActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "Bạn hãy đăng nhập ", Toast.LENGTH_SHORT).show();
            }
        });
        sanphamDDG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AccountActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "Bạn hãy đăng nhập ", Toast.LENGTH_SHORT).show();
            }
        });
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        cart_badge.setText(String.valueOf(Gobal.SizeProduct));
    }
}

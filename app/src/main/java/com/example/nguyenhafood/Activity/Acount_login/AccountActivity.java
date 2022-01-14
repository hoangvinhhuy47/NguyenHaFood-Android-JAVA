package com.example.nguyenhafood.Activity.Acount_login;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.nguyenhafood.Apdater.Acount_Login.AddviewInViewpageApdater;
import com.example.nguyenhafood.Fragment.LoginFragment;
import com.example.nguyenhafood.Fragment.RegistraterFragment;
import com.example.nguyenhafood.R;
import com.google.android.material.tabs.TabLayout;

public class AccountActivity extends AppCompatActivity {
    private ViewPager mVpAccount;
    private TabLayout tablayout;
    ImageView backaccount;
    private LoginFragment mloginFragment;
    private RegistraterFragment mregistraterFragment;
    private AddviewInViewpageApdater addviewInViewpageApdater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        backaccount = findViewById(R.id.backaccount);
        backaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mVpAccount = findViewById(R.id.vp_Account);
        tablayout = findViewById(R.id.tablayout);

        mloginFragment = new LoginFragment();
        mregistraterFragment = new RegistraterFragment();
        tablayout.setupWithViewPager(mVpAccount);

        addviewInViewpageApdater = new AddviewInViewpageApdater(getSupportFragmentManager(), 0);
        addviewInViewpageApdater.addFragment(mloginFragment, "Đăng Nhâp");
        addviewInViewpageApdater.addFragment(mregistraterFragment, "Đăng ký");
        mVpAccount.setAdapter(addviewInViewpageApdater);
    }


}

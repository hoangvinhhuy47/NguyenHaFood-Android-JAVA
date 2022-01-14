package com.example.nguyenhafood.Activity.Acount_login;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.nguyenhafood.Activity.BaseActivity.BaseActivity;
import com.example.nguyenhafood.Activity.ViewCartsAcivity;
import com.example.nguyenhafood.Apdater.Acount_Login.TapAllOderAdapter;
import com.example.nguyenhafood.Apdater.Acount_Login.VP_AlloderAdapter;
import com.example.nguyenhafood.Fragment.SearchFragment;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Interface.IAllOder;
import com.example.nguyenhafood.Model.Acount_Login.DataTapAllOder;
import com.example.nguyenhafood.R;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;

public class ActivityAllOder extends BaseActivity implements IAllOder {
    ImageView iconbackalloder, view_cart_allorder;
    TextView cart_badge;
    ViewPager vp_QLdonhang;
    RecyclerView rcl_tapQLdonhang;
    TapAllOderAdapter tapAllOderAdapter;
    VP_AlloderAdapter pagerAdapter;
    SpinKitView spinkit_alloder;
    List<DataTapAllOder> dataTapAllOders = new ArrayList<>();
    LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
    Display display;
    Point size;
    int reselect = 0;
    int reselect1 = 0;
    RelativeLayout search;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_allorders);
        size = new Point();
        display = getWindowManager().getDefaultDisplay();
        display.getSize(size);
        search=findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(SearchFragment.class);
            }
        });
        iconbackalloder = findViewById(R.id.iconbackalloder);
        cart_badge = findViewById(R.id.cart_badge);
        cart_badge.setText(String.valueOf(Gobal.getSizeProduct()));
        spinkit_alloder = findViewById(R.id.spinkit_alloder);
        view_cart_allorder = findViewById(R.id.view_cart_allorder);
        view_cart_allorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), ViewCartsAcivity.class);
                startActivity(intent);
            }
        });
        iconbackalloder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        rcl_tapQLdonhang = findViewById(R.id.rcl_tapQLdonhang);
        vp_QLdonhang = findViewById(R.id.vp_QLdonhang);
        initTabsAdapter();
        setupViewPager(vp_QLdonhang);
        tapAllOderAdapter = new TapAllOderAdapter(dataTapAllOders, getApplication(), this, manager);
        rcl_tapQLdonhang.setLayoutManager(manager);
        rcl_tapQLdonhang.setAdapter(tapAllOderAdapter);
        vp_QLdonhang.addOnPageChangeListener(this);
        Intent intent = this.getIntent();
        for (int i = 0; i < 4; i++) {
            if (intent.getStringExtra("key1").equals(String.valueOf(i)) == true) {
                dataTapAllOders.get(i).setiSActive(true);

                vp_QLdonhang.setCurrentItem(i, true);
                break;
            }
            else {
                dataTapAllOders.get(i).setiSActive(false);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        cart_badge.setText(String.valueOf(Gobal.SizeProduct));
    }

    private void initTabsAdapter() {
        dataTapAllOders.add(new DataTapAllOder("Đang Xử Lý", false));
        dataTapAllOders.add(new DataTapAllOder("Đang vận chuyển", false));
        dataTapAllOders.add(new DataTapAllOder("Đã hủy", false));
        dataTapAllOders.add(new DataTapAllOder("Hoàn thành", false));

    }

    private void setupViewPager(ViewPager viewPager) {
        try {
            pagerAdapter = new VP_AlloderAdapter(getSupportFragmentManager());
            //adding dummy fragment to the viewpager
            for (int i = 0; i < 4; i++) {
                Bundle bundle = new Bundle();
                bundle.putInt("key", i);
                Fragment fragment = new SampleFragmentOneAlloder();
                fragment.setArguments(bundle);
                pagerAdapter.addFragment(fragment, "");
            }
            viewPager.setAdapter(pagerAdapter);
        } catch (Exception exception) {
        }
    }

    @Override
    public void onItemClick(int position) {
        tapAllOderAdapter = new TapAllOderAdapter(dataTapAllOders, getApplication(), this, manager);
        tapAllOderAdapter.notifyDataSetChanged();
        vp_QLdonhang.setCurrentItem(position, true);

    }

    @Override
    public void onItemClick1(int position) {
        if (position == 0 || position == 3) {
            rcl_tapQLdonhang.scrollToPosition(position);
            reselect = position;
        } else if (position > reselect) {
            rcl_tapQLdonhang.scrollToPosition(position + 1);
            reselect = position;
        } else {
            rcl_tapQLdonhang.scrollToPosition(position - 1);
            reselect = position;
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tapAllOderAdapter.setCurrentSelected(position);
        if (position == 0 || position == 3) {
            rcl_tapQLdonhang.scrollToPosition(position);
            reselect1 = position;
        } else if (position > reselect1) {
            rcl_tapQLdonhang.scrollToPosition(position + 1);
            reselect1 = position;
        } else {
            rcl_tapQLdonhang.scrollToPosition(position - 1);
            reselect1 = position;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
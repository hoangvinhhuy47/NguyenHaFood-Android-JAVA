package com.example.nguyenhafood.Activity.Acount_login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.nguyenhafood.API.APIClient;
import com.example.nguyenhafood.Activity.BaseActivity.BaseActivity;
import com.example.nguyenhafood.Activity.ViewCartsAcivity;
import com.example.nguyenhafood.Apdater.Acount_Login.ProductBuyAdapter;
import com.example.nguyenhafood.Database.DBHelper;
import com.example.nguyenhafood.Fragment.SearchFragment;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Model.Acount_Login.DataGetProductBuy;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.Acount_Login.GetProductBuyRequest;
import com.example.nguyenhafood.Response.Acount_Login.GetProductBuyResponse;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityProductBuy extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    DBHelper dbHelper;
    int pageindex = 1;
    TextView cart_badge;
    boolean isSrolling = false;
    RecyclerView recycle_product_buy;
    ProductBuyAdapter productBuyAdapter;
    List<DataGetProductBuy> datalist = new ArrayList<>();
    ImageView icon_backdamua;
    RelativeLayout see_cart_productbuy;
    LinearLayoutManager manager = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
    SpinKitView spin_kit_activity_productbuy;
    SwipeRefreshLayout refest_productbuy;
    RelativeLayout search;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productbuy);
        recycle_product_buy = findViewById(R.id.recycle_product_buy);
        icon_backdamua = findViewById(R.id.icon_backdamua);
        see_cart_productbuy = findViewById(R.id.see_cart_productbuy);
        cart_badge = findViewById(R.id.cart_badge);
        cart_badge.setText(String.valueOf(Gobal.SizeProduct));
        spin_kit_activity_productbuy = findViewById(R.id.spin_kit_activity_productbuy);
        refest_productbuy = findViewById(R.id.refest_productbuy);
        dbHelper = new DBHelper(getApplication());
        icon_backdamua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(SearchFragment.class);
            }
        });
        see_cart_productbuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), ViewCartsAcivity.class);
                startActivity(intent);
            }
        });
        GetProductBuyRequest getProductBuyRequest = new GetProductBuyRequest();
        getProductBuyRequest.setUserID(dbHelper.GetDL());
        getProductBuyRequest.setPageIndex(String.valueOf(pageindex));
        GetProductBuy(getProductBuyRequest);
        refest_productbuy.setColorSchemeResources(R.color.maucam, R.color.mauden, R.color.mautrang);
        refest_productbuy.setOnRefreshListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        cart_badge.setText(String.valueOf(Gobal.SizeProduct));
    }

    public void GetProductBuy(GetProductBuyRequest getProductBuyRequest) {
        Call<GetProductBuyResponse> getProductBuyResponseCall = APIClient.getProductBuySerVice().GetProductBuy(Gobal.GuiID, getProductBuyRequest);
        getProductBuyResponseCall.enqueue(new Callback<GetProductBuyResponse>() {
            @Override
            public void onResponse(Call<GetProductBuyResponse> call, Response<GetProductBuyResponse> response) {
                if (response.isSuccessful()) {
                    GetProductBuyResponse getProductBuyResponse = response.body();
                    if (getProductBuyResponse.getStatusID() == 1) {
                        isSrolling = false;
                        datalist.removeAll(datalist);
                        datalist.addAll(getProductBuyResponse.getProductBuyList());
                        productBuyAdapter = new ProductBuyAdapter(datalist, getApplication());
                        recycle_product_buy.setAdapter(productBuyAdapter);
                        recycle_product_buy.setLayoutManager(manager);
                        recycle_product_buy.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                                super.onScrollStateChanged(recyclerView, newState);
                                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                                    if (isSrolling == false) {
                                        if (manager != null || manager.findLastVisibleItemPosition() == datalist.size() - 1) {
                                            spin_kit_activity_productbuy.setVisibility(View.VISIBLE);
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    pageindex = pageindex + 1;
                                                    GetProductBuyRequest getProductBuyRequest1 = new GetProductBuyRequest();
                                                    getProductBuyRequest1.setPageIndex(String.valueOf(pageindex));
                                                    getProductBuyRequest1.setUserID(dbHelper.GetDL());
                                                    LoadMoreGetProductBuy(getProductBuyRequest1);
                                                    spin_kit_activity_productbuy.setVisibility(View.GONE);
                                                }
                                            }, 1200);
                                        }
                                    } else {
                                        isSrolling = true;
                                    }
                                }
                            }
                        });

                    } else {
                        Model.Dialog(getApplicationContext());
                    }
                }
            }

            @Override
            public void onFailure(Call<GetProductBuyResponse> call, Throwable t) {
                Model.Dialog(getApplicationContext());
            }
        });
    }

    public void LoadMoreGetProductBuy(GetProductBuyRequest getProductBuyRequest) {
        Call<GetProductBuyResponse> getProductBuyResponseCall = APIClient.getProductBuySerVice().GetProductBuy(Gobal.GuiID, getProductBuyRequest);
        getProductBuyResponseCall.enqueue(new Callback<GetProductBuyResponse>() {
            @Override
            public void onResponse(Call<GetProductBuyResponse> call, Response<GetProductBuyResponse> response) {
                if (response.isSuccessful()) {
                    GetProductBuyResponse getProductBuyResponse = response.body();
                    if (getProductBuyResponse.getStatusID() == 1) {
                        if (getProductBuyResponse.getProductBuyList().size() != 0) {
                            datalist.addAll(getProductBuyResponse.getProductBuyList());
                            productBuyAdapter.LoadMore(getProductBuyResponse.getProductBuyList());
//                            datalist.add(null);
//                            productBuyAdapter.notifyItemInserted(datalist.size() - 1);
//                            datalist.remove(datalist.size() - 1);
//                            datalist.addAll(getProductBuyResponse.getProductBuyList());
//                            productBuyAdapter = new ProductBuyAdapter(datalist, getApplication());
//                            recycle_product_buy.setLayoutManager(manager);
//                            recycle_product_buy.setAdapter(productBuyAdapter);
//                            recycle_product_buy.setHasFixedSize(true);

                        } else {
                            isSrolling = true;
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<GetProductBuyResponse> call, Throwable t) {
                Model.Dialog(getApplicationContext());
            }
        });
    }

    @Override
    public void onRefresh() {
        datalist = new ArrayList<>();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pageindex = 1;
                GetProductBuyRequest getProductBuyRequest = new GetProductBuyRequest();
                getProductBuyRequest.setUserID(dbHelper.GetDL());
                getProductBuyRequest.setPageIndex(String.valueOf(pageindex));
                GetProductBuy(getProductBuyRequest);
                cart_badge.setText(String.valueOf(Gobal.SizeProduct));
                refest_productbuy.setRefreshing(false);
            }
        }, 2500);
    }
}

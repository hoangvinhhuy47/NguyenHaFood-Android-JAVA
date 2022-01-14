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
import com.example.nguyenhafood.Apdater.Acount_Login.GetProductLaterBuyAdapter;
import com.example.nguyenhafood.Database.DBHelper;
import com.example.nguyenhafood.Fragment.SearchFragment;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Interface.UpdateProductbuylateView;
import com.example.nguyenhafood.Model.Home.DataProductViewList;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.Acount_Login.GetProductLaterBuyRequest;
import com.example.nguyenhafood.Response.Acount_Login.GetProductLaterBuyResponse;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityProductlater extends BaseActivity implements UpdateProductbuylateView {
    ImageView iconback_muasau;
    GetProductLaterBuyAdapter getProductLaterBuyAdapter;
    RecyclerView recycle_productbuylate_acountlogin;
    DBHelper dbHelper;
    boolean isSrolling = false;
    private int pagenumber = 1;
    List<DataProductViewList> dataProductViewLists = new ArrayList<>();
    LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
    TextView cart_badge;
    ImageView view_cart;
    SpinKitView spin_kit_activity_buylater;
    SwipeRefreshLayout refest_productlater;
    RelativeLayout search;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productspurchasedlater);
        dbHelper = new DBHelper(getApplication());
        view_cart = findViewById(R.id.view_cart);
        refest_productlater = findViewById(R.id.refest_productlater);
        spin_kit_activity_buylater = findViewById(R.id.spin_kit_activity_buylater);
        cart_badge = findViewById(R.id.cart_badge);
        cart_badge.setText(String.valueOf(Gobal.SizeProduct));
        iconback_muasau = findViewById(R.id.iconback_muasau);
        recycle_productbuylate_acountlogin = findViewById(R.id.recycle_productbuylate_acountlogin);
        iconback_muasau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        view_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), ViewCartsAcivity.class);
                startActivity(intent);
            }
        });
        refest_productlater.setColorSchemeResources(R.color.maucam, R.color.mauden, R.color.mautrang);
        refest_productlater.setOnRefreshListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        GetProductLaterBuyRequest getProductLaterBuyRequest = new GetProductLaterBuyRequest();
        getProductLaterBuyRequest.setPageIndex("1");
        getProductLaterBuyRequest.setUserID(dbHelper.GetDL());
        GetProductLaterBuy(getProductLaterBuyRequest);
        cart_badge.setText(String.valueOf(Gobal.SizeProduct));
    }

    public void GetProductLaterBuy(GetProductLaterBuyRequest getProductLaterBuyRequest) {
        Call<GetProductLaterBuyResponse> getProductLaterBuyResponseCall = APIClient.getProductLaterBuyService().GetProductLaterBuy(Gobal.GuiID, getProductLaterBuyRequest);
        getProductLaterBuyResponseCall.enqueue(new Callback<GetProductLaterBuyResponse>() {
            @Override
            public void onResponse(Call<GetProductLaterBuyResponse> call, Response<GetProductLaterBuyResponse> response) {
                if (response.isSuccessful()) {
                    GetProductLaterBuyResponse getProductLaterBuyResponse = response.body();
                    if (getProductLaterBuyResponse.getStatusID() == 1) {
                        dataProductViewLists.removeAll(dataProductViewLists);
                        dataProductViewLists.addAll(getProductLaterBuyResponse.getHomeData());
                        getProductLaterBuyAdapter = new GetProductLaterBuyAdapter(getProductLaterBuyResponse.getHomeData(), getApplication(), ActivityProductlater.this);
                        recycle_productbuylate_acountlogin.setAdapter(getProductLaterBuyAdapter);
                        recycle_productbuylate_acountlogin.setLayoutManager(linearLayoutManager2);
                        recycle_productbuylate_acountlogin.setHasFixedSize(true);
                        recycle_productbuylate_acountlogin.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                                super.onScrollStateChanged(recyclerView, newState);
                                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                                    if (isSrolling == false) {
                                        if (linearLayoutManager2 != null && linearLayoutManager2.findLastVisibleItemPosition() == dataProductViewLists.size() - 1) {

                                            spin_kit_activity_buylater.setVisibility(View.VISIBLE);
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    pagenumber = pagenumber + 1;
                                                    GetProductLaterBuyRequest getProductLaterBuyRequest1 = new GetProductLaterBuyRequest();
                                                    getProductLaterBuyRequest1.setUserID(dbHelper.GetDL());
                                                    getProductLaterBuyRequest1.setPageIndex(String.valueOf(pagenumber));
                                                    GetProductLaterBuyMore(getProductLaterBuyRequest1);
                                                    spin_kit_activity_buylater.setVisibility(View.GONE);
                                                }
                                            }, 1200);

                                        } else {
                                            isSrolling = true;
                                        }
                                    }
                                }
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<GetProductLaterBuyResponse> call, Throwable t) {
                Model.Dialog(getApplicationContext());
            }
        });
    }

    public void GetProductLaterBuyMore(GetProductLaterBuyRequest getProductLaterBuyRequest) {
        Call<GetProductLaterBuyResponse> getProductLaterBuyResponseCall = APIClient.getProductLaterBuyService().GetProductLaterBuy(Gobal.GuiID, getProductLaterBuyRequest);
        getProductLaterBuyResponseCall.enqueue(new Callback<GetProductLaterBuyResponse>() {
            @Override
            public void onResponse(Call<GetProductLaterBuyResponse> call, Response<GetProductLaterBuyResponse> response) {
                if (response.isSuccessful()) {
                    GetProductLaterBuyResponse getProductLaterBuyResponse = response.body();
                    if (getProductLaterBuyResponse.getStatusID() == 1) {
                        if (getProductLaterBuyResponse.getHomeData().size() != 0) {
                            getProductLaterBuyAdapter.LoadMore(getProductLaterBuyResponse.getHomeData());
                            dataProductViewLists.addAll(getProductLaterBuyResponse.getHomeData());
//                            dataProductViewLists.add(null);
//                            getProductLaterBuyAdapter.notifyItemInserted(dataProductViewLists.size() - 1);
//                            dataProductViewLists.remove(dataProductViewLists.size() - 1);
//                            dataProductViewLists.addAll(getProductLaterBuyResponse.getHomeData());
//                            getProductLaterBuyAdapter = new GetProductLaterBuyAdapter(dataProductViewLists, getApplication(), ActivityProductlater.this);
//                            recycle_productbuylate_acountlogin.setLayoutManager(linearLayoutManager2);
//                            recycle_productbuylate_acountlogin.setAdapter(getProductLaterBuyAdapter);
//                            recycle_productbuylate_acountlogin.setHasFixedSize(true);
//                            getProductLaterBuyAdapter.notifyDataSetChanged();
                        } else {
                            isSrolling = true;
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<GetProductLaterBuyResponse> call, Throwable t) {
                Model.Dialog(getApplicationContext());
            }
        });
    }

    @Override
    public void UpDateview(int position) {
        pagenumber = 1;
        dataProductViewLists.remove(position);
        getProductLaterBuyAdapter = new GetProductLaterBuyAdapter(dataProductViewLists, getApplication(), ActivityProductlater.this);
        getProductLaterBuyAdapter.notifyDataSetChanged();
        cart_badge.setText(String.valueOf(Gobal.SizeProduct));
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pagenumber = 1;
                isSrolling = false;
                GetProductLaterBuyRequest getProductLaterBuyRequest = new GetProductLaterBuyRequest();
                getProductLaterBuyRequest.setPageIndex("1");
                getProductLaterBuyRequest.setUserID(dbHelper.GetDL());
                GetProductLaterBuy(getProductLaterBuyRequest);
                cart_badge.setText(String.valueOf(Gobal.SizeProduct));
                refest_productlater.setRefreshing(false);
            }
        }, 2500);
    }
}
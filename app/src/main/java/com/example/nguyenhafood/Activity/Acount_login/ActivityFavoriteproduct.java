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
import com.example.nguyenhafood.Apdater.Acount_Login.ProductFavoriteAdpater;
import com.example.nguyenhafood.Database.DBHelper;
import com.example.nguyenhafood.Fragment.SearchFragment;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Interface.UpdateFavoriteView;
import com.example.nguyenhafood.Model.Acount_Login.DataProductReview;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.Acount_Login.GetProductFavoriteRequest;
import com.example.nguyenhafood.Response.Acount_Login.GetProductFavoriteResponse;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityFavoriteproduct extends BaseActivity implements UpdateFavoriteView {
    ImageView icon_backyeuthich;
    DBHelper dbHelper;
    int pageindex = 1;
    RecyclerView recycle_product_favorite;
    ProductFavoriteAdpater productFavoriteAdpater;
    boolean isSrolling = false;
    ImageView view_cart;
    TextView cart_badge;
    List<DataProductReview> datalist = new ArrayList<>();
    LinearLayoutManager manager = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
    SpinKitView spin_kit_activity_favorite;
    SwipeRefreshLayout refest_favorite;
    RelativeLayout search;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoriteproduct);
        cart_badge = findViewById(R.id.cart_badge);
        cart_badge.setText(String.valueOf(Gobal.SizeProduct));
        refest_favorite = findViewById(R.id.refest_favorite);
        spin_kit_activity_favorite = findViewById(R.id.spin_kit_activity_favorite);
        dbHelper = new DBHelper(getApplication());
        icon_backyeuthich = findViewById(R.id.icon_backyeuthich);
        recycle_product_favorite = findViewById(R.id.recycle_product_favorite);
        icon_backyeuthich.setOnClickListener(new View.OnClickListener() {
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
        view_cart = findViewById(R.id.view_cart);
        view_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), ViewCartsAcivity.class);
                startActivity(intent);
            }
        });
        refest_favorite.setColorSchemeResources(R.color.maucam, R.color.mauden, R.color.mautrang);
        refest_favorite.setOnRefreshListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        pageindex = 1;
        GetProductFavoriteRequest getProductFavoriteRequest = new GetProductFavoriteRequest();
        getProductFavoriteRequest.setUserID(dbHelper.GetDL());
        getProductFavoriteRequest.setPageIndex(String.valueOf(pageindex));
        GetProductViewList(getProductFavoriteRequest);
        cart_badge.setText(String.valueOf(Gobal.SizeProduct));
    }

    public void GetProductViewList(GetProductFavoriteRequest getProductFavoriteRequest) {
        Call<GetProductFavoriteResponse> getProductFavoriteResponseCall = APIClient.getProductFavoriteSerVice().GetProductFavorite(Gobal.GuiID, getProductFavoriteRequest);
        getProductFavoriteResponseCall.enqueue(new Callback<GetProductFavoriteResponse>() {
            @Override
            public void onResponse(Call<GetProductFavoriteResponse> call, Response<GetProductFavoriteResponse> response) {
                if (response.isSuccessful()) {
                    GetProductFavoriteResponse getProductFavoriteResponse = response.body();
                    if (getProductFavoriteResponse.getStatusID() == 1) {
                        productFavoriteAdpater = new ProductFavoriteAdpater(getProductFavoriteResponse.getProductFavoriteList(), getApplication(), ActivityFavoriteproduct.this);
                        datalist.addAll(getProductFavoriteResponse.getProductFavoriteList());
                        recycle_product_favorite.setAdapter(productFavoriteAdpater);
                        recycle_product_favorite.setLayoutManager(manager);
                        recycle_product_favorite.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                                super.onScrollStateChanged(recyclerView, newState);
                                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                                    if (isSrolling == false) {
                                        if (manager != null || manager.findLastVisibleItemPosition() == datalist.size() - 1) {

                                            spin_kit_activity_favorite.setVisibility(View.VISIBLE);
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    pageindex = pageindex + 1;
                                                    GetProductFavoriteRequest getProductFavoriteRequest = new GetProductFavoriteRequest();
                                                    getProductFavoriteRequest.setPageIndex(String.valueOf(pageindex));
                                                    getProductFavoriteRequest.setUserID(dbHelper.GetDL());
                                                    GetProductViewListMore(getProductFavoriteRequest);
                                                    spin_kit_activity_favorite.setVisibility(View.GONE);
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
            public void onFailure(Call<GetProductFavoriteResponse> call, Throwable t) {

            }
        });
    }

    public void GetProductViewListMore(GetProductFavoriteRequest getProductFavoriteRequest) {
        Call<GetProductFavoriteResponse> getProductFavoriteResponseCall = APIClient.getProductFavoriteSerVice().GetProductFavorite(Gobal.GuiID, getProductFavoriteRequest);
        getProductFavoriteResponseCall.enqueue(new Callback<GetProductFavoriteResponse>() {
            @Override
            public void onResponse(Call<GetProductFavoriteResponse> call, Response<GetProductFavoriteResponse> response) {
                if (response.isSuccessful()) {
                    GetProductFavoriteResponse getProductFavoriteResponse = response.body();
                    if (getProductFavoriteResponse.getProductFavoriteList().size() != 0) {
                        datalist.addAll(getProductFavoriteResponse.getProductFavoriteList());
                        productFavoriteAdpater.update(getProductFavoriteResponse.getProductFavoriteList());
//                        datalist.add(null);
//                        productFavoriteAdpater.notifyItemInserted(datalist.size() - 1);
//                        datalist.remove(datalist.size() - 1);
//                        datalist.addAll(getProductFavoriteResponse.getProductFavoriteList());
//                        productFavoriteAdpater = new ProductFavoriteAdpater(datalist, getApplication(), ActivityFavoriteproduct.this);
//                        recycle_product_favorite.setLayoutManager(manager);
//                        recycle_product_favorite.setAdapter(productFavoriteAdpater);
//                        recycle_product_favorite.setHasFixedSize(true);
                    } else {
                        isSrolling = true;
                        pageindex = 1;
                    }
                }
            }

            @Override
            public void onFailure(Call<GetProductFavoriteResponse> call, Throwable t) {
                Model.Dialog(getApplicationContext());
            }
        });
    }

    @Override
    public void UpDateview(int position) {
        pageindex = 1;
        datalist.remove(position);
        productFavoriteAdpater = new ProductFavoriteAdpater(datalist, getApplication(), this);
        productFavoriteAdpater.notifyDataSetChanged();


    }

    @Override
    public void UpdateSize(int position1) {
        productFavoriteAdpater = new ProductFavoriteAdpater(datalist, getApplication(), this);
        productFavoriteAdpater.notifyDataSetChanged();
        cart_badge.setText(String.valueOf(Gobal.SizeProduct));
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pageindex = 1;
                isSrolling = false;
                GetProductFavoriteRequest getProductFavoriteRequest = new GetProductFavoriteRequest();
                getProductFavoriteRequest.setUserID(dbHelper.GetDL());
                getProductFavoriteRequest.setPageIndex(String.valueOf(pageindex));
                GetProductViewList(getProductFavoriteRequest);
                cart_badge.setText(String.valueOf(Gobal.SizeProduct));
                refest_favorite.setRefreshing(false);
            }
        }, 2500);
    }
}


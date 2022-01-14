package com.example.nguyenhafood.Activity.Acount_login;

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
import com.example.nguyenhafood.Apdater.Acount_Login.ProductViewAdapter;
import com.example.nguyenhafood.Database.DBHelper;
import com.example.nguyenhafood.Fragment.SearchFragment;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Interface.UpdateProductView;
import com.example.nguyenhafood.Model.Acount_Login.DataProductReview;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.Acount_Login.GetProductViewRequest;
import com.example.nguyenhafood.Response.Acount_Login.GetProductViewResponse;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//com.softwareviet.tamphuc
public class ActivityViewproduct extends BaseActivity implements UpdateProductView {
    ImageView icon_backdaxem;
    DBHelper dbHelper;
    int pageindex = 1;
    RecyclerView recycle_product_review;
    ProductViewAdapter productViewAdapter;
    boolean isSrolling = false;
    List<DataProductReview> datalist = new ArrayList<>();
    ImageView view_cart;
    TextView cart_badge;
    LinearLayoutManager manager1 = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
    SpinKitView spin_kit_activity_productview;
    SwipeRefreshLayout refest_viewedproduct;
    RelativeLayout search;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewedproducts);
        dbHelper = new DBHelper(getApplication());
        icon_backdaxem = findViewById(R.id.icon_backdaxem);
        refest_viewedproduct = findViewById(R.id.refest_viewedproduct);
        cart_badge = findViewById(R.id.cart_badge);
        cart_badge.setText(String.valueOf(Gobal.SizeProduct));
        spin_kit_activity_productview = findViewById(R.id.spin_kit_activity_productview);
        recycle_product_review = findViewById(R.id.recycle_product_review);
        view_cart = findViewById(R.id.view_cart);
        search = findViewById(R.id.search);
        icon_backdaxem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        view_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               goToActivity(ViewCartsAcivity.class);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(SearchFragment.class);
            }
        });
        GetProductViewRequest getProductViewRequest = new GetProductViewRequest();
        getProductViewRequest.setUserID(dbHelper.GetDL());
        getProductViewRequest.setPageIndex(String.valueOf(pageindex));
        GetProductViewList(getProductViewRequest);
        refest_viewedproduct.setColorSchemeResources(R.color.maucam, R.color.mauden, R.color.maucam);
        refest_viewedproduct.setOnRefreshListener(this);
    }

    public void GetProductViewList(GetProductViewRequest getProductViewRequest) {
        Call<GetProductViewResponse> getProductViewResponseCall = APIClient.getProductViewSerVice().GetProDuctView(Gobal.GuiID, getProductViewRequest);
        getProductViewResponseCall.enqueue(new Callback<GetProductViewResponse>() {
            @Override
            public void onResponse(Call<GetProductViewResponse> call, Response<GetProductViewResponse> response) {
                if (response.isSuccessful()) {
                    GetProductViewResponse getProductViewResponse = response.body();
                    if (getProductViewResponse.getStatusID() == 1) {
                        datalist.addAll(getProductViewResponse.getProductViewList());
                        productViewAdapter = new ProductViewAdapter(datalist, getApplication(), ActivityViewproduct.this);
                        recycle_product_review.setAdapter(productViewAdapter);
                        recycle_product_review.setLayoutManager(manager1);
                        recycle_product_review.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                                super.onScrollStateChanged(recyclerView, newState);
                                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                                    if (isSrolling == false) {
                                        if (manager1 != null || manager1.findLastVisibleItemPosition() == datalist.size() - 1) {
                                            spin_kit_activity_productview.setVisibility(View.VISIBLE);
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    pageindex = pageindex + 1;
                                                    GetProductViewRequest getProductViewRequest1 = new GetProductViewRequest();
                                                    getProductViewRequest1.setPageIndex(String.valueOf(pageindex));
                                                    getProductViewRequest1.setUserID(dbHelper.GetDL());
                                                    GetProductViewListMore(getProductViewRequest1);
                                                    spin_kit_activity_productview.setVisibility(View.GONE);
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
            public void onFailure(Call<GetProductViewResponse> call, Throwable t) {
                Model.Dialog(getApplicationContext());
            }
        });
    }

    public void GetProductViewListMore(GetProductViewRequest getProductViewRequest) {
        Call<GetProductViewResponse> getProductViewResponseCall = APIClient.getProductViewSerVice().GetProDuctView(Gobal.GuiID, getProductViewRequest);
        getProductViewResponseCall.enqueue(new Callback<GetProductViewResponse>() {
            @Override
            public void onResponse(Call<GetProductViewResponse> call, Response<GetProductViewResponse> response) {
                if (response.isSuccessful()) {
                    GetProductViewResponse getProductViewResponse = response.body();
                    if (getProductViewResponse.getStatusID() == 1) {
                        if (getProductViewResponse.getProductViewList().size() != 0) {
                            datalist.addAll(getProductViewResponse.getProductViewList());
                            productViewAdapter.LoadMore(getProductViewResponse.getProductViewList());

                        } else {
                            isSrolling = true;
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<GetProductViewResponse> call, Throwable t) {
                Model.Dialog(getApplicationContext());
            }
        });
    }

    @Override
    public void UpDateview(int position) {
        productViewAdapter = new ProductViewAdapter(datalist, getApplicationContext(), ActivityViewproduct.this);
        productViewAdapter.notifyDataSetChanged();
        cart_badge.setText(String.valueOf(Gobal.SizeProduct));

    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pageindex = 1;
                isSrolling = false;
                GetProductViewRequest getProductViewRequest = new GetProductViewRequest();
                getProductViewRequest.setUserID(dbHelper.GetDL());
                getProductViewRequest.setPageIndex(String.valueOf(pageindex));
                GetProductViewList(getProductViewRequest);
                refest_viewedproduct.setRefreshing(false);
            }
        }, 1000);
    }
}


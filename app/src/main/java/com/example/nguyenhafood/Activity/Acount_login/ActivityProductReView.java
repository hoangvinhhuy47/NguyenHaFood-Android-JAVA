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
import com.example.nguyenhafood.Apdater.Acount_Login.ProductReviewAdapter;
import com.example.nguyenhafood.Database.DBHelper;
import com.example.nguyenhafood.Fragment.SearchFragment;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Interface.UpdateProductView;
import com.example.nguyenhafood.Model.Acount_Login.DataProductReview;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.Acount_Login.GetProductReViewRequest;
import com.example.nguyenhafood.Response.Acount_Login.GetProductReViewResponse;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityProductReView extends BaseActivity implements UpdateProductView {
    RecyclerView recycle_productlistreivew_acountlogin;
    DBHelper dbHelper;
    ProductReviewAdapter productReviewAdapter;
    List<DataProductReview> dataProductReviews = new ArrayList<>();
    LinearLayoutManager manager1 = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
    private int pageindex = 1;
    private boolean isScrolling = false;
    SpinKitView spin_kit_activity_review;
    SwipeRefreshLayout refest_producrreview;
    TextView cart_badge;
    ImageView view_cart, iconback_productreview;
    RelativeLayout search;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productlistreview);
        recycle_productlistreivew_acountlogin = findViewById(R.id.recycle_productlistreivew_acountlogin);
        spin_kit_activity_review = findViewById(R.id.spin_kit_activity_review);
        iconback_productreview = findViewById(R.id.iconback_productreview);
        refest_producrreview = findViewById(R.id.refest_producrreview);
        cart_badge = findViewById(R.id.cart_badge);
        view_cart = findViewById(R.id.view_cart);
        dbHelper = new DBHelper(getApplication());
        GetProductReViewRequest getProductReViewRequest = new GetProductReViewRequest();
        getProductReViewRequest.setPageIndex(String.valueOf(pageindex));
        getProductReViewRequest.setUserID(dbHelper.GetDL());
        GetProductReView(getProductReViewRequest);
        refest_producrreview.setColorSchemeResources(R.color.maucam, R.color.mauden, R.color.mautrang);
        refest_producrreview.setOnRefreshListener(this);
        cart_badge.setText(String.valueOf(Gobal.SizeProduct));
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
                goToActivity(ViewCartsAcivity.class);
            }
        });
        iconback_productreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        cart_badge.setText(String.valueOf(Gobal.SizeProduct));
    }

    public void GetProductReView(GetProductReViewRequest getProductReViewRequest) {
        Call<GetProductReViewResponse> getProductReViewResponseCall = APIClient.getProductReViewSerVice().GetProductReView(Gobal.GuiID, getProductReViewRequest);
        getProductReViewResponseCall.enqueue(new Callback<GetProductReViewResponse>() {
            @Override
            public void onResponse(Call<GetProductReViewResponse> call, Response<GetProductReViewResponse> response) {
                if (response.isSuccessful()) {
                    GetProductReViewResponse reponse = response.body();
                    if (reponse.getStatusID() == 1) {
                        pageindex = 1;
                        isScrolling = false;
                        dataProductReviews.removeAll(dataProductReviews);
                        dataProductReviews.addAll(reponse.getProductReViewList());
                        productReviewAdapter = new ProductReviewAdapter(dataProductReviews, getApplication(), ActivityProductReView.this);
                        recycle_productlistreivew_acountlogin.setAdapter(productReviewAdapter);
                        recycle_productlistreivew_acountlogin.setLayoutManager(manager1);
                        recycle_productlistreivew_acountlogin.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                                super.onScrollStateChanged(recyclerView, newState);
                                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                                    if (isScrolling == false) {
                                        if (manager1 != null || manager1.findLastVisibleItemPosition() == dataProductReviews.size() - 1) {
                                            spin_kit_activity_review.setVisibility(View.VISIBLE);
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    pageindex = pageindex + 1;
                                                    GetProductReViewRequest getProductReViewRequest1 = new GetProductReViewRequest();
                                                    getProductReViewRequest1.setUserID(dbHelper.GetDL());
                                                    getProductReViewRequest1.setPageIndex(String.valueOf(pageindex));
                                                    LoadMoreProductReview(getProductReViewRequest1);
                                                    spin_kit_activity_review.setVisibility(View.GONE);
                                                }
                                            }, 1200);

                                        } else {
                                            isScrolling = true;
                                        }

                                    }
                                }
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<GetProductReViewResponse> call, Throwable t) {
                Model.Dialog(getApplicationContext());
            }
        });
    }

    public void LoadMoreProductReview(GetProductReViewRequest getProductReViewRequest) {
        Call<GetProductReViewResponse> getProductReViewResponseCall = APIClient.getProductReViewSerVice().GetProductReView(Gobal.GuiID, getProductReViewRequest);
        getProductReViewResponseCall.enqueue(new Callback<GetProductReViewResponse>() {
            @Override
            public void onResponse(Call<GetProductReViewResponse> call, Response<GetProductReViewResponse> response) {
                if (response.isSuccessful()) {
                    GetProductReViewResponse reponse = response.body();
                    if (reponse.getStatusID() == 1) {
                        if (reponse.getProductReViewList().size() != 0) {
                            productReviewAdapter.LoadMore(reponse.getProductReViewList());
                            dataProductReviews.addAll(reponse.getProductReViewList());
//                            dataProductReviews.add(null);
//                            productReviewAdapter.notifyItemInserted(dataProductReviews.size() - 1);
//                            dataProductReviews.remove(dataProductReviews.size() - 1);
//                            dataProductReviews.addAll(reponse.getProductReViewList());
//                            productReviewAdapter = new ProductReviewAdapter(dataProductReviews, getApplication(),ActivityProductReView.this);
//                            recycle_productlistreivew_acountlogin.setLayoutManager(manager1);
//                            recycle_productlistreivew_acountlogin.setAdapter(productReviewAdapter);
//                            recycle_productlistreivew_acountlogin.setHasFixedSize(true);
                        } else {
                            isScrolling = true;

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<GetProductReViewResponse> call, Throwable t) {
                Model.Dialog(getApplicationContext());
            }
        });
    }

    @Override
    public void UpDateview(int position) {
        productReviewAdapter = new ProductReviewAdapter(dataProductReviews, getApplication(), ActivityProductReView.this);
        productReviewAdapter.notifyDataSetChanged();
        cart_badge.setText(String.valueOf(Gobal.SizeProduct));
    }

    @Override
    public void onRefresh() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                GetProductReViewRequest getProductReViewRequest = new GetProductReViewRequest();
                getProductReViewRequest.setUserID(dbHelper.GetDL());
                getProductReViewRequest.setPageIndex("1");
                GetProductReView(getProductReViewRequest);
                cart_badge.setText(String.valueOf(Gobal.SizeProduct));
                refest_producrreview.setRefreshing(false);
            }
        }, 2500);
    }
}

package com.example.nguyenhafood.Activity.Notification;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.nguyenhafood.API.APIClient;
import com.example.nguyenhafood.Activity.BaseActivity.BaseActivity;
import com.example.nguyenhafood.Activity.ViewCartsAcivity;
import com.example.nguyenhafood.Fragment.SearchFragment;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.Home.GetNewsDetailRequest;
import com.example.nguyenhafood.Response.Home.GetNewsDetailResponse;
import com.example.nguyenhafood.Untils.Model;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityNotificationDetail extends BaseActivity {
    Intent intent;
    ImageView btn_back_newsdetail, img_news;
    RelativeLayout see_cart_newsdetail,search;
    TextView caption_notifinewsdetail, viewcount_notification_detail, date_notifinews_detail;
    WebView longder_notification;
    String style = "<style>img{display: inline;height:auto;max-width: 100%; max-height:350} iframe{display: inline;height: 300;max-width: 100%;min-width:100%; max-height:100%} p{font-weight:normal;font-family: Arial, Helvetica, sans-serif} #toc{display: inline;max-width: 100%; margin: 20px 0px !important}}</style>";
    ScrollView scl_orderdetail;
    ShimmerFrameLayout shimmerFrameLayoutorderdetail;
    TextView cart_badge;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_detail);
        intent = this.getIntent();
        btn_back_newsdetail = findViewById(R.id.btn_back_newsdetail);
        scl_orderdetail = findViewById(R.id.scl_orderdetail);
        shimmerFrameLayoutorderdetail=findViewById(R.id.shimmerFrameLayoutorderdetail);
        cart_badge=findViewById(R.id.cart_badge);
        shimmerFrameLayoutorderdetail.setVisibility(View.VISIBLE);
        shimmerFrameLayoutorderdetail.startShimmer();
        scl_orderdetail.setVisibility(View.GONE);
        btn_back_newsdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        img_news = findViewById(R.id.img_news);
        see_cart_newsdetail = findViewById(R.id.see_cart_newsdetail);
        search = findViewById(R.id.search);
        caption_notifinewsdetail = findViewById(R.id.caption_notifinewsdetail);
        viewcount_notification_detail = findViewById(R.id.viewcount_notification_detail);
        date_notifinews_detail = findViewById(R.id.date_notifinews_detail);
        longder_notification = findViewById(R.id.longder_notification);
        longder_notification.requestFocus();
        longder_notification.getSettings().setLightTouchEnabled(true);
        longder_notification.getSettings().setJavaScriptEnabled(true);
        longder_notification.getSettings().setGeolocationEnabled(true);
        longder_notification.setSoundEffectsEnabled(true);
        longder_notification.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        ///Get dữ liệu
        GetNewsDetailRequest getNewsDetailRequest = new GetNewsDetailRequest();
        getNewsDetailRequest.setNewsID(intent.getStringExtra("NewsID"));
        if (getNewsDetailRequest.getNewsID() != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    GetNewsDetail(getNewsDetailRequest);
                }
            }, 700);

        } else {
            Model.Dialog(getApplicationContext());
        }
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(SearchFragment.class);
            }
        });
        see_cart_newsdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(ViewCartsAcivity.class);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        cart_badge.setText(String.valueOf(Gobal.SizeProduct));
    }

    public void GetNewsDetail(GetNewsDetailRequest getNewsDetailRequest){
        Call<GetNewsDetailResponse> getNewsDetailResponseCall = APIClient.getNewsDetailService().GetNewsDetail(Gobal.GuiID, getNewsDetailRequest);
        getNewsDetailResponseCall.enqueue(new Callback<GetNewsDetailResponse>() {
            @Override
            public void onResponse(Call<GetNewsDetailResponse> call, Response<GetNewsDetailResponse> response) {
                if (response.isSuccessful()){
                    GetNewsDetailResponse getNewsDetailResponse = response.body();
                    if (getNewsDetailResponse.getStatusID() == 1){
                        caption_notifinewsdetail.setText(getNewsDetailResponse.getNews().getNewsTitle());
                        viewcount_notification_detail.setText(String.valueOf(getNewsDetailResponse.getNews().getViewCount()));
                        date_notifinews_detail.setText(Model.convertDateToStringnotifi(Model.convertStringToDate(getNewsDetailResponse.getNews().getPublishDate())));
                        Picasso.with(getApplication())
                                .load(Gobal.IDImage + getNewsDetailResponse.getNews().getImage())
                                .fit().centerCrop()
                                .error(R.drawable.imageerro)
                                .into(img_news);
                        longder_notification.loadDataWithBaseURL(null, getNewsDetailResponse.getNews().getLongDescription() + style, "text/HTML", "UTF-8", null);
                        shimmerFrameLayoutorderdetail.setVisibility(View.GONE);
                        scl_orderdetail.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetNewsDetailResponse> call, Throwable t) {
                Model.Dialog(getApplicationContext());
            }
        });
    }
}

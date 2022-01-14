package com.example.nguyenhafood.Home;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.nguyenhafood.API.APIClient;
import com.example.nguyenhafood.Fragment.MainActivity;
import com.example.nguyenhafood.Activity.ViewCartsAcivity;
import com.example.nguyenhafood.Apdater.Imfomation.ProductRelationListAdapter;
import com.example.nguyenhafood.Apdater.Imfomation.WebItemPhotoListAdapter;
import com.example.nguyenhafood.Apdater.Imfomation.WebItemRelationAdapter;
import com.example.nguyenhafood.Database.DBHelper;
import com.example.nguyenhafood.Fragment.SearchFragment;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Interface.UpdateImage;
import com.example.nguyenhafood.Model.ImfomationProduct.DataWebItemRelation;
import com.example.nguyenhafood.Model.ImfomationProduct.ProductRelationList;
import com.example.nguyenhafood.Model.ImfomationProduct.WebItemPhotoList;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.Acount_Login.AddRemoveFavoriteRequest;
import com.example.nguyenhafood.Request.ViewCart.AddToCardRequest;
import com.example.nguyenhafood.Request.Acount_Login.GetMoreItemRelationRequest;
import com.example.nguyenhafood.Request.Home.GetMoreProductOnCategoryRequest;
import com.example.nguyenhafood.Request.Acount_Login.GetProductDetailRequest;
import com.example.nguyenhafood.Response.Acount_Login.AddRemoveFavoriteReponse;
import com.example.nguyenhafood.Response.Acount_Login.GetMoreItemRelationResponse;
import com.example.nguyenhafood.Response.Home.GetMoreProductOnCategoryResponse;
import com.example.nguyenhafood.Response.Order.GetProductDetailResponse;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.github.ybq.android.spinkit.SpinKitView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class   ImfomationProductActivity extends AppCompatActivity implements UpdateImage {
    WebView motasanpham;
    ImageView imgae_product, back;
    ShimmerFrameLayout shimmerFrameLayout;
    TextView price_product, saleprice, specialPrice;
    Intent intent;
    Button vietdanhgia, add_product_tocart;
    TextView name_product;
    DBHelper db;
    boolean ShowIMG = false;
    List<WebItemPhotoList> webItemPhotoLists = new ArrayList<>();
    RecyclerView rcy_prodcut_similar, rcl_image_prodcut_imfomation, rcl_webitem_relation;
    ProductRelationListAdapter productRelationListAdapter;
    WebItemPhotoListAdapter webItemPhotoListAdapter;
    List<ProductRelationList> lst_product_relation = new ArrayList<>();
    TextView fulladdress_user, txt_reviewvalue_imfomation, txt_review_imfomation;
    RatingBar review_star_imfomation;
    RelativeLayout rlv_relationproduct;
    boolean iScronling = false;
    boolean iScronling1 = false;
    ProgressBar progressbar;
    int pagenumber = 1;
    int pagenumber1 = 1;
    TextView review_count5, review_count4, review_count3, review_count2, review_count1, seeall_txtchiitet, top18;
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplication(), LinearLayoutManager.HORIZONTAL, false);
    LinearLayoutManager lner_webrelation = new LinearLayoutManager(getApplication(), LinearLayoutManager.HORIZONTAL, false);
    RelativeLayout rlv_webview;
    ImageView addremovefavorite, see_search_imfomationproduct, back_home_imfomationproduct, see_cart_imfomationproduct, menu_imfomationproduct;
    String style = "<style>img{display: inline;height:auto;max-width: 100%; max-height:350} iframe{display: inline;height: 300;max-width: 100%;min-width:100%; max-height:100%} p{font-weight:normal;font-family: Arial, Helvetica, sans-serif} #toc{display: inline;max-width: 100%; margin: 20px 0px !important}}</style>";
    LinearLayout review_product;
    ScrollView srl_imfomation_product;
    SpinKitView spin_kit_activity_imfomation;
    ProgressBar review_value1, review_value2, review_value3, review_value4, review_value5;
    public static int WRAP_CONTENT;
    List<DataWebItemRelation> productListItemMenuAdapter = new ArrayList<>();
    WebItemRelationAdapter webItemRelationAdapter;
    SwipeRefreshLayout refest_imfomation;
    ProgressBar processbar_addfavorite;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = this.getIntent();
        setContentView(R.layout.activity_seeproduct);
        top18 = findViewById(R.id.top18);
        processbar_addfavorite=findViewById(R.id.processbar_addfavorite);
        refest_imfomation = findViewById(R.id.refest_imfomation);
        seeall_txtchiitet = findViewById(R.id.seeall_txtchiitet);
        srl_imfomation_product = findViewById(R.id.srl_imfomation_product);
        rlv_webview = findViewById(R.id.rlv_webview);
        spin_kit_activity_imfomation = findViewById(R.id.spin_kit_activity_imfomation);
        review_value1 = findViewById(R.id.review_value1);
        review_value2 = findViewById(R.id.review_value2);
        review_value3 = findViewById(R.id.review_value3);
        review_value4 = findViewById(R.id.review_value4);
        review_value5 = findViewById(R.id.review_value5);
        rcl_webitem_relation = findViewById(R.id.rcl_webitem_relation);
        review_count5 = findViewById(R.id.review_count5);
        review_count4 = findViewById(R.id.review_count4);
        review_count3 = findViewById(R.id.review_count3);
        review_count2 = findViewById(R.id.review_count2);
        review_count1 = findViewById(R.id.review_count1);
        review_product = findViewById(R.id.review_product);
        review_star_imfomation = findViewById(R.id.review_star_imfomation);
        txt_reviewvalue_imfomation = findViewById(R.id.txt_reviewvalue_imfomation);
        txt_review_imfomation = findViewById(R.id.txt_review_imfomation);
        fulladdress_user = findViewById(R.id.fulladdress_user);
        shimmerFrameLayout = findViewById(R.id.shimmerFrameLayout123);
        rlv_relationproduct = findViewById(R.id.rlv_relationproduct);
        rcl_image_prodcut_imfomation = findViewById(R.id.rcl_image_prodcut_imfomation);
        rcy_prodcut_similar = findViewById(R.id.rcy_prodcut_similar);
        menu_imfomationproduct = findViewById(R.id.menu_imfomationproduct);
        see_search_imfomationproduct = findViewById(R.id.see_search_imfomationproduct);
        back_home_imfomationproduct = findViewById(R.id.back_home_imfomationproduct);
        see_cart_imfomationproduct = findViewById(R.id.see_cart_imfomationproduct);
        shimmerFrameLayout.startShimmer();
        srl_imfomation_product.setVisibility(View.GONE);
        progressbar = findViewById(R.id.progressbar);
        db = new DBHelper(getApplication());
        seeall_txtchiitet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);
                lp.addRule(RelativeLayout.BELOW, top18.getId());
                rlv_webview.setLayoutParams(lp);
                srl_imfomation_product.smoothScrollTo(Integer.parseInt(String.valueOf(top18.getScrollY() + 800)), 2000);
                seeall_txtchiitet.setVisibility(View.GONE);
            }
        });
        see_search_imfomationproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), SearchFragment.class);
                i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
            }
        });
        see_cart_imfomationproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), ViewCartsAcivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
            }
        });
        back_home_imfomationproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
            }
        });

        menu_imfomationproduct.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), menu_imfomationproduct);
                popupMenu.inflate(R.menu.menu_imfomation_product);
               // popupMenu.setForceShowIcon(true);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.back_home:
                                Intent i = new Intent(getApplication(), MainActivity.class);
                                Gobal.ShowFragment = "Home";
                                i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivity(i);
                                break;
                            case R.id.menu_product:
                                Intent i1 = new Intent(getApplication(), MainActivity.class);
                                Gobal.ShowFragment = "Menu";
                                i1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivity(i1);
                                break;
                            case R.id.acount:
                                Intent i2 = new Intent(getApplication(), MainActivity.class);
                                Gobal.ShowFragment = "Acount";
                                i2.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivity(i2);
                                break;
                            case R.id.share:
                                Intent i3 = new Intent(Intent.ACTION_SEND);
                                i3.setType("text/plain");
                                i3.putExtra(Intent.EXTRA_SUBJECT, "Sản Phẩm");
                                i3.putExtra(Intent.EXTRA_TEXT, Gobal.WebDomain + "detail/xuc-xich-heo-mini-nipponham-200g-1337.html");
                                startActivity(Intent.createChooser(i3, "Chia Sẽ sản Phẩm"));
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
        imgae_product = findViewById(R.id.imgae_product);
        imgae_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showimage(ImfoGobal.getIDImage());
            }
        });
        name_product = findViewById(R.id.name_product);
        saleprice = findViewById(R.id.saleprice);
        motasanpham = findViewById(R.id.txt_chitiet);
        addremovefavorite = findViewById(R.id.addremovefavorite);
        specialPrice = findViewById(R.id.specialPrice);
        price_product = findViewById(R.id.price_product);
        vietdanhgia = findViewById(R.id.vietdanhgia);
        back = findViewById(R.id.back);
        add_product_tocart = findViewById(R.id.add_product_tocart);
        add_product_tocart.setVisibility(View.GONE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        motasanpham.requestFocus();
        motasanpham.getSettings().setLightTouchEnabled(true);
        motasanpham.getSettings().setJavaScriptEnabled(true);
        motasanpham.getSettings().setGeolocationEnabled(true);
        motasanpham.setSoundEffectsEnabled(true);
        motasanpham.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        ///Get dữ liệu
        GetProductDetailRequest getProductDetailRequest = new GetProductDetailRequest();
        getProductDetailRequest.setProductID(intent.getStringExtra("ProductID"));
        getProductDetailRequest.setUserID(db.GetDL());
        if (getProductDetailRequest.getProductID() != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    GetProductDetail(getProductDetailRequest);
                }
            }, 700);

        } else {
            Model.Dialog(getApplicationContext());
        }
        refest_imfomation.setColorSchemeResources(R.color.maucam, R.color.mauden, R.color.mautrang);
        refest_imfomation.setOnRefreshListener(this);

    }

    public void showimage(String image) {

        Dialog dialog = new Dialog(ImfomationProductActivity.this);
        dialog.setContentView(R.layout.dialog_seepicture_product_imfomation);
        dialog.show();
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView imga_seeproduct_dialog = dialog.findViewById(R.id.imga_seeproduct_dialog);
        Picasso.with(getApplication())
                .load(Gobal.IDImage + image)
                .into(imga_seeproduct_dialog);


    }

    public void showdialog(String imageView, String tensp, String gia) {
        Dialog dialog = new Dialog(ImfomationProductActivity.this);
        dialog.setContentView(R.layout.dialog_addsanphamgiohang);
        dialog.show();
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView hinh = dialog.findViewById(R.id.hinh);
        ImageView close = dialog.findViewById(R.id.close);
        TextView tensp1 = dialog.findViewById(R.id.tensp);
        TextView gia1 = dialog.findViewById(R.id.gia);
        Button addgiohang1 = dialog.findViewById(R.id.addxemgiohang);
        Picasso.with(getApplication())
                .load(Gobal.IDImage + imageView)
                .fit()
                .error(R.drawable.imageerro)
                .centerCrop()
                .into(hinh);
        tensp1.setText(tensp);
        gia1.setText(gia);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        addgiohang1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), ViewCartsAcivity.class);
                startActivity(intent);
            }
        });
    }

    public void GetProductDetail(GetProductDetailRequest getProductDetailRequest) {
        Call<GetProductDetailResponse> getProductDetailResponseCall = APIClient.getProductDetailService().GetProductDetail(Gobal.GuiID, getProductDetailRequest);
        getProductDetailResponseCall.enqueue(new Callback<GetProductDetailResponse>() {
            @Override
            public void onResponse(Call<GetProductDetailResponse> call, Response<GetProductDetailResponse> response) {
                if (response.isSuccessful()) {
                    GetProductDetailResponse getProductDetailResponse = response.body();
                    if (getProductDetailResponse.getStatusID() == 1) {
                        if (getProductDetailResponse.getHomeData().getAddressBook() != null) {
                            fulladdress_user.setText(getProductDetailResponse.getHomeData().getAddressBook().getFullAddress());
                        }
                        Model.changeValueStar(getProductDetailResponse.getHomeData().getReviewTotalList(), review_count5, review_count4, review_count3, review_count2, review_count1);
                        txt_review_imfomation.setText(String.valueOf(getProductDetailResponse.getHomeData().getWebItem().getReviewCount()) + "  Nhận xét");
                        txt_reviewvalue_imfomation.setText(String.valueOf(getProductDetailResponse.getHomeData().getWebItem().getReviewValue()));
                        review_star_imfomation.setRating(Float.parseFloat(String.valueOf(getProductDetailResponse.getHomeData().getWebItem().getReviewValue())));
                        try {
                            Model.ChangeValueCount(getProductDetailResponse.getHomeData().getWebItem().getReviewCount(), review_value5, review_value4, review_value3, review_value2, review_value1, getProductDetailResponse.getHomeData().getReviewTotalList());
                        }catch (Exception exception){
                        }
                        webItemPhotoLists.addAll(getProductDetailResponse.getHomeData().getWebItemPhotoList());
                        vietdanhgia.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    if (Gobal.getLoginStatus() == 1) {
                                        Intent intent1 = new Intent(getApplication(), WriteReviewActivity.class);
                                        intent1.putExtra("ItemID", getProductDetailResponse.getHomeData().getWebItem().getItemID());
                                        startActivity(intent1);
                                    } else {
                                        Model.dialogAcountLogin(v.getContext());
                                    }
                                }catch (Exception exception){
                                    Model.dialogAcountLogin(v.getContext());
                                }
                            }
                        });
                        motasanpham.loadDataWithBaseURL(null, getProductDetailResponse.getHomeData().getWebItem().getDescription() + style, "text/HTML", "UTF-8", null);
                        Picasso.with(getApplication())
                                .load(Gobal.IDImage + webItemPhotoLists.get(0).getImage())
                                .fit()
                                .centerCrop()
                                .into(imgae_product);
                        ImfoGobal.setIDImage(getProductDetailResponse.getHomeData().getWebItem().getImage());
                        name_product.setText(getProductDetailResponse.getHomeData().getWebItem().getName());
                        ////
                        if (Model.hideprice(getProductDetailResponse.getHomeData().getWebItem().getPrice(), getProductDetailResponse.getHomeData().getWebItem().getSalePrice()) == true) {
                            saleprice.setVisibility(View.GONE);
                            specialPrice.setVisibility(View.GONE);
                            price_product.setText(Model.showprice(getProductDetailResponse.getHomeData().getWebItem().getPrice(), getProductDetailResponse.getHomeData().getWebItem().getSalePrice()));
                        } else {
                            price_product.setText(Model.showprice(getProductDetailResponse.getHomeData().getWebItem().getPrice(), getProductDetailResponse.getHomeData().getWebItem().getSalePrice()));
                            saleprice.setText(Model.showsaleprice(getProductDetailResponse.getHomeData().getWebItem().getPrice(), getProductDetailResponse.getHomeData().getWebItem().getSalePrice()));
                            specialPrice.setText("- " + String.valueOf(getProductDetailResponse.getHomeData().getWebItem().getSpecialPrice()) + " %");
                            saleprice.setPaintFlags(saleprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        }
                        ///xem review

                        review_product.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplication(), SeeReviewAcitivity.class);
                                intent.putExtra("ProductID", getProductDetailResponse.getHomeData().getWebItem().getItemID());
                                intent.putExtra("ReViewCount",String.valueOf(getProductDetailResponse.getHomeData().getWebItem().getReviewValue()));
                                intent.putExtra("ReViewValue",String.valueOf(getProductDetailResponse.getHomeData().getWebItem().getReviewCount()));
                                startActivity(intent);
                            }
                        });
                        ////
                        lst_product_relation.addAll(getProductDetailResponse.getHomeData().getProductRelationList());
                        productRelationListAdapter = new ProductRelationListAdapter(lst_product_relation, getApplication());
                        rcy_prodcut_similar.setAdapter(productRelationListAdapter);
                        rcy_prodcut_similar.setLayoutManager(linearLayoutManager);

                        ///// hinh các sp
                        webItemPhotoListAdapter = new WebItemPhotoListAdapter(getProductDetailResponse.getHomeData().getWebItemPhotoList(), getApplication(), ImfomationProductActivity.this);
                        rcl_image_prodcut_imfomation.setAdapter(webItemPhotoListAdapter);
                        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getApplication(), LinearLayoutManager.HORIZONTAL, false);
                        rcl_image_prodcut_imfomation.setLayoutManager(linearLayoutManager1);
                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        add_product_tocart.setVisibility(View.VISIBLE);
                        srl_imfomation_product.setVisibility(View.VISIBLE);
                        if (getProductDetailResponse.getHomeData().getWebItem().isFavorite() == false) {
                            addremovefavorite.setImageResource(R.drawable.love);
                            ShowIMG = false;
                        } else {
                            addremovefavorite.setImageResource(R.drawable.addlove);
                            ShowIMG = true;
                        }
                        ////

                        addremovefavorite.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                processbar_addfavorite.setVisibility(View.VISIBLE);
                                spin_kit_activity_imfomation.setVisibility(View.VISIBLE);
                                if (Gobal.getLoginStatus() == 1) {
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (ShowIMG == false) {
                                                ShowIMG = true;
                                                AddRemoveFavoriteRequest addRemoveFavoriteRequest = new AddRemoveFavoriteRequest();
                                                addRemoveFavoriteRequest.setUserID(db.GetDL());
                                                addRemoveFavoriteRequest.setProductID(getProductDetailResponse.getHomeData().getWebItem().getItemID());
                                                addRemoveFavoriteRequest.setSkuID(intent.getStringExtra("SkuID"));
                                                AddProductFavorite(addRemoveFavoriteRequest, getApplication());
                                                addremovefavorite.setImageResource(R.drawable.addlove);
                                            } else {
                                                ShowIMG = false;
                                                AddRemoveFavoriteRequest addRemoveFavoriteRequest = new AddRemoveFavoriteRequest();
                                                addRemoveFavoriteRequest.setUserID(db.GetDL());
                                                addRemoveFavoriteRequest.setProductID(getProductDetailResponse.getHomeData().getWebItem().getItemID());
                                                addRemoveFavoriteRequest.setSkuID(intent.getStringExtra("SkuID"));
                                                AddProductFavorite(addRemoveFavoriteRequest, getApplication());
                                                addremovefavorite.setImageResource(R.drawable.love);
                                            }
                                            processbar_addfavorite.setVisibility(View.GONE);
                                            spin_kit_activity_imfomation.setVisibility(View.GONE);
                                        }
                                    }, 700);
                                } else {
                                    processbar_addfavorite.setVisibility(View.GONE);
                                    spin_kit_activity_imfomation.setVisibility(View.GONE);
                                    Model.dialogAcountLogin(v.getContext());
                                }
                            }
                        });
                        add_product_tocart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Gobal.SizeProduct = Gobal.SizeProduct + 1;
                                AddToCardRequest addToCardRequest = new AddToCardRequest();
                                if (Gobal.getLoginStatus() == 0) {
                                    addToCardRequest.setUserID(db.GetDL());
                                    addToCardRequest.setIsLogin("False");
                                    addToCardRequest.setProductID(intent.getStringExtra("ProductID"));
                                    addToCardRequest.setPrice(String.valueOf(intent.getStringExtra("PriceAdd")));
                                    addToCardRequest.setQuantity("1");
                                    addToCardRequest.setSkuID(intent.getStringExtra("SkuID"));
                                    Model.addToCard(addToCardRequest, getApplicationContext(),getProductDetailResponse.getHomeData().getWebItem().getImage(),getProductDetailResponse.getHomeData().getWebItem().getName(),getProductDetailResponse.getHomeData().getWebItem().getPrice(),getProductDetailResponse.getHomeData().getWebItem().getSalePrice());

                                } else {
                                    addToCardRequest.setUserID(db.GetDL());
                                    addToCardRequest.setIsLogin("True");
                                    addToCardRequest.setProductID(intent.getStringExtra("ProductID"));
                                    addToCardRequest.setPrice(String.valueOf(intent.getStringExtra("PriceAdd")));
                                    addToCardRequest.setQuantity("1");
                                    addToCardRequest.setSkuID(intent.getStringExtra("SkuID"));
                                    Model.addToCard(addToCardRequest, getApplicationContext(),getProductDetailResponse.getHomeData().getWebItem().getImage(),getProductDetailResponse.getHomeData().getWebItem().getName(),getProductDetailResponse.getHomeData().getWebItem().getPrice(),getProductDetailResponse.getHomeData().getWebItem().getSalePrice());
                                }

                                showdialog(intent.getStringExtra("Image"), name_product.getText().toString(), price_product.getText().toString());
                            }
                        });

                        //////
                        if (getProductDetailResponse.getHomeData().getWebItemRelation().size() == 0) {
//                            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                            rlv_relationproduct.addView(motasanpham, params);
                            rlv_relationproduct.setVisibility(View.GONE);
                        } else {
                            productListItemMenuAdapter.addAll(getProductDetailResponse.getHomeData().getWebItemRelation());
                            webItemRelationAdapter = new WebItemRelationAdapter(productListItemMenuAdapter, getApplication());
                            rcl_webitem_relation.setAdapter(webItemRelationAdapter);
                            rcl_webitem_relation.setLayoutManager(lner_webrelation);
                            rcl_webitem_relation.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                @Override
                                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                                    super.onScrollStateChanged(recyclerView, newState);
                                    if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                                        if (iScronling1 == false) {
                                            if (lner_webrelation != null && lner_webrelation.findLastVisibleItemPosition() == productListItemMenuAdapter.size() - 1) {
                                                progressbar.setVisibility(View.VISIBLE);
                                                pagenumber1 = pagenumber1 + 1;
                                                loadMoreWebItemRelation(getProductDetailResponse.getHomeData().getWebItem().getItemID(), String.valueOf(pagenumber1));
                                            }
                                        }
                                    }
                                }
                            });
                        }

                        rcy_prodcut_similar.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                                super.onScrollStateChanged(recyclerView, newState);
                                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                                    if (iScronling == false) {
                                        if (linearLayoutManager != null && linearLayoutManager.findLastVisibleItemPosition() == lst_product_relation.size() - 1) {
                                            progressbar.setVisibility(View.VISIBLE);
                                            pagenumber = pagenumber + 1;
                                            loadMore(getProductDetailResponse.getHomeData().getWebItem().getItemID(), String.valueOf(pagenumber));
                                        }
                                    }
                                }
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<GetProductDetailResponse> call, Throwable t) {
                Model.Dialog(getApplicationContext());
            }
        });
    }

    public void loadMore(String ID, String pagenumber) {
        GetMoreProductOnCategoryRequest getMoreProductOnCategoryRequest = new GetMoreProductOnCategoryRequest();
        getMoreProductOnCategoryRequest.setPageIndex(pagenumber);
        getMoreProductOnCategoryRequest.setProductCategoryID(ID);
        GetMoreProductOnCategory(getMoreProductOnCategoryRequest);
    }

    public void loadMoreWebItemRelation(String ID, String pagenumber) {
        GetMoreItemRelationRequest getMoreItemRelationRequest = new GetMoreItemRelationRequest();
        getMoreItemRelationRequest.setPageIndex(pagenumber);
        getMoreItemRelationRequest.setProductID(ID);
        getMoreItemRelationRequest.setUserID(db.GetDL());
        GetMoreWebItemRelation(getMoreItemRelationRequest);
    }

    @Override
    public void UpdateImage(int position, String Image) {
        webItemPhotoListAdapter = new WebItemPhotoListAdapter(webItemPhotoLists, getApplication(), ImfomationProductActivity.this);
        webItemPhotoListAdapter.notifyDataSetChanged();
        Picasso.with(getApplication())
                .load(Gobal.IDImage + Image)
                .fit()
                .centerCrop()
                .into(imgae_product);
        ImfoGobal.setIDImage(Image);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                shimmerFrameLayout.setVisibility(View.VISIBLE);
                shimmerFrameLayout.startShimmer();
                srl_imfomation_product.setVisibility(View.GONE);
                GetProductDetailRequest getProductDetailRequest = new GetProductDetailRequest();
                getProductDetailRequest.setProductID(intent.getStringExtra("ProductID"));
                getProductDetailRequest.setUserID(db.GetDL());
                if (getProductDetailRequest.getProductID() != null) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            GetProductDetail(getProductDetailRequest);
                        }
                    }, 700);

                } else {
                    Model.Dialog(getApplicationContext());
                }
                refest_imfomation.setRefreshing(false);
            }
        }, 2500);
    }

    public static class ImfoGobal {
        public static String getIDImage() {
            return IDImage;
        }

        public static void setIDImage(String IDImage) {
            ImfoGobal.IDImage = IDImage;
        }

        public static String IDImage;
    }

    public static void AddProductFavorite(AddRemoveFavoriteRequest addRemoveFavoriteRequest, Context context) {
        Call<AddRemoveFavoriteReponse> addRemoveFavoriteReponseCall = APIClient.addRemoveFavoriteSerVice().AddRemoveFavorite(Gobal.GuiID, addRemoveFavoriteRequest);
        addRemoveFavoriteReponseCall.enqueue(new Callback<AddRemoveFavoriteReponse>() {
            @Override
            public void onResponse(Call<AddRemoveFavoriteReponse> call, Response<AddRemoveFavoriteReponse> response) {
                if (response.isSuccessful()) {
                    AddRemoveFavoriteReponse addRemoveFavoriteReponse = response.body();
                    if (addRemoveFavoriteReponse.getStatusID() == 1) {
                    } else {

                    }
                }
            }

            @Override
            public void onFailure(Call<AddRemoveFavoriteReponse> call, Throwable t) {

            }
        });
    }

    public void GetMoreProductOnCategory(GetMoreProductOnCategoryRequest getMoreProductOnCategoryRequest) {
        Call<GetMoreProductOnCategoryResponse> getMoreProductOnCategoryResponseCall = APIClient.getMoreProductOnCategoryService().GetMoreProductOnCategory(Gobal.GuiID, getMoreProductOnCategoryRequest);
        getMoreProductOnCategoryResponseCall.enqueue(new Callback<GetMoreProductOnCategoryResponse>() {
            @Override
            public void onResponse(Call<GetMoreProductOnCategoryResponse> call, Response<GetMoreProductOnCategoryResponse> response) {
                if (response.isSuccessful()) {
                    GetMoreProductOnCategoryResponse getMoreProductOnCategoryResponse = response.body();
                    if (getMoreProductOnCategoryResponse.getStatusID() == 1) {
                        if (getMoreProductOnCategoryResponse.getProductRelation().size() > 0) {
                            lst_product_relation.addAll(getMoreProductOnCategoryResponse.getProductRelation());
                            productRelationListAdapter.loadmore(getMoreProductOnCategoryResponse.getProductRelation());
                        } else {
                            iScronling = true;
                        }
                    } else {
                        iScronling = true;
                    }
                }
            }

            @Override
            public void onFailure(Call<GetMoreProductOnCategoryResponse> call, Throwable t) {
                Model.Dialog(getApplicationContext());
            }
        });
    }

    public void GetMoreWebItemRelation(GetMoreItemRelationRequest getMoreItemRelationRequest) {
        Call<GetMoreItemRelationResponse> getMoreItemRelationResponseCall = APIClient.getMoreWebItemRelationService().GetMoreItemRelation(Gobal.GuiID, getMoreItemRelationRequest);
        getMoreItemRelationResponseCall.enqueue(new Callback<GetMoreItemRelationResponse>() {
            @Override
            public void onResponse(Call<GetMoreItemRelationResponse> call, Response<GetMoreItemRelationResponse> response) {
                if (response.isSuccessful()) {
                    GetMoreItemRelationResponse getMoreItemRelationResponse = response.body();
                    if (getMoreItemRelationResponse.getStatusID() == 1) {
                        if (getMoreItemRelationResponse.getWebItemRelation().size() > 0) {
                            productListItemMenuAdapter.addAll(getMoreItemRelationResponse.getWebItemRelation());
                            webItemRelationAdapter.LoadMore(getMoreItemRelationResponse.getWebItemRelation());

                        } else {
                            iScronling1 = true;
                        }
                    } else {
                        iScronling1 = true;


                    }
                }
            }

            @Override
            public void onFailure(Call<GetMoreItemRelationResponse> call, Throwable t) {

            }
        });
    }

}

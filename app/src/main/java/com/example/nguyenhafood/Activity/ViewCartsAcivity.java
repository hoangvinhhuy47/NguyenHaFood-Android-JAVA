package com.example.nguyenhafood.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.nguyenhafood.API.APIClient;
import com.example.nguyenhafood.Activity.BaseActivity.BaseActivity;
import com.example.nguyenhafood.Activity.GiaiDoanDatHang.HinhThucGiaoHang;
import com.example.nguyenhafood.Apdater.ViewCart.GetCartAdapter;
import com.example.nguyenhafood.Apdater.ViewCart.GetPromotionAdapter;
import com.example.nguyenhafood.Database.DBHelper;
import com.example.nguyenhafood.Fragment.MainActivity;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Interface.UpdateViewCard;
import com.example.nguyenhafood.Model.AddPromotion.DataGetPromotionCode;
import com.example.nguyenhafood.Model.AddPromotion.DataOrderDetailList;
import com.example.nguyenhafood.Model.AddPromotion.DataPromotionScreenShow;
import com.example.nguyenhafood.Request.ViewCart.GetPromotionCodeRequest;
import com.example.nguyenhafood.Response.ViewCart.GetPromotionCodeResponse;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.Model.ViewCart.GetCard;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.ViewCart.GetCardRequest;
import com.example.nguyenhafood.Request.ViewCart.UpdateUserInCartRequest;
import com.example.nguyenhafood.Response.ViewCart.GetCardResponse;
import com.example.nguyenhafood.Response.ViewCart.UpdateUserInCartResponse;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewCartsAcivity extends BaseActivity implements UpdateViewCard {
    ImageView backxemgiohang;
    Button btn_muahang, btn_dathang;
    GetCartAdapter getCartAdapteradapter;
    RecyclerView recy_viewcard;
    TextView price_viewcard, price_viewcard1, qualiti_product_viewcart;
    RelativeLayout cart_view, cart_null;
    double a = 0;
    ShimmerFrameLayout shimmerFrameLayoutcart;
    DBHelper db;
    private int pagenumber = 1;
    TextView viewmore_cart;
    List<GetCard> lst_getcard = new ArrayList<>();
    TextView add_promotion_viewcart, promotion_price;
    boolean addpromotion = false;
    SwipeRefreshLayout refest_viewcart;
    DataOrderDetailList dataOrderDetailList;
    EditText edit_promotioncode;
    List<DataOrderDetailList> dataOrderDetails = new ArrayList<>();
    ProgressBar progessbar_promotion;
    DataGetPromotionCode dataGetPromotionCode = new DataGetPromotionCode();
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
    double toltalpricechangepromotion = 0;
    List<String> abc;
    ArrayAdapter<String> lst;
    List<DataPromotionScreenShow> lst_datapromotioncode = new ArrayList<>();
    DataPromotionScreenShow dataPromotionScreenShow = new DataPromotionScreenShow();
    GetPromotionCodeResponse getPromotionCodeResponse;
    GetCardResponse getCardResponse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewcart);
        db = new DBHelper(getApplication());
        findID();
        backxemgiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(MainActivity.class);
            }
        });
        shimmerFrameLayoutcart.startShimmer();

        getCartAdapteradapter = new GetCartAdapter(lst_getcard, getApplication(), this, false, dataGetPromotionCode);
        recy_viewcard.setAdapter(getCartAdapteradapter);
        if (Gobal.getLoginStatus() == 0) {
            GetCardRequest getCardRequest = new GetCardRequest();
            getCardRequest.setUserID(db.GetDL());
            getCardRequest.setPageIndex(String.valueOf(pagenumber));
            GetCard(getCardRequest);
        } else {
            if (Gobal.AddCartSTT != null) {
                GetCardRequest getCardRequest = new GetCardRequest();
                getCardRequest.setUserID(db.GetDL());
                getCardRequest.setPageIndex(String.valueOf(pagenumber));
                GetCard(getCardRequest);
            } else {
                UpdateUserInCartRequest updateUserInCartRequest = new UpdateUserInCartRequest();
                updateUserInCartRequest.setCartID(Gobal.getCardID());
                updateUserInCartRequest.setUserID(db.GetDL());
                GetCardUpdate(updateUserInCartRequest);
            }
        }
        backxemgiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gobal.PromotionCode = "";
                goToActivity(MainActivity.class);
            }
        });
        btn_muahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gobal.PromotionCode = "";
                goToNewActivityAndClosePreviousActivities(MainActivity.class);
            }
        });

        refest_viewcart.setColorSchemeResources(R.color.maucam, R.color.mauden, R.color.mautrang);
        refest_viewcart.setOnRefreshListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void findID() {
        promotion_price = findViewById(R.id.promotion_price);
        progessbar_promotion = findViewById(R.id.progessbar_promotion);
        edit_promotioncode = findViewById(R.id.edit_promotioncode);
        cart_view = findViewById(R.id.cart_view);
        refest_viewcart = findViewById(R.id.refest_viewcart);
        add_promotion_viewcart = findViewById(R.id.add_promotion_viewcart);
        shimmerFrameLayoutcart = findViewById(R.id.shimmerFrameLayoutcart);
        backxemgiohang = findViewById(R.id.backxemgiohang);
        qualiti_product_viewcart = findViewById(R.id.qualiti_product_viewcart);
        btn_muahang = findViewById(R.id.btn_muahang);
        btn_dathang = findViewById(R.id.btn_dathang);
        price_viewcard = findViewById(R.id.price_viewcard);
        cart_null = findViewById(R.id.cart_null);
        recy_viewcard = findViewById(R.id.recy_viewcard);
        price_viewcard1 = findViewById(R.id.price_viewcard1);
    }
    public void GetCard(GetCardRequest getCardRequest) {
        Call<GetCardResponse> getCardResponseCall = APIClient.getCardSerVice().GetCard(Gobal.GuiID, getCardRequest);
        getCardResponseCall.enqueue(new Callback<GetCardResponse>() {
            @Override
            public void onResponse(Call<GetCardResponse> call, Response<GetCardResponse> response) {
                if (response.isSuccessful()) {
                    a =0;
                    Gobal.TitlePromotionCode ="";
                    toltalpricechangepromotion=0;
                    addpromotion = false;
                    edit_promotioncode.setFocusable(true);
                    edit_promotioncode.setEnabled(true);
                    edit_promotioncode.setText("");
                    promotion_price.setText("");
                    promotion_price.setTextColor(Color.parseColor("#000000"));
                    promotion_price.setFocusable(false);
                    getCardResponse = new GetCardResponse();
                    getCardResponse = response.body();
                    if (getCardResponse.getStatusID() == 1) {
                        if (getCardResponse.getHomeData() == null || getCardResponse.getHomeData().size() == 0) {
                            cart_view.setVisibility(View.GONE);
                            cart_null.setVisibility(View.VISIBLE);
                            shimmerFrameLayoutcart.stopShimmer();
                            shimmerFrameLayoutcart.setVisibility(View.GONE);
                            btn_dathang.setVisibility(View.GONE);
                            Gobal.SizeProduct = 0;
                        } else {
                            ///dữ liệu promotion
                            ////
                            btn_dathang.setVisibility(View.VISIBLE);
                            /////
                            Gobal.dataOrders.OrderID = String.valueOf(getCardResponse.getHomeData().get(0).getCartID());
                            lst_getcard.removeAll(lst_getcard);
                            lst_getcard.addAll(getCardResponse.getHomeData());
                            getCartAdapteradapter = new GetCartAdapter(lst_getcard, getApplication(), ViewCartsAcivity.this, false, dataGetPromotionCode);
                            recy_viewcard.setAdapter(getCartAdapteradapter);
                            recy_viewcard.setLayoutManager(linearLayoutManager);
                            ////
                            Gobal.SizeProduct = 0;
                            for (int i = 0; i < getCardResponse.getHomeData().size(); i++) {
                                Gobal.SizeProduct = Gobal.SizeProduct + getCardResponse.getHomeData().get(i).getQuantity();
                            }
                            if (getCardResponse.getHomeData().size() != 0) {
                                Gobal.setCardID(String.valueOf(getCardResponse.getHomeData().get(0).getCartID()));
                            }
                            qualiti_product_viewcart.setText("(" + String.valueOf(Gobal.SizeProduct) + ")");
                            a = 0;
                            a = getCardResponse.getHomeData().get(0).getTotalAmount();
                            Gobal.PriceOrder = Integer.parseInt(String.valueOf(Math.round(a)));
                            price_viewcard.setText(Model.ChangeDecimal(a));
                            price_viewcard1.setText(Model.ChangeDecimal(a));
                            shimmerFrameLayoutcart.stopShimmer();
                            shimmerFrameLayoutcart.setVisibility(View.GONE);
                            //// dữ liệu lấy mã promotion
                            if (Gobal.PromotionCode.equals("") == false) {
                                edit_promotioncode.setText(Gobal.PromotionCode);
                                GetPromotionCodeRequest getPromotionCodeRequest = new GetPromotionCodeRequest();
                                for (int i = 0; i < getCardResponse.getHomeData().size(); i++) {
                                    dataOrderDetailList = new DataOrderDetailList();
                                    dataOrderDetailList.setSkuProductID(getCardResponse.getHomeData().get(i).getSkuProductID());
                                    dataOrderDetailList.setAmount(String.valueOf(getCardResponse.getHomeData().get(i).getAmount()));
                                    dataOrderDetailList.setPrice(String.valueOf(getCardResponse.getHomeData().get(i).getPrice()));
                                    dataOrderDetailList.setQuantity(String.valueOf(getCardResponse.getHomeData().get(i).getQuantity()));
                                    dataOrderDetails.add(dataOrderDetailList);
                                }
                                getPromotionCodeRequest.setOrderDetailList(dataOrderDetails);
                                getPromotionCodeRequest.setPromotionCode(Gobal.PromotionCode);
                                GetPromotionCode(getPromotionCodeRequest, getApplicationContext(), true);
                            }
                            if (Gobal.PromotionCode.equals("")){
                                Gobal.PromotionCode="";
                                add_promotion_viewcart.setText("Áp Dụng");
                                add_promotion_viewcart.setBackground(add_promotion_viewcart.getContext().getResources().getDrawable(R.drawable.button_muahang));
                                addpromotion = false;
                                edit_promotioncode.setText("");
                                edit_promotioncode.setFocusable(true);
                                edit_promotioncode.setEnabled(true);
                                edit_promotioncode.setFocusableInTouchMode(true);
                            }
                            add_promotion_viewcart.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    try {
                                        if (addpromotion == false) {
                                            progessbar_promotion.setVisibility(View.VISIBLE);
                                            GetPromotionCodeRequest getPromotionCodeRequest = new GetPromotionCodeRequest();
                                            dataOrderDetails = new ArrayList<>();
                                            dataOrderDetails.removeAll(dataOrderDetails);
                                            for (int i = 0; i < getCardResponse.getHomeData().size(); i++) {
                                                dataOrderDetailList = new DataOrderDetailList();
                                                dataOrderDetailList.setSkuProductID(getCardResponse.getHomeData().get(i).getSkuProductID());
                                                dataOrderDetailList.setAmount(String.valueOf(getCardResponse.getHomeData().get(i).getAmount()));
                                                dataOrderDetailList.setPrice(String.valueOf(getCardResponse.getHomeData().get(i).getPrice()));
                                                dataOrderDetailList.setQuantity(String.valueOf(getCardResponse.getHomeData().get(i).getQuantity()));
                                                dataOrderDetails.add(dataOrderDetailList);
                                            }
                                            getPromotionCodeRequest.setOrderDetailList(dataOrderDetails);
                                            getPromotionCodeRequest.setPromotionCode(edit_promotioncode.getText().toString());
                                            GetPromotionCode(getPromotionCodeRequest, v.getContext(), false);
                                        } else {
                                            Dialog dialog = new Dialog(v.getContext());
                                            dialog.setContentView(R.layout.dialog_cartdelete);
                                            dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                            dialog.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
                                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                            dialog.show();
                                            TextView text_thongbao = dialog.findViewById(R.id.text_thongbao);
                                            ImageView img_dialog_eixt = dialog.findViewById(R.id.img_dialog_eixt);
                                            Button accept_dialog = dialog.findViewById(R.id.accept_dialog);
                                            Button refuse_dialog = dialog.findViewById(R.id.refuse_dialog);
                                            dialog.getWindow().setGravity(Gravity.BOTTOM);
                                            text_thongbao.setText("Bạn có muốn hủy mã giảm giá này không?");
                                            img_dialog_eixt.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    dialog.dismiss();
                                                }
                                            });
                                            refuse_dialog.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    dialog.dismiss();

                                                }
                                            });
                                            accept_dialog.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    a = 0;
                                                    Gobal.PromotionCode="";
                                                    Gobal.TitlePromotionCode="";
                                                    getCardResponse = new GetCardResponse();
                                                    getPromotionCodeResponse = new GetPromotionCodeResponse();
                                                    add_promotion_viewcart.setText("Áp Dụng");
                                                    add_promotion_viewcart.setBackground(add_promotion_viewcart.getContext().getResources().getDrawable(R.drawable.button_muahang));
                                                    addpromotion = false;
                                                    edit_promotioncode.setText("");
                                                    edit_promotioncode.setFocusable(true);
                                                    edit_promotioncode.setEnabled(true);
                                                    edit_promotioncode.setFocusableInTouchMode(true);
                                                    dialog.dismiss();
                                                    GetCardRequest getCardRequest = new GetCardRequest();
                                                    getCardRequest.setUserID(db.GetDL());
                                                    getCardRequest.setPageIndex(String.valueOf(pagenumber));
                                                    GetCard(getCardRequest);
                                                    //

                                                    //
                                                }
                                            });

                                        }

                                    } catch (Exception exception) {
                                    }
                                }
                            });
                            if (Gobal.getLoginStatus() == 1) {
                                btn_dathang.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(getApplication(), HinhThucGiaoHang.class);
                                        Gobal.ToTalPrice = price_viewcard1.getText().toString();
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                    }
                                });
                            } else {
                                btn_dathang.setVisibility(View.GONE);
                            }
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<GetCardResponse> call, Throwable t) {
                Model.Dialog(viewmore_cart.getContext());
            }
        });
    }

    public void GetPromotionCode(GetPromotionCodeRequest getPromotionCodeRequest, Context context, boolean Show) {
        Call<GetPromotionCodeResponse> getPromotionCodeResponseCall = APIClient.getPromotionCodeSerVice().GetPromotionCode(Gobal.GuiID, getPromotionCodeRequest);
        getPromotionCodeResponseCall.enqueue(new Callback<GetPromotionCodeResponse>() {
            @Override
            public void onResponse(Call<GetPromotionCodeResponse> call, Response<GetPromotionCodeResponse> response) {
                if (response.isSuccessful()) {
                    progessbar_promotion.setVisibility(View.GONE);
                    getPromotionCodeResponse = new GetPromotionCodeResponse();
                    getPromotionCodeResponse = response.body();
                    if (getPromotionCodeResponse.getStatusID() == 1) {
                        if (getPromotionCodeResponse.getPromotion().getPromotionType() == 1 || getPromotionCodeResponse.getPromotion().getPromotionType() == 0) {
                            if (getPromotionCodeResponse.getPromotion().getPromotion() != null) {
                                lst_datapromotioncode = new ArrayList<>();
                                lst_datapromotioncode.removeAll(lst_datapromotioncode);
                                ///
                                edit_promotioncode.setFocusable(false);
                                price_viewcard.setText(Model.ChangeDecimal(a));
                                price_viewcard1.setText(Model.ChangeDecimal(a - getPromotionCodeResponse.getPromotion().getPromotion().getDiscountAmount()));
                                Gobal.PriceOrder = Integer.parseInt(String.valueOf(Math.round(a - getPromotionCodeResponse.getPromotion().getPromotion().getDiscountAmount())));

                                add_promotion_viewcart.setText("Hủy");
                                addpromotion = true;
                                add_promotion_viewcart.setBackground(add_promotion_viewcart.getContext().getResources().getDrawable(R.drawable.buttoncancel));
                                promotion_price.setText("Giảm Giá Theo Hóa Đơn");
                                Gobal.TitlePromotionCode ="Giảm Giá Theo Hóa Đơn";
                                promotion_price.setPaintFlags(promotion_price.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                                promotion_price.setTextColor(Color.parseColor("#000000"));
                                promotion_price.setFocusable(true);
                                promotion_price.setFocusableInTouchMode(true);
                                promotion_price.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        DialogPromotion(context, "Giảm Giá Theo Hóa Đơn", Gobal.PromotionCode, "- " + Model.ChangeDecimal(getPromotionCodeResponse.getPromotion().getPromotion().getDiscountAmount()),
                                                Model.ChangeDecimal(a), Model.ChangeDecimal(a - getPromotionCodeResponse.getPromotion().getPromotion().getDiscountAmount()), lst_datapromotioncode, false);
                                    }
                                });
                                if (Show == false) {
                                    Gobal.PromotionCode = edit_promotioncode.getText().toString();
                                    DialogPromotion(context, "Giảm Giá Theo Hóa Đơn", Gobal.PromotionCode, "- " + Model.ChangeDecimal(getPromotionCodeResponse.getPromotion().getPromotion().getDiscountAmount()),
                                            Model.ChangeDecimal(a), Model.ChangeDecimal(a - getPromotionCodeResponse.getPromotion().getPromotion().getDiscountAmount()), lst_datapromotioncode, false);
                                }

                            } else {
                                DialogErro(context);
                            }
                        } else if (getPromotionCodeResponse.getPromotion().getPromotionType() == 2 || getPromotionCodeResponse.getPromotion().getPromotionType() == 3) {
                            ////
                            if (getPromotionCodeResponse.getPromotion().getPromotionItems() != null) {
                                toltalpricechangepromotion = a;
                                lst_datapromotioncode = new ArrayList<>();
                                lst_datapromotioncode.removeAll(lst_datapromotioncode);
                                for (int i = 0; i < lst_getcard.size(); i++) {
                                    try {
                                        for (int j = 0; j < getPromotionCodeResponse.getPromotion().getPromotionItems().size(); j++) {
                                            if (lst_getcard.get(i).getSkuProductID().equals(getPromotionCodeResponse.getPromotion().getPromotionItems().get(j).getProductID())) {
                                                toltalpricechangepromotion = toltalpricechangepromotion - (getPromotionCodeResponse.getPromotion().getPromotionItems().get(j).getDiscountAmount());
                                                dataPromotionScreenShow = new DataPromotionScreenShow();
                                                dataPromotionScreenShow.setProductName(lst_getcard.get(i).getItemName());
                                                dataPromotionScreenShow.setQuanliti(getPromotionCodeResponse.getPromotion().getPromotionItems().get(j).getQuantity());
                                                dataPromotionScreenShow.setImage(lst_getcard.get(i).getImage());
                                                dataPromotionScreenShow.setPrice(getPromotionCodeResponse.getPromotion().getPromotionItems().get(j).getDiscountAmount());
                                                lst_datapromotioncode.add(dataPromotionScreenShow);
                                                break;
                                            }
                                        }
                                    } catch (Exception exception) {
                                    }
                                }

                                ///
                                edit_promotioncode.setFocusable(false);
                                price_viewcard.setText(Model.ChangeDecimal(toltalpricechangepromotion));
                                price_viewcard1.setText(Model.ChangeDecimal(toltalpricechangepromotion));
                                Gobal.PriceOrder = Integer.parseInt(String.valueOf(Math.round(toltalpricechangepromotion)));

                                add_promotion_viewcart.setText("Hủy");
                                addpromotion = true;
                                add_promotion_viewcart.setBackground(add_promotion_viewcart.getContext().getResources().getDrawable(R.drawable.buttoncancel));
                                promotion_price.setText("Giảm Giá Theo Sản Phẩm");
                                Gobal.TitlePromotionCode ="Giảm Giá Theo Sản Phẩm";

                                promotion_price.setPaintFlags(promotion_price.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                                promotion_price.setTextColor(Color.parseColor("#000000"));
                                promotion_price.setFocusable(true);
                                promotion_price.setFocusableInTouchMode(true);
                                promotion_price.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        DialogPromotion(context, "Giảm Giá Theo Sản Phẩm", Gobal.PromotionCode, "",
                                                Model.ChangeDecimal(a), Model.ChangeDecimal(toltalpricechangepromotion),  lst_datapromotioncode, true);
                                    }
                                });
                                if (Show == false) {
                                    Gobal.PromotionCode = edit_promotioncode.getText().toString();
                                    DialogPromotion(context, "Giảm Giá Theo Sản Phẩm", Gobal.PromotionCode, "",
                                            Model.ChangeDecimal(a), Model.ChangeDecimal(toltalpricechangepromotion),  lst_datapromotioncode, true);
                                }
//
                            } else {
                                DialogErro(context);
                            }
                            ///

                        } else if (getPromotionCodeResponse.getPromotion().getPromotionType() == 4) {
                            ///
                            if (getPromotionCodeResponse.getPromotion().getPromotionItemGifs() != null) {
                                ///

                                edit_promotioncode.setFocusable(false);
                                price_viewcard.setText(Model.ChangeDecimal(a));
                                price_viewcard1.setText(Model.ChangeDecimal(a));
                                add_promotion_viewcart.setText("Hủy");
                                addpromotion = true;
                                add_promotion_viewcart.setBackground(add_promotion_viewcart.getContext().getResources().getDrawable(R.drawable.buttoncancel));
                                promotion_price.setText("Tặng Kèm Sản Phẩm Theo Hóa Đơn");
                                Gobal.TitlePromotionCode="Tặng Kèm Sản Phẩm Theo Hóa Đơn";
                                promotion_price.setPaintFlags(promotion_price.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                                promotion_price.setTextColor(Color.parseColor("#000000"));
                                promotion_price.setFocusable(true);
                                promotion_price.setFocusableInTouchMode(true);
                                promotion_price.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        DialogPromotion(context, "Tặng Sản Phẩm Theo Hóa Đơn", Gobal.PromotionCode, "",
                                                Model.ChangeDecimal(a), Model.ChangeDecimal(a), lst_datapromotioncode, true);
                                    }
                                });

                                ////

                                lst_datapromotioncode = new ArrayList<>();
                                lst_datapromotioncode.removeAll(lst_datapromotioncode);
                                    for (int i = 0; i < getPromotionCodeResponse.getPromotion().getPromotionItemGifs().size(); i++) {
                                        if (getPromotionCodeResponse.getPromotion().getPromotionItemGifs().get(i) != null) {
                                            dataPromotionScreenShow = new DataPromotionScreenShow();
                                            dataPromotionScreenShow.setImage(getPromotionCodeResponse.getPromotion().getPromotionItemGifs().get(i).getImage());
                                            dataPromotionScreenShow.setProductName("Tặng: "+getPromotionCodeResponse.getPromotion().getPromotionItemGifs().get(i).getProductName());
                                            dataPromotionScreenShow.setQuanliti(getPromotionCodeResponse.getPromotion().getPromotionItemGifs().get(i).getDiscountQuantity());
                                            lst_datapromotioncode.add(dataPromotionScreenShow);
                                    }
                                }
                                if (Show == false) {
                                    Gobal.PromotionCode = edit_promotioncode.getText().toString();
                                    DialogPromotion(context, "Tặng Sản Phẩm Theo Hóa Đơn", Gobal.PromotionCode, "",
                                            Model.ChangeDecimal(a), Model.ChangeDecimal(a), lst_datapromotioncode, true);
                                }

                            } else {
                                DialogErro(context);                            }
                            ///
                        } else if (getPromotionCodeResponse.getPromotion().getPromotionType() == 6) {
                            ///
                            if (getPromotionCodeResponse.getPromotion().getPromotionItemGifs() != null) {
                                ///

                                edit_promotioncode.setFocusable(false);
                                price_viewcard.setText(Model.ChangeDecimal(a));
                                price_viewcard1.setText(Model.ChangeDecimal(a));
                                add_promotion_viewcart.setText("Hủy");
                                addpromotion = true;
                                add_promotion_viewcart.setBackground(add_promotion_viewcart.getContext().getResources().getDrawable(R.drawable.buttoncancel));
                                promotion_price.setText("Tặng  Sản Phẩm Theo Sản Phẩm");
                                Gobal.TitlePromotionCode="Tặng  Sản Phẩm Theo Sản Phẩm";
                                promotion_price.setPaintFlags(promotion_price.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                                promotion_price.setTextColor(Color.parseColor("#000000"));
                                promotion_price.setFocusable(true);
                                promotion_price.setFocusableInTouchMode(true);
                                promotion_price.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        DialogPromotion(context, "Tặng  Sản Phẩm Theo Sản Phẩm", Gobal.PromotionCode, "",
                                                Model.ChangeDecimal(a), Model.ChangeDecimal(a), lst_datapromotioncode, true);
                                    }
                                });

                                ////

                                lst_datapromotioncode = new ArrayList<>();
                                lst_datapromotioncode.removeAll(lst_datapromotioncode);
                                    for (int i = 0; i < getPromotionCodeResponse.getPromotion().getPromotionItemGifs().size(); i++) {
                                        if (getPromotionCodeResponse.getPromotion().getPromotionItemGifs().get(i) != null) {
                                            dataPromotionScreenShow = new DataPromotionScreenShow();
                                            dataPromotionScreenShow.setImage("Tặng: "+getPromotionCodeResponse.getPromotion().getPromotionItemGifs().get(i).getImage());
                                            dataPromotionScreenShow.setProductName(getPromotionCodeResponse.getPromotion().getPromotionItemGifs().get(i).getProductName());
                                            dataPromotionScreenShow.setQuanliti(getPromotionCodeResponse.getPromotion().getPromotionItemGifs().get(i).getDiscountQuantity());
                                            lst_datapromotioncode.add(dataPromotionScreenShow);

                                    }
                                }
                                if (Show == false) {
                                    Gobal.PromotionCode = edit_promotioncode.getText().toString();
                                    DialogPromotion(context, "Tặng Sản Phẩm Theo Sản Phẩm", Gobal.PromotionCode, "",
                                            Model.ChangeDecimal(a), Model.ChangeDecimal(a),  lst_datapromotioncode, true);
                                }

                            } else {
                                DialogErro(context);                            }
                            ///
                        }
                    } else {
                        DialogErro(context);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetPromotionCodeResponse> call, Throwable t) {
                Model.Dialog(getApplicationContext());
                progessbar_promotion.setVisibility(View.GONE);
            }
        });
    }

    public void GetCardUpdate(UpdateUserInCartRequest updateUserInCartRequest) {
        Call<UpdateUserInCartResponse> updateUserInCartResponseCall = APIClient.updateUserInCartSerVice().UDUserInCart(Gobal.GuiID, updateUserInCartRequest);
        updateUserInCartResponseCall.enqueue(new Callback<UpdateUserInCartResponse>() {
            @Override
            public void onResponse(Call<UpdateUserInCartResponse> call, Response<UpdateUserInCartResponse> response) {
                if (response.isSuccessful()) {
                    UpdateUserInCartResponse updateUserInCartResponse = response.body();
                    if (updateUserInCartResponse.getStatusID() == 1) {
                        GetCardRequest getCardRequest = new GetCardRequest();
                        getCardRequest.setUserID(db.GetDL());
                        getCardRequest.setPageIndex(String.valueOf(pagenumber));
                        GetCard(getCardRequest);
                    }
                }
            }

            @Override
            public void onFailure(Call<UpdateUserInCartResponse> call, Throwable t) {
                Model.Dialog(getApplicationContext());
            }
        });
    }

    @Override
    public void CallBackViewCard(int Totalpirce, boolean TilePromotionCode) {
        getCartAdapteradapter = new GetCartAdapter(lst_getcard, getApplication(), ViewCartsAcivity.this, false, dataGetPromotionCode);
        getCartAdapteradapter.notifyDataSetChanged();
        Gobal.PromotionCode="";

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                GetCardRequest getCardRequest = new GetCardRequest();
                getCardRequest.setUserID(db.GetDL());
                getCardRequest.setPageIndex(String.valueOf(pagenumber));
                GetCard(getCardRequest);
                Gobal.PromotionCode = edit_promotioncode.getText().toString();
            }
        }, 1200);

        qualiti_product_viewcart.setText("(" + String.valueOf(Gobal.SizeProduct) + ")");
        a = a - Totalpirce;
        Gobal.PriceOrder = Integer.parseInt(String.valueOf(Math.round(a)));
        edit_promotioncode.setText("");
        add_promotion_viewcart.setText("Áp Dụng");
        promotion_price.setText("");
        add_promotion_viewcart.setBackground(add_promotion_viewcart.getContext().getResources().getDrawable(R.drawable.button_muahang));
        addpromotion = false;
        price_viewcard.setText(Model.ChangeDecimal(a));
        price_viewcard1.setText(Model.ChangeDecimal(a));
        if (Gobal.SizeProduct == 0) {
            cart_view.setVisibility(View.GONE);
            cart_null.setVisibility(View.VISIBLE);
            btn_dathang.setVisibility(View.GONE);
        }
    }

    @Override
    public void CallBackProductItem(double price, boolean calculation, boolean TilePromotionCode) {
        getCartAdapteradapter = new GetCartAdapter(lst_getcard, getApplication(), ViewCartsAcivity.this, false, dataGetPromotionCode);
        getCartAdapteradapter.notifyDataSetChanged();
        Gobal.PromotionCode="";
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                GetCardRequest getCardRequest = new GetCardRequest();
                getCardRequest.setUserID(db.GetDL());
                getCardRequest.setPageIndex(String.valueOf(pagenumber));
                GetCard(getCardRequest);
                Gobal.PromotionCode = edit_promotioncode.getText().toString();

            }
        }, 1200);

        qualiti_product_viewcart.setText("(" + String.valueOf(Gobal.SizeProduct) + ")");
        edit_promotioncode.setText("");
        add_promotion_viewcart.setText("Áp Dụng");
        promotion_price.setText("");
        add_promotion_viewcart.setBackground(add_promotion_viewcart.getContext().getResources().getDrawable(R.drawable.button_muahang));
        addpromotion = false;
        if (calculation == true) {
            a = a + price;
            Gobal.PriceOrder = Integer.parseInt(String.valueOf(Math.round(a)));
            price_viewcard.setText(Model.ChangeDecimal(a));
            price_viewcard1.setText(Model.ChangeDecimal(a));
        }
        if (calculation == false) {
            a = a - price;
            Gobal.PriceOrder = Integer.parseInt(String.valueOf(Math.round(a)));
            price_viewcard.setText(Model.ChangeDecimal(a));
            price_viewcard1.setText(Model.ChangeDecimal(a));
        }
        if (Gobal.SizeProduct == 0) {
            cart_view.setVisibility(View.GONE);
            cart_null.setVisibility(View.VISIBLE);
            btn_dathang.setVisibility(View.GONE);
        }
    }

    @Override
    public void UpdatePromotion(double price) {

    }

    @Override
    public void onRefresh() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Gobal.PromotionCode = "";
                getCardResponse = new GetCardResponse();
                getPromotionCodeResponse = new GetPromotionCodeResponse();
                add_promotion_viewcart.setText("Áp Dụng");
                add_promotion_viewcart.setBackground(add_promotion_viewcart.getContext().getResources().getDrawable(R.drawable.button_muahang));
                addpromotion = false;
                edit_promotioncode.setText("");
                edit_promotioncode.setFocusable(true);
                edit_promotioncode.setEnabled(true);
                edit_promotioncode.setFocusableInTouchMode(true);
                if (Gobal.getLoginStatus() == 0) {
                    GetCardRequest getCardRequest = new GetCardRequest();
                    getCardRequest.setUserID(db.GetDL());
                    getCardRequest.setPageIndex(String.valueOf(pagenumber));
                    GetCard(getCardRequest);
                } else {
                    if (Gobal.AddCartSTT != null) {
                        GetCardRequest getCardRequest = new GetCardRequest();
                        getCardRequest.setUserID(db.GetDL());
                        getCardRequest.setPageIndex(String.valueOf(pagenumber));
                        GetCard(getCardRequest);
                    } else {
                        UpdateUserInCartRequest updateUserInCartRequest = new UpdateUserInCartRequest();
                        updateUserInCartRequest.setCartID(Gobal.getCardID());
                        updateUserInCartRequest.setUserID(db.GetDL());
                        GetCardUpdate(updateUserInCartRequest);
                    }
                }
                refest_viewcart.setRefreshing(false);
            }
        }, 2500);
    }


    public void DialogPromotion(Context context, String textmagiamgia, String magiamgia, String phieumuahang, String tamtinh, String tongtien
            , List<DataPromotionScreenShow> lst, boolean Show) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_promotion);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        TextView text_magiamgia = dialog.findViewById(R.id.text_magiamgia);
        TextView text_loaigiamgia = dialog.findViewById(R.id.text_loaigiamgia);
        TextView text_phieumuahang = dialog.findViewById(R.id.text_phieumuahang);
        TextView text_tamtinh = dialog.findViewById(R.id.text_tamtinh);
        TextView text_tongtien = dialog.findViewById(R.id.text_tongtien);
        ImageView img_dialog_eixt = dialog.findViewById(R.id.img_dialog_eixt);
        RecyclerView recy_dialog_promotioncode = dialog.findViewById(R.id.recy_dialog_promotioncode);
        Button accept_dialog = dialog.findViewById(R.id.accept_dialog);
        dialog.getWindow().setGravity(Gravity.CENTER_VERTICAL);
        text_loaigiamgia.setText(textmagiamgia);
        text_magiamgia.setText(magiamgia);
        text_phieumuahang.setText(phieumuahang);
        text_tamtinh.setText(tamtinh);
        text_tongtien.setText(tongtien);
        if (Show == true) {
            try {
                GetPromotionAdapter adapter1;
                recy_dialog_promotioncode.setVisibility(View.VISIBLE);
                adapter1 = new GetPromotionAdapter(lst, getApplicationContext());
                LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                recy_dialog_promotioncode.setAdapter(adapter1);
                recy_dialog_promotioncode.setLayoutManager(manager);
            }catch (Exception exception){
            }

        } else {
            recy_dialog_promotioncode.setVisibility(View.GONE);
        }
        accept_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        img_dialog_eixt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
    public void DialogErro(Context context){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_errointernet);
        dialog.show();
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView texterro = dialog.findViewById(R.id.texterro);
        texterro.setText("Mã giảm giá không hợp lệ, \n Vui lòng thử lại sau");
        new CountDownTimer(2500, 50) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                dialog.dismiss();
            }
        }.start();
    }
}

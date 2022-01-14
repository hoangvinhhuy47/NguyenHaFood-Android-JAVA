package com.example.nguyenhafood.Activity.GiaiDoanDatHang;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenhafood.API.APIClient;
import com.example.nguyenhafood.Activity.BaseActivity.BaseActivity;
import com.example.nguyenhafood.Activity.BaseActivity.NotificationApp;
import com.example.nguyenhafood.Apdater.ViewCart.GetPromotionAdapter;
import com.example.nguyenhafood.Apdater.ViewCart.Product_XacNhanDonHang;
import com.example.nguyenhafood.Fragment.MainActivity;
import com.example.nguyenhafood.Activity.ViewCartsAcivity;
import com.example.nguyenhafood.Database.DBHelper;
import com.example.nguyenhafood.Fragment.LoginFragment;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Model.AddPromotion.DataGetPromotionCode;
import com.example.nguyenhafood.Model.AddPromotion.DataOrderDetailList;
import com.example.nguyenhafood.Model.AddPromotion.DataPromotionScreenShow;
import com.example.nguyenhafood.Request.ViewCart.GetPromotionCodeRequest;
import com.example.nguyenhafood.Response.ViewCart.GetPromotionCodeResponse;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.Model.Orders.DataCreateOrder;
import com.example.nguyenhafood.Model.ViewCart.GetCard;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.ViewCart.CreateOrderRequest;
import com.example.nguyenhafood.Request.ViewCart.GetCardRequest;
import com.example.nguyenhafood.Response.Order.CreateOrderResponse;
import com.example.nguyenhafood.Response.ViewCart.GetCardResponse;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class XacNhanDatHang extends BaseActivity {
    DBHelper dbHelper;
    RecyclerView recycle_orderconfirmation;
    Product_XacNhanDonHang product_xacNhanDonHang;
    TextView txt_address_orderconfirmation, txt_delivery_orderconfirmation, txt_pay_orderconfirmation, totalprice_cart;
    TextView btn_change_cart, btn_xemdiachi, btn_change_formdeliverty, delivery_form, change_promotion, promotion_price, btn_payment, provisional_price;
    ImageView add_electronicbill;
    EditText txt_note_order;
    Button btn_accpect_order;
    TextView text_electricbill;
    SpinKitView spin_kit_xacnhandonhang;
    ImageView back_xacnhandonhang, ic_backform_delivery, ic_backviewcart, ic_backform_buyment;
    List<GetCard> getCardList = new ArrayList<>();
    private NotificationManagerCompat notificationManagerCompat;
    double toltalpricechangepromotion = 0;
    List<String> abc;
    ArrayAdapter<String> lst;
    List<DataOrderDetailList> dataOrderDetails = new ArrayList<>();
    DataOrderDetailList dataOrderDetailList;
    DataGetPromotionCode dataGetPromotionCode = new DataGetPromotionCode();
    List<DataPromotionScreenShow> lst_datapromotioncode = new ArrayList<>();
    DataPromotionScreenShow dataPromotionScreenShow = new DataPromotionScreenShow();
    GetPromotionCodeResponse getPromotionCodeResponse;
    double a =0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderconfirmation);
        dbHelper = new DBHelper(getApplication());
        findID();
        this.notificationManagerCompat = NotificationManagerCompat.from(getApplication());
        recycle_orderconfirmation.setNestedScrollingEnabled(false);
        ic_backform_buyment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), ViewCartsAcivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        ic_backviewcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), ViewCartsAcivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);
            }
        });
        ic_backform_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(HinhThucGiaoHang.class);
            }
        });
        ic_backform_buyment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToActivity(HinhThucThanhToan.class);

            }
        });
        back_xacnhandonhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(HinhThucThanhToan.class);
            }
        });
        add_electronicbill.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                goToActivity(Activity_XuatHoaDonDienTu.class);
            }
        });

        ////
        btn_xemdiachi.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                goToActivity(HinhThucGiaoHang.class);

            }
        });
        btn_change_cart.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                goToActivity(ViewCartsAcivity.class);

            }
        });
        change_promotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(ViewCartsAcivity.class);
                Gobal.PromotionCode="";
            }
        });
        btn_change_formdeliverty.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                goToActivity(HinhThucGiaoHang.class);
            }
        });
        btn_payment.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                goToActivity(HinhThucThanhToan.class);

            }
        });
        delivery_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(HinhThucGiaoHang.class);
            }
        });
        btn_accpect_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spin_kit_xacnhandonhang.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (Gobal.Electricbill == 2) {
                            //
                            UUID ui = UUID.randomUUID();
                            Gobal.IDORDER = ui.toString();
                            DataCreateOrder dataCreateOrder = new DataCreateOrder();
                            dataCreateOrder.setOrderID(Gobal.IDORDER);
                            dataCreateOrder.setOrderCode("");
                            dataCreateOrder.setCustomerID(dbHelper.GetDL());
                            dataCreateOrder.setCustomerName(LoginFragment.GobalLogin.getFullName());
                            dataCreateOrder.setCustomerPhone(Gobal.CellPhone);
                            dataCreateOrder.setShipAddress(txt_address_orderconfirmation.getText().toString());
                            dataCreateOrder.setPaymentMethodID(Gobal.dataOrders.PaymentMethodID);
                            dataCreateOrder.setVATMethodID(2);
                            dataCreateOrder.setTaxCode(Gobal.dataOrders.TaxCode);
                            dataCreateOrder.setTaxCompanyAddress(Gobal.dataOrders.TaxCompanyAddress);
                            dataCreateOrder.setTaxCompanyName(Gobal.dataOrders.TaxCompanyName);
                            dataCreateOrder.setTaxCompanyEmail(Gobal.dataOrders.TaxCompanyEmail);
                            dataCreateOrder.setNotes(txt_note_order.getText().toString());
                            dataCreateOrder.setTaxNotes(Gobal.dataOrders.TaxNotes);
                            dataCreateOrder.setTransportTypeID(Gobal.dataOrders.TransportTypeID);
                            ///
                            CreateOrderRequest createOrderRequest = new CreateOrderRequest();
                            createOrderRequest.setOrderDetailList(getCardList);
                            createOrderRequest.setOrder(dataCreateOrder);
                            createOrderRequest.setPromotionCode(Gobal.PromotionCode);

                            CreateOrder(createOrderRequest);

                        } else {
                            UUID ui = UUID.randomUUID();
                            Gobal.IDORDER = ui.toString();
                            DataCreateOrder dataCreateOrder = new DataCreateOrder();
                            dataCreateOrder.setOrderID(Gobal.IDORDER);
                            dataCreateOrder.setOrderCode("");
                            dataCreateOrder.setCustomerID(dbHelper.GetDL());
                            dataCreateOrder.setCustomerName(LoginFragment.GobalLogin.getFullName());
                            dataCreateOrder.setCustomerPhone(Gobal.CellPhone);
                            dataCreateOrder.setShipAddress(txt_address_orderconfirmation.getText().toString());
                            dataCreateOrder.setPaymentMethodID(Gobal.dataOrders.PaymentMethodID);
                            dataCreateOrder.setVATMethodID(1);
                            dataCreateOrder.setTaxCode("");
                            dataCreateOrder.setTaxCompanyAddress("");
                            dataCreateOrder.setTaxCompanyName("");
                            dataCreateOrder.setTaxCompanyEmail("");
                            dataCreateOrder.setNotes(txt_note_order.getText().toString());
                            dataCreateOrder.setTaxNotes("");
                            dataCreateOrder.setTransportTypeID(Gobal.dataOrders.TransportTypeID);
                            ///
                            CreateOrderRequest createOrderRequest = new CreateOrderRequest();
                            createOrderRequest.setOrderDetailList(getCardList);
                            createOrderRequest.setOrder(dataCreateOrder);
                            createOrderRequest.setPromotionCode(Gobal.PromotionCode);

                            CreateOrder(createOrderRequest);
                        }
                    }
                }, 400);
            }
        });


    }

    private void sendOnChannel1() {

        Notification notification = new NotificationCompat.Builder(getApplication(), NotificationApp.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("Thông Báo")
                .setContentText("Đặt Hàng Thành Công \n Xem Chi Tiết Tại Quản Lí Đơn Hàng.")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        int notificationId = 1;
        this.notificationManagerCompat.notify(notificationId, notification);
    }

    public void findID() {
        promotion_price = findViewById(R.id.promotion_price);
        provisional_price = findViewById(R.id.provisional_price);
        spin_kit_xacnhandonhang = findViewById(R.id.spin_kit_xacnhandonhang);
        back_xacnhandonhang = findViewById(R.id.back_xacnhandonhang);
        btn_change_cart = findViewById(R.id.btn_change_cart);
        btn_xemdiachi = findViewById(R.id.btn_xemdiachi);
        text_electricbill = findViewById(R.id.text_electricbill);
        txt_note_order = findViewById(R.id.txt_note_order);
        btn_change_formdeliverty = findViewById(R.id.btn_change_formdeliverty);
        delivery_form = findViewById(R.id.delivery_form);
        change_promotion = findViewById(R.id.change_promotion);
        btn_payment = findViewById(R.id.btn_payment);
        btn_accpect_order = findViewById(R.id.btn_accpect_order);
        recycle_orderconfirmation = findViewById(R.id.recycle_orderconfirmation);
        txt_address_orderconfirmation = findViewById(R.id.txt_address_orderconfirmation);
        txt_delivery_orderconfirmation = findViewById(R.id.txt_delivery_orderconfirmation);
        txt_pay_orderconfirmation = findViewById(R.id.txt_pay_orderconfirmation);
        add_electronicbill = findViewById(R.id.add_electronicbill);
        ic_backviewcart = findViewById(R.id.ic_backviewcart);
        ic_backform_delivery = findViewById(R.id.ic_backform_delivery);
        ic_backform_buyment = findViewById(R.id.ic_backform_buyment);
        totalprice_cart = findViewById(R.id.totalprice_cart);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Gobal.dataOrders.TransportTypeID == 1) {
            txt_delivery_orderconfirmation.setText("Giao Hàng Tiêu Chuẩn");
        } else {
            txt_delivery_orderconfirmation.setText("Giao Hàng Nhanh");
        }
        if (Gobal.dataOrders.PaymentMethodID == 1) {
            txt_pay_orderconfirmation.setText("Thanh Toán Bằng Tiền Mặt");
        } else if (Gobal.dataOrders.PaymentMethodID == 2) {
            txt_pay_orderconfirmation.setText("Thanh Toán Chuyển Khoản");
        } else {
            txt_pay_orderconfirmation.setText("Thanh Toán Bằng Momo");

        }
        if (Gobal.Electricbill == 2) {
            add_electronicbill.setBackground(add_electronicbill.getContext().getResources().getDrawable(R.drawable.order_accpect));
            text_electricbill.setText("Có Xuất Hóa Đơn");
            text_electricbill.setTextColor(Color.parseColor("#FFAB31"));
        }
        txt_address_orderconfirmation.setText(Gobal.dataOrders.ShipAddress);
        GetCardRequest getCardRequest = new GetCardRequest();
        getCardRequest.setPageIndex("1");
        getCardRequest.setUserID(dbHelper.GetDL());
        GetCard(getCardRequest);

    }

    public void GetCard(GetCardRequest getCardRequest) {
        Call<GetCardResponse> getCardResponseCall = APIClient.getCardSerVice().GetCard(Gobal.GuiID, getCardRequest);
        getCardResponseCall.enqueue(new Callback<GetCardResponse>() {
            @Override
            public void onResponse(Call<GetCardResponse> call, Response<GetCardResponse> response) {
                if (response.isSuccessful()) {
                    GetCardResponse getCardResponse = response.body();
                    if (getCardResponse.getStatusID() == 1) {
                        try {

                        getCardList.removeAll(getCardList);
                        getCardList.addAll(getCardResponse.getHomeData());
                        a = getCardResponse.getHomeData().get(0).getTotalAmount();
                        totalprice_cart.setText(Model.ChangeDecimal(getCardResponse.getHomeData().get(0).getTotalAmount()));
                        provisional_price.setText(Model.ChangeDecimal(getCardResponse.getHomeData().get(0).getTotalAmount()));
                        product_xacNhanDonHang = new Product_XacNhanDonHang(getCardResponse.getHomeData(), getApplication());
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
                        recycle_orderconfirmation.setAdapter(product_xacNhanDonHang);
                        recycle_orderconfirmation.setLayoutManager(linearLayoutManager);
                        //// promotion
                        dataOrderDetails = new ArrayList<>();
                        dataOrderDetails.removeAll(dataOrderDetails);
                        try {
                            if (Gobal.PromotionCode.equals("") == false) {
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

                            } else {
                                promotion_price.setText("");

                            }
                        }catch (Exception exception){}
                        } catch (Exception exception) {
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<GetCardResponse> call, Throwable t) {
                Model.Dialog(getApplicationContext());
            }
        });
    }


    public void GetPromotionCode(GetPromotionCodeRequest getPromotionCodeRequest, Context context, boolean Show) {
        Call<GetPromotionCodeResponse> getPromotionCodeResponseCall = APIClient.getPromotionCodeSerVice().GetPromotionCode(Gobal.GuiID, getPromotionCodeRequest);
        getPromotionCodeResponseCall.enqueue(new Callback<GetPromotionCodeResponse>() {
            @Override
            public void onResponse(Call<GetPromotionCodeResponse> call, Response<GetPromotionCodeResponse> response) {
                if (response.isSuccessful()) {
                    getPromotionCodeResponse = new GetPromotionCodeResponse();
                    getPromotionCodeResponse = response.body();
                    if (getPromotionCodeResponse.getStatusID() == 1) {
                        try {
                            if (getPromotionCodeResponse.getPromotion().getPromotionType() == 1 || getPromotionCodeResponse.getPromotion().getPromotionType() == 0) {
                                if (getPromotionCodeResponse.getPromotion().getPromotion() != null) {

                                    lst_datapromotioncode = new ArrayList<>();
                                    lst_datapromotioncode.removeAll(lst_datapromotioncode);
                                    ///
                                    promotion_price.setText("Giảm Giá Theo Hóa Đơn");
                                    promotion_price.setPaintFlags(promotion_price.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                                    promotion_price.setTextColor(Color.parseColor("#000000"));
                                    promotion_price.setFocusable(true);
                                    promotion_price.setFocusableInTouchMode(true);
                                    promotion_price.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            DialogPromotion(v.getContext(), "Giảm Giá Theo Hóa Đơn", Gobal.PromotionCode, "- " + Model.ChangeDecimal(getPromotionCodeResponse.getPromotion().getPromotion().getDiscountAmount()),
                                                    Model.ChangeDecimal(a), Model.ChangeDecimal(a - getPromotionCodeResponse.getPromotion().getPromotion().getDiscountAmount()), lst_datapromotioncode, false);
                                        }
                                    });
                                    totalprice_cart.setText(Model.ChangeDecimal(a - getPromotionCodeResponse.getPromotion().getPromotion().getDiscountAmount()));


                                } else {

                                }
                            } else if (getPromotionCodeResponse.getPromotion().getPromotionType() == 2 || getPromotionCodeResponse.getPromotion().getPromotionType() == 3) {
                                ////
                                if (getPromotionCodeResponse.getPromotion().getPromotionItems() != null) {
                                    toltalpricechangepromotion = a;
                                    lst_datapromotioncode = new ArrayList<>();
                                    lst_datapromotioncode.removeAll(lst_datapromotioncode);
                                    for (int i = 0; i < getCardList.size(); i++) {
                                        try {
                                            for (int j = 0; j < getPromotionCodeResponse.getPromotion().getPromotionItems().size(); j++) {
                                                if (getCardList.get(i).getSkuProductID().equals(getPromotionCodeResponse.getPromotion().getPromotionItems().get(j).getProductID())) {
                                                    toltalpricechangepromotion = toltalpricechangepromotion - (getPromotionCodeResponse.getPromotion().getPromotionItems().get(j).getDiscountAmount());
                                                    dataPromotionScreenShow = new DataPromotionScreenShow();
                                                    dataPromotionScreenShow.setProductName(getCardList.get(i).getItemName());
                                                    dataPromotionScreenShow.setQuanliti(getPromotionCodeResponse.getPromotion().getPromotionItems().get(j).getQuantity());
                                                    dataPromotionScreenShow.setImage(getCardList.get(i).getImage());
                                                    dataPromotionScreenShow.setPrice(getPromotionCodeResponse.getPromotion().getPromotionItems().get(j).getDiscountAmount());
                                                    lst_datapromotioncode.add(dataPromotionScreenShow);
                                                    break;
                                                }
                                            }
                                        } catch (Exception exception) {
                                        }
                                    }

                                    ///
                                    promotion_price.setText("Giảm Giá Theo Sản Phẩm");
                                    promotion_price.setPaintFlags(promotion_price.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                                    promotion_price.setTextColor(Color.parseColor("#000000"));
                                    promotion_price.setFocusable(true);
                                    promotion_price.setFocusableInTouchMode(true);
                                    totalprice_cart.setText(Model.ChangeDecimal(toltalpricechangepromotion));
                                    promotion_price.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            DialogPromotion(v.getContext(), "Giảm Giá Theo Sản Phẩm", Gobal.PromotionCode, "",
                                                    Model.ChangeDecimal(a), Model.ChangeDecimal(toltalpricechangepromotion), lst_datapromotioncode, true);
                                        }
                                    });

//
                                } else {

                                }
                                ///

                            } else if (getPromotionCodeResponse.getPromotion().getPromotionType() == 4) {
                                ///
                                if (getPromotionCodeResponse.getPromotion().getPromotionItemGifs() != null) {
                                    ///

                                    promotion_price.setText("Tặng Kèm Sản Phẩm Theo Hóa Đơn");
                                    promotion_price.setPaintFlags(promotion_price.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                                    promotion_price.setTextColor(Color.parseColor("#000000"));
                                    promotion_price.setFocusable(true);
                                    promotion_price.setFocusableInTouchMode(true);
                                    promotion_price.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            DialogPromotion(v.getContext(), "Tặng Sản Phẩm Theo Hóa Đơn", Gobal.PromotionCode, "",
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
                                            dataPromotionScreenShow.setProductName("Tặng: " + getPromotionCodeResponse.getPromotion().getPromotionItemGifs().get(i).getProductName());
                                            dataPromotionScreenShow.setQuanliti(getPromotionCodeResponse.getPromotion().getPromotionItemGifs().get(i).getDiscountQuantity());
                                            lst_datapromotioncode.add(dataPromotionScreenShow);
                                        }
                                    }


                                } else {
                                }
                                ///
                            } else if (getPromotionCodeResponse.getPromotion().getPromotionType() == 6) {
                                ///
                                if (getPromotionCodeResponse.getPromotion().getPromotionItemGifs() != null) {
                                    ///

                                    promotion_price.setText("Tặng Kèm Sản Phẩm Theo Sản Phẩm");
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
                                            dataPromotionScreenShow.setImage("Tặng: " + getPromotionCodeResponse.getPromotion().getPromotionItemGifs().get(i).getImage());
                                            dataPromotionScreenShow.setProductName(getPromotionCodeResponse.getPromotion().getPromotionItemGifs().get(i).getProductName());
                                            dataPromotionScreenShow.setQuanliti(getPromotionCodeResponse.getPromotion().getPromotionItemGifs().get(i).getDiscountQuantity());
                                            lst_datapromotioncode.add(dataPromotionScreenShow);

                                        }
                                    }


                                } else {
                                }
                                ///
                            }
                        }catch (Exception exception){

                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "" + getPromotionCodeResponse.getErrorDescription(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetPromotionCodeResponse> call, Throwable t) {
                Model.Dialog(getApplicationContext());

            }
        });
    }
    public void CreateOrder(CreateOrderRequest createOrderRequest) {
        Call<CreateOrderResponse> createOrderResponseCall = APIClient.createOrderSerVice().CreateOrder(Gobal.GuiID, createOrderRequest);
        createOrderResponseCall.enqueue(new Callback<CreateOrderResponse>() {
            @Override
            public void onResponse(Call<CreateOrderResponse> call, Response<CreateOrderResponse> response) {
                if (response.isSuccessful()) {
                    CreateOrderResponse createOrderResponse = response.body();
                    if (createOrderResponse.getStatusID() == 1) {
                        Gobal.PromotionCode="";
                        Gobal.SizeProduct = 0;
                        Toast.makeText(getApplication(), "Đơn Hàng Thành Công", Toast.LENGTH_SHORT).show();
                        sendOnChannel1();
                        spin_kit_xacnhandonhang.setVisibility(View.GONE);
                        if (Gobal.dataOrders.PaymentMethodID == 3) {
                            Intent intent = new Intent(getApplication(), Activity_Momo.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(getApplication(), "Tạo đơn hàng không thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<CreateOrderResponse> call, Throwable t) {
                Model.Dialog(getApplicationContext());
            }
        });
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
}

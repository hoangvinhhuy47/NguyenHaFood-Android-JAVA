package com.example.nguyenhafood.Untils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyenhafood.API.APIClient;
import com.example.nguyenhafood.Activity.Acount_login.ActivityAllOder;
import com.example.nguyenhafood.Fragment.MainActivity;
import com.example.nguyenhafood.Activity.ViewCartsAcivity;
import com.example.nguyenhafood.Database.DBHelper;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Model.ImfomationProduct.DataReviewTotalList;
import com.example.nguyenhafood.Model.Review.DataReViewToTal;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.ViewCart.AddToCardRequest;
import com.example.nguyenhafood.Request.Order.CancelOrderRequest;
import com.example.nguyenhafood.Request.Order.ReBuyRequest;
import com.example.nguyenhafood.Response.ViewCart.AddToCardResponse;
import com.example.nguyenhafood.Response.Order.CancelOrderResponse;
import com.example.nguyenhafood.Response.Order.ReBuyResponse;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class Model {

    public static String showprice(double a, double b) {
        if (String.valueOf(a).equals(String.valueOf(b)) == true) {
            return ChangeDecimal(a);
        } else {
            return ChangeDecimal(b);
        }
    }

    public static String showsaleprice(double a, double b) {
        if (String.valueOf(a).equals(String.valueOf(b)) == true) {
            return ChangeDecimal(b);
        } else {
            return ChangeDecimal(a);
        }
    }

    public static boolean hideprice(double a, double b) {
        if (String.valueOf(a).equals(String.valueOf(b)) == true) {
            return true;
        } else {
            return false;
        }
    }

    public static double showprice1(double a, double b) {
        if (String.valueOf(a).equals(String.valueOf(b)) == true) {
            return a;
        } else {
            return b;
        }
    }

    public static boolean checklogin(int Login) {
        if (Login == 0) {
            return false;
        } else {
            return true;
        }
    }

    public static String ChangeDecimal(double a) {
        if (String.valueOf(a).equals("0") == false) {
            Locale lc = Locale.getDefault();
            NumberFormat nu = NumberFormat.getCurrencyInstance(lc);
            return nu.format(a);
        }
        return "####";
    }

    public static void addToCard(AddToCardRequest addToCardRequest, Context context, String Image, String Name, double Price, double SalePrice) {
        Call<AddToCardResponse> addToCardResponseCall = APIClient.addToCardSerVice().AddToCart(Gobal.GuiID, addToCardRequest);
        addToCardResponseCall.enqueue(new Callback<AddToCardResponse>() {
            @Override
            public void onResponse(Call<AddToCardResponse> call, Response<AddToCardResponse> response) {
                if (response.isSuccessful()) {
                    AddToCardResponse addToCardResponse = response.body();
                    if (addToCardResponse.getStatusID() == 1) {
                        Gobal.AddCartSTT = Gobal.AddCartSTT + 1;
                        try {
                            Gobal.PromotionCode="";
                            Dialog dialog = new Dialog(context);
                            dialog.setContentView(R.layout.dialog_addsanphamgiohang);
                            dialog.show();
                            dialog.getWindow().setGravity(Gravity.BOTTOM);
                            dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                            dialog.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            ImageView hinh = dialog.findViewById(R.id.hinh);
                            TextView tensp = dialog.findViewById(R.id.tensp);
                            TextView gia = dialog.findViewById(R.id.gia);
                            ImageView close = dialog.findViewById(R.id.close);
                            Button addxemgiohang = dialog.findViewById(R.id.addxemgiohang);
                            Picasso.with(context)
                                    .load(Gobal.IDImage + Image)
                                    .fit().centerCrop()
                                    .into(hinh);
                            tensp.setText(Name);
                            gia.setText("Giá: " + Model.showprice(Price, SalePrice));
                            addxemgiohang.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(context, ViewCartsAcivity.class);
                                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                                    context.getApplicationContext().startActivity(intent);
                                    dialog.dismiss();
                                }
                            });
                            close.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });
                        } catch (Exception exception) {
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<AddToCardResponse> call, Throwable t) {

            }
        });
    }

    public static void Dialog(Context context) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_errointernet);
        dialog.show();
        dialog.getWindow().setGravity(Gravity.TOP);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        new CountDownTimer(2500, 50) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                dialog.dismiss();
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory(Intent.CATEGORY_HOME);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(homeIntent);
            }
        }.start();
    }

    public static void DialogDelete(Context context) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_cartdelete);
        dialog.show();
        dialog.getWindow().setGravity(Gravity.TOP);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView text_thongbao = dialog.findViewById(R.id.text_thongbao);
        ImageView img_dialog_eixt = dialog.findViewById(R.id.img_dialog_eixt);
        Button accept_dialog = dialog.findViewById(R.id.accept_dialog);
        Button refuse_dialog = dialog.findViewById(R.id.refuse_dialog);
        dialog.dismiss();

    }

    public static void ChangeColorHome(boolean Active, TextView button, RelativeLayout linearLayout) {
        if (Active == true) {
            button.setTextColor(Color.parseColor("#FFFEFE"));
            linearLayout.setBackground(linearLayout.getContext().getResources().getDrawable(R.drawable.backgroud_catory_home));

        }
        if (Active == false) {
            button.setTextColor(Color.parseColor("#000000"));
            linearLayout.setBackgroundColor(Color.parseColor("#FFFEFE"));

        }
    }

    public static void ChangeColor(boolean Active, TextView button, LinearLayout linearLayout) {
        if (Active == true) {
            button.setTextColor(Color.parseColor("#ffab40"));
            linearLayout.setBackgroundColor(Color.parseColor("#ffab40"));

        }
        if (Active == false) {
            button.setTextColor(Color.parseColor("#000000"));
            linearLayout.setBackgroundColor(Color.parseColor("#F8F7F5"));

        }
    }

    public static void ChangeBackGroundAllProduct(boolean Active, TextView textView, RelativeLayout linearLayout) {
        if (Active == true) {
            textView.setTextColor(Color.parseColor("#ffab40"));
            linearLayout.setBackground(linearLayout.getContext().getResources().getDrawable(R.drawable.button));

        }
        if (Active == false) {
            textView.setTextColor(Color.parseColor("#000000"));
            linearLayout.setBackgroundColor(Color.parseColor("#F8F7F5"));


        }
    }

    public static String convertDateToString(Date date) {
        if (date == null)
            return null;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(date);
    }

    public static String convertDateToStringnotifi(Date date) {
        if (date == null)
            return null;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(" dd/MM/yyyy, HH:mm");
        return simpleDateFormat.format(date);
    }

    public static Date convertStringToDate(String dateString) {
        if (dateString == null)
            return null;

        if (dateString.length() >= 18)
            dateString = dateString.substring(0, 18);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            return simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void ChangBackgroundMenuDTO(Boolean aBoolean, RelativeLayout relativeLayout) {
        if (aBoolean == true) {
            relativeLayout.setBackground(relativeLayout.getContext().getResources().getDrawable(R.drawable.buttonactive));
        }
        if (aBoolean == false) {
            relativeLayout.setBackground(relativeLayout.getContext().getResources().getDrawable(R.drawable.buttonactiveitem));
        }
    }

//    public static void DialogBuyProduct(Context context, String hinh1, String tensp1, double price, double saleprice, String Skuid, String ItemID) {
//        Gobal.SizeProduct = Gobal.SizeProduct + 1;
//        if (Gobal.SizeProduct <= 20) {
//            DBHelper dbHelper = new DBHelper(context);
//            if (Gobal.getLoginStatus() == 0) {
//                AddToCardRequest addToCardRequest = new AddToCardRequest();
//                addToCardRequest.setUserID(dbHelper.GetDL());
//                addToCardRequest.setIsLogin("False");
//                addToCardRequest.setProductID(ItemID);
//                addToCardRequest.setPrice(String.valueOf(Model.showprice1(price, price)));
//                addToCardRequest.setQuantity("1");
//                addToCardRequest.setSkuID(Skuid);
//                Model.addToCard(addToCardRequest, context);
//            } else {
//                AddToCardRequest addToCardRequest = new AddToCardRequest();
//                addToCardRequest.setUserID(dbHelper.GetDL());
//                addToCardRequest.setIsLogin("True");
//                addToCardRequest.setProductID(ItemID);
//                addToCardRequest.setPrice(String.valueOf(Model.showprice1(price, price)));
//                addToCardRequest.setQuantity("1");
//                addToCardRequest.setSkuID(Skuid);
//                Model.addToCard(addToCardRequest, context);
//            }
//            Dialog dialog = new Dialog(context);
//            dialog.setContentView(R.layout.dialog_addsanphamgiohang);
//            dialog.show();
//            dialog.getWindow().setGravity(Gravity.BOTTOM);
//            dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            dialog.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
//            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            ImageView hinh = dialog.findViewById(R.id.hinh);
//            TextView tensp = dialog.findViewById(R.id.tensp);
//            TextView gia = dialog.findViewById(R.id.gia);
//            ImageView close = dialog.findViewById(R.id.close);
//            Button addxemgiohang = dialog.findViewById(R.id.addxemgiohang);
//            Picasso.with(context)
//                    .load(Gobal.IDImage + hinh1)
//                    .fit().centerCrop()
//                    .into(hinh);
//            tensp.setText(tensp1);
//            gia.setText("Giá: " + Model.showprice(price, saleprice));
//            addxemgiohang.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(context, ViewCartsAcivity.class);
//                    context.startActivity(intent);
//                }
//            });
//            close.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//                }
//            });
//        } else {
//            Toast.makeText(context, "Giỏ Hàng Đã Đầy", Toast.LENGTH_SHORT).show();
//        }
//    }

    public static void changeValueStar1(List<DataReViewToTal> DataReviewTotalList, TextView txt5, TextView txt4, TextView txt3, TextView txt2, TextView txt1) {
        for (int i = 0; i < DataReviewTotalList.size(); i++) {
            if (DataReviewTotalList.get(i).getReviewValue() == 5) {
                txt5.setText(String.valueOf(DataReviewTotalList.get(i).getReviewCount()));
            }
            if (DataReviewTotalList.get(i).getReviewValue() == 4) {
                txt4.setText(String.valueOf(DataReviewTotalList.get(i).getReviewCount()));
            }
            if (DataReviewTotalList.get(i).getReviewValue() == 3) {
                txt3.setText(String.valueOf(DataReviewTotalList.get(i).getReviewCount()));
            }
            if (DataReviewTotalList.get(i).getReviewValue() == 2) {
                txt2.setText(String.valueOf(DataReviewTotalList.get(i).getReviewCount()));
            }
            if (DataReviewTotalList.get(i).getReviewValue() == 1) {
                txt1.setText(String.valueOf(DataReviewTotalList.get(i).getReviewCount()));
            }
        }
    }

    public static void changeValueStar(List<DataReviewTotalList> DataReviewTotalList, TextView txt5, TextView txt4, TextView txt3, TextView txt2, TextView txt1) {
        for (int i = 0; i < DataReviewTotalList.size(); i++) {
            if (DataReviewTotalList.get(i).getReviewValue() == 5) {
                txt5.setText(String.valueOf(DataReviewTotalList.get(i).getReviewCount()));
            }
            if (DataReviewTotalList.get(i).getReviewValue() == 4) {
                txt4.setText(String.valueOf(DataReviewTotalList.get(i).getReviewCount()));
            }
            if (DataReviewTotalList.get(i).getReviewValue() == 3) {
                txt3.setText(String.valueOf(DataReviewTotalList.get(i).getReviewCount()));
            }
            if (DataReviewTotalList.get(i).getReviewValue() == 2) {
                txt2.setText(String.valueOf(DataReviewTotalList.get(i).getReviewCount()));
            }
            if (DataReviewTotalList.get(i).getReviewValue() == 1) {
                txt1.setText(String.valueOf(DataReviewTotalList.get(i).getReviewCount()));
            }
        }
    }

    public static String ChangeStatusOrder(int status, TextView textView) {
        if (String.valueOf(status).equals("1") == true) {
            textView.setTextColor(Color.parseColor("#ffab40"));
            return "Đang xử lý";
        } else if (String.valueOf(status).equals("2") == true) {
            textView.setTextColor(Color.parseColor("#ffab40"));
            return "Đang vận chuyển";
        } else if (String.valueOf(status).equals("3") == true) {
            textView.setTextColor(Color.parseColor("#222121"));
            return "Đã hủy";
        } else if (String.valueOf(status).equals("4") == true) {
            textView.setTextColor(Color.parseColor("#5DE462"));
            return "Hoàn thành";
        }
        return "Lỗi";
    }

    public static String ChangeStatusButtonOrder(int status) {
        if (String.valueOf(status).equals("1") == true) {
            return "Hủy Đơn Hàng";
        } else if (String.valueOf(status).equals("2") == true) {
            return "Tiếp tục mua hàng";
        } else if (String.valueOf(status).equals("3") == true) {
            return "Đặt lại";
        } else if (String.valueOf(status).equals("4") == true) {
            return "Mua lại";
        }
        return "Lỗi";
    }

    public static String ChangeStatusVATOrder(int status) {
        if (String.valueOf(status).equals("1") == true) {
            return "(Không lấy hóa đơn)";
        } else if (String.valueOf(status).equals("2") == true) {
            return "(có hóa đơn)";
        }
        return "Lỗi";
    }

    public static String ChangeStatusHTgiaohangOrder(int status) {
        if (String.valueOf(status).equals("1") == true) {
            return "Giao hàng tiêu chuẩn";
        } else if (String.valueOf(status).equals("2") == true) {
            return "Giao hàng nhanh";
        }
        return "Lỗi";
    }

    public static String ChangePaymentOrder(int status) {
        if (String.valueOf(status).equals("1") == true) {
            return "Thanh toán bằng tiền mặt";
        } else if (String.valueOf(status).equals("2") == true) {
            return "Thanh toán bằng hình thức chuyển khoản";
        }
        return "Lỗi";
    }

    public static void showbutton(int status, TextView txt, Context context, String OrderID, RelativeLayout relativeLayout, EditText editText, TextView txt1, ProgressBar progressBar) {
        if (status == 1) {
            relativeLayout.setVisibility(View.VISIBLE);
            editText.setVisibility(View.VISIBLE);
            txt1.setVisibility(View.GONE);
            txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editText.getText().toString().equals("") == true) {
                        editText.setError("Xin vui lòng nhập lý do hủy");
                    } else {
                        DialogCancelOrder(v.getContext(), OrderID, progressBar, editText);
                    }
                }
            });
        }
        if (status == 2) {
            relativeLayout.setVisibility(View.GONE);
            txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentDVC = new Intent(context, MainActivity.class);
                    intentDVC.putExtra("ImfomationHome", "abc");
                    intentDVC.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.getApplicationContext().startActivity(intentDVC);
                }
            });
        }
        if (status == 3) {
            relativeLayout.setVisibility(View.VISIBLE);
            editText.setVisibility(View.GONE);
            txt1.setVisibility(View.VISIBLE);
            txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogRebuy(v.getContext(), OrderID, progressBar);
                }
            });
        }
        if (status == 4) {
            relativeLayout.setVisibility(View.GONE);
            txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogRebuy(v.getContext(), OrderID, progressBar);
                }
            });
        }

    }

    public static void DialogRebuy(Context context, String OrderID, ProgressBar progressBar) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_rebuy_canceloder);
        dialog.show();
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView img_exitdialogrebuy = dialog.findViewById(R.id.img_exitdialogrebuy);
        TextView txt_dialogRebuy_huy = dialog.findViewById(R.id.txt_dialogRebuy_huy);
        TextView txt_dialogRebuy_chapnhan = dialog.findViewById(R.id.txt_dialogRebuy_chapnhan);
        DBHelper dbHelper = new DBHelper(context);
        ReBuyRequest reBuyRequest = new ReBuyRequest();
        reBuyRequest.setOrderID(OrderID);
        reBuyRequest.setUserID(dbHelper.GetDL());
        img_exitdialogrebuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        txt_dialogRebuy_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        txt_dialogRebuy_chapnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                dialog.dismiss();
                CountDownTimer countDownTimer = new CountDownTimer(500, 100) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        progressBar.setVisibility(View.GONE);
                        ReBuy(reBuyRequest, context);
                    }
                }.start();

            }
        });
    }

    ///// Change review count
//    public static void ChangeReViewCount(int width,int total, int total1, int total2, int total3, int total4, int total5,ProgressBar rc1,ProgressBar rc2,ProgressBar rc3,ProgressBar rc4,ProgressBar rc5){
//        if (total1!=0){
//            rc1.setProgress((((total1*100/total)*width)/100));
//        }
//        if (total2!=0){
//           rc2.setProgress(((total2*100/total)*width)/100);
//        }
//        if (total3!=0){
//            rc3.setProgress(((total3*100/total)*width)/100);
//        }
//        if (total4!=0){
//            rc4.setProgress(((total4*100/total)*width)/100);
//        }
//        if (total5!=0){
//            rc5.setProgress(((total5*100/total)*width)/100);
//        }
//    }
    @SuppressLint("NewApi")
    public static void ChangeValueCount(int Total, ProgressBar rc1, ProgressBar rc2, ProgressBar rc3, ProgressBar rc4, ProgressBar rc5, List<DataReviewTotalList> datalist) {
        rc1.setProgress(0);
        rc2.setProgress(0);
        rc3.setProgress(0);
        rc4.setProgress(0);
        rc5.setProgress(0);
        try {
            for (int i = 0; i < datalist.size(); i++) {
                if (datalist.get(i).getReviewValue() == 1) {
                    rc1.setProgress((datalist.get(i).getReviewCount() * 100) / Total, true);
                }
                if (datalist.get(i).getReviewValue() == 2) {
                    rc2.setProgress((datalist.get(i).getReviewCount() * 100) / Total, true);
                }
                if (datalist.get(i).getReviewValue() == 3) {
                    rc3.setProgress((datalist.get(i).getReviewCount() * 100) / Total, true);
                }
                if (datalist.get(i).getReviewValue() == 4) {
                    rc4.setProgress((datalist.get(i).getReviewCount() * 100) / Total, true);
                }
                if (datalist.get(i).getReviewValue() == 5) {
                    rc5.setProgress((datalist.get(i).getReviewCount() * 100) / Total, true);
                }
            }
        } catch (Exception exception) {
            rc1.setProgress(0);
            rc2.setProgress(0);
            rc3.setProgress(0);
            rc4.setProgress(0);
            rc5.setProgress(0);
        }

    }

    //    public void hideTopBar() {
    @SuppressLint("NewApi")
    public static void ChangeValueCount1(int Total, ProgressBar rc1, ProgressBar rc2, ProgressBar rc3, ProgressBar rc4, ProgressBar rc5, List<DataReViewToTal> datalist) {
        rc1.setProgress(0);
        rc2.setProgress(0);
        rc3.setProgress(0);
        rc4.setProgress(0);
        rc5.setProgress(0);
        try {

            for (int i = 0; i < datalist.size(); i++) {
                if (datalist.get(i).getReviewValue() == 1) {
                    rc1.setProgress((datalist.get(i).getReviewCount() * 100) / Total, true);
                }
                if (datalist.get(i).getReviewValue() == 2) {
                    rc2.setProgress((datalist.get(i).getReviewCount() * 100) / Total, true);
                }
                if (datalist.get(i).getReviewValue() == 3) {
                    rc3.setProgress((datalist.get(i).getReviewCount() * 100) / Total, true);
                }
                if (datalist.get(i).getReviewValue() == 4) {
                    rc4.setProgress((datalist.get(i).getReviewCount() * 100) / Total, true);
                }
                if (datalist.get(i).getReviewValue() == 5) {
                    rc5.setProgress((datalist.get(i).getReviewCount() * 100) / Total, true);
                }
            }

        } catch (Exception exception) {
            rc1.setProgress(0);
            rc2.setProgress(0);
            rc3.setProgress(0);
            rc4.setProgress(0);
            rc5.setProgress(0);
        }

    }

    //        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//    }
    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void DialogCancelOrder(Context context, String OrderID, ProgressBar progressBar, EditText editText) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_rebuy_canceloder);
        dialog.show();
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView img_exitdialogrebuy = dialog.findViewById(R.id.img_exitdialogrebuy);
        TextView txt_dialogRebuy_huy = dialog.findViewById(R.id.txt_dialogRebuy_huy);
        TextView txt_Text_Text = dialog.findViewById(R.id.txt_Text_Text);
        txt_Text_Text.setText("Bạn thật sự muốn hủy đơn hàng");
        TextView txt_dialogRebuy_chapnhan = dialog.findViewById(R.id.txt_dialogRebuy_chapnhan);
        DBHelper dbHelper = new DBHelper(context);
        CancelOrderRequest cancelOrderRequest = new CancelOrderRequest();
        cancelOrderRequest.setOrderID(OrderID);
        cancelOrderRequest.setReason(editText.getText().toString());
        img_exitdialogrebuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        txt_dialogRebuy_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        txt_dialogRebuy_chapnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                dialog.dismiss();

                CountDownTimer countDownTimer = new CountDownTimer(500, 100) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        progressBar.setVisibility(View.GONE);
                        CancelOrder(cancelOrderRequest, context);
                    }
                }.start();
            }
        });
    }

    public void AddProduct(TextView cartsize, Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartsize.setText(Gobal.SizeProduct);
            }
        });
    }

    public static void ChangBackgroundTapAllOder(Boolean aBoolean, RelativeLayout relativeLayout, TextView textView) {
        if (aBoolean == true) {
            textView.setTextColor(Color.parseColor("#FFFFFF"));
            relativeLayout.setBackground(relativeLayout.getContext().getResources().getDrawable(R.drawable.buttonactiveitem));
        }
        if (aBoolean == false) {
            textView.setTextColor(textView.getContext().getResources().getColor(R.color.mauden));
            relativeLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
    }

    public static void dialogAcountLogin(Context context) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_loginacount);
        dialog.show();
        dialog.getWindow().setGravity(Gravity.TOP);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button dialog_ok = dialog.findViewById(R.id.dialog_ok);
        dialog_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }

    //
    public static void ReBuy(ReBuyRequest reBuyRequest, Context context) {
        Call<ReBuyResponse> reBuyResponseCall = APIClient.reBuyService().ReBuy(Gobal.GuiID, reBuyRequest);
        reBuyResponseCall.enqueue(new Callback<ReBuyResponse>() {
            @Override
            public void onResponse(Call<ReBuyResponse> call, Response<ReBuyResponse> response) {
                if (response.isSuccessful()) {
                    ReBuyResponse reBuyResponse = response.body();
                    if (reBuyResponse.getStatusID() == 1) {
                        Intent intentRebuy = new Intent(context, ViewCartsAcivity.class);
                        intentRebuy.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.getApplicationContext().startActivity(intentRebuy);
                        Toast.makeText(context, "Thêm Thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, " Không Thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ReBuyResponse> call, Throwable t) {

            }
        });
    }

    public static void CancelOrder(CancelOrderRequest cancelOrderRequest, Context context) {
        Call<CancelOrderResponse> cancelOrderResponseCall = APIClient.cancelOrderSerVice().CancelOrder(Gobal.GuiID, cancelOrderRequest);
        cancelOrderResponseCall.enqueue(new Callback<CancelOrderResponse>() {
            @Override
            public void onResponse(Call<CancelOrderResponse> call, Response<CancelOrderResponse> response) {
                CancelOrderResponse cancelOrderResponse = response.body();
                if (cancelOrderResponse.getStatusID() == 1) {
                    Intent intent = new Intent(context, ActivityAllOder.class);
                    intent.putExtra("key1", "2");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.getApplicationContext().startActivity(intent);
                    Toast.makeText(context, "Hủy đơn hàng thành công!!!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Hủy đơn hàng không thành công!!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CancelOrderResponse> call, Throwable t) {

            }
        });
    }
}

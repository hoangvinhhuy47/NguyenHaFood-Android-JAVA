package com.example.nguyenhafood.Activity.GiaiDoanDatHang;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.nguyenhafood.API.APICLinetTest;
import com.example.nguyenhafood.Activity.BaseActivity.NotificationApp;
import com.example.nguyenhafood.Fragment.MainActivity;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Model.Momo.DatamomoResponse;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.ViewCart.CheckMoMoRequest;
import com.example.nguyenhafood.Request.ViewCart.MomoRequest;
import com.example.nguyenhafood.Response.ViewCart.CheckMomoResponse;
import com.example.nguyenhafood.Response.ViewCart.MomoResponse;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Momo extends AppCompatActivity {
    ProgressBar pro_momo;
    DatamomoResponse data = new DatamomoResponse();
    String URL, URL1;
    private NotificationManagerCompat notificationManagerCompat;
    private Handler mHandler = new Handler();
    private boolean doubleBackToExitPressedOnce;
    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mHandler != null) {
            mHandler.removeCallbacks(mRunnable);
        }
    }

    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        sendOnChannel1("Thanh Toán Không Thành Công");
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activi_momo);
        pro_momo = findViewById(R.id.pro_momo);
        try {
            URL = this.getIntent().getData().toString();
            URL1 = this.getIntent().getData().getQueryParameters("signature").toString();
            if (this.getIntent().getData().getQueryParameters("orderId") != null) {
                CheckMoMoRequest checkMoMoRequest = new CheckMoMoRequest();
                checkMoMoRequest.setParamString(URL.substring(URL.indexOf("?") + 1, URL.indexOf("signature") - 1));
                checkMoMoRequest.setSignature(URL1.substring(1, URL1.length() - 1));
                CheckSignature(checkMoMoRequest);
            } else {
                UUID ui = UUID.randomUUID();

                MomoRequest momoRequest = new MomoRequest();
                momoRequest.setAmount(1500);
                momoRequest.setOrderID(ui.toString());
                momoRequest.setOrderInfo("Đơn Hàng Của: " + Gobal.UserName);
                momoRequest.setRedirectUrl("app://open.my.app");
                MomoPayment(momoRequest);
            }
        } catch (Exception exception) {
            UUID ui = UUID.randomUUID();

            MomoRequest momoRequest = new MomoRequest();
            momoRequest.setAmount(1500);
            momoRequest.setOrderID(ui.toString());
            momoRequest.setOrderInfo("Đơn Hàng Của: " + Gobal.UserName);
            momoRequest.setRedirectUrl("app://open.my.app");
            MomoPayment(momoRequest);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        CountDownTimer countDownTimer = new CountDownTimer(120000, 2) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                sendOnChannel1("Thanh Toán Không Thành Công");
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }.start();
    }

    public void MomoPayment(MomoRequest momoRequest) {
        Call<MomoResponse> momoResponseCall = APICLinetTest.momoService().MomoPayment("NjtUcnVuZ3RhbVBodWM7S0JMXzY7NjM2NzI5MDEwNTczMjMwMDAw", momoRequest);
        momoResponseCall.enqueue(new Callback<MomoResponse>() {
            @Override
            public void onResponse(Call<MomoResponse> call, Response<MomoResponse> response) {
                if (response.isSuccessful()) {
                    MomoResponse momoResponse = response.body();
                    if (momoResponse.getStatusID() == 1) {
                        if (momoResponse.getData().getErrorCode() == 0) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(momoResponse.getData().getDeeplink()));
                            startActivity(intent);

                        } else {
                            finish();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<MomoResponse> call, Throwable t) {
            }
        });
    }

    public void CheckSignature(CheckMoMoRequest checkMoMoRequest) {
        Call<CheckMomoResponse> checkMomoResponseCall = APICLinetTest.checkMomoService().CheckSignature("NjtUcnVuZ3RhbVBodWM7S0JMXzY7NjM2NzI5MDEwNTczMjMwMDAw", checkMoMoRequest);
        checkMomoResponseCall.enqueue(new Callback<CheckMomoResponse>() {
            @Override
            public void onResponse(Call<CheckMomoResponse> call, Response<CheckMomoResponse> response) {
                if (response.isSuccessful()) {
                    CheckMomoResponse checkMomoResponse = response.body();
                    if (checkMomoResponse.getStatusID() == 1) {
                        if (checkMomoResponse.getData().isStatus() == true) {
                            sendOnChannel1("Xác Nhận Đúng,Thanh Toán Thành Công");

                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        } else {
                            sendOnChannel1("Thanh Toán Không Thành Công");
                            finish();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<CheckMomoResponse> call, Throwable t) {

            }
        });
    }
    private void sendOnChannel1( String a) {

        Notification notification = new NotificationCompat.Builder(getApplication(), NotificationApp.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("Thông Báo")
                .setContentText(a)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        int notificationId = 1;
        this.notificationManagerCompat.notify(notificationId, notification);
    }
    public static void DialogLoadding(Context context, boolean Show) {
        Dialog dialog = new Dialog(context);
        Dialog dialog1 = new Dialog(context);
        dialog.dismiss();
        dialog1.setContentView(R.layout.dialog_order_screefull);
        dialog1.show();
        dialog1.getWindow().setGravity(Gravity.CENTER_HORIZONTAL);
        dialog1.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog1.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button accept_dialog_orderaccpect = dialog1.findViewById(R.id.accept_dialog_orderaccpect);
        accept_dialog_orderaccpect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
                Intent intent = new Intent(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });
    }
}

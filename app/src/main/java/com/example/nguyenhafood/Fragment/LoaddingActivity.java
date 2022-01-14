package com.example.nguyenhafood.Fragment;

import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.nguyenhafood.API.APIClient;
import com.example.nguyenhafood.Activity.BaseActivity.NotificationApp;
import com.example.nguyenhafood.Database.DBHelper;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Model.LoginUser.Customer;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.ViewCart.GetCardRequest;
import com.example.nguyenhafood.Request.Home.UserRequest;
import com.example.nguyenhafood.Response.ViewCart.GetCardResponse;
import com.example.nguyenhafood.Response.Home.UserReponse;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoaddingActivity extends AppCompatActivity {
    DBHelper db;
    private NotificationManagerCompat notificationManagerCompat;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadding);
        db = new DBHelper(getApplication());
        this.notificationManagerCompat = NotificationManagerCompat.from(getApplication());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Customer customer = db.GetUser();
                if (db.GetUser() != null) {
                    if (customer.getCustomerID() != null || customer.getCustomerID().equals("") == false) {
                        LoginFragment.GobalLogin.setFullName(customer.getFullName());
                        LoginFragment.GobalLogin.setUserID(customer.getUserName());
                        LoginFragment.GobalLogin.setPassWord(customer.getPassword());
                        UserRequest userRequest = new UserRequest();
                        userRequest.setUserName(customer.getUserName());
                        userRequest.setPassword(customer.getPassword());
                        Checklogin(userRequest);
                    } else {
                        if (db.GetDL() == null) {
                            db.DeleteUserUR(1);
                            UUID ui = UUID.randomUUID();
                            if (db.InsertUserER(String.valueOf(ui)) == true) {
                                Intent intent = new Intent(getApplication(), MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                Gobal.setLoginStatus(0);
                                startActivity(intent);
                                Gobal.UserID=ui.toString();
                            }
                        } else {
                            Gobal.setLoginStatus(0);
                            Intent intent = new Intent(getApplication(), MainActivity.class);
                            startActivity(intent);
                        }

                    }
                } else {
                    if (db.GetDL() == null) {
                        db.DeleteUserUR(1);
                        UUID ui = UUID.randomUUID();
                        if (db.InsertUserER(String.valueOf(ui)) == true) {
                            Intent intent = new Intent(getApplication(), MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            Gobal.setLoginStatus(0);
                            startActivity(intent);
                        }
                    } else {
                        Gobal.setLoginStatus(0);
                        Intent intent = new Intent(getApplication(), MainActivity.class);
                        startActivity(intent);
                    }

                }
            }
        }, 400);
    }

    public void Checklogin(UserRequest userRequest) {
        Call<UserReponse> userReponseCall = APIClient.getUserService().UserLogin(Gobal.GuiID, userRequest);
        userReponseCall.enqueue(new Callback<UserReponse>() {
            @Override
            public void onResponse(Call<UserReponse> call, Response<UserReponse> response) {
                if (response.isSuccessful()) {
                    UserReponse userReponse = response.body();
                    if (userReponse.getStatusID() == 1) {
                        LoginFragment.GobalLogin.setCellPhone(userReponse.getCustomer().getCellPhone());
                        Gobal.UserID=userReponse.getCustomer().getCustomerID();
                        Gobal.CellPhone = String.valueOf(userReponse.getCustomer().getCellPhone());
                        Gobal.Gmail = userReponse.getCustomer().getEmail();
                        Gobal.AdressAcount =userReponse.getCustomer().getAddress();
                        Gobal.CreateDay=userReponse.getCustomer().getCreatedDate();
                        Gobal.setLoginStatus(1);
//                        db.InsertUserER(userReponse.getCustomer().getCustomerID());
                        Gobal.AddCartSTT = "Có Dữ liệu";
                        GetCardRequest getCardRequest = new GetCardRequest();
                        getCardRequest.setPageIndex("1");
                        getCardRequest.setUserID(userReponse.getCustomer().getCustomerID());
                        GetCard(getCardRequest);

                    } else {
                        UUID ui = UUID.randomUUID();
                        db.DeleteUserUR(1);
                        Gobal.UserID=ui.toString();
                        if (db.InsertUserER(ui.toString()) == true) {
                            Gobal.setLoginStatus(0);
                            Intent intent = new Intent(getApplication(), MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<UserReponse> call, Throwable t) {
                Model.Dialog(LoaddingActivity.this);
            }
        });
    }

    public void GetCard(GetCardRequest getCardRequest) {
        Call<GetCardResponse> getCardResponseCall = APIClient.getCardSerVice().GetCard(Gobal.GuiID, getCardRequest);
        getCardResponseCall.enqueue(new Callback<GetCardResponse>() {
            @Override
            public void onResponse(Call<GetCardResponse> call, Response<GetCardResponse> response) {
                if (response.isSuccessful()) {
                    GetCardResponse getCardResponse = response.body();
                    if (getCardResponse.getStatusID() == 1) {

                        for (int i = 0; i < getCardResponse.getHomeData().size(); i++) {
                            Gobal.SizeProduct = Gobal.SizeProduct + getCardResponse.getHomeData().get(i).getQuantity();
                        }
                        Intent intent = new Intent(getApplication(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        sendOnChannel1();
                    }
                }

            }

            @Override
            public void onFailure(Call<GetCardResponse> call, Throwable t) {
                Model.Dialog(getApplicationContext());
            }
        });
    }
    private void sendOnChannel1()  {
        Notification notification = new NotificationCompat.Builder(getApplication(), NotificationApp.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("")
                .setContentText("Chào Mừng "+ LoginFragment.GobalLogin.getFullName()+" Đến Với NGUYENHAFOOD")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        int notificationId = 1;
        this.notificationManagerCompat.notify(notificationId, notification);
    }
}

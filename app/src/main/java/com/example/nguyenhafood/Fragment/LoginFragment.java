package com.example.nguyenhafood.Fragment;

import android.app.Dialog;
import android.app.Notification;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.example.nguyenhafood.API.APIClient;
import com.example.nguyenhafood.Activity.BaseActivity.NotificationApp;
import com.example.nguyenhafood.Database.DBHelper;
import com.example.nguyenhafood.Fragment.BaseFragment.BaseFragment;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.Home.UserRequest;
import com.example.nguyenhafood.Response.Home.UserReponse;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends BaseFragment {
    DBHelper db;
    Button btndangnhap;
    ProgressBar progessbar;
    RelativeLayout reson_useapp;
    private TextInputLayout text_input_username;
    private TextInputLayout text_input_password;
    private TextInputEditText edt_username_dn, edt_pass_dn;
    private NotificationManagerCompat notificationManagerCompat;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_login, container, false);
        db = new DBHelper(getContext());
        btndangnhap = view.findViewById(R.id.btndangnhap);
        text_input_password = view.findViewById(R.id.text_input_password);
        text_input_username = view.findViewById(R.id.text_input_username);
        edt_pass_dn = view.findViewById(R.id.edt_pass_dn);
        edt_username_dn = view.findViewById(R.id.edt_username_dn);
        progessbar = view.findViewById(R.id.progessbar);
        this.notificationManagerCompat = NotificationManagerCompat.from(getActivity());
        reson_useapp=view.findViewById(R.id.reson_useapp);
        reson_useapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToView_Link("https://nguyenhafood.vn/blog/quy-dinh-va-chinh-sach");
            }
        });
        //    User user = db.GetUser();
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validatePassword() == true && validateUsername() == true) {
                    UserRequest userRequest = new UserRequest();
                    userRequest.setUserName(text_input_username.getEditText().getText().toString());
                    userRequest.setPassword(text_input_password.getEditText().getText().toString());
                    Checklogin(userRequest);
                }

            }
        });
        return view;
    }
    private void sendOnChannel1(String Name)  {

        Notification notification = new NotificationCompat.Builder(getActivity(), NotificationApp.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("Thông Báo")
                .setContentText("Chào Mừng "+ Name+" Đến NguyenHaFood")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        int notificationId = 1;
        this.notificationManagerCompat.notify(notificationId, notification);
    }
    public void Checklogin(UserRequest userRequest) {
        Call<UserReponse> userReponseCall = APIClient.getUserService().UserLogin(Gobal.GuiID, userRequest);
        userReponseCall.enqueue(new Callback<UserReponse>() {
            @Override
            public void onResponse(Call<UserReponse> call, Response<UserReponse> response) {
                if (response.isSuccessful()) {
                    UserReponse userReponse = response.body();
                    if (userReponse.getStatusID() == 1) {
                        Gobal.UserID = userReponse.getCustomer().getCustomerID();
                        if (db.InserUser(userReponse.getCustomer().getUserName(), userReponse.getCustomer().getCustomerID(),
                                userReponse.getCustomer().getFullName(), userReponse.getCustomer().getPassword(), text_input_password.getEditText().getText().toString()) == true) {
                            Gobal.setLoginStatus(1);
                            GobalLogin.setUserID(userReponse.getCustomer().getUserName());
                            GobalLogin.setFullName(userReponse.getCustomer().getFullName());
                            LoginFragment.GobalLogin.setPassWord(text_input_password.getEditText().getText().toString());
                            LoginFragment.GobalLogin.setCellPhone(String.valueOf(userReponse.getCustomer().getCellPhone()));
                            Gobal.CellPhone = String.valueOf(userReponse.getCustomer().getCellPhone());
                            Gobal.Gmail = userReponse.getCustomer().getEmail();
                            Gobal.AdressAcount = userReponse.getCustomer().getAddress();
                            Gobal.CreateDay = userReponse.getCustomer().getCreatedDate();
                            Gobal.AddCartSTT = "Có Dữ liệu";
                            db.DeleteUserUR(1);
                            sendOnChannel1(userReponse.getCustomer().getFullName());
                            db.InsertUserER(userReponse.getCustomer().getCustomerID());
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);


                        } else {
                            db.DeleteUser(1);
                            if (db.InserUser(userReponse.getCustomer().getUserName(), userReponse.getCustomer().getCustomerID(),
                                    userReponse.getCustomer().getFullName(), userReponse.getCustomer().getPassword(), text_input_password.getEditText().getText().toString()) == true) {
                                Gobal.setLoginStatus(1);
                                GobalLogin.setUserID(userReponse.getCustomer().getCustomerID());
                                GobalLogin.setFullName(userReponse.getCustomer().getFullName());
                                Gobal.CellPhone = String.valueOf(userReponse.getCustomer().getCellPhone());
                                Gobal.Gmail = userReponse.getCustomer().getEmail();
                                Gobal.AdressAcount = userReponse.getCustomer().getAddress();
                                Gobal.CreateDay = userReponse.getCustomer().getCreatedDate();
                                Gobal.AddCartSTT = "Có Dữ liệu";
                                db.DeleteUserUR(1);
                                db.InsertUserER(userReponse.getCustomer().getCustomerID());
                            }
                            else {
                                Toast.makeText(getActivity(),"Lỗi Hệ Thống", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else if (userReponse.getStatusID() == -1) {
                        Gobal.setLoginStatus(0);
                        dialogerro("Tài Khoản Đăng Nhập Thất Bại");
                    }
                }
            }

            @Override
            public void onFailure(Call<UserReponse> call, Throwable t) {
                Model.Dialog(getContext());

            }
        });
    }

    public boolean validateUsername() {
        String usernameinput = text_input_username.getEditText().getText().toString().trim();
        if (usernameinput.isEmpty()) {
            text_input_username.setError("Item này không được rỗng");
            return false;
        } else if (usernameinput.length() > 20) {
            text_input_username.setError("Không được vượt quá 20 kí tự");
            return false;
        } else
            text_input_username.setError(null);
        return true;
    }

    public boolean validatePassword() {
        String passwordinput = text_input_password.getEditText().getText().toString().trim();
        if (passwordinput.isEmpty()) {
            text_input_password.setError("Item này không được rỗng");
            return false;
        } else
            text_input_password.setError(null);
        return true;
    }

    public void showLoading() {
        getView().findViewById(R.id.loader).setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        getView().findViewById(R.id.loader).setVisibility(View.GONE);
    }

    public static class GobalLogin {
        public static String getFullName() {
            return FullName;
        }

        public static void setFullName(String fullName) {
            FullName = fullName;
        }

        public static String FullName;

        public static String getUserID() {
            return UserID;
        }

        public static void setUserID(String userID) {
            UserID = userID;
        }
        public static String UserID;

        public static String getPassWord() {
            return PassWord;
        }

        public static void setPassWord(String passWord) {
            PassWord = passWord;
        }

        public static String PassWord;

        public static String getCellPhone() {
            return CellPhone;
        }

        public static void setCellPhone(String cellPhone) {
            CellPhone = cellPhone;
        }

        public static String CellPhone;
    }

    public void dialogsurr(String text) {
        ImageView exit_erorr;
        TextView txt_error;
        Button aceptdialog, extidialog;
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_complet);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        aceptdialog = dialog.findViewById(R.id.aceptdialog);
        extidialog = dialog.findViewById(R.id.extidialog);
        aceptdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        extidialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        txt_error = dialog.findViewById(R.id.txt_error);
        txt_error.setText(text);
        exit_erorr = dialog.findViewById(R.id.exit_erorr);
        exit_erorr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void dialogerro(String text) {
        ImageView exit_erorr;
        TextView txt_error;
        Button aceptdialog, extidialog;
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_error);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        aceptdialog = dialog.findViewById(R.id.aceptdialog);
        extidialog = dialog.findViewById(R.id.extidialog);
        aceptdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        extidialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        txt_error = dialog.findViewById(R.id.txt_error);
        txt_error.setText(text);
        exit_erorr = dialog.findViewById(R.id.exit_erorr);
        exit_erorr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

}
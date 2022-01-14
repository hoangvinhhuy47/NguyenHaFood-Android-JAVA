package com.example.nguyenhafood.Activity.Acount_login;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.nguyenhafood.Activity.BaseActivity.BaseActivity;
import com.example.nguyenhafood.R;

public class SettingActivity extends BaseActivity {
    private static final String LOG_TAG = "NguyenHaFoood";
    private static final int MY_PERMISSION_REQUEST_CODE_PHONE_STATE = 1;
    SwitchCompat acpect_setting;
    TextView text;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        acpect_setting = findViewById(R.id.acpect_setting);
        text=findViewById(R.id.text);
        acpect_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askPermissionAndGetPhoneNumbers();
            }
        });
    }

    private void askPermissionAndGetPhoneNumbers() {

        // With Android Level >= 23, you have to ask the user
        // for permission to get Phone Number.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) { // 23

            // Check if we have READ_PHONE_STATE permission
            int readPhoneStatePermission = ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.READ_PHONE_STATE);

            if (readPhoneStatePermission != PackageManager.PERMISSION_GRANTED) {
                // If don't have permission so prompt the user.
                this.requestPermissions(
                        new String[]{Manifest.permission.READ_PHONE_STATE},
                        MY_PERMISSION_REQUEST_CODE_PHONE_STATE
                );
                return;
            }
        }
        this.getPhoneNumbers();
    }

    // Need to ask user for permission: android.permission.READ_PHONE_STATE
    @SuppressLint("MissingPermission")
    private void getPhoneNumbers() {
        try {
            TelephonyManager manager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);

            String phoneNumber1 = manager.getLine1Number();

            this.text.setText(phoneNumber1);

            //

            // Other Informations:
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) { // API Level 26.
                String imei = manager.getImei();
                int phoneCount = manager.getPhoneCount();
            }
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) { // API Level 28.
                SignalStrength signalStrength = manager.getSignalStrength();
                int level = signalStrength.getLevel();

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    // When you have the request results
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //
        switch (requestCode) {
            case MY_PERMISSION_REQUEST_CODE_PHONE_STATE: {

                // Note: If request is cancelled, the result arrays are empty.
                // Permissions granted (SEND_SMS).
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    this.getPhoneNumbers();

                }
                // Cancelled or denied.
                else {
                }
                break;
            }
        }
    }


    // When results returned
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MY_PERMISSION_REQUEST_CODE_PHONE_STATE) {
            if (resultCode == RESULT_OK) {
                // Do something with data (Result returned).
            } else if (resultCode == RESULT_CANCELED) {
            } else {
            }
        }
    }
}


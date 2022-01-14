package com.example.nguyenhafood.Activity.Acount_login;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nguyenhafood.R;

public class ActivityConnect extends AppCompatActivity {
    ImageView icon_backConnect;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        icon_backConnect = findViewById(R.id.icon_backConnect);
        icon_backConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

package com.example.nguyenhafood.Activity.Acount_login;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nguyenhafood.Fragment.MainActivity;
import com.example.nguyenhafood.R;

public class ShareAppActivity extends AppCompatActivity {
    ImageView closeshareapp, copylinkshare;
    TextView linkapp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shareapp);
        closeshareapp = findViewById(R.id.closeshareapp);
        closeshareapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        linkapp = findViewById(R.id.linkapp);
        copylinkshare = findViewById(R.id.copylinkshare);

        copylinkshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cm = (ClipboardManager)getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(linkapp.getText().toString());
            }
        });

    }

}

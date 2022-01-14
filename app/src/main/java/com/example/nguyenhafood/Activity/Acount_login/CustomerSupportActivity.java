package com.example.nguyenhafood.Activity.Acount_login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.nguyenhafood.Activity.BaseActivity.BaseActivity;
import com.example.nguyenhafood.R;

public class CustomerSupportActivity extends BaseActivity {
    TextView support_client,regulations,contact_imfomation,chat_with;
    ImageView back;
    String link = "https://nguyenhafood.vn/blog/tro-giup-cham-soc-khach-hang";
    String linkchat="https://www.facebook.com/messages/t/100653545341152";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actitivy_customersupport);
        chat_with = findViewById(R.id.chat_with);
        back=findViewById(R.id.back);
        support_client=findViewById(R.id.support_client);
        regulations=findViewById(R.id.regulations);
        contact_imfomation=findViewById(R.id.contact_imfomation);

        ///
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        contact_imfomation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(intent);
            }
        });
        support_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(intent);
            }
        });
        regulations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(intent);
            }
        });
        chat_with.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkchat));
                startActivity(intent);
            }
        });
    }
}

package com.example.nguyenhafood.Activity.GiaiDoanDatHang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nguyenhafood.Database.DBHelper;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.R;

public class Activity_XuatHoaDonDienTu extends AppCompatActivity {
    EditText txt_TaxCode, TaxCompanyName, TaxCompanyAddress, TaxCompanyEmail;
    ImageView back;
    CheckBox checkbox_address_billelectric;
    Button btn_accpect_billelectric, btn_refuse_billelectric;
    EditText note_billelectric;
    DBHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronicbill);
        dbHelper = new DBHelper(getApplication());
        note_billelectric = findViewById(R.id.note_billelectric);
        btn_refuse_billelectric = findViewById(R.id.btn_refuse_billelectric);
        btn_accpect_billelectric = findViewById(R.id.btn_accpect_billelectric);
        TaxCompanyEmail = findViewById(R.id.TaxCompanyEmail);
        TaxCompanyAddress = findViewById(R.id.TaxCompanyAddress);
        TaxCompanyName = findViewById(R.id.TaxCompanyName);
        txt_TaxCode = findViewById(R.id.txt_TaxCode);
        back = findViewById(R.id.back);
        checkbox_address_billelectric = findViewById(R.id.checkbox_address_billelectric);
        TaxCompanyEmail.setText(Gobal.dataOrders.TaxCompanyEmail);
        TaxCompanyAddress.setText(Gobal.dataOrders.TaxCompanyAddress);
        TaxCompanyName.setText(Gobal.dataOrders.TaxCompanyName);
        txt_TaxCode.setText(Gobal.dataOrders.TaxCode);
        note_billelectric.setText(Gobal.dataOrders.TaxNotes);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        checkbox_address_billelectric.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    TaxCompanyAddress.setText(Gobal.dataOrders.ShipAddress);
                    TaxCompanyEmail.setText(Gobal.Gmail);
                } else {
                    TaxCompanyAddress.setText("");
                    TaxCompanyEmail.setText("");
                }
            }
        });
        btn_accpect_billelectric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txt_TaxCode.getText().toString().equals("") == true) {
                    txt_TaxCode.setError("không được bỏ trống");
                }
                if (TaxCompanyName.getText().toString().equals("") == true) {
                    TaxCompanyName.setError("không được bỏ trống");
                }
                if (TaxCompanyAddress.getText().toString().equals("") == true) {
                    TaxCompanyAddress.setError("không được bỏ trống");
                }
                if (TaxCompanyEmail.getText().toString().equals("") == true) {
                    TaxCompanyEmail.setError("không được bỏ trống");
                }
                if (txt_TaxCode.getText().toString().equals("") == false && TaxCompanyName.getText().toString().equals("") == false &&
                        TaxCompanyAddress.getText().toString().equals("") == false && TaxCompanyEmail.getText().toString().equals("") == false) {
                    Gobal.dataOrders.TaxCode = txt_TaxCode.getText().toString();
                    Gobal.dataOrders.TaxCompanyName = TaxCompanyName.getText().toString();
                    Gobal.dataOrders.TaxCompanyAddress = TaxCompanyAddress.getText().toString();
                    Gobal.dataOrders.TaxCompanyEmail = TaxCompanyEmail.getText().toString();
                    Gobal.dataOrders.TaxNotes = note_billelectric.getText().toString();
                    Intent intent = new Intent(getApplication(), XacNhanDatHang.class);
                    Gobal.Electricbill = 2;
                    startActivity(intent);
                }
            }
        });
        btn_refuse_billelectric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gobal.dataOrders.TaxCode = txt_TaxCode.getText().toString();
                Gobal.dataOrders.TaxCompanyName = TaxCompanyName.getText().toString();
                Gobal.dataOrders.TaxCompanyAddress = TaxCompanyAddress.getText().toString();
                Gobal.dataOrders.TaxCompanyEmail = TaxCompanyEmail.getText().toString();
                Gobal.dataOrders.TaxNotes = note_billelectric.getText().toString();
                Intent intent = new Intent(getApplication(), XacNhanDatHang.class);
                Gobal.Electricbill = 1;
                startActivity(intent);
            }
        });
    }
}

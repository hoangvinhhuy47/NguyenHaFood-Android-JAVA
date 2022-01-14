package com.example.nguyenhafood.Activity.Address;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenhafood.API.APIClient;
import com.example.nguyenhafood.Activity.BaseActivity.BaseActivity;
import com.example.nguyenhafood.Activity.ViewCartsAcivity;
import com.example.nguyenhafood.Apdater.AcountUser.GetAddressBookAdapter;
import com.example.nguyenhafood.Database.DBHelper;
import com.example.nguyenhafood.Fragment.SearchFragment;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Interface.UpdateAddress;
import com.example.nguyenhafood.Model.AcountUser.DataAddressBook;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.Acount_Login.GetAddressBookRequest;
import com.example.nguyenhafood.Response.Acount_Login.GetAddressBookResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressActivity extends BaseActivity implements UpdateAddress {
    ImageView back;
    RecyclerView rcl_addressbook;
    GetAddressBookAdapter getAddressBookAdapter;
    DBHelper dbHelper;
    List<DataAddressBook> dataAddressBooks = new ArrayList<>();
    Button addnew_address;
    TextView cart_badge;
    boolean Show =false;
    RelativeLayout see_cart,search;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DBHelper(getApplication());
        setContentView(R.layout.activity_address);
        back = findViewById(R.id.back);
        see_cart=findViewById(R.id.see_cart);
        rcl_addressbook = findViewById(R.id.rcl_addressbook);
        cart_badge=findViewById(R.id.cart_badge);
        cart_badge.setText(String.valueOf(Gobal.SizeProduct));
        search=findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(SearchFragment.class);
            }
        });
        see_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplication(),ViewCartsAcivity.class);
                startActivity(intent);
            }
        });
        cart_badge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(ViewCartsAcivity.class);
            }
        });
        addnew_address=findViewById(R.id.addnew_address);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        GetAddressBookRequest getAddressBookRequest = new GetAddressBookRequest();
        getAddressBookRequest.setUserID(dbHelper.GetDL());
        GetAdressBook(getAddressBookRequest);
        addnew_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(AddNewAddressBookActivity.class);
            }
        });
        Intent intent = this.getIntent();
        if (intent.getStringExtra("Show")!=null){
            Show = Boolean.parseBoolean(intent.getStringExtra("Show"));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        GetAddressBookRequest getAddressBookRequest = new GetAddressBookRequest();
        getAddressBookRequest.setUserID(dbHelper.GetDL());
        GetAdressBook(getAddressBookRequest);
    }

    public void GetAdressBook(GetAddressBookRequest getAddressBookRequest) {
        Call<GetAddressBookResponse> getAddressBookResponseCall = APIClient.getAddressBookService().GetAddressBook(Gobal.GuiID, getAddressBookRequest);
        getAddressBookResponseCall.enqueue(new Callback<GetAddressBookResponse>() {
            @Override
            public void onResponse(Call<GetAddressBookResponse> call, Response<GetAddressBookResponse> response) {
                if (response.isSuccessful()) {
                    GetAddressBookResponse getAddressBookResponse = response.body();
                    if (getAddressBookResponse.getStatusID() == 1) {
                        dataAddressBooks.removeAll(dataAddressBooks);
                        dataAddressBooks.addAll(getAddressBookResponse.getAddressBook());
                        getAddressBookAdapter = new GetAddressBookAdapter(dataAddressBooks, getApplication(), AddressActivity.this::UpdateAddressBook,Show);
                        rcl_addressbook.setAdapter(getAddressBookAdapter);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
                        rcl_addressbook.setLayoutManager(linearLayoutManager);
                    }
                }
            }
            @Override
            public void onFailure(Call<GetAddressBookResponse> call, Throwable t) {
                Model.Dialog(getApplicationContext());
            }
        });
    }

    @Override
    public void UpdateAddressBook(int position) {
        GetAddressBookRequest getAddressBookRequest = new GetAddressBookRequest();
        getAddressBookRequest.setUserID(dbHelper.GetDL());
        GetAdressBook(getAddressBookRequest);
    }
}

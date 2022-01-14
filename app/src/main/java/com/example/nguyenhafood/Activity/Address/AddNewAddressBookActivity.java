package com.example.nguyenhafood.Activity.Address;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import com.example.nguyenhafood.API.APIClient;
import com.example.nguyenhafood.Database.DBHelper;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Model.AcountUser.DataAddressBook;
import com.example.nguyenhafood.Request.Acount_Login.GetAllWardRequest;
import com.example.nguyenhafood.Response.Acount_Login.GetAllWardResponse;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.Acount_Login.AddAddressBookRequest;
import com.example.nguyenhafood.Request.Acount_Login.GetAllCityRequest;
import com.example.nguyenhafood.Response.Acount_Login.GetAllCityResponse;
import com.example.nguyenhafood.Request.Acount_Login.GetAllDistrictRequest;
import com.example.nguyenhafood.Response.Acount_Login.AddAddressBookResponse;
import com.example.nguyenhafood.Response.Acount_Login.GetAllDistrictResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewAddressBookActivity extends AppCompatActivity {
    EditText name_addnewadress, number_addnewadress, wards_addnewadress, address_;
    AppCompatSpinner city_addnewadress, distric_addnewaddress,distric_ward;
    RadioButton default_addnewaddress;
    TextView lengh_text;
    ImageView back;
    private static final int MY_PERMISSION_REQUEST_CODE_PHONE_STATE = 1;
    Button save_addnewaddress;
    private static final String LOG_TAG = "NguyenHaFoood";
    ArrayAdapter arrayAdapter;
    DBHelper dbHelper;
    ArrayAdapter arrayAdapter1;
    ArrayAdapter arrayAdapter2;
    String a, b, c,d = "";
    boolean ischecked = false;
    String idCity, idCountry, idDistric,idWard = "";
    String IDShow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_a_new_address);
        name_addnewadress = findViewById(R.id.name_addnewadress);
        dbHelper = new DBHelper(getApplication());
        lengh_text = findViewById(R.id.lengh_text);
        number_addnewadress = findViewById(R.id.number_addnewadress);
        city_addnewadress = findViewById(R.id.city_addnewadress);
        distric_addnewaddress = findViewById(R.id.distric_addnewaddress);
        wards_addnewadress = findViewById(R.id.wards_addnewadress);
        default_addnewaddress = findViewById(R.id.default_addnewaddress);
        distric_ward =findViewById(R.id.distric_ward);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        address_ = findViewById(R.id.address_);
        save_addnewaddress = findViewById(R.id.save_addnewaddress);
        number_addnewadress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
//                askPermissionAndGetPhoneNumbers();
            }
        });



        default_addnewaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ischecked == true) {
                    default_addnewaddress.setChecked(false);
                    ischecked = false;
                } else {
                    default_addnewaddress.setChecked(true);
                    ischecked = true;
                }
            }
        });
        name_addnewadress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (name_addnewadress.getText().toString().length() < 50) {
                    lengh_text.setText(String.valueOf(name_addnewadress.getText().toString().length()));
                } else {
                }
            }
        });
        wards_addnewadress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                c = wards_addnewadress.getText().toString();

                address_.setText(c +","+d +" ," + b + " ," + a);

            }
        });
        GetAllCityRequest getAllCityRequest = new GetAllCityRequest();
        GetAllCity(getAllCityRequest);
        save_addnewaddress.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                DataAddressBook dataAddressBook = new DataAddressBook();
                dataAddressBook.setCityID(idCity);
                dataAddressBook.setCountryID(idCountry);
                dataAddressBook.setObjectID(dbHelper.GetDL());
                dataAddressBook.setObjectType(String.valueOf(1));
                dataAddressBook.setFullAddress(address_.getText().toString());
                dataAddressBook.setDistrictID(idDistric);
                dataAddressBook.setAddress(wards_addnewadress.getText().toString());
                dataAddressBook.setWardID(idWard);
                if (ischecked == true) {
                    dataAddressBook.setDefault(true);
                } else {
                    dataAddressBook.setDefault(false);
                }
                if (name_addnewadress.getText().toString().equals("") == true || number_addnewadress.getText().toString().equals("") == true) {
                    name_addnewadress.setError("Vui Lòng Nhập Đủ Dữ Liệu");
                } else {
                    dataAddressBook.setFullName(name_addnewadress.getText().toString());
                    if (number_addnewadress.getText().toString().substring(0,3).equals("+84")==true){
                        dataAddressBook.setCellPhone("0"+number_addnewadress.getText().toString().substring(3));
                    }
                    else {
                        dataAddressBook.setCellPhone(number_addnewadress.getText().toString());
                    }
                    dataAddressBook.setSortOrder("1");
                    AddAddressBookRequest addAddressBookRequest = new AddAddressBookRequest();
                    addAddressBookRequest.setAddressBook(dataAddressBook);
                    AddNewAddress(addAddressBookRequest);
                }
            }
        });
        distric_addnewaddress.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                GetAllDistrictRequest getAllDistrictRequest = new GetAllDistrictRequest();
                getAllDistrictRequest.setCityID(IDShow);
                GetAllDistrict(getAllDistrictRequest);
                return false;
            }
        });


    }

    public void AddNewAddress(AddAddressBookRequest addAddressBookRequest) {
        Call<AddAddressBookResponse> addAddressBookResponseCall = APIClient.addAddressBookSerVice().AddAddressBook(Gobal.GuiID, addAddressBookRequest);
        addAddressBookResponseCall.enqueue(new Callback<AddAddressBookResponse>() {
            @Override
            public void onResponse(Call<AddAddressBookResponse> call, Response<AddAddressBookResponse> response) {
                if (response.isSuccessful()) {
                    AddAddressBookResponse addAddressBookResponse = response.body();
                    if (addAddressBookResponse.getStatusID() == 1) {
                        finish();
                    } else {
                    }
                }
            }

            @Override
            public void onFailure(Call<AddAddressBookResponse> call, Throwable t) {
                Model.Dialog(getApplicationContext());
            }
        });

    }
    public void GetAllWard(GetAllWardRequest getAllWardRequest) {
        Call<GetAllWardResponse> getAllWardResponseCall = APIClient.getAllWardSerVice().GetAllWard(Gobal.GuiID, getAllWardRequest);
        getAllWardResponseCall.enqueue(new Callback<GetAllWardResponse>() {
            @Override
            public void onResponse(Call<GetAllWardResponse> call, Response<GetAllWardResponse> response) {
                if (response.isSuccessful()){
                    GetAllWardResponse getAllWardResponse = response.body();
                    if (getAllWardResponse.getStatusID()==1){
                        try {
                            arrayAdapter2 = new ArrayAdapter(getApplication(), R.layout.spinner_item, getAllWardResponse.getWardList());
                            arrayAdapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                            distric_ward.setAdapter(arrayAdapter2);
                            distric_ward.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    d = getAllWardResponse.getWardList().get(position).getWardName();
                                    idWard = getAllWardResponse.getWardList().get(position).getWardID();
                                    address_.setText(c +","+d +" ," + b + " ," + a);
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                        }catch (Exception exception){
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllWardResponse> call, Throwable t) {

            }
        });
    }
    public void GetAllCity(GetAllCityRequest getAllCityRequest) {
        Call<GetAllCityResponse> getAllCityResponseCall = APIClient.getAllCitySerViceSerVice().GetAllCity(Gobal.GuiID, getAllCityRequest);
        getAllCityResponseCall.enqueue(new Callback<GetAllCityResponse>() {
            @Override
            public void onResponse(Call<GetAllCityResponse> call, Response<GetAllCityResponse> response) {
                if (response.isSuccessful()) {
                    GetAllCityResponse getAllCityResponse = response.body();
                    if (getAllCityResponse.getStatusID() == 1) {
                        try {
                            arrayAdapter = new ArrayAdapter(getApplication(), R.layout.spinner_item, getAllCityResponse.getCityList());
                            arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                            city_addnewadress.setAdapter(arrayAdapter);
                            idCity = getAllCityResponse.getCityList().get(0).getCityID();
                            idCountry = getAllCityResponse.getCityList().get(0).getCountryID();
                            city_addnewadress.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    IDShow = getAllCityResponse.getCityList().get(position).getCityID();

                                    address_.setText(getAllCityResponse.getCityList().get(position).getCityName());
                                    a = getAllCityResponse.getCityList().get(position).getCityName();
                                    address_.setText(c +","+d +" ," + b + " ," + a);
                                    idCity = getAllCityResponse.getCityList().get(position).getCityID();
                                    idCountry = getAllCityResponse.getCityList().get(position).getCountryID();
                                    GetAllDistrictRequest getAllDistrictRequest = new GetAllDistrictRequest();
                                    getAllDistrictRequest.setCityID(getAllCityResponse.getCityList().get(position).getCityID());
                                    GetAllDistrict(getAllDistrictRequest);
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });

                        } catch (Exception exception) {
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllCityResponse> call, Throwable t) {
                Model.Dialog(getApplicationContext());
            }
        });
    }

    public void GetAllDistrict(GetAllDistrictRequest getAllDistrictRequest) {
        Call<GetAllDistrictResponse> getAllDistrictRequestCall = APIClient.getAllDistrictSerVice().GetAllDistrict(Gobal.GuiID, getAllDistrictRequest);
        getAllDistrictRequestCall.enqueue(new Callback<GetAllDistrictResponse>() {
            @Override
            public void onResponse(Call<GetAllDistrictResponse> call, Response<GetAllDistrictResponse> response) {
                if (response.isSuccessful()) {
                    GetAllDistrictResponse getAllDistrictResponse = response.body();
                    if (getAllDistrictResponse.getStatusID() == 1) {
                        arrayAdapter1 = new ArrayAdapter(getApplication(), R.layout.spinner_item, getAllDistrictResponse.getDistrictList());
                        arrayAdapter1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        distric_addnewaddress.setAdapter(arrayAdapter1);
                        idDistric = getAllDistrictResponse.getDistrictList().get(0).getDistrictID();
                        distric_addnewaddress.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                b = getAllDistrictResponse.getDistrictList().get(position).getDistrictName();
                                address_.setText(c +","+d +" ," + b + " ," + a);
                                idDistric = getAllDistrictResponse.getDistrictList().get(position).getDistrictID();
                                GetAllWardRequest getAllWardRequest = new GetAllWardRequest();
                                getAllWardRequest.setDistrictID(getAllDistrictResponse.getDistrictList().get(position).getDistrictID());
                                GetAllWard(getAllWardRequest);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllDistrictResponse> call, Throwable t) {
                Model.Dialog(getApplication());
            }
        });
    }
//
//    private void askPermissionAndGetPhoneNumbers() {
//
//        // With Android Level >= 23, you have to ask the user
//        // for permission to get Phone Number.
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) { // 23
//
//            // Check if we have READ_PHONE_STATE permission
//            int readPhoneStatePermission = ActivityCompat.checkSelfPermission(this,
//                    Manifest.permission.READ_PHONE_STATE);
//
//            if (readPhoneStatePermission != PackageManager.PERMISSION_GRANTED) {
//                // If don't have permission so prompt the user.
//                this.requestPermissions(
//                        new String[]{Manifest.permission.READ_PHONE_STATE},
//                        MY_PERMISSION_REQUEST_CODE_PHONE_STATE
//                );
//                return;
//            }
//        }
//        this.getPhoneNumbers();
//    }
//
//    // Need to ask user for permission: android.permission.READ_PHONE_STATE
//    @SuppressLint("MissingPermission")
//    private void getPhoneNumbers() {
//        try {
//            TelephonyManager manager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
//
//            String phoneNumber1 = manager.getLine1Number();
//
//            this.number_addnewadress.setText(phoneNumber1);
//
//            //
//
//            // Other Informations:
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) { // API Level 26.
//                String imei = manager.getImei();
//                int phoneCount = manager.getPhoneCount();
//            }
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) { // API Level 28.
//                SignalStrength signalStrength = manager.getSignalStrength();
//                int level = signalStrength.getLevel();
//
//            }
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    // When you have the request results
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String permissions[], int[] grantResults) {
//
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        //
//        switch (requestCode) {
//            case MY_PERMISSION_REQUEST_CODE_PHONE_STATE: {
//
//                // Note: If request is cancelled, the result arrays are empty.
//                // Permissions granted (SEND_SMS).
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    this.getPhoneNumbers();
//
//                }
//                // Cancelled or denied.
//                else {
//                }
//                break;
//            }
//        }
//    }
//
//
//    // When results returned
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == MY_PERMISSION_REQUEST_CODE_PHONE_STATE) {
//            if (resultCode == RESULT_OK) {
//                // Do something with data (Result returned).
//            } else if (resultCode == RESULT_CANCELED) {
//            } else {
//            }
//        }
//    }
}

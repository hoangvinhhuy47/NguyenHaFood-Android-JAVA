package com.example.nguyenhafood.Activity.Address;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import com.example.nguyenhafood.API.APIClient;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Model.AcountUser.DataUpdateAddressBook;
import com.example.nguyenhafood.Request.Acount_Login.GetAllWardRequest;
import com.example.nguyenhafood.Response.Acount_Login.GetAllWardResponse;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.Acount_Login.GetAllCityRequest;
import com.example.nguyenhafood.Response.Acount_Login.GetAllCityResponse;
import com.example.nguyenhafood.Request.Acount_Login.GetAllDistrictRequest;
import com.example.nguyenhafood.Request.Acount_Login.UpdateAddressBookRequest;
import com.example.nguyenhafood.Response.Acount_Login.GetAllDistrictResponse;
import com.example.nguyenhafood.Response.Acount_Login.UpdateAddressBookResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateAddressActivity extends AppCompatActivity {
    EditText name_user_addressupdate, phone_updateaddress, updateadresss, wards_updateadress;
    AppCompatSpinner city_address_update, alldictric_updateadresss, alldictric_updateward;
    RadioButton default_updateaddress;
    ArrayAdapter arrayAdapter;
    ArrayAdapter arrayAdapter1;
    ArrayAdapter arrayAdapter2;

    boolean ischecked = false;
    String a, b, c,d = "";
    Button btn_update_adress;
    TextView lenghtext;
    String idCity, idCountry, idDistric,idWard = "";
    ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addressupdate);
        btn_update_adress = findViewById(R.id.btn_update_adress);
        wards_updateadress = findViewById(R.id.wards_updateadress);
        name_user_addressupdate = findViewById(R.id.name_user_addressupdate);
        phone_updateaddress = findViewById(R.id.phone_updateaddress);
        updateadresss = findViewById(R.id.updateadresss);
        lenghtext = findViewById(R.id.lengh_text);
        default_updateaddress = findViewById(R.id.default_updateaddress);
        alldictric_updateadresss = findViewById(R.id.alldictric_updateadresss);
        alldictric_updateward = findViewById(R.id.alldictric_updateward);
        Intent intent = this.getIntent();
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        name_user_addressupdate.setText(intent.getStringExtra("FullName"));
        phone_updateaddress.setText(intent.getStringExtra("CellPhone"));
        updateadresss.setText(intent.getStringExtra("FullAddress"));
        city_address_update = findViewById(R.id.city_address_update);
        if (intent.getStringExtra("IsDefault").equals("true") == true) {
            default_updateaddress.setChecked(true);
        } else {
            default_updateaddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ischecked == true) {
                        default_updateaddress.setChecked(false);
                        ischecked = false;
                    } else {
                        default_updateaddress.setChecked(true);
                        ischecked = true;
                    }
                }
            });
        }
        name_user_addressupdate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                lenghtext.setText(String.valueOf(name_user_addressupdate.getText().toString().length()));
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (name_user_addressupdate.getText().toString().length() < 50) {
                    lenghtext.setText(String.valueOf(name_user_addressupdate.getText().toString().length()));
                }
            }
        });


        GetAllCityRequest getAllCityRequest = new GetAllCityRequest();
        GetAllCity(getAllCityRequest, intent.getStringExtra("CityID"), intent.getStringExtra("DistrictID"),intent.getStringExtra("WardID"));
        wards_updateadress.setText(intent.getStringExtra("Address"));
        c = wards_updateadress.getText().toString();
        wards_updateadress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                c = wards_updateadress.getText().toString();
                updateadresss.setText(c +","+d +" ," + b + " ," + a);
            }
        });
        btn_update_adress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataUpdateAddressBook dataUpdateAddressBook = new DataUpdateAddressBook();
                dataUpdateAddressBook.setAddressID(intent.getStringExtra("AddressID"));
                dataUpdateAddressBook.setObjectID(intent.getStringExtra("ObjectID"));
                dataUpdateAddressBook.setObjectType(intent.getStringExtra("ObjectType"));
                if (name_user_addressupdate.getText().toString().equals("") == true || phone_updateaddress.getText().toString().equals("") == true) {
                    phone_updateaddress.setError("Vui Lòng Nhập Đủ Dữ Liệu");
                    name_user_addressupdate.setError("Vui Lòng Nhập Đủ Dữ Liệu");
                } else {
                    dataUpdateAddressBook.setFullName(name_user_addressupdate.getText().toString());
                    dataUpdateAddressBook.setCellPhone(phone_updateaddress.getText().toString());

                    dataUpdateAddressBook.setCountryID(idCountry);
                    dataUpdateAddressBook.setCityID(idCity);
                    dataUpdateAddressBook.setDistrictID(idDistric);
                    dataUpdateAddressBook.setWardID(idWard);
                    dataUpdateAddressBook.setAddress(wards_updateadress.getText().toString());
                    dataUpdateAddressBook.setDefault(ischecked);
                    dataUpdateAddressBook.setSortOrder(intent.getStringExtra("SortOrder"));
                    UpdateAddressBookRequest updateAddressBookRequest = new UpdateAddressBookRequest();
                    updateAddressBookRequest.setAddressBook(dataUpdateAddressBook);
                    UpDateAddress(updateAddressBookRequest);
                }

            }
        });
    }

    public void UpDateAddress(UpdateAddressBookRequest updateAddressBookRequest) {
        Call<UpdateAddressBookResponse> updateAddressBookResponseCall = APIClient.updateAddressBookSerVice().updateAddressBook(Gobal.GuiID, updateAddressBookRequest);
        updateAddressBookResponseCall.enqueue(new Callback<UpdateAddressBookResponse>() {
            @Override
            public void onResponse(Call<UpdateAddressBookResponse> call, Response<UpdateAddressBookResponse> response) {
                if (response.isSuccessful()) {
                    UpdateAddressBookResponse updateAddressBookResponse = response.body();
                    if (updateAddressBookResponse.getStatusID() == 1) {
                        finish();
                    }
                    else {
                        Toast.makeText(getApplication(), "Cập Nhật Không Thành Công", Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<UpdateAddressBookResponse> call, Throwable t) {

            }
        });
    }

    public void GetAllWard(GetAllWardRequest getAllWardRequest,String WardID) {
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
                            alldictric_updateward.setAdapter(arrayAdapter2);
                            for (int i = 0; i < getAllWardResponse.getWardList().size(); i++) {
                                if (WardID.equals(getAllWardResponse.getWardList().get(i).getWardID()) == true) {
                                    alldictric_updateadresss.setSelection(i);
                                    d = getAllWardResponse.getWardList().get(i).getWardName();
                                    updateadresss.setText(c +","+d +" ," + b + " ," + a);
                                    idWard = getAllWardResponse.getWardList().get(i).getWardID();
                                    break;
                                }
                            }

                            alldictric_updateadresss.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    d = getAllWardResponse.getWardList().get(position).getWardName();
                                    idWard = getAllWardResponse.getWardList().get(position).getWardID();
                                    updateadresss.setText(c +","+d +" ," + b + " ," + a);
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                        }catch (Exception exception){}

                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllWardResponse> call, Throwable t) {

            }
        });
    }


    public void GetAllCity(GetAllCityRequest getAllCityRequest, String CityID, String DisctID,String WardID) {
        Call<GetAllCityResponse> getAllCityResponseCall = APIClient.getAllCitySerViceSerVice().GetAllCity(Gobal.GuiID, getAllCityRequest);
        getAllCityResponseCall.enqueue(new Callback<GetAllCityResponse>() {
            @Override
            public void onResponse(Call<GetAllCityResponse> call, Response<GetAllCityResponse> response) {
                if (response.isSuccessful()) {
                    GetAllCityResponse getAllCityResponse = response.body();
                    if (getAllCityResponse.getStatusID() == 1) {
                        arrayAdapter = new ArrayAdapter(getApplication(), R.layout.spinner_item, getAllCityResponse.getCityList());
                        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        city_address_update.setAdapter(arrayAdapter);

                        for (int i = 0; i < getAllCityResponse.getCityList().size(); i++) {
                            if (CityID.equals(getAllCityResponse.getCityList().get(i).getCityID()) == true) {
                                city_address_update.setSelection(i);
                                idCity = getAllCityResponse.getCityList().get(i).getCityID();
                                idCountry = getAllCityResponse.getCityList().get(i).getCountryID();
                                break;
                            }
                        }
                        city_address_update.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                GetAllDistrictRequest getAllDistrictRequest = new GetAllDistrictRequest();
                                getAllDistrictRequest.setCityID(getAllCityResponse.getCityList().get(position).getCityID());
                                GetAllDistrict(getAllDistrictRequest, DisctID,WardID);
                                updateadresss.setText(getAllCityResponse.getCityList().get(position).getCityName());
                                a = getAllCityResponse.getCityList().get(position).getCityName();
                                updateadresss.setText(c +","+d +" ," + b + " ," + a);
                                idCity = getAllCityResponse.getCityList().get(position).getCityID();
                                idCountry = getAllCityResponse.getCityList().get(position).getCountryID();

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllCityResponse> call, Throwable t) {
                Model.Dialog(getApplicationContext());
            }
        });
    }

    public void GetAllDistrict(GetAllDistrictRequest getAllDistrictRequest, String DisctID,String WardID) {
        Call<GetAllDistrictResponse> getAllDistrictRequestCall = APIClient.getAllDistrictSerVice().GetAllDistrict(Gobal.GuiID, getAllDistrictRequest);
        getAllDistrictRequestCall.enqueue(new Callback<GetAllDistrictResponse>() {
            @Override
            public void onResponse(Call<GetAllDistrictResponse> call, Response<GetAllDistrictResponse> response) {
                if (response.isSuccessful()) {
                    GetAllDistrictResponse getAllDistrictResponse = response.body();
                    if (getAllDistrictResponse.getStatusID() == 1) {
                        try {
                            arrayAdapter1 = new ArrayAdapter(getApplication(), R.layout.spinner_item, getAllDistrictResponse.getDistrictList());
                            arrayAdapter1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                            alldictric_updateadresss.setAdapter(arrayAdapter1);

                            for (int i = 0; i < getAllDistrictResponse.getDistrictList().size(); i++) {
                                if (DisctID.equals(getAllDistrictResponse.getDistrictList().get(i).getDistrictID()) == true) {
                                    alldictric_updateadresss.setSelection(i);
                                    b = getAllDistrictResponse.getDistrictList().get(i).getDistrictName();
                                    updateadresss.setText(c +","+d +" ," + b + " ," + a);
                                    idDistric = getAllDistrictResponse.getDistrictList().get(i).getDistrictID();
                                    break;
                                }
                            }

                            alldictric_updateadresss.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    b = getAllDistrictResponse.getDistrictList().get(position).getDistrictName();
                                    idDistric = getAllDistrictResponse.getDistrictList().get(position).getDistrictID();
                                    updateadresss.setText(c +","+d +" ," + b + " ," + a);
                                    GetAllWardRequest getAllWardRequest = new GetAllWardRequest();
                                    getAllWardRequest.setDistrictID(getAllDistrictResponse.getDistrictList().get(position).getDistrictID());
                                    GetAllWard(getAllWardRequest,WardID);
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                        } catch (Exception e) {
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllDistrictResponse> call, Throwable t) {
                Model.Dialog(getApplication());
            }
        });
    }
}

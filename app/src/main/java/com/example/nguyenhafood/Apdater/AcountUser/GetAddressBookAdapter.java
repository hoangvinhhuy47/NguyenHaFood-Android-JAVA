package com.example.nguyenhafood.Apdater.AcountUser;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenhafood.API.APIClient;
import com.example.nguyenhafood.Activity.Address.UpdateAddressActivity;
import com.example.nguyenhafood.Activity.GiaiDoanDatHang.HinhThucGiaoHang;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Interface.UpdateAddress;
import com.example.nguyenhafood.Model.AcountUser.DataAddressBook;
import com.example.nguyenhafood.Model.AcountUser.DataUpdateAddressBook;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.Acount_Login.DeleteAddressBookRequest;
import com.example.nguyenhafood.Request.Acount_Login.UpdateAddressBookRequest;
import com.example.nguyenhafood.Response.Acount_Login.DeleteAddressBookResponse;
import com.example.nguyenhafood.Response.Acount_Login.UpdateAddressBookResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class GetAddressBookAdapter extends RecyclerView.Adapter<GetAddressBookAdapter.MyViewHolder> {
    Context contex;
    List<DataAddressBook> dataAddressBooks = new ArrayList<>();
    UpdateAddress updateAddress;
    boolean show = false;

    public GetAddressBookAdapter(List<DataAddressBook> dataAddressBooks, Context contex, UpdateAddress updateAddress, boolean Show) {
        this.dataAddressBooks.addAll(dataAddressBooks);
        this.contex = contex;
        this.updateAddress = updateAddress;
        this.show = Show;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address, parent, false);
        return new GetAddressBookAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataAddressBook dataAddressBook = dataAddressBooks.get(position);
        holder.name_address.setText(dataAddressBook.getFullName());
        holder.address.setText(dataAddressBook.getFullAddress());
        holder.phone_address.setText(dataAddressBook.getCellPhone());
        if (show == true) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Gobal.ShowAddress = "Tên: "+dataAddressBook.getFullName() + "\n" +
                            "SĐT: "+dataAddressBook.getCellPhone() + "\n" +
                            "D/c: "+dataAddressBook.getFullAddress();
                    Intent intent = new Intent(contex, HinhThucGiaoHang.class);
                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    Gobal.dataOrders.ShipAddress=dataAddressBook.getFullAddress();
                    contex.getApplicationContext().startActivity(intent);
                }
            });
        }
        if (dataAddressBook.isDefault() == false) {
            holder.default_adress.setVisibility(View.GONE);
        }
        holder.menu_setting_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(contex, holder.menu_setting_address);
                popupMenu.inflate(R.menu.menu_addressbook);
                if (dataAddressBook.isDefault() == true) {
                    popupMenu.getMenu().findItem(R.id.default_adress_chose).setVisible(false);
                    popupMenu.getMenu().findItem(R.id.delete_adress).setVisible(false);
                }
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.default_adress_chose:
                                DataUpdateAddressBook dataUpdateAddressBook = new DataUpdateAddressBook();
                                dataUpdateAddressBook.setAddress(dataAddressBook.getAddress());
                                dataUpdateAddressBook.setAddressID(dataAddressBook.getAddressID());
                                dataUpdateAddressBook.setCellPhone(dataAddressBook.getCellPhone());
                                dataUpdateAddressBook.setCityID(dataAddressBook.getCityID());
                                dataUpdateAddressBook.setFullName(dataAddressBook.getFullName());
                                dataUpdateAddressBook.setDefault(true);
                                dataUpdateAddressBook.setCountryID(dataAddressBook.getCountryID());
                                dataUpdateAddressBook.setSortOrder(String.valueOf(dataAddressBook.getSortOrder()));
                                dataUpdateAddressBook.setObjectID(dataAddressBook.getObjectID());
                                dataUpdateAddressBook.setObjectType(dataAddressBook.getObjectType());
                                dataUpdateAddressBook.setDistrictID(dataAddressBook.getDistrictID());
                                UpdateAddressBookRequest updateAddressBookRequest = new UpdateAddressBookRequest();
                                updateAddressBookRequest.setAddressBook(dataUpdateAddressBook);
                                UpdateAddressBook(updateAddressBookRequest);
                                return false;
                            case R.id.setting_adress:
                                Intent intent = new Intent(contex, UpdateAddressActivity.class);
                                intent.putExtra("AddressID", dataAddressBook.getAddressID());
                                intent.putExtra("ObjectID", dataAddressBook.getObjectID());
                                intent.putExtra("ObjectType", dataAddressBook.getObjectType());
                                intent.putExtra("FullAddress", dataAddressBook.getFullAddress());
                                intent.putExtra("CountryID", dataAddressBook.getCountryID());
                                intent.putExtra("DistrictID", dataAddressBook.getDistrictID());
                                intent.putExtra("CellPhone", dataAddressBook.getCellPhone());
                                intent.putExtra("Address", dataAddressBook.getAddress());
                                intent.putExtra("IsDefault", String.valueOf(dataAddressBook.isDefault()));
                                intent.putExtra("SortOrder", String.valueOf(dataAddressBook.getSortOrder()));
                                intent.putExtra("CityID", String.valueOf(dataAddressBook.getCityID()));
                                intent.putExtra("FullName", dataAddressBook.getFullName());
                                intent.putExtra("WardID",dataAddressBook.getWardID());
                                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                                contex.getApplicationContext().startActivity(intent);
                                return false;
                            case R.id.delete_adress:
                                Dialog dialog = new Dialog(v.getContext());
                                dialog.setContentView(R.layout.dialog_cartdelete);
                                dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                dialog.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
                                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                dialog.show();
                                TextView text_thongbao = dialog.findViewById(R.id.text_thongbao);
                                ImageView img_dialog_eixt = dialog.findViewById(R.id.img_dialog_eixt);
                                Button accept_dialog = dialog.findViewById(R.id.accept_dialog);
                                Button refuse_dialog = dialog.findViewById(R.id.refuse_dialog);
                                text_thongbao.setText("Bạn Có Muốn Xóa Địa Chỉ Này Không?");
                                dialog.getWindow().setGravity(Gravity.BOTTOM);
                                img_dialog_eixt.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });
                                refuse_dialog.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });
                                accept_dialog.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        DeleteAddressBookRequest deleteAddressBookRequest = new DeleteAddressBookRequest();
                                        deleteAddressBookRequest.setAddressID(dataAddressBook.getAddressID());
                                        DeleteAddressBook(deleteAddressBookRequest);
                                        updateAddress.UpdateAddressBook(position);
                                        dialog.dismiss();
                                    }
                                });
                                return false;
                        }
                        return true;
                    }

                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataAddressBooks.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name_address, phone_address, address, default_adress;
        ImageView menu_setting_address;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            menu_setting_address = itemView.findViewById(R.id.menu_setting_address);
            name_address = itemView.findViewById(R.id.name_address);
            phone_address = itemView.findViewById(R.id.phone_address);
            address = itemView.findViewById(R.id.address);
            default_adress = itemView.findViewById(R.id.default_adress);
        }
    }

    public void UpdateAddressBook(UpdateAddressBookRequest updateAddressBookRequest) {
        Call<UpdateAddressBookResponse> updateAddressBookResponseCall = APIClient.updateAddressBookSerVice().updateAddressBook(Gobal.GuiID, updateAddressBookRequest);
        updateAddressBookResponseCall.enqueue(new Callback<UpdateAddressBookResponse>() {
            @Override
            public void onResponse(Call<UpdateAddressBookResponse> call, Response<UpdateAddressBookResponse> response) {
                if (response.isSuccessful()) {
                    UpdateAddressBookResponse updateAddressBookResponse = response.body();
                    if (updateAddressBookResponse.getStatusID() == 1) {
                        updateAddress.UpdateAddressBook(1);
                    }
                }
            }

            @Override
            public void onFailure(Call<UpdateAddressBookResponse> call, Throwable t) {
                Model.Dialog(contex);
            }
        });
    }

    public void DeleteAddressBook(DeleteAddressBookRequest deleteAddressBookRequest) {
        Call<DeleteAddressBookResponse> deleteAddressBookResponseCall = APIClient.deleteAddressBookSerVice().DeleteAddressBook(Gobal.GuiID, deleteAddressBookRequest);
        deleteAddressBookResponseCall.enqueue(new Callback<DeleteAddressBookResponse>() {
            @Override
            public void onResponse(Call<DeleteAddressBookResponse> call, Response<DeleteAddressBookResponse> response) {
                if (response.isSuccessful()) {
                    DeleteAddressBookResponse deleteAddressBookResponse = response.body();
                    if (deleteAddressBookResponse.getStatusID() == 1) {
                        updateAddress.UpdateAddressBook(1);
                    }
                }
            }

            @Override
            public void onFailure(Call<DeleteAddressBookResponse> call, Throwable t) {
                Model.Dialog(contex);
            }
        });
    }
}

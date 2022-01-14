package com.example.nguyenhafood.Apdater.ViewCart;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenhafood.API.APIClient;
import com.example.nguyenhafood.Database.DBHelper;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Home.ImfomationProductActivity;
import com.example.nguyenhafood.Interface.UpdateViewCard;
import com.example.nguyenhafood.Model.AddPromotion.DataGetPromotionCode;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.Model.ViewCart.GetCard;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.ViewCart.AddProductBuyLaterRequest;
import com.example.nguyenhafood.Request.Acount_Login.RemoveProdcutRequest;
import com.example.nguyenhafood.Request.ViewCart.UpdateQuantityToCartRequest;
import com.example.nguyenhafood.Response.ViewCart.AddProductBuyLaterResponse;
import com.example.nguyenhafood.Response.ViewCart.RemoveProductResponse;
import com.example.nguyenhafood.Response.ViewCart.UpdateQuanTityToCartResponse;
import com.github.ybq.android.spinkit.SpinKitView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class GetCartAdapter extends RecyclerView.Adapter<GetCartAdapter.MyViewHolder> {
    List<GetCard> datalist = new ArrayList<>();
    UpdateViewCard updateViewCard;
    private Context context;
    DBHelper dbHelper;
    boolean TilePromotionCode;
    DataGetPromotionCode dataGetPromotionCode = new DataGetPromotionCode();

    public GetCartAdapter(List<GetCard> datalist, Context context, UpdateViewCard updateViewCard, boolean TilePromotionCode, DataGetPromotionCode dataGetPromotionCode) {
        this.datalist.addAll(datalist);
        this.context = context;
        this.updateViewCard = updateViewCard;
        this.TilePromotionCode = TilePromotionCode;
        this.dataGetPromotionCode = dataGetPromotionCode;
    }

    @NonNull
    @Override
    public GetCartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viewcart, parent, false);
        dbHelper = new DBHelper(context);
        return new GetCartAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GetCartAdapter.MyViewHolder holder, int position) {
        GetCard db = datalist.get(position);
        holder.Name_viewcard.setText(db.getItemName());
        holder.qualiti_viewcard.setText(String.valueOf(db.getQuantity()));
        Picasso.with(context)
                .load(Gobal.IDImage + db.getImage())
                .fit()
                .centerCrop()
                .error(R.drawable.imageerro)
                .into(holder.img_viewcard);


        /////
        holder.delete_viewcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                dialog.getWindow().setGravity(Gravity.BOTTOM);
                text_thongbao.setText("Bạn Có Muốn Xóa SP Này Không?");
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
                        holder.progessbar_flus.setVisibility(View.VISIBLE);
                        holder.delete_viewcard.setFocusable(false);
                        CountDownTimer countDownTimer = new CountDownTimer(800, 200) {
                            @Override
                            public void onTick(long millisUntilFinished) {

                            }
                            @Override
                            public void onFinish() {

                                RemoveProdcutRequest removeProdcutRequest = new RemoveProdcutRequest();
                                removeProdcutRequest.setCartDetailID(String.valueOf(db.getDetailID()));
                                holder.spin_kit_cart.setVisibility(View.VISIBLE);
                                RemoveOnCare(removeProdcutRequest);
                                holder.spin_kit_cart.setVisibility(View.GONE);
                                Gobal.SizeProduct = Gobal.SizeProduct - db.getQuantity();
                                updateViewCard.CallBackViewCard(Integer.parseInt(String.valueOf(Math.round(db.getAmount()))),TilePromotionCode);
                                datalist.remove(position);
                                notifyDataSetChanged();
                                dialog.dismiss();
                                holder.progessbar_flus.setVisibility(View.GONE);
                                holder.delete_viewcard.setFocusable(true);

                            }
                        }.start();


                    }
                });

            }
        });
        holder.flus_viewcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.progessbar_flus.setVisibility(View.VISIBLE);
                holder.flus_viewcard.setFocusable(false);
                CountDownTimer countDownTimer = new CountDownTimer(800, 200) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        try {

                            Gobal.SizeProduct = Gobal.SizeProduct + 1;
                            UpdateQuantityToCartRequest updateQuantityToCartRequest = new UpdateQuantityToCartRequest();
                            updateQuantityToCartRequest.setCartDetailID(String.valueOf(db.getDetailID()));
                            updateQuantityToCartRequest.setQuantity(String.valueOf(Integer.parseInt(holder.qualiti_viewcard.getText().toString()) + 1));
                            UpdateQuantityToCart(updateQuantityToCartRequest);
                            updateViewCard.CallBackProductItem(db.getPrice(), true,TilePromotionCode);
                            holder.qualiti_viewcard.setText(String.valueOf(Integer.parseInt(holder.qualiti_viewcard.getText().toString()) + 1));
                            holder.totalprice_product_cart.setText(Model.ChangeDecimal(Double.parseDouble(holder.totalprice.getText().toString()) + db.getPrice()));
                            holder.totalprice.setText(String.valueOf(Double.parseDouble(String.valueOf(holder.totalprice.getText().toString())) + db.getPrice()));
                            holder.progessbar_flus.setVisibility(View.GONE);
                            holder.flus_viewcard.setFocusable(true);
                            notifyDataSetChanged();
                        }catch (Exception exception){

                        }
                    }
                }.start();

            }
        });
        holder.add_product_buylater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddProductBuyLaterRequest addProductBuyLaterRequest = new AddProductBuyLaterRequest();
                addProductBuyLaterRequest.setUserID(dbHelper.GetDL());
                addProductBuyLaterRequest.setProductID(db.getItemID());
                addProductBuyLaterRequest.setSkuID(db.getSkuID());
                holder.spin_kit_cart.setVisibility(View.VISIBLE);
                AddProductBuyLate(addProductBuyLaterRequest, db.getQuantity(), db.getAmount(), position);
                holder.spin_kit_cart.setVisibility(View.GONE);
            }
        });
        holder.refuse_viewcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.progessbar_flus.setVisibility(View.VISIBLE);
                holder.refuse_viewcard.setFocusable(false);
                CountDownTimer countDownTimer = new CountDownTimer(800, 200) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        Gobal.SizeProduct = Gobal.SizeProduct - 1;
                        UpdateQuantityToCartRequest updateQuantityToCartRequest = new UpdateQuantityToCartRequest();
                        updateQuantityToCartRequest.setCartDetailID(String.valueOf(db.getDetailID()));
                        updateQuantityToCartRequest.setQuantity(String.valueOf(Integer.parseInt(holder.qualiti_viewcard.getText().toString()) - 1));
                        if (updateQuantityToCartRequest.getQuantity().equals("0") == true) {
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
                            dialog.getWindow().setGravity(Gravity.BOTTOM);
                            text_thongbao.setText("Bạn Có Muốn Xóa SP Này Không ?");
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
                                    RemoveProdcutRequest removeProdcutRequest = new RemoveProdcutRequest();
                                    removeProdcutRequest.setCartDetailID(String.valueOf(db.getDetailID()));
                                    RemoveOnCare(removeProdcutRequest);
                                    updateViewCard.CallBackViewCard(Integer.parseInt(String.valueOf(Math.round(db.getAmount()))),TilePromotionCode);
                                    datalist.remove(position);
                                    notifyDataSetChanged();
                                    dialog.dismiss();
                                }
                            });
                        } else {

                            holder.qualiti_viewcard.setText(String.valueOf(Integer.parseInt(holder.qualiti_viewcard.getText().toString()) - 1));
                            updateViewCard.CallBackProductItem(db.getPrice(), false,TilePromotionCode);
                            UpdateQuantityToCart(updateQuantityToCartRequest);
                            holder.totalprice_product_cart.setText(Model.ChangeDecimal(Double.parseDouble(holder.totalprice.getText().toString()) - db.getPrice()));
                            holder.totalprice.setText(String.valueOf(Double.parseDouble(String.valueOf(holder.totalprice.getText().toString())) - db.getPrice()));
                        }
                        holder.refuse_viewcard.setFocusable(false);
                        holder.progessbar_flus.setVisibility(View.GONE);
                        notifyDataSetChanged();

                    }
                }.start();

            }
        });
        holder.Name_viewcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ImfomationProductActivity.class);
                intent.putExtra("ProductID", db.getItemID());
                intent.putExtra("SkuID", db.getSkuID());
                intent.putExtra("Image", db.getImage());
                intent.putExtra("PriceAdd", String.valueOf(db.getPrice()));
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);
            }
        });
        if (TilePromotionCode == false) {
            holder.totalprice.setText(String.valueOf(db.getAmount()));
            if (String.valueOf(db.getPrice()).equals(String.valueOf(db.getOrgPrice())) == true) {
                holder.sellprice_product_cart.setText(Model.ChangeDecimal(db.getPrice()));
                holder.saleprice_product_cart.setVisibility(View.GONE);
                holder.specialprice_product_viewcart.setVisibility(View.GONE);
            } else {
                holder.specialprice_product_viewcart.setText("- " + String.valueOf(db.getSpecialPrice()) + "%");
                holder.sellprice_product_cart.setText(Model.ChangeDecimal(db.getPrice()));
                holder.saleprice_product_cart.setText(Model.ChangeDecimal(db.getOrgPrice()));
                holder.saleprice_product_cart.setPaintFlags(holder.saleprice_product_cart.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                holder.saleprice_product_cart.setText(Model.ChangeDecimal(db.getOrgPrice()));
            }
            holder.totalprice_product_cart.setText(Model.ChangeDecimal(db.getAmount()));
        } else {
            holder.totalprice.setText(String.valueOf(db.getAmount()));
            holder.totalprice_product_cart.setText(Model.ChangeDecimal(db.getAmount()));

            if (String.valueOf(db.getPrice()).equals(String.valueOf(db.getOrgPrice())) == true) {
                holder.sellprice_product_cart.setText(Model.ChangeDecimal(db.getPrice()));
                holder.saleprice_product_cart.setVisibility(View.GONE);
                holder.specialprice_product_viewcart.setVisibility(View.GONE);
            } else {
                holder.specialprice_product_viewcart.setText("- " + String.valueOf(db.getSpecialPrice()) + "%");
                holder.sellprice_product_cart.setText(Model.ChangeDecimal(db.getPrice()));
                holder.saleprice_product_cart.setText(Model.ChangeDecimal(db.getOrgPrice()));
                holder.saleprice_product_cart.setPaintFlags(holder.saleprice_product_cart.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                holder.saleprice_product_cart.setText(Model.ChangeDecimal(db.getOrgPrice()));
            }
            try {
                for (int j = 0; j < dataGetPromotionCode.getPromotionItems().size(); j++) {
                    if (db.getSkuProductID().equals(dataGetPromotionCode.getPromotionItems().get(j).getProductID())) {
                        holder.totalprice_product_cart.setText(Model.ChangeDecimal(db.getAmount() - dataGetPromotionCode.getPromotionItems().get(j).getDiscountAmount()));
                    }

                }

            } catch (Exception exception) {
            }

        }
    }


    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progessbar_flus;
        ImageView img_viewcard;
        TextView Name_viewcard, qualiti_viewcard, totalprice_product_cart, sellprice_product_cart, saleprice_product_cart, specialprice_product_viewcart, totalprice;
        ImageView delete_viewcard;
        Button refuse_viewcard, flus_viewcard;
        TextView add_product_buylater;
        SpinKitView spin_kit_cart;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            progessbar_flus = itemView.findViewById(R.id.progessbar_flus);
            img_viewcard = itemView.findViewById(R.id.img_viewcard);
            Name_viewcard = itemView.findViewById(R.id.Name_viewcard);
            qualiti_viewcard = itemView.findViewById(R.id.qualiti_viewcard);
            delete_viewcard = itemView.findViewById(R.id.delete_viewcard);
            refuse_viewcard = itemView.findViewById(R.id.refuse_viewcard);
            saleprice_product_cart = itemView.findViewById(R.id.saleprice_product_cart);
            flus_viewcard = itemView.findViewById(R.id.flus_viewcard);
            totalprice_product_cart = itemView.findViewById(R.id.totalprice_product_cart);
            sellprice_product_cart = itemView.findViewById(R.id.sellprice_product_cart);
            add_product_buylater = itemView.findViewById(R.id.add_product_buylater);
            specialprice_product_viewcart = itemView.findViewById(R.id.specialprice_product_viewcart);
            totalprice = itemView.findViewById(R.id.totalprice);
            spin_kit_cart = itemView.findViewById(R.id.spin_kit_cart);
        }
    }

    public void AddProductBuyLate(AddProductBuyLaterRequest addProductBuyLaterRequest, int a, double price, int position) {
        Call<AddProductBuyLaterResponse> addProductBuyLaterResponseCall = APIClient.addProductLaterBuySerVice().AddProductLaterBuy(Gobal.GuiID, addProductBuyLaterRequest);
        addProductBuyLaterResponseCall.enqueue(new Callback<AddProductBuyLaterResponse>() {
            @Override
            public void onResponse(Call<AddProductBuyLaterResponse> call, Response<AddProductBuyLaterResponse> response) {
                if (response.isSuccessful()) {
                    AddProductBuyLaterResponse addProductBuyLaterResponse = response.body();
                    if (addProductBuyLaterResponse.getStatusID() == 1) {
                        Gobal.SizeProduct = Gobal.SizeProduct - a;
                        updateViewCard.CallBackViewCard(Integer.parseInt(String.valueOf(Math.round(price))),TilePromotionCode);
                        datalist.remove(position);
                        notifyDataSetChanged();
                    } else {
                        Toast.makeText(context, "Không Thành Công", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<AddProductBuyLaterResponse> call, Throwable t) {

            }
        });
    }

    public void RemoveOnCare(RemoveProdcutRequest removeProdcutRequest) {
        Call<RemoveProductResponse> removeProductResponseCall = APIClient.removeSerVice().RemoveProduct(Gobal.GuiID, removeProdcutRequest);
        removeProductResponseCall.enqueue(new Callback<RemoveProductResponse>() {
            @Override
            public void onResponse(Call<RemoveProductResponse> call, Response<RemoveProductResponse> response) {
                if (response.isSuccessful()) {
                    RemoveProductResponse removeProductResponse = response.body();
                    if (removeProductResponse.getStatusID() == 1) {

                    } else {
                        Toast.makeText(context, " Không Thành Công", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RemoveProductResponse> call, Throwable t) {

                Model.Dialog(context);
            }
        });
    }

    public void UpdateQuantityToCart(UpdateQuantityToCartRequest updateQuantityToCartRequest) {
        Call<UpdateQuanTityToCartResponse> updateQuanTityToCartResponseCall = APIClient.updateQuantityToCartSerVice().UDQuantityToCart(Gobal.GuiID, updateQuantityToCartRequest);
        updateQuanTityToCartResponseCall.enqueue(new Callback<UpdateQuanTityToCartResponse>() {
            @Override
            public void onResponse(Call<UpdateQuanTityToCartResponse> call, Response<UpdateQuanTityToCartResponse> response) {
                UpdateQuanTityToCartResponse updateQuanTityToCartResponse = response.body();
                if (updateQuanTityToCartResponse.getStatusID() == 1) {
                    Toast.makeText(context, "Thành Công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, " Không Thành Công", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UpdateQuanTityToCartResponse> call, Throwable t) {

            }
        });
    }
}



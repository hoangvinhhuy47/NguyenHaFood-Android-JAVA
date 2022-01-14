package com.example.nguyenhafood.Apdater.Acount_Login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenhafood.API.APIClient;
import com.example.nguyenhafood.Database.DBHelper;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Home.ImfomationProductActivity;
import com.example.nguyenhafood.Interface.UpdateProductbuylateView;
import com.example.nguyenhafood.Model.Home.DataProductViewList;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.Acount_Login.ChangeProductLaterBuyToCartRequest;
import com.example.nguyenhafood.Response.Acount_Login.ChangeProductLaterBuyToCartResponse;
import com.github.ybq.android.spinkit.SpinKitView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class GetProductLaterBuyAdapter extends RecyclerView.Adapter<GetProductLaterBuyAdapter.MyViewHolder> {
    List<DataProductViewList> dataProductViewLists = new ArrayList<>();
    private Context context;
    DBHelper dbHelper;

    UpdateProductbuylateView updateProductbuylateView;
    public GetProductLaterBuyAdapter(List<DataProductViewList> dataProductViewLists, Context context,UpdateProductbuylateView updateProductbuylateView) {
        this.dataProductViewLists.addAll(dataProductViewLists);
        this.context = context;
        this.updateProductbuylateView = updateProductbuylateView;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_productspurchasedlater, parent, false);
        dbHelper = new DBHelper(context);
        return new GetProductLaterBuyAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataProductViewList db = dataProductViewLists.get(position);
        Picasso.with(context)
                .load(Gobal.IDImage + db.getImage())
                .fit().centerCrop()
                .error(R.drawable.imageerro)
                .into(holder.image_productbuylater);
        holder.name_product_buylater.setText(db.getItemName());
        if (String.valueOf(db.getPrice()).equals(String.valueOf(db.getOrgPrice())) == true) {
            holder.sellprice_productbuylate.setText(Model.ChangeDecimal(db.getPrice()));
            holder.sale_price_productbuylate.setVisibility(View.GONE);
            holder.special_price_productbuylater.setVisibility(View.GONE);
        } else {
            holder.special_price_productbuylater.setText("- " + String.valueOf(db.getSpecialPrice()) + "%");
            holder.sellprice_productbuylate.setText(Model.ChangeDecimal(db.getOrgPrice()));
            holder.sale_price_productbuylate.setText(Model.ChangeDecimal(db.getPrice()));
            holder.sale_price_productbuylate.setPaintFlags(holder.sale_price_productbuylate.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.sale_price_productbuylate.setText(Model.ChangeDecimal(db.getPrice()));
        }
        holder.see_imfomation_productbuylater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ImfomationProductActivity.class);
                intent.putExtra("ProductID", db.getItemID());
                intent.putExtra("SkuID", db.getSkuID());
                intent.putExtra("Image", db.getImage());
                intent.putExtra("PriceAdd", String.valueOf(Model.showprice1(db.getPrice(), db.getSalePrice())));
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);
            }
        });
        holder.add_tocart_productbuylater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.spin_kit_product_later.setVisibility(View.VISIBLE);
                Gobal.SizeProduct=Gobal.SizeProduct+db.getQuantity();
                CountDownTimer countDownTimer = new CountDownTimer(1000, 50) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }
                    @Override
                    public void onFinish() {
                        ChangeProductLaterBuyToCartRequest changeProductLaterBuyToCartRequest = new ChangeProductLaterBuyToCartRequest();
                        changeProductLaterBuyToCartRequest.setUserID(dbHelper.GetDL());
                        changeProductLaterBuyToCartRequest.setProductID(db.getItemID());
                        changeProductLaterBuyToCartRequest.setSkuID(db.getSkuID());
                        ChangeProductLaterBuyToCart(changeProductLaterBuyToCartRequest);
                        dataProductViewLists.remove(position);
                        notifyDataSetChanged();
                        holder.spin_kit_product_later.setVisibility(View.GONE);
                        updateProductbuylateView.UpDateview(position);
                    }
                }.start();
            }
        });
    }

public void LoadMore(List<DataProductViewList> lists){
        dataProductViewLists.addAll(lists);
        notifyDataSetChanged();
}
    @Override
    public int getItemCount() {
        return dataProductViewLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image_productbuylater;
        TextView name_product_buylater, special_price_productbuylater, sellprice_productbuylate, sale_price_productbuylate;
        ImageView see_imfomation_productbuylater, add_tocart_productbuylater;
        SpinKitView spin_kit_product_later;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sale_price_productbuylate = itemView.findViewById(R.id.sale_price_productbuylate);
            image_productbuylater = itemView.findViewById(R.id.image_productbuylater);
            name_product_buylater = itemView.findViewById(R.id.name_product_buylater);
            special_price_productbuylater = itemView.findViewById(R.id.special_price_productbuylater);
            sellprice_productbuylate = itemView.findViewById(R.id.sellprice_productbuylate);
            add_tocart_productbuylater = itemView.findViewById(R.id.add_tocart_productbuylater);
            see_imfomation_productbuylater = itemView.findViewById(R.id.see_imfomation_productbuylater);
            spin_kit_product_later = itemView.findViewById(R.id.spin_kit_product_later);
        }
    }

    public void ChangeProductLaterBuyToCart(ChangeProductLaterBuyToCartRequest changeProductLaterBuyToCartRequest) {
        Call<ChangeProductLaterBuyToCartResponse> changeProductLaterBuyToCartResponseCall = APIClient.changeProductLaterBuyToCartService().ChangeProductLaterBuyToCart(Gobal.GuiID, changeProductLaterBuyToCartRequest);
        changeProductLaterBuyToCartResponseCall.enqueue(new Callback<ChangeProductLaterBuyToCartResponse>() {
            @Override
            public void onResponse(Call<ChangeProductLaterBuyToCartResponse> call, Response<ChangeProductLaterBuyToCartResponse> response) {
                if (response.isSuccessful()) {
                    ChangeProductLaterBuyToCartResponse changeProductLaterBuyToCartResponse = response.body();
                    if (changeProductLaterBuyToCartResponse.getStatusID() == 1) {
                    } else {
                    }
                }
            }

            @Override
            public void onFailure(Call<ChangeProductLaterBuyToCartResponse> call, Throwable t) {

            }
        });
    }
}

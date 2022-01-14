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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenhafood.Database.DBHelper;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Home.ImfomationProductActivity;
import com.example.nguyenhafood.Interface.UpdateFavoriteView;
import com.example.nguyenhafood.Model.Acount_Login.DataProductReview;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.Acount_Login.AddRemoveFavoriteRequest;
import com.example.nguyenhafood.Request.ViewCart.AddToCardRequest;
import com.github.ybq.android.spinkit.SpinKitView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static com.example.nguyenhafood.Home.ImfomationProductActivity.AddProductFavorite;

public class ProductFavoriteAdpater extends RecyclerView.Adapter<ProductFavoriteAdpater.MyViewHolder> {
    List<DataProductReview> datalist = new ArrayList<>();
    Context context;
    DBHelper dbHelper;
    UpdateFavoriteView updateFavoriteView;

    public ProductFavoriteAdpater(List<DataProductReview> datalist, Context context, UpdateFavoriteView updateFavoriteView) {
        this.datalist.addAll(datalist);
        this.context = context;
        this.updateFavoriteView = updateFavoriteView;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favoriteproducts, parent, false);
        dbHelper = new DBHelper(context);
        return new ProductFavoriteAdpater.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataProductReview db = datalist.get(position);

        Picasso.with(context)
                .load(Gobal.IDImage + db.getImage())
                .fit().centerCrop()
                .error(R.drawable.imageerro)
                .into(holder.image_product_favorite);
        holder.name_product_favorite.setText(db.getItemName());
        if (String.valueOf(db.getPrice()).equals(String.valueOf(db.getSalePrice())) == true) {
            holder.sellprice_product_favorite.setText(Model.ChangeDecimal(db.getPrice()));
            holder.sale_price_product_favorite.setVisibility(View.GONE);
            holder.special_price_product_favorite.setVisibility(View.GONE);
        } else {
            holder.special_price_product_favorite.setText("- " + String.valueOf(db.getSpecialPrice()) + "%");
            holder.sellprice_product_favorite.setText(Model.ChangeDecimal(db.getSalePrice()));
            holder.sale_price_product_favorite.setText(Model.ChangeDecimal(db.getPrice()));
            holder.sale_price_product_favorite.setPaintFlags(holder.sale_price_product_favorite.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.sale_price_product_favorite.setText(Model.ChangeDecimal(db.getPrice()));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
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
        holder.addcart_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gobal.SizeProduct=Gobal.SizeProduct+1;
                 updateFavoriteView.UpdateSize(position);
                if (Gobal.getLoginStatus() == 0) {
                    AddToCardRequest addToCardRequest = new AddToCardRequest();
                    addToCardRequest.setUserID(dbHelper.GetDL());
                    addToCardRequest.setIsLogin("False");
                    addToCardRequest.setProductID(db.getItemID());
                    addToCardRequest.setPrice(String.valueOf(Model.showprice1(db.getPrice(), db.getSalePrice())));
                    addToCardRequest.setQuantity("1");
                    addToCardRequest.setSkuID(db.getSkuID());
                    Model.addToCard(addToCardRequest, v.getContext(),db.getImage(),db.getItemName(),db.getPrice(),db.getSalePrice());

                } else {
                    AddToCardRequest addToCardRequest = new AddToCardRequest();
                    addToCardRequest.setUserID(dbHelper.GetDL());
                    addToCardRequest.setIsLogin("True");
                    addToCardRequest.setProductID(db.getItemID());
                    addToCardRequest.setPrice(String.valueOf(Model.showprice1(db.getPrice(), db.getSalePrice())));
                    addToCardRequest.setQuantity("1");
                    addToCardRequest.setSkuID(db.getSkuID());
                    Model.addToCard(addToCardRequest, v.getContext(),db.getImage(),db.getItemName(),db.getPrice(),db.getSalePrice());

                }

            }
        });
        holder.imfomation_favorite.setOnClickListener(new View.OnClickListener() {
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
        holder.delete_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.spin_kit_product_favorite.setVisibility(View.VISIBLE);
                CountDownTimer countDownTimer = new CountDownTimer(1000, 50) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }
                    @Override
                    public void onFinish() {
                        AddRemoveFavoriteRequest addRemoveFavoriteRequest = new AddRemoveFavoriteRequest();
                        addRemoveFavoriteRequest.setProductID(db.getItemID());
                        addRemoveFavoriteRequest.setSkuID(db.getSkuID());
                        addRemoveFavoriteRequest.setUserID(dbHelper.GetDL());
                        AddProductFavorite(addRemoveFavoriteRequest, context);
                        datalist.remove(position);
                        notifyDataSetChanged();
                        holder.spin_kit_product_favorite.setVisibility(View.GONE);
                        updateFavoriteView.UpDateview(position);
                    }
                }.start();
            }
        });
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }
    public void update (List<DataProductReview> db){
        datalist.addAll(db);
        notifyDataSetChanged();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image_product_favorite;
        TextView name_product_favorite, sellprice_product_favorite, sale_price_product_favorite, special_price_product_favorite;
        ImageView imfomation_favorite, addcart_favorite, delete_favorite;
        SpinKitView spin_kit_product_favorite;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            spin_kit_product_favorite = itemView.findViewById(R.id.spin_kit_product_favorite);
            special_price_product_favorite = itemView.findViewById(R.id.special_price_product_favorite);
            sale_price_product_favorite = itemView.findViewById(R.id.sale_price_product_favorite);
            sellprice_product_favorite = itemView.findViewById(R.id.sellprice_product_favorite);
            name_product_favorite = itemView.findViewById(R.id.name_product_favorite);
            image_product_favorite = itemView.findViewById(R.id.image_product_favorite);
            imfomation_favorite = itemView.findViewById(R.id.imfomation_favorite);
            addcart_favorite = itemView.findViewById(R.id.addcart_favorite);
            delete_favorite = itemView.findViewById(R.id.delete_favorite);
        }
    }
}

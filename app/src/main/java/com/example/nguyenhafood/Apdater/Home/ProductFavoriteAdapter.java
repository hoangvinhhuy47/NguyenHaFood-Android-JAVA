package com.example.nguyenhafood.Apdater.Home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
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
import com.example.nguyenhafood.Interface.UpdateSizeCart;
import com.example.nguyenhafood.Model.Home.vw_Favorite;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.ViewCart.AddToCardRequest;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class ProductFavoriteAdapter extends RecyclerView.Adapter<ProductFavoriteAdapter.MyViewHolder> {
    List<vw_Favorite> datalist = new ArrayList<>();
    Context context;
    DBHelper dbHelper;
    UpdateSizeCart updateSizeCart;
    public ProductFavoriteAdapter(List<vw_Favorite> datalist, Context context,UpdateSizeCart updateSizeCart) {
        this.datalist.addAll(datalist);
        this.context = context;
        this.updateSizeCart = updateSizeCart;
    }

    @NonNull
    @Override
    public ProductFavoriteAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listfavorite_products, parent, false);
        dbHelper = new DBHelper(context);
        return new ProductFavoriteAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ProductFavoriteAdapter.MyViewHolder holder, int position) {
        vw_Favorite db = datalist.get(position);
        holder.tensanpham_favorite.setText(db.getItemName());
        Picasso.with(context)
                .load(Gobal.IDImage + db.getImage())
                .fit().centerCrop()
                .error(R.drawable.imageerro)
                .into(holder.img_listfavorite);

            holder.reviewvalue_favorite.setText(String.valueOf(db.getReviewValue()));

        if (String.valueOf(db.getPrice()).equals(String.valueOf(db.getSalePrice())) == true) {
            holder.price_favorite.setText(Model.ChangeDecimal(db.getPrice()));
            holder.salepice_favorite.setVisibility(View.GONE);
            holder.specialprice_favorite.setVisibility(View.GONE);
        } else {
            holder.specialprice_favorite.setText("- " + String.valueOf(db.getSpecialPrice()) + "%");
            holder.price_favorite.setText(Model.ChangeDecimal(db.getSalePrice()));
            holder.salepice_favorite.setText(Model.ChangeDecimal(db.getPrice()));
            holder.salepice_favorite.setPaintFlags(holder.salepice_favorite.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            //            holder.salepricesea.setTextColor(Color.parseColor("#CA0000"));
            holder.salepice_favorite.setText(Model.ChangeDecimal(db.getPrice()));
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
        holder.add_productfavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gobal.SizeProduct = Gobal.SizeProduct+1;
                updateSizeCart.UpdateSize(position);
                if (Gobal.getLoginStatus() == 0) {
                    AddToCardRequest addToCardRequest = new AddToCardRequest();
                    addToCardRequest.setUserID(dbHelper.GetDL());
                    addToCardRequest.setIsLogin("False");
                    addToCardRequest.setProductID(db.getItemID());
                    addToCardRequest.setPrice(String.valueOf(Model.showprice1(db.getPrice(), db.getSalePrice())));
                    addToCardRequest.setQuantity("1");
                    addToCardRequest.setSkuID(db.getSkuID());
                    Model.addToCard(addToCardRequest, context,db.getImage(),db.getItemName(),db.getPrice(),db.getSalePrice());
                } else {
                    AddToCardRequest addToCardRequest = new AddToCardRequest();
                    addToCardRequest.setUserID(dbHelper.GetDL());
                    addToCardRequest.setIsLogin("True");
                    addToCardRequest.setProductID(db.getItemID());
                    addToCardRequest.setPrice(String.valueOf(Model.showprice1(db.getPrice(), db.getSalePrice())));
                    addToCardRequest.setQuantity("1");
                    addToCardRequest.setSkuID(db.getSkuID());
                    Model.addToCard(addToCardRequest, context,db.getImage(),db.getItemName(),db.getPrice(),db.getSalePrice());
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img_listfavorite;
        TextView tensanpham_favorite, price_favorite, salepice_favorite, specialprice_favorite;
        ImageView add_productfavorite;
        TextView reviewvalue_favorite;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img_listfavorite = itemView.findViewById(R.id.img_listfavorite);
            tensanpham_favorite = itemView.findViewById(R.id.tensanpham_favorite);
            price_favorite = itemView.findViewById(R.id.price_favorite);
            add_productfavorite = itemView.findViewById(R.id.add_productfavorite);
            salepice_favorite = itemView.findViewById(R.id.salepice_favorite);
            specialprice_favorite = itemView.findViewById(R.id.specialprice_favorite);
            reviewvalue_favorite = itemView.findViewById(R.id.reviewvalue_favorite);
        }
    }
}

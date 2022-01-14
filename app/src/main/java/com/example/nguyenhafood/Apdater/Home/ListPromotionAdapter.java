package com.example.nguyenhafood.Apdater.Home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenhafood.Database.DBHelper;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Home.ImfomationProductActivity;
import com.example.nguyenhafood.Interface.UpdateSizeCart;
import com.example.nguyenhafood.Model.Home.ItemListProMotion;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.ViewCart.AddToCardRequest;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class ListPromotionAdapter extends RecyclerView.Adapter<ListPromotionAdapter.MyViewHolder> {
    Context context;
    List<ItemListProMotion> datalist = new ArrayList<>();
    DBHelper dbHelper;
    UpdateSizeCart updateSizeCart;
    public ListPromotionAdapter(List<ItemListProMotion> datalist, Context context, UpdateSizeCart updateSizeCart) {
        this.datalist.addAll(datalist);
        this.context = context;
        this.updateSizeCart = updateSizeCart;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listpromotion, parent, false);
        dbHelper = new DBHelper(context);
        return new ListPromotionAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ItemListProMotion db = datalist.get(position);
        Picasso.with(context)
                .load(Gobal.IDImage + db.getImage())
                .fit().centerCrop()
                .error(R.drawable.imageerro)
                .into(holder.img_promotion);
        holder.name_product_promotion.setText(db.getName());
        holder.review_value_promotion.setRating(Float.parseFloat(String.valueOf(db.getReviewValue())));
        if (String.valueOf(db.getPrice()).equals(String.valueOf(db.getSalePrice())) == true) {
            holder.price_promotion_home.setText(Model.ChangeDecimal(db.getPrice()));
            holder.sale_price_promotion_home.setVisibility(View.GONE);
          //  holder.specialPrice_promotion_home.setVisibility(View.GONE);
        } else {
            holder.price_promotion_home.setText(Model.ChangeDecimal(db.getSalePrice()));
            holder.sale_price_promotion_home.setText(Model.ChangeDecimal(db.getPrice()));
            holder.sale_price_promotion_home.setPaintFlags(holder.sale_price_promotion_home.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.sale_price_promotion_home.setTextColor(Color.parseColor("#CA0000"));
            holder.sale_price_promotion_home.setText(Model.ChangeDecimal(db.getPrice()));
        }
        holder.chose_promotion_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gobal.SizeProduct = Gobal.SizeProduct + 1;
                updateSizeCart.UpdateSize(position);
                AddToCardRequest addToCardRequest = new AddToCardRequest();
                addToCardRequest.setUserID(dbHelper.GetDL());
                addToCardRequest.setProductID(db.getItemID());
                addToCardRequest.setPrice(String.valueOf(Model.showprice1(db.getPrice(), db.getSalePrice())));
                addToCardRequest.setQuantity("1");
                addToCardRequest.setSkuID(db.getSkuID());
                if (Gobal.getLoginStatus() == 0) {

                    addToCardRequest.setIsLogin("False");

                    Model.addToCard(addToCardRequest, context,db.getImage(),db.getName(),db.getPrice(),db.getSalePrice());

                } else {

                    addToCardRequest.setIsLogin("True");

                    Model.addToCard(addToCardRequest, context,db.getImage(),db.getName(),db.getPrice(),db.getSalePrice());
                }


            }
        });
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
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img_promotion;
        TextView price_promotion_home, sale_price_promotion_home, specialPrice_promotion_home, name_product_promotion;
        Button chose_promotion_home;
        RatingBar review_value_promotion;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img_promotion = itemView.findViewById(R.id.img_lstpromotion);
            price_promotion_home = itemView.findViewById(R.id.price_promotion_home);
            sale_price_promotion_home = itemView.findViewById(R.id.sale_price_promotion_home);
            specialPrice_promotion_home = itemView.findViewById(R.id.specialPrice_promotion_home);
            chose_promotion_home = itemView.findViewById(R.id.chose_promotion_home);
            name_product_promotion = itemView.findViewById(R.id.name_product_promotion);
            review_value_promotion=itemView.findViewById(R.id.review_value_promotion);
        }
    }
}

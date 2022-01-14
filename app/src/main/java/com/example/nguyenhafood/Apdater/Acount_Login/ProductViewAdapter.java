package com.example.nguyenhafood.Apdater.Acount_Login;

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
import com.example.nguyenhafood.Interface.UpdateProductView;
import com.example.nguyenhafood.Model.Acount_Login.DataProductReview;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.ViewCart.AddToCardRequest;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class ProductViewAdapter extends RecyclerView.Adapter<ProductViewAdapter.MyViewHolder> {
    List<DataProductReview> datalist = new ArrayList<>();
    Context context;
    DBHelper dbHelper;
    UpdateProductView updateProductView;

    public ProductViewAdapter(List<DataProductReview> datalist, Context context, UpdateProductView updateProductView) {
        this.datalist.addAll(datalist);
        this.context = context;
        this.updateProductView = updateProductView;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_product, parent, false);
        dbHelper = new DBHelper(context);
        return new ProductViewAdapter.MyViewHolder(view);
    }

    public void LoadMore(List<DataProductReview> lst) {
        datalist.addAll(lst);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataProductReview db = datalist.get(position);
        Picasso.with(context)
                .load(Gobal.IDImage + db.getImage())
                .fit().centerCrop()
                .error(R.drawable.imageerro)
                .into(holder.image_product_review);
        holder.name_product_review.setText(db.getItemName());
        if (String.valueOf(db.getPrice()).equals(String.valueOf(db.getSalePrice())) == true) {
            holder.sellprice_product_review.setText(Model.ChangeDecimal(db.getPrice()));
            holder.sale_price_product_review.setVisibility(View.GONE);
            holder.special_price_product_review.setVisibility(View.GONE);
        } else {
            holder.special_price_product_review.setText("- " + String.valueOf(db.getSpecialPrice()) + "%");
            holder.sellprice_product_review.setText(Model.ChangeDecimal(db.getSalePrice()));
            holder.sale_price_product_review.setText(Model.ChangeDecimal(db.getPrice()));
            holder.sale_price_product_review.setPaintFlags(holder.sale_price_product_review.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.sale_price_product_review.setText(Model.ChangeDecimal(db.getPrice()));
        }
        holder.name_product_review.setOnClickListener(new View.OnClickListener() {
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
        holder.addcart_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gobal.SizeProduct = Gobal.SizeProduct + 1;
                updateProductView.UpDateview(position);
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
        holder.imfomation_view.setOnClickListener(new View.OnClickListener() {
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

    ///
    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image_product_review;
        TextView name_product_review, sellprice_product_review, sale_price_product_review, special_price_product_review;
        ImageView imfomation_view, addcart_view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            special_price_product_review = itemView.findViewById(R.id.special_price_product_review);
            sale_price_product_review = itemView.findViewById(R.id.sale_price_product_review);
            sellprice_product_review = itemView.findViewById(R.id.sellprice_product_review);
            name_product_review = itemView.findViewById(R.id.name_product_review);
            image_product_review = itemView.findViewById(R.id.image_product_review);
            imfomation_view = itemView.findViewById(R.id.imfomation_view);
            addcart_view = itemView.findViewById(R.id.addcart_view);
        }
    }
}

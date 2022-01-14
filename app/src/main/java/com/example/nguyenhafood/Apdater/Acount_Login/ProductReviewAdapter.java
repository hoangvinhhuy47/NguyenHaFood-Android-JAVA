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

public class ProductReviewAdapter extends RecyclerView.Adapter<ProductReviewAdapter.MyViewHolder> {
    List<DataProductReview> datalist = new ArrayList<>();
    Context context;
    DBHelper dbHelper;
UpdateProductView updateProductView;
    public ProductReviewAdapter(List<DataProductReview> datalist, Context context,UpdateProductView updateProductView) {
        this.datalist.addAll(datalist);
        this.context = context;
        this.updateProductView=  updateProductView;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_getproductreview, parent, false);
        dbHelper = new DBHelper(context);
        return new ProductReviewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataProductReview db = datalist.get(position);

        Picasso.with(context)
                .load(Gobal.IDImage + db.getImage())
                .fit().centerCrop()
                .error(R.drawable.imageerro)
                .into(holder.image_productreview);
        holder.name_product_review.setText(db.getItemName());
        if (String.valueOf(db.getPrice()).equals(String.valueOf(db.getSalePrice())) == true) {
            holder.sellprice_productreview.setText(Model.ChangeDecimal(db.getPrice()));
            holder.sale_price_productreview.setVisibility(View.GONE);
            holder.special_price_productreview.setVisibility(View.GONE);
        } else {
            holder.special_price_productreview.setText("- " + String.valueOf(db.getSpecialPrice()) + "%");
            holder.sellprice_productreview.setText(Model.ChangeDecimal(db.getSalePrice()));
            holder.sale_price_productreview.setText(Model.ChangeDecimal(db.getPrice()));
            holder.sale_price_productreview.setPaintFlags(holder.sale_price_productreview.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.sale_price_productreview.setText(Model.ChangeDecimal(db.getPrice()));
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
        holder.add_tocart_productreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gobal.SizeProduct=Gobal.SizeProduct+1;
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
        holder.see_imfomation_productreview.setOnClickListener(new View.OnClickListener() {
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

    public void LoadMore(List<DataProductReview> lst){
        datalist.addAll(lst);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image_productreview;
        TextView name_product_review, sellprice_productreview, sale_price_productreview, special_price_productreview;
        ImageView see_imfomation_productreview, add_tocart_productreview;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            special_price_productreview = itemView.findViewById(R.id.special_price_productreview);
            sale_price_productreview = itemView.findViewById(R.id.sale_price_productreview);
            sellprice_productreview = itemView.findViewById(R.id.sellprice_productreview);
            name_product_review = itemView.findViewById(R.id.name_product_review);
            image_productreview = itemView.findViewById(R.id.image_productreview);
            see_imfomation_productreview = itemView.findViewById(R.id.see_imfomation_productreview);
            add_tocart_productreview = itemView.findViewById(R.id.add_tocart_productreview);
        }
    }
}

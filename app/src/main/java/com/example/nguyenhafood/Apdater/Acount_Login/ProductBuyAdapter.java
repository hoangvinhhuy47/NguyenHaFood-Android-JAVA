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
import com.example.nguyenhafood.Model.Acount_Login.DataGetProductBuy;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class ProductBuyAdapter extends RecyclerView.Adapter<ProductBuyAdapter.MyViewHolder> {
    List<DataGetProductBuy> datalist = new ArrayList<>();
    Context context;
    DBHelper dbHelper;

    public ProductBuyAdapter(List<DataGetProductBuy> datalist, Context context) {
        this.datalist.addAll(datalist);
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_productbuy, parent, false);
        dbHelper = new DBHelper(context);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataGetProductBuy db = datalist.get(position);
        Picasso.with(context)
                .load(Gobal.IDImage + db.getImage())
                .fit().centerCrop()
                .error(R.drawable.imageerro)
                .into(holder.image_product_buy);
        holder.name_product_buy.setText(db.getItemName());
        holder.imfomation_buy.setOnClickListener(new View.OnClickListener() {
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
        holder.name_product_buy.setOnClickListener(new View.OnClickListener() {
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
        if (String.valueOf(db.getPrice()).equals(String.valueOf(db.getSalePrice())) == true) {
            holder.sellprice_product_buy.setText(Model.ChangeDecimal(db.getPrice()));
            holder.sale_price_product_buy.setVisibility(View.GONE);
            holder.special_price_product_buy.setVisibility(View.GONE);
        } else {
            holder.special_price_product_buy.setText("- " + String.valueOf(db.getSpecialPrice()) + "%");
            holder.sellprice_product_buy.setText(Model.ChangeDecimal(db.getSalePrice()));
            holder.sale_price_product_buy.setText(Model.ChangeDecimal(db.getPrice()));
            holder.sale_price_product_buy.setPaintFlags(holder.sale_price_product_buy.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.sale_price_product_buy.setText(Model.ChangeDecimal(db.getPrice()));
        }
    }

    public void LoadMore(List<DataGetProductBuy> dataGetProductBuys) {
        datalist.addAll(dataGetProductBuys);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image_product_buy;
        TextView special_price_product_buy, name_product_buy, sellprice_product_buy, sale_price_product_buy;
        ImageView imfomation_buy, addcart_buy;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image_product_buy = itemView.findViewById(R.id.image_product_buy);
            special_price_product_buy = itemView.findViewById(R.id.special_price_product_buy);
            name_product_buy = itemView.findViewById(R.id.name_product_buy);
            sellprice_product_buy = itemView.findViewById(R.id.sellprice_product_buy);
            sale_price_product_buy = itemView.findViewById(R.id.sale_price_product_buy);
            imfomation_buy = itemView.findViewById(R.id.imfomation_buy);
            addcart_buy = itemView.findViewById(R.id.addcart_buy);
        }
    }
}

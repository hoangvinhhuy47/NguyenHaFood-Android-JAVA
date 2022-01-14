package com.example.nguyenhafood.Apdater.Acount_Login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Home.ImfomationProductActivity;
import com.example.nguyenhafood.Model.Acount_Login.DataDetailList;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class ListProductOrderdetailAdapter extends RecyclerView.Adapter<ListProductOrderdetailAdapter.MyViewHolder> {
    List<DataDetailList> datalist = new ArrayList<>();
    Context context;

    public ListProductOrderdetailAdapter(Context context, List<DataDetailList> datalist) {
        this.context = context;
        this.datalist.addAll(datalist);
    }

    @NonNull
    @Override
    public ListProductOrderdetailAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listproduct_orderdetail, parent, false);
        return new ListProductOrderdetailAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListProductOrderdetailAdapter.MyViewHolder holder, int position) {
        DataDetailList db = datalist.get(position);
        Picasso.with(context)
                .load(Gobal.IDImage + db.getImage())
                .fit().centerCrop()
                .error(R.drawable.imageerro)
                .into(holder.image_listproduct_orderdetail);
        holder.name_listproduct_orderdetail.setText(db.getProductName());
        holder.text_Quantity_orderdetail.setText(String.valueOf(db.getQuantity()));
        holder.price_listproduct_orderdetail.setText(Model.ChangeDecimal(db.getPrice()));
        holder.Amount_listproduct_orderdetail.setText(Model.ChangeDecimal(db.getAmount()));
        holder.txt_STT_orderdetali.setText(String.valueOf(position + 1) + ".");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ImfomationProductActivity.class);
                intent.putExtra("ProductID", db.getItemID());
                intent.putExtra("SkuID", db.getSkuID());
                intent.putExtra("Image",db.getImage());
                intent.putExtra("PriceAdd", String.valueOf(Model.showprice1(db.getPrice(), db.getAmount())));
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);
            }
        });
        if (position % 2 != 0) {
            holder.backgroud_product_orderdetail.setBackgroundColor(Color.parseColor("#FAF3E2"));
        }
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name_listproduct_orderdetail, sellprice_listproduct_orderdetail, text_Quantity_orderdetail, txt_STT_orderdetali, price_listproduct_orderdetail, Amount_listproduct_orderdetail;
        ImageView image_listproduct_orderdetail;
        RelativeLayout backgroud_product_orderdetail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image_listproduct_orderdetail = itemView.findViewById(R.id.image_listproduct_orderdetail);
            name_listproduct_orderdetail = itemView.findViewById(R.id.name_listproduct_orderdetail);
            text_Quantity_orderdetail = itemView.findViewById(R.id.text_Quantity_orderdetail);
            txt_STT_orderdetali = itemView.findViewById(R.id.txt_STT_orderdetali);
            price_listproduct_orderdetail = itemView.findViewById(R.id.price_listproduct_orderdetail);
            Amount_listproduct_orderdetail = itemView.findViewById(R.id.Amount_listproduct_orderdetail);
            backgroud_product_orderdetail =itemView.findViewById(R.id.backgroud_product_orderdetail);
        }
    }
}

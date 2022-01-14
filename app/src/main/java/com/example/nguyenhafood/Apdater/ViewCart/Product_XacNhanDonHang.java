package com.example.nguyenhafood.Apdater.ViewCart;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenhafood.Database.DBHelper;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.Model.ViewCart.GetCard;
import com.example.nguyenhafood.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Product_XacNhanDonHang extends RecyclerView.Adapter<Product_XacNhanDonHang.MyViewHolder> {
    List<GetCard> datalist = new ArrayList<>();
    private Context context;
    DBHelper dbHelper;
    double totalprice;

    public Product_XacNhanDonHang(List<GetCard> datalist, Context context) {
        this.datalist.addAll(datalist);
        this.context = context;

    }

    @NonNull
    @Override
    public Product_XacNhanDonHang.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanphamxacnhandonhang, parent, false);
        dbHelper = new DBHelper(context);
        return new Product_XacNhanDonHang.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        GetCard db = datalist.get(position);
        holder.Name_viewproduct_bill.setText(db.getItemName());
        holder.qualiti_viewproduct_bill.setText("SL x"+String.valueOf(db.getQuantity()));
        Picasso.with(context)
                .load(Gobal.IDImage + db.getImage())
                .fit()
                .centerCrop()
                .into(holder.img_viewprocut_bill);
         totalprice = db.getAmount();
        if (String.valueOf(db.getPrice()).equals(String.valueOf(db.getOrgPrice())) == true) {
            holder.sellprice_viewproduct_bill.setText(Model.ChangeDecimal(db.getPrice()));
            holder.saleprice_product_bill.setVisibility(View.GONE);
            holder.speacial_viewproduct_bill.setVisibility(View.GONE);
        } else {
            holder.speacial_viewproduct_bill.setText("- " + String.valueOf(db.getSpecialPrice()) + "%");
            holder.sellprice_viewproduct_bill.setText(Model.ChangeDecimal(db.getPrice()));
            holder.saleprice_product_bill.setText(Model.ChangeDecimal(db.getOrgPrice()));
            holder.saleprice_product_bill.setPaintFlags(holder.saleprice_product_bill.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.saleprice_product_bill.setText(Model.ChangeDecimal(db.getOrgPrice()));
        }
        if (position%2==0){
            holder.backgroud_orderconfirmation.setBackgroundColor(Color.parseColor("#d1d8e0"));
        }
        else {
            holder.backgroud_orderconfirmation.setBackgroundColor(Color.parseColor("#FFFFFF"));

        }
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img_viewprocut_bill;
        TextView Name_viewproduct_bill, qualiti_viewproduct_bill, sellprice_viewproduct_bill, saleprice_product_bill, speacial_viewproduct_bill;
        LinearLayout backgroud_orderconfirmation;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            backgroud_orderconfirmation=itemView.findViewById(R.id.backgroud_orderconfirmation);
            img_viewprocut_bill = itemView.findViewById(R.id.img_viewprocut_bill);
            Name_viewproduct_bill = itemView.findViewById(R.id.Name_viewproduct_bill);
            sellprice_viewproduct_bill = itemView.findViewById(R.id.sellprice_viewproduct_bill);
            saleprice_product_bill = itemView.findViewById(R.id.saleprice_product_bill);
            qualiti_viewproduct_bill = itemView.findViewById(R.id.qualiti_viewproduct_bill);
            speacial_viewproduct_bill = itemView.findViewById(R.id.speacial_viewproduct_bill);
        }
    }
}

package com.example.nguyenhafood.Apdater.Imfomation;

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
import com.example.nguyenhafood.Model.ImfomationProduct.DataWebItemRelation;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.ViewCart.AddToCardRequest;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class WebItemRelationAdapter extends RecyclerView.Adapter<WebItemRelationAdapter.MyViewHolder> {
    private Context context;
    DBHelper dbHelper;
    List<DataWebItemRelation> datalist = new ArrayList<>();
    public WebItemRelationAdapter(List<DataWebItemRelation> datalist, Context context) {
        this.datalist.addAll(datalist);
        this.context = context;
    }
    @NonNull
    @Override
    public WebItemRelationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seeproduct_webitemrelation, parent, false);
        dbHelper = new DBHelper(context);
        return new WebItemRelationAdapter.MyViewHolder(view);
    }
    public void LoadMore(List<DataWebItemRelation> lst) {
        datalist.addAll(lst);
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull WebItemRelationAdapter.MyViewHolder holder, int position) {
        DataWebItemRelation list = datalist.get(position);
        Picasso.with(context)
                .load(Gobal.IDImage + list.getImage())
                .fit().centerCrop()
                .into(holder.img_prodcut_webrelation);
        holder.name_prodcut_webrelation.setText(list.getName());
        if (Model.hideprice(list.getPrice(),list.getSalePrice())==true){
            holder.saleprice_prodcut_webrelation.setVisibility(View.GONE);
            holder.special_prodcut_webrelation.setVisibility(View.GONE);
            holder.price_prodcut_webrelation.setText(Model.showprice(list.getPrice(),list.getSalePrice()));
        }
        else {
            holder.saleprice_prodcut_webrelation.setPaintFlags(holder.saleprice_prodcut_webrelation.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.price_prodcut_webrelation.setText(Model.showprice(list.getPrice(),list.getSalePrice()));
            holder.saleprice_prodcut_webrelation.setText(Model.showsaleprice(list.getPrice(),list.getSalePrice()));
            holder.special_prodcut_webrelation.setText("- " +String.valueOf(list.getSpecialPrice())+"%");
        }
        holder.choose_prodcut_webrelation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gobal.SizeProduct = Gobal.SizeProduct+1;
                if (Gobal.getLoginStatus() == 0) {
                    AddToCardRequest addToCardRequest = new AddToCardRequest();
                    addToCardRequest.setUserID(dbHelper.GetDL());
                    addToCardRequest.setIsLogin("False");
                    addToCardRequest.setProductID(list.getItemID());
                    addToCardRequest.setPrice(String.valueOf(Model.showprice1(list.getPrice(), list.getSalePrice())));
                    addToCardRequest.setQuantity("1");
                    addToCardRequest.setSkuID(list.getSkuID());
                    Model.addToCard(addToCardRequest, context,list.getImage(),list.getName(),list.getPrice(),list.getSalePrice());
                } else {
                    AddToCardRequest addToCardRequest = new AddToCardRequest();
                    addToCardRequest.setUserID(dbHelper.GetDL());
                    addToCardRequest.setIsLogin("True");
                    addToCardRequest.setProductID(list.getItemID());
                    addToCardRequest.setPrice(String.valueOf(Model.showprice1(list.getPrice(), list.getSalePrice())));
                    addToCardRequest.setQuantity("1");
                    addToCardRequest.setSkuID(list.getSkuID());
                    Model.addToCard(addToCardRequest, context,list.getImage(),list.getName(),list.getPrice(),list.getSalePrice());

                }

            }
        });
        holder.reviewcount_prodcut_webrelation.setText(String.valueOf(list.getReviewCount()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ImfomationProductActivity.class);
                intent.putExtra("ProductID", list.getItemID());
                intent.putExtra("SkuID",list.getSkuID());
                intent.putExtra("Image",list.getImage());
                intent.putExtra("PriceAdd",String.valueOf(Model.showprice1(list.getPrice(),list.getSalePrice())));
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
        ImageView img_prodcut_webrelation;
        TextView name_prodcut_webrelation,price_prodcut_webrelation,saleprice_prodcut_webrelation,special_prodcut_webrelation,reviewcount_prodcut_webrelation;
        ImageView choose_prodcut_webrelation;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            special_prodcut_webrelation=itemView.findViewById(R.id.special_prodcut_webrelation);
            img_prodcut_webrelation = itemView.findViewById(R.id.img_prodcut_webrelation);
            name_prodcut_webrelation=itemView.findViewById(R.id.name_prodcut_webrelation);
            price_prodcut_webrelation=itemView.findViewById(R.id.price_prodcut_webrelation);
            saleprice_prodcut_webrelation=itemView.findViewById(R.id.saleprice_prodcut_webrelation);
            choose_prodcut_webrelation=itemView.findViewById(R.id.choose_prodcut_webrelation);
            reviewcount_prodcut_webrelation=itemView.findViewById(R.id.reviewcount_prodcut_webrelation);
        }
    }
}


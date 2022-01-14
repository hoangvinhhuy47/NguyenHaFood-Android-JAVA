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
import com.example.nguyenhafood.Model.ImfomationProduct.ProductRelationList;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.ViewCart.AddToCardRequest;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class ProductRelationListAdapter extends RecyclerView.Adapter<ProductRelationListAdapter.MyViewHolder> {
    //Sản phẩm tương tự
    private Context context;
    DBHelper dbHelper;
    List<ProductRelationList> datalist = new ArrayList<>();
    public ProductRelationListAdapter(List<ProductRelationList> datalist, Context context) {
        this.datalist.addAll(datalist);
        this.context = context;
    }
    @NonNull
    @Override
    public ProductRelationListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seeproduct_productrelationlist, parent, false);
        dbHelper = new DBHelper(context);
        return new ProductRelationListAdapter.MyViewHolder(view);
    }
public void loadmore(List<ProductRelationList> lst){
        datalist.addAll(lst);
        notifyDataSetChanged();
}
    @Override
    public void onBindViewHolder(@NonNull ProductRelationListAdapter.MyViewHolder holder, int position) {
        ProductRelationList list = datalist.get(position);
        Picasso.with(context)
                .load(Gobal.IDImage + list.getImage())
                .fit().centerCrop()
                .error(R.drawable.imageerro)
                .into(holder.img_simlar);
        holder.name_product_similar.setText(list.getName());
        if (Model.hideprice(list.getPrice(),list.getSalePrice())==true){
            holder.saleprice_prodcut_similar.setVisibility(View.GONE);
            holder.special_prodcut_similar.setVisibility(View.GONE);
            holder.price_prodcut_similar.setText(Model.showprice(list.getPrice(),list.getSalePrice()));
        }
        else {
            holder.saleprice_prodcut_similar.setPaintFlags(holder.saleprice_prodcut_similar.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.price_prodcut_similar.setText(Model.showprice(list.getPrice(),list.getSalePrice()));
            holder.saleprice_prodcut_similar.setText(Model.showsaleprice(list.getPrice(),list.getSalePrice()));
            holder.special_prodcut_similar.setText("- " +String.valueOf(list.getSpecialPrice())+"%");
        }
        holder.chose_prodcut_similar.setOnClickListener(new View.OnClickListener() {
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
        holder.reviewcount_simlir.setText(String.valueOf(list.getReviewCount()));
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
        ImageView img_simlar;
        TextView name_product_similar,price_prodcut_similar,saleprice_prodcut_similar,special_prodcut_similar,reviewcount_simlir;
        ImageView chose_prodcut_similar;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            special_prodcut_similar=itemView.findViewById(R.id.special_prodcut_similar);
            img_simlar = itemView.findViewById(R.id.img_simlar);
            name_product_similar=itemView.findViewById(R.id.name_product_similar);
            price_prodcut_similar=itemView.findViewById(R.id.price_prodcut_similar);
            saleprice_prodcut_similar=itemView.findViewById(R.id.saleprice_prodcut_similar);
            chose_prodcut_similar=itemView.findViewById(R.id.chose_prodcut_similar);
            reviewcount_simlir=itemView.findViewById(R.id.reviewcount_simlir);
        }
    }
}

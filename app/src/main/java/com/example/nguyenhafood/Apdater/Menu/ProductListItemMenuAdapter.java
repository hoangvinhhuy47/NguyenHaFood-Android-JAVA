package com.example.nguyenhafood.Apdater.Menu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenhafood.Database.DBHelper;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Home.ImfomationProductActivity;
import com.example.nguyenhafood.Interface.UpdateSizeCart;
import com.example.nguyenhafood.Model.Home.vw_WebItemWebsite;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.ViewCart.AddToCardRequest;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class    ProductListItemMenuAdapter extends RecyclerView.Adapter<ProductListItemMenuAdapter.MyViewHolder>{
    List<vw_WebItemWebsite> datalist = new ArrayList<>();
    private Context context;
    int select = 0;
    DBHelper dbhelpper;
    UpdateSizeCart updateSizeCart;
    public ProductListItemMenuAdapter(List<vw_WebItemWebsite> datalist, Context context, UpdateSizeCart updateSizeCart) {
        this.datalist.addAll(datalist);
        this.context = context;
        this.updateSizeCart = updateSizeCart;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_button_listsanpham, parent, false);
        dbhelpper = new DBHelper(context);
        return new ProductListItemMenuAdapter.MyViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        vw_WebItemWebsite db = datalist.get(position);

        if (select == position) {
            for (int i = 0; i < datalist.size(); i++) {
                Picasso.with(context)
                        .load(Gobal.IDImage + datalist.get(0).getImage())
                        .fit().centerCrop()
                        .into(holder.img_sanphammenu);
                if (String.valueOf(datalist.get(0).getPrice()).equals(String.valueOf(datalist.get(0).getSalePrice())) == false) {
                    holder.giatien.setText(Model.ChangeDecimal(datalist.get(0).getSalePrice()));
                    holder.giatien1.setText(Model.ChangeDecimal(datalist.get(0).getPrice()));
                    holder.specialPrice.setText("" + String.valueOf(datalist.get(0).getSpecialPrice()) + "%");
                    holder.giatien1.setPaintFlags(holder.giatien1.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    holder.giatien.setText(Model.ChangeDecimal(datalist.get(0).getPrice()));
                    holder.giatien1.setVisibility(View.GONE);
                    holder.specialPrice.setVisibility(View.GONE);
                }
                holder.name_listproduct_menu.setText(datalist.get(0).getName());
                holder.rating_product_menu.setRating(Float.parseFloat(String.valueOf(datalist.get(0).getReviewValue())));
                holder.totalrating_product_menu.setText(String.valueOf(datalist.get(0).getReviewCount()));
            }
        }
        if (String.valueOf(db.getSalePrice()).equals(String.valueOf(db.getPrice())) == false) {
            holder.giatien.setText(Model.ChangeDecimal(db.getSalePrice()));
            holder.giatien1.setText(Model.ChangeDecimal(db.getPrice()));
            holder.specialPrice.setText("-" + String.valueOf(db.getSpecialPrice()) + "%");
            holder.giatien1.setPaintFlags(holder.giatien1.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.giatien.setText(Model.ChangeDecimal(db.getPrice()));
            holder.giatien1.setVisibility(View.GONE);
            holder.specialPrice.setVisibility(View.GONE);
        }
        Picasso.with(context)
                .load(Gobal.IDImage + db.getImage())
                .fit().centerCrop()
                .into(holder.img_sanphammenu);
        holder.name_listproduct_menu.setText(db.getName());
        holder.rating_product_menu.setRating(Float.parseFloat(String.valueOf(db.getReviewValue())));
        holder.totalrating_product_menu.setText(String.valueOf(db.getReviewCount()));
        holder.motasanpham.setOnClickListener(new View.OnClickListener() {
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
        holder.chonsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Gobal.SizeProduct = Gobal.SizeProduct + 1;
                    updateSizeCart.UpdateSize(position);
                    if (Model.checklogin(Gobal.getLoginStatus()) == false) {
                        AddToCardRequest addToCardRequest = new AddToCardRequest();
                        addToCardRequest.setUserID(dbhelpper.GetDL());
                        addToCardRequest.setIsLogin("False");
                        addToCardRequest.setProductID(db.getItemID());
                        addToCardRequest.setPrice(String.valueOf(Model.showprice1(db.getPrice(), db.getSalePrice())));
                        addToCardRequest.setQuantity("1");
                        addToCardRequest.setSkuID(db.getSkuID());
                        Model.addToCard(addToCardRequest, context,db.getImage(),db.getName(),db.getPrice(),db.getSalePrice());
                    } else {
                        AddToCardRequest addToCardRequest = new AddToCardRequest();
                        addToCardRequest.setUserID(dbhelpper.GetDL());
                        addToCardRequest.setIsLogin("True");
                        addToCardRequest.setProductID(db.getItemID());
                        addToCardRequest.setPrice(String.valueOf(Model.showprice1(db.getPrice(), db.getSalePrice())));
                        addToCardRequest.setQuantity("1");
                        addToCardRequest.setSkuID(db.getSkuID());
                        Model.addToCard(addToCardRequest, context,db.getImage(),db.getName(),db.getPrice(),db.getSalePrice());
                    }

                }
                catch (Exception exception){
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }
    public void LoadMore(List<vw_WebItemWebsite> lst){
        datalist.addAll(lst);
        notifyDataSetChanged();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img_sanphammenu;
        TextView name_listproduct_menu, giatien, giatien1, specialPrice, totalrating_product_menu;
        RelativeLayout motasanpham;
        Button chonsp;
        RatingBar rating_product_menu;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img_sanphammenu = itemView.findViewById(R.id.img_sanphammenu);
            name_listproduct_menu = itemView.findViewById(R.id.ten);
            giatien = itemView.findViewById(R.id.giatien);
            motasanpham = itemView.findViewById(R.id.motasanpham);
            chonsp = itemView.findViewById(R.id.chonsp);
            giatien1 = itemView.findViewById(R.id.giatien1);
            specialPrice = itemView.findViewById(R.id.specialPrice);
            totalrating_product_menu = itemView.findViewById(R.id.totalrating_product_menu);
            rating_product_menu = itemView.findViewById(R.id.rating_product_menu);
        }
    }
}

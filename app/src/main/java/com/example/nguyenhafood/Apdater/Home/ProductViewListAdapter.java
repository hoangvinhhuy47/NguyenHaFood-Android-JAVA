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
import com.example.nguyenhafood.Model.Home.DataProductViewList;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.ViewCart.AddToCardRequest;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class ProductViewListAdapter extends RecyclerView.Adapter<ProductViewListAdapter.MyViewHolder> {
    List<DataProductViewList> dataProductViewLists = new ArrayList<>();
    private Context context;
    DBHelper dbHelper;
    UpdateSizeCart updateSizeCart;

    public ProductViewListAdapter(List<DataProductViewList> dataProductViewLists, Context context, UpdateSizeCart updateSizeCart) {
        this.dataProductViewLists.addAll(dataProductViewLists);
        this.context = context;
        this.updateSizeCart = updateSizeCart;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listviewedproducts, parent, false);
        dbHelper = new DBHelper(context);
        return new ProductViewListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataProductViewList db = dataProductViewLists.get(position);
        Picasso.with(context)
                .load(Gobal.IDImage + db.getImage())
                .fit().centerCrop()
                .error(R.drawable.imageerro)
                .into(holder.image_productlist_home);
        holder.name_productlist_home.setText(db.getItemName());
        if (String.valueOf(db.getPrice()).equals(String.valueOf(db.getSalePrice())) == true) {
            holder.sellprice_productlist_home.setText(Model.ChangeDecimal(db.getPrice()));
            holder.saleprice_productlist_home.setVisibility(View.GONE);
            holder.special_prdcutlist_home.setVisibility(View.GONE);
        } else {
            holder.special_prdcutlist_home.setText("- " + String.valueOf(db.getSpecialPrice()) + "%");
            holder.sellprice_productlist_home.setText(Model.ChangeDecimal(db.getSalePrice()));
            holder.saleprice_productlist_home.setText(Model.ChangeDecimal(db.getPrice()));
            holder.saleprice_productlist_home.setPaintFlags(holder.saleprice_productlist_home.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            //            holder.salepricesea.setTextColor(Color.parseColor("#CA0000"));
            holder.saleprice_productlist_home.setText(Model.ChangeDecimal(db.getPrice()));
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

        holder.reviewvalue_productlistview.setText(String.valueOf(db.getReviewValue()));

        holder.choseitem_productlist_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gobal.SizeProduct = Gobal.SizeProduct + 1;
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
        return dataProductViewLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name_productlist_home, special_prdcutlist_home, sellprice_productlist_home, saleprice_productlist_home, reviewvalue_productlistview;
        ImageView choseitem_productlist_home;
        ImageView image_productlist_home;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            choseitem_productlist_home = itemView.findViewById(R.id.choseitem_productlist_home);
            name_productlist_home = itemView.findViewById(R.id.name_productlist_home);
            special_prdcutlist_home = itemView.findViewById(R.id.special_prdcutlist_home);
            sellprice_productlist_home = itemView.findViewById(R.id.sellprice_productlist_home);
            saleprice_productlist_home = itemView.findViewById(R.id.saleprice_productlist_home);
            image_productlist_home = itemView.findViewById(R.id.image_productlist_home);
            reviewvalue_productlistview = itemView.findViewById(R.id.reviewvalue_productlistview);
        }
    }
}

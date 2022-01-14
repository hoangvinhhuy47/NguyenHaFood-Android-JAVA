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
import com.example.nguyenhafood.Model.Home.vw_WebItemWebsite;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.ViewCart.AddToCardRequest;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class ListProductAdapter extends RecyclerView.Adapter<ListProductAdapter.MyViewHolder> {
    List<vw_WebItemWebsite> datalist = new ArrayList<>();
    DBHelper dbHelper;
    private Context context;
   UpdateSizeCart updateSizeCart;
    public ListProductAdapter(List<vw_WebItemWebsite> datalist, Context context, UpdateSizeCart updateSizeCart) {
        this.datalist.addAll(datalist);
        this.context = context;
        this.updateSizeCart=updateSizeCart;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listproduct_producthome, parent, false);
        dbHelper = new DBHelper(context);
        return new ListProductAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        vw_WebItemWebsite item = datalist.get(position);
        Picasso.with(context)
                .load(Gobal.IDImage + item.getImage())
                .fit().centerCrop()
                .error(R.drawable.imageerro)
                .into(holder.image_lstproduct_producthome);
        holder.name_lstproduct_producthome.setText(item.getName());
        if (String.valueOf(item.getPrice()).equals(String.valueOf(item.getSalePrice())) == true) {
            holder.sellprice_lstproduct_producthome.setText(Model.ChangeDecimal(item.getPrice()));
            holder.sale_lstproduct_producthome.setVisibility(View.GONE);
            holder.special_lstproduct_producthome.setVisibility(View.GONE);
        } else {
            holder.special_lstproduct_producthome.setText("- " + String.valueOf(item.getSpecialPrice()) + "%");
            holder.sellprice_lstproduct_producthome.setText(Model.ChangeDecimal(item.getSalePrice()));
            holder.sale_lstproduct_producthome.setText(Model.ChangeDecimal(item.getPrice()));
            holder.sale_lstproduct_producthome.setPaintFlags(holder.sale_lstproduct_producthome.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//            holder.salepricesea.setTextColor(Color.parseColor("#CA0000"));
            holder.sale_lstproduct_producthome.setText(Model.ChangeDecimal(item.getPrice()));
        }
        holder.chooselstproduct_producthome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gobal.SizeProduct = Gobal.SizeProduct+1;
                updateSizeCart.UpdateSize(position);
                if (Gobal.getLoginStatus() == 0) {
                    AddToCardRequest addToCardRequest = new AddToCardRequest();
                    addToCardRequest.setUserID(dbHelper.GetDL());
                    addToCardRequest.setIsLogin("False");
                    addToCardRequest.setProductID(item.getItemID());
                    addToCardRequest.setPrice(String.valueOf(Model.showprice1(item.getPrice(), item.getSalePrice())));
                    addToCardRequest.setQuantity("1");
                    addToCardRequest.setSkuID(item.getSkuID());
                    Model.addToCard(addToCardRequest, context,item.getImage(),item.getName(),item.getPrice(),item.getSalePrice());

                } else {
                    AddToCardRequest addToCardRequest = new AddToCardRequest();
                    addToCardRequest.setUserID(dbHelper.GetDL());
                    addToCardRequest.setIsLogin("True");
                    addToCardRequest.setProductID(item.getItemID());
                    addToCardRequest.setPrice(String.valueOf(Model.showprice1(item.getPrice(), item.getSalePrice())));
                    addToCardRequest.setQuantity("1");
                    addToCardRequest.setSkuID(item.getSkuID());
                    Model.addToCard(addToCardRequest, context,item.getImage(),item.getName(),item.getPrice(),item.getSalePrice());

                }

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ImfomationProductActivity.class);
                intent.putExtra("ProductID", item.getItemID());
                intent.putExtra("SkuID", item.getSkuID());
                intent.putExtra("Image", item.getImage());
                intent.putExtra("PriceAdd", String.valueOf(Model.showprice1(item.getPrice(), item.getSalePrice())));
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);
            }
        });

            holder.reviewvalue_lstproduct_producthome.setText(String.valueOf(item.getReviewValue()));

    }


    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView sellprice_lstproduct_producthome, name_lstproduct_producthome, sale_lstproduct_producthome, special_lstproduct_producthome, reviewvalue_lstproduct_producthome;
        ImageView image_lstproduct_producthome;
        ImageView chooselstproduct_producthome;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image_lstproduct_producthome = itemView.findViewById(R.id.image_lstproduct_producthome);
            sale_lstproduct_producthome = itemView.findViewById(R.id.sale_lstproduct_producthome);
            sellprice_lstproduct_producthome = itemView.findViewById(R.id.sellprice_lstproduct_producthome);
            chooselstproduct_producthome = itemView.findViewById(R.id.chooselstproduct_producthome);
            name_lstproduct_producthome = itemView.findViewById(R.id.name_lstproduct_producthome);
            special_lstproduct_producthome = itemView.findViewById(R.id.special_lstproduct_producthome);
            reviewvalue_lstproduct_producthome = itemView.findViewById(R.id.reviewvalue_lstproduct_producthome);
        }
    }
}

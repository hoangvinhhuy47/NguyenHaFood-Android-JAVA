package com.example.nguyenhafood.Apdater.Search;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

public class ProductlistSearchAdapter extends RecyclerView.Adapter<ProductlistSearchAdapter.MyViewHolder> {
    private List<vw_WebItemWebsite> datalist = new ArrayList<>();
    private Context context;
    DBHelper dbHelper;
    UpdateSizeCart updateSizeCart;
    public ProductlistSearchAdapter(Context context, List<vw_WebItemWebsite> datalist, UpdateSizeCart updateSizeCart) {
        this.context = context;
        this.datalist.addAll(datalist);
        this.updateSizeCart = updateSizeCart;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragmentsearch, parent, false);
        dbHelper = new DBHelper(context);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        vw_WebItemWebsite vw_webItemWebsite = datalist.get(position);
        Picasso.with(context)
                .load(Gobal.IDImage + vw_webItemWebsite.getImage())
                .fit()
                .centerCrop()
                .error(R.drawable.imageerro)
                .into(holder.img_searchproduct);
        holder.reviewcount_fragmentsearch.setText(String.valueOf(vw_webItemWebsite.getReviewCount()));
        holder.tensanphamsearch.setText(vw_webItemWebsite.getName());
        holder.price_product_search.setText(Model.showprice(vw_webItemWebsite.getPrice(), vw_webItemWebsite.getSalePrice()));
        if (String.valueOf(vw_webItemWebsite.getPrice()).equals(String.valueOf(vw_webItemWebsite.getSalePrice()))) {
            holder.specialPrice_search.setVisibility(View.GONE);
            holder.sale_price_search.setVisibility(View.GONE);
        } else {
            holder.sale_price_search.setText(Model.ChangeDecimal(vw_webItemWebsite.getPrice()));
            holder.sale_price_search.setPaintFlags(holder.sale_price_search.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.specialPrice_search.setVisibility(View.VISIBLE);
            holder.specialPrice_search.setText("-" + String.valueOf(vw_webItemWebsite.getSpecialPrice()) + "%");
        }
        holder.chitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ImfomationProductActivity.class);
                intent.putExtra("ProductID", vw_webItemWebsite.getItemID());
                intent.putExtra("SkuID", vw_webItemWebsite.getSkuID());
                intent.putExtra("Image", vw_webItemWebsite.getImage());
                intent.putExtra("PriceAdd", String.valueOf(Model.showprice1(vw_webItemWebsite.getPrice(), vw_webItemWebsite.getSalePrice())));
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);
            }
        });
        holder.add_product_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gobal.SizeProduct=Gobal.SizeProduct+1;
                updateSizeCart.UpdateSize(position);
                if (Gobal.getLoginStatus() == 0) {
                    AddToCardRequest addToCardRequest = new AddToCardRequest();
                    addToCardRequest.setSkuID(vw_webItemWebsite.getSkuID());
                    addToCardRequest.setUserID(dbHelper.GetDL());
                    addToCardRequest.setQuantity("1");
                    addToCardRequest.setIsLogin("False");
                    addToCardRequest.setProductID(vw_webItemWebsite.getItemID());
                    addToCardRequest.setPrice(String.valueOf(Model.showprice1(vw_webItemWebsite.getPrice(), vw_webItemWebsite.getSalePrice())));
                    Model.addToCard(addToCardRequest, context,vw_webItemWebsite.getImage(),vw_webItemWebsite.getName(),vw_webItemWebsite.getPrice(),vw_webItemWebsite.getSalePrice());
                } else {
                    AddToCardRequest addToCardRequest = new AddToCardRequest();
                    addToCardRequest.setSkuID(vw_webItemWebsite.getSkuID());
                    addToCardRequest.setUserID(dbHelper.GetDL());
                    addToCardRequest.setQuantity("1");
                    addToCardRequest.setIsLogin("True");
                    addToCardRequest.setProductID(vw_webItemWebsite.getItemID());
                    addToCardRequest.setPrice(String.valueOf(Model.showprice1(vw_webItemWebsite.getPrice(), vw_webItemWebsite.getSalePrice())));
                    Model.addToCard(addToCardRequest, context,vw_webItemWebsite.getImage(),vw_webItemWebsite.getName(),vw_webItemWebsite.getPrice(),vw_webItemWebsite.getSalePrice());
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }
    public void  LoadMore(List<vw_WebItemWebsite> lst){
        datalist.addAll(lst);
        notifyDataSetChanged();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tensanphamsearch, price_product_search, sale_price_search;
        TextView specialPrice_search, reviewcount_fragmentsearch;
        ImageView img_searchproduct;
        RelativeLayout chitiet;
        TextView add_product_search;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            add_product_search = itemView.findViewById(R.id.add_product_search);
            tensanphamsearch = itemView.findViewById(R.id.tensanphamsearch);
            price_product_search = itemView.findViewById(R.id.price_product_search);
            img_searchproduct = itemView.findViewById(R.id.img_searchproduct);
            sale_price_search = itemView.findViewById(R.id.sale_price_search);
            specialPrice_search = itemView.findViewById(R.id.specialPrice_search);
            chitiet = itemView.findViewById(R.id.chitiet);
            reviewcount_fragmentsearch = itemView.findViewById(R.id.reviewcount_fragmentsearch);
        }
    }
}

package com.example.nguyenhafood.Apdater.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.nguyenhafood.Interface.UpdatePromotion;
import com.example.nguyenhafood.Model.Home.CatoryPromotion;
import com.example.nguyenhafood.Model.Home.Item;
import com.example.nguyenhafood.Model.Home.ListItemProMotion;
import com.example.nguyenhafood.R;

import java.util.ArrayList;
import java.util.List;

public class CatoryPromotionAdapter  extends RecyclerView.Adapter<CatoryPromotionAdapter.MyViewHolder> {
    Context context;
    UpdatePromotion updatePromotion;
    List<ListItemProMotion> datalist = new ArrayList<>();
    public CatoryPromotionAdapter(List<ListItemProMotion> datalist, Context context,UpdatePromotion updatePromotion) {
        this.datalist.addAll(datalist);
        this.context = context;
        this.updatePromotion = updatePromotion;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_buttonpromotion, parent, false);
        return new CatoryPromotionAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ListItemProMotion db = datalist.get(position);
        holder.btn_promotion_home.setText(db.getCategory().getCategoryName());
        holder.btn_promotion_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePromotion.CallBackPromotion(position,db.getItemList());
            }
        });
    }


    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView btn_promotion_home;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            btn_promotion_home=itemView.findViewById(R.id.btn_promotion_home);
        }
    }
}

package com.example.nguyenhafood.Apdater.ViewCart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenhafood.Database.DBHelper;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Model.AddPromotion.DataGetPromotionCode;
import com.example.nguyenhafood.Model.AddPromotion.DataPromotionScreenShow;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Untils.Model;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class GetPromotionAdapter extends RecyclerView.Adapter<GetPromotionAdapter.MyViewHolder> {
    List<DataPromotionScreenShow> lst = new ArrayList<>();
    Context context;

    public GetPromotionAdapter(List<DataPromotionScreenShow> lst1, Context context) {
        lst.addAll(lst1);
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_promotioncode_showsreen, parent, false);
        return new GetPromotionAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataPromotionScreenShow db = lst.get(position);
        holder.text_promotioncode.setText(db.getProductName());
        Picasso.with(context)
                .load(Gobal.IDImage + db.getImage())
                .fit()
                .centerCrop()
                .error(R.drawable.imageerro)
                .into(holder.img_promotioncode);
        holder.qualiti_promotioncode.setText("SL: "+String.valueOf(db.getQuanliti()));
        if (db.getPrice()!=0){
            holder.price_promotioncode.setVisibility(View.VISIBLE);
            holder.price_promotioncode.setText("Giảm"+ Model.ChangeDecimal(db.getPrice()));
        }
        else {
            holder.price_promotioncode.setVisibility(View.GONE);

        }

    }

    @Override
    public int getItemCount() {
        return lst.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text_promotioncode,qualiti_promotioncode,price_promotioncode;
        ImageView img_promotioncode;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img_promotioncode = itemView.findViewById(R.id.img_promotioncode);
            text_promotioncode = itemView.findViewById(R.id.text_promotioncode);
            qualiti_promotioncode=itemView.findViewById(R.id.qualiti_promotioncode);
            price_promotioncode=itemView.findViewById(R.id.price_promotioncode);
        }
    }
}

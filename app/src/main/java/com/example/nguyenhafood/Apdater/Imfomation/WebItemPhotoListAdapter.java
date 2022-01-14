package com.example.nguyenhafood.Apdater.Imfomation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenhafood.Database.DBHelper;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Interface.UpdateImage;
import com.example.nguyenhafood.Model.ImfomationProduct.ProductRelationList;
import com.example.nguyenhafood.Model.ImfomationProduct.WebItemPhotoList;
import com.example.nguyenhafood.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class WebItemPhotoListAdapter extends RecyclerView.Adapter<WebItemPhotoListAdapter.MyViewHolder> {
    List<WebItemPhotoList> datalist = new ArrayList<>();
    Context context;
    UpdateImage updateImage;

    public WebItemPhotoListAdapter(List<WebItemPhotoList> datalist, Context context,UpdateImage updateImage) {
        this.datalist.addAll(datalist);
        this.context = context;
        this.updateImage = updateImage;
    }
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seeproduct_pictureinformation, parent, false);

        return new WebItemPhotoListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        WebItemPhotoList db = datalist.get(position);
        Picasso.with(context)
                .load(Gobal.IDImage + db.getImage())
                .fit()
                .centerCrop()
                .into(holder.image_seemanyprodcut_imfomation);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateImage.UpdateImage(position,db.getImage());
            }
        });

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image_seemanyprodcut_imfomation;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image_seemanyprodcut_imfomation= itemView.findViewById(R.id.image_seemanyprodcut_imfomation);
        }
    }
}

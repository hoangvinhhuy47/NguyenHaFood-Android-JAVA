package com.example.nguyenhafood.Apdater.Menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Interface.UpdateListMenuRecycleView;
import com.example.nguyenhafood.Model.Home.Item;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CatoryMenuItemAdapter extends RecyclerView.Adapter<CatoryMenuItemAdapter.MyViewHolder> {
    private Context context;
    List<Item> datalist = new ArrayList<>();
    UpdateListMenuRecycleView updateListMenuRecycleView;

    public CatoryMenuItemAdapter(List<Item> datalist, Context context, UpdateListMenuRecycleView updateListMenuRecycleView) {
        this.datalist.addAll(datalist);
        this.context = context;
        this.updateListMenuRecycleView = updateListMenuRecycleView;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_button_productportfolio, parent, false);
        return new CatoryMenuItemAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Item item = datalist.get(position);
        Picasso.with(context)
                .load(Gobal.IDImage + item.getCategory().getIconApp())
                .into(holder.img_menu_item);
        holder.name_menu_item.setText(item.getCategory().getCategoryName());
        Model.ChangeColor(item.isActive, holder.name_menu_item, holder.lni_catory_menu);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.isActive == false) {
                    updateListMenuRecycleView.UpdateCatoryMenu(position, item.getCategory().getProductCategoryID(), item.getCategory().getProductCategoryID());
                    for (int i = 0; i < datalist.size(); i++) {
                        if (i == position) {
                            datalist.get(i).setActive(true);
                        } else {
                            datalist.get(i).setActive(false);
                        }
                    }
                    item.setActive(true);
                    Model.ChangeColor(item.isActive, holder.name_menu_item, holder.lni_catory_menu);
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img_menu_item;
        TextView name_menu_item;
        LinearLayout lni_catory_menu;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            lni_catory_menu = itemView.findViewById(R.id.lni_catory_menu);
            img_menu_item = itemView.findViewById(R.id.img_menu_item);
            name_menu_item = itemView.findViewById(R.id.name_menu_item);
        }
    }
}

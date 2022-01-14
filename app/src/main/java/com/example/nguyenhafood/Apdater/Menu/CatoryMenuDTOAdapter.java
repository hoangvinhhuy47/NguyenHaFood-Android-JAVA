package com.example.nguyenhafood.Apdater.Menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Interface.UpdateListMenuRecycleView;
import com.example.nguyenhafood.Model.Home.Item;

import com.example.nguyenhafood.Model.Home.ItemDTO;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CatoryMenuDTOAdapter extends RecyclerView.Adapter<CatoryMenuDTOAdapter.MyViewHolder> {
    private Context context;
    List<ItemDTO> datalist = new ArrayList<>();
    private int selectedPos = RecyclerView.NO_POSITION;
    List<Item> abc = new ArrayList<>();
    UpdateListMenuRecycleView updateListMenuRecycleView;
    int select = 0;

    int index;

    public CatoryMenuDTOAdapter(List<ItemDTO> datalist, Context context, UpdateListMenuRecycleView updateListMenuRecycleView) {
        this.datalist.addAll(datalist);
        this.context = context;
        this.updateListMenuRecycleView = updateListMenuRecycleView;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_button_productsuggestion, parent, false);
        return new CatoryMenuDTOAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ItemDTO db = datalist.get(position);
        Picasso.with(context)
                .load(Gobal.IDImage + db.getCategoryRoot().getIconApp())
                .into(holder.img_menuc);
        holder.txt_tenMunec.setText(db.getCategoryRoot().getCategoryName());
        Model.ChangBackgroundMenuDTO(db.getiSActive(), holder.catory_menuDTO);
        holder.catory_menuDTO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db.getiSActive() == false) {
                    try {
                        updateListMenuRecycleView.UpdateCatoryItemMenu(position, db.getItemList().get(0).getCategory().getProductCategoryID(), db.getItemList().get(0).getCategory().getProductCategoryID(), db.getCategoryRoot().getPhoto());
                        for (int i = 0; i < datalist.size(); i++) {
                            if (i == position) {
                                datalist.get(i).setiSActive(true);
                            } else {
                                datalist.get(i).setiSActive(false);
                            }
                        }
                        db.setiSActive(true);
                        Model.ChangBackgroundMenuDTO(db.getiSActive(), holder.catory_menuDTO);
                        notifyDataSetChanged();
                    } catch (Exception exception) {
                        updateListMenuRecycleView.UpdateCatoryItemMenu(position, "", "", "");
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img_menuc;
        TextView txt_tenMunec;
        RelativeLayout catory_menuDTO;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img_menuc = itemView.findViewById(R.id.img_menuc);
            txt_tenMunec = itemView.findViewById(R.id.txt_tenMunec);
            catory_menuDTO = itemView.findViewById(R.id.catory_menuDTO);
        }
    }
}

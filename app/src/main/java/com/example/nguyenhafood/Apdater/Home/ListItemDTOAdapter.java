package com.example.nguyenhafood.Apdater.Home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenhafood.Fragment.MainActivity;
import com.example.nguyenhafood.Database.DBHelper;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Interface.UpdateListSeaRecycleView;
import com.example.nguyenhafood.Interface.UpdateSizeCart;
import com.example.nguyenhafood.Model.Home.ItemDTO;
import com.example.nguyenhafood.Model.Home.vw_WebItemWebsite;
import com.example.nguyenhafood.R;

import java.util.ArrayList;
import java.util.List;

public class ListItemDTOAdapter extends RecyclerView.Adapter<ListItemDTOAdapter.MyViewHolder> implements UpdateSizeCart {
    private Context context;
    List<ItemDTO> datalist = new ArrayList<>();
    DBHelper dbHelper;
    RecyclerView rcl_lstproduct_home;
    ListProductAdapter listProductAdapter;
    List<vw_WebItemWebsite> vw_webItemWebsites = new ArrayList<>();
    UpdateListSeaRecycleView updatePromotion;
    public ListItemDTOAdapter(List<ItemDTO> datalist, Context context, UpdateListSeaRecycleView updatePromotion) {
        this.datalist.addAll(datalist);
        this.context = context;
        this.updatePromotion =updatePromotion;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listcatory_producthome, parent, false);
        dbHelper = new DBHelper(context);
        rcl_lstproduct_home = view.findViewById(R.id.rcl_lstproduct_home);
        return new ListItemDTOAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ItemDTO db = datalist.get(position);
        holder.name_lstproduct_home.setText(db.getCategoryRoot().getCategoryName());
        holder.seemore_lstprodcut_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gobal.ShowFragment="Menu";
                Gobal.IDProduct = db.getCategoryRoot().getProductCategoryID();
                Gobal.IDCatoryProduct =db.getItemList().get(0).getCategory().getProductCategoryID();
                Intent intent = new Intent(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        for (int i = 0; i < datalist.size(); i++) {
            if (i == position) {
                vw_webItemWebsites.removeAll(vw_webItemWebsites);
                try {
                    for (int j = 0; j < datalist.get(i).getItemList().size(); j++) {
                        vw_webItemWebsites.addAll(datalist.get(i).getItemList().get(j).getProductList());
//                    break;
                    }
                    listProductAdapter = new ListProductAdapter(vw_webItemWebsites, context,this);
                    rcl_lstproduct_home.setAdapter(listProductAdapter);
                    if (vw_webItemWebsites.size()>2) {

                        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2, RecyclerView.HORIZONTAL, false);
                        rcl_lstproduct_home.setLayoutManager(gridLayoutManager);
                        break;
                    }
                    else {
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1, RecyclerView.HORIZONTAL, false);
                        rcl_lstproduct_home.setLayoutManager(gridLayoutManager);
                    }
                }
                catch (Exception exception){
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    @Override
    public void UpdateSize(int position1) {
        listProductAdapter = new ListProductAdapter(vw_webItemWebsites, context,this);
        listProductAdapter.notifyDataSetChanged();
        updatePromotion.CallBackView(position1,"");
    }

    @Override
    public void onRefresh() {

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name_lstproduct_home, seemore_lstprodcut_home;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name_lstproduct_home = itemView.findViewById(R.id.name_lstproduct_home);
            seemore_lstprodcut_home = itemView.findViewById(R.id.seemore_lstprodcut_home);
        }
    }
}

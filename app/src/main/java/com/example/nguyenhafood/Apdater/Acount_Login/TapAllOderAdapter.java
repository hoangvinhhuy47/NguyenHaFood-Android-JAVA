package com.example.nguyenhafood.Apdater.Acount_Login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenhafood.Interface.IAllOder;
import com.example.nguyenhafood.Model.Acount_Login.DataTapAllOder;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.R;

import java.util.ArrayList;
import java.util.List;

public class TapAllOderAdapter extends RecyclerView.Adapter<TapAllOderAdapter.MyViewHolder> {
    Context context;
    LinearLayoutManager layoutManager;
    private int currentSelected = 0;
    private IAllOder iAllOder;
    List<DataTapAllOder> datalist = new ArrayList<>();

    public TapAllOderAdapter(List<DataTapAllOder> datalist, Context context, IAllOder iAllOder,LinearLayoutManager layoutManager) {
        this.datalist.addAll(datalist);
        this.context = context;
        this.iAllOder = iAllOder;
        this.layoutManager = layoutManager;
    }

    @NonNull
    @Override
    public TapAllOderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vieworder_allorder_button, parent, false);
        return new TapAllOderAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TapAllOderAdapter.MyViewHolder holder, int position) {
        DataTapAllOder db = datalist.get(position);
        holder.btn_aloder.setText(db.getNametapAllOder());
        Model.ChangBackgroundTapAllOder(db.isiSActive(), holder.Relat_tabitem, holder.btn_aloder);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < datalist.size(); i++) {
                    if (i == position) {
                        datalist.get(i).setiSActive(true);
                    } else {
                        datalist.get(i).setiSActive(false);
                    }
                }
               // db.setiSActive(true);
                Model.ChangBackgroundTapAllOder(db.isiSActive(), holder.Relat_tabitem, holder.btn_aloder);
                notifyDataSetChanged();
                iAllOder.onItemClick(position);
                iAllOder.onItemClick1(position);
            }
        });
    }

    private View getViewByPosition(int pos) {
        if (layoutManager == null) {
            return null;
        }
        final int firstListItemPosition = layoutManager.findFirstVisibleItemPosition();
        final int lastListItemPosition = firstListItemPosition + layoutManager.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition) {
            return null;
        } else {
            final int childIndex = pos - firstListItemPosition;
            return layoutManager.getChildAt(childIndex);
        }
    }

    private void select(int position) {
        ;//updating dataset
        for (int i =0; i <4;i++){
            if (position==i){
                datalist.get(i).setiSActive(true);

            }
            else {
                datalist.get(i).setiSActive(false);
            }
        }
        if (currentSelected > 0) {
            deselect(currentSelected);
        }
        View targetView = getViewByPosition(position);
        if (targetView != null) {
            // change the appearance
            RelativeLayout Relat_tabitem = targetView.findViewById(R.id.Relat_tabitem);
            TextView btn_aloder = targetView.findViewById(R.id.ten_tapalloder);
            Model.ChangBackgroundTapAllOder(datalist.get(position).isiSActive(), Relat_tabitem, btn_aloder);
        }
        currentSelected = position;

    }

    private void deselect(int position) {
        for (int i =0; i <4;i++){
            if (position==i){
                datalist.get(i).setiSActive(false);
            }
        } //updating dataset
        notifyDataSetChanged();
        if (getViewByPosition(position) != null) {
            View targetView = getViewByPosition(position);
            if (targetView != null) {
                // change the appearance
                RelativeLayout Relat_tabitem = targetView.findViewById(R.id.Relat_tabitem);
                TextView btn_aloder = targetView.findViewById(R.id.ten_tapalloder);
                Model.ChangBackgroundTapAllOder(datalist.get(position).isiSActive(), Relat_tabitem, btn_aloder);
                notifyDataSetChanged();
            }
        }

        currentSelected = -1;
    }

    public void setCurrentSelected(int i) {
        select(i);
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView btn_aloder;
        RelativeLayout Relat_tabitem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            btn_aloder = itemView.findViewById(R.id.ten_tapalloder);
            Relat_tabitem = itemView.findViewById(R.id.Relat_tabitem);
        }
    }
}

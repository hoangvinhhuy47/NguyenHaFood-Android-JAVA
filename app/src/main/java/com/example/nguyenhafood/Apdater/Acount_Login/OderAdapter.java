package com.example.nguyenhafood.Apdater.Acount_Login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenhafood.Activity.Acount_login.ActivityOrderDetail;
import com.example.nguyenhafood.Model.Acount_Login.DataOder;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.R;

import java.util.ArrayList;
import java.util.List;

public class OderAdapter  extends RecyclerView.Adapter<OderAdapter.MyViewHolder> {
    private Context context;
    List<DataOder> orderList = new ArrayList<>();
    public OderAdapter(List<DataOder> oderList, Context context) {
        this.orderList.addAll(oderList);
        this.context = context;
    }
    @NonNull
    @Override
    public OderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vieworder_alloder, parent, false);
        return new OderAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OderAdapter.MyViewHolder holder, int position) {
        DataOder db = orderList.get(position);
        holder.text_madonhang_Oder.setText(db.getOrderCode());
        holder.text_Ngaydathang_oder.setText(Model.convertDateToString(Model.convertStringToDate(db.getCreatedDate())));
        holder.text_diachigiaohang_oder.setText(db.getShipAddress());
        holder.text_tongtien_oder.setText(Model.ChangeDecimal(db.getTotalAmount()));
        holder.text_status_oder.setText(Model.ChangeStatusOrder(db.getStatus(), holder.text_status_oder));
        if (position % 2 != 0) {
            holder.backgroud_view_oder.setBackgroundColor(Color.parseColor("#FAF1D8"));
        }
        else {
            holder.backgroud_view_oder.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityOrderDetail.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("OrderID", db.getOrderID());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
    public void  LoadMore(List<DataOder> dataOders1){
        orderList.addAll(dataOders1);
        notifyDataSetChanged();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text_madonhang_Oder, text_Ngaydathang_oder, text_diachigiaohang_oder, text_tongtien_oder, text_status_oder;
        RelativeLayout backgroud_view_oder;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text_madonhang_Oder = itemView.findViewById(R.id.text_madonhang_Oder);
            text_Ngaydathang_oder = itemView.findViewById(R.id.text_Ngaydathang_oder);
            text_diachigiaohang_oder = itemView.findViewById(R.id.text_diachigiaohang_oder);
            text_tongtien_oder = itemView.findViewById(R.id.text_tongtien_oder);
            text_status_oder = itemView.findViewById(R.id.text_status_oder);
            backgroud_view_oder = itemView.findViewById(R.id.backgroud_view_oder);
        }
    }
}
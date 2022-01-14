package com.example.nguyenhafood.Apdater.NOtification;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenhafood.Activity.Notification.ActivityNotificationDetail;
import com.example.nguyenhafood.Apdater.Imfomation.ProductRelationListAdapter;
import com.example.nguyenhafood.Database.DBHelper;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Model.Notification.DataNotification;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Untils.Model;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {
    List<DataNotification> datalist = new ArrayList<>();
    String style = "<style>img{display: inline;height:auto;max-width: 100%; max-height:350} iframe{display: inline;height: 300;max-width: 100%;min-width:100%; max-height:100%} p{font-weight:normal;font-family: Arial, Helvetica, sans-serif} #toc{display: inline;max-width: 100%; margin: 20px 0px !important}}</style>";
    Context context;
    public static int WRAP_CONTENT;

    public NotificationAdapter(Context context, List<DataNotification> datalist) {
        this.context = context;
        this.datalist.addAll(datalist);
    }
    public void loadmore(List<DataNotification> lst){
        datalist.addAll(lst);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
        return new NotificationAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataNotification db = datalist.get(position);
        Picasso.with(context)
                .load(Gobal.IDImage + db.getImage())
                .fit().centerCrop()
                .error(R.drawable.imageerro)
                .into(holder.img_notification);
        holder.caption_notifinews.setText(db.getNewsTitle());
        holder.shortder_notifinews.setText(db.getShortDescription());
        holder.date_notifinews.setText(Model.convertDateToStringnotifi(Model.convertStringToDate(db.getPublishDate())));
        holder.viewcount_notification.setText(String.valueOf(db.getViewCount()));
        holder.card_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityNotificationDetail.class);
                intent.putExtra("NewsID", db.getNewsID());
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);
            }
        });
        if (position % 2 !=0){
            holder.card_notification.setCardBackgroundColor(Color.parseColor("#FAF4E2"));
        }else {
            holder.card_notification.setCardBackgroundColor(Color.parseColor("#ffffff"));
        }

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img_notification;
        TextView caption_notifinews, shortder_notifinews, date_notifinews, viewcount_notification;
        CardView card_notification;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img_notification = itemView.findViewById(R.id.img_notification);
            caption_notifinews = itemView.findViewById(R.id.caption_notifinews);
            shortder_notifinews = itemView.findViewById(R.id.shortder_notifinews);
            date_notifinews = itemView.findViewById(R.id.date_notifinews);
            viewcount_notification = itemView.findViewById(R.id.viewcount_notification);
            card_notification = itemView.findViewById(R.id.card_notification);
        }
    }
}
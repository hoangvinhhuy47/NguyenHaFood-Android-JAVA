package com.example.nguyenhafood.Apdater.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.Model.Review.DataReviewList;
import com.example.nguyenhafood.R;

import java.util.ArrayList;
import java.util.List;

public class SeeReviewAdapter extends RecyclerView.Adapter<SeeReviewAdapter.MyViewHolder> {
    List<DataReviewList> dataReviewLists = new ArrayList<>();
    Context context;
    public SeeReviewAdapter(List<DataReviewList> dataReviewLists, Context context) {
        this.dataReviewLists.addAll(dataReviewLists);
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seereview_product, parent, false);
        return new SeeReviewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataReviewList lst = dataReviewLists.get(position);
        holder.fullname_review_product.setText(lst.getFullName());
        holder.img_menu_item.setText(lst.getFullName().substring(0,1));
        holder.text_title_reviewproduct.setText(lst.getReviewTitle());
        holder.text_content_review.setText(lst.getReviewContent());
        if (holder.text_content_review.getText().toString().length()>50 ){
            holder.text_content_review.setText(lst.getReviewContent().substring(0,50)+"....Xem Thêm.");
            holder.text_content_review.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.text_content_review.setText(lst.getReviewContent());
                }
            });
        }
        holder.star_review_user.setRating(Float.parseFloat(String.valueOf(lst.getReviewValue())));
        holder.text_review_date.setText("Nhận Xét Vào Ngày: "+Model.convertDateToString(Model.convertStringToDate(lst.getReviewDate())));

    }

    @Override
    public int getItemCount() {
        return dataReviewLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView img_menu_item,fullname_review_product,text_title_reviewproduct,text_content_review,text_review_date;
        RatingBar star_review_user;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text_review_date = itemView.findViewById(R.id.text_review_date);
            img_menu_item=itemView.findViewById(R.id.img_menu_item);
            fullname_review_product=itemView.findViewById(R.id.fullname_review_product);
            text_title_reviewproduct=itemView.findViewById(R.id.text_title_reviewproduct);
            text_content_review=itemView.findViewById(R.id.text_content_review);
            star_review_user=itemView.findViewById(R.id.star_review_user);
        }
    }
}

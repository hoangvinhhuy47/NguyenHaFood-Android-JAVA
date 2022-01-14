package com.example.nguyenhafood.Apdater.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Model.Home.vw_SlideDetail;
import com.example.nguyenhafood.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SlideAdapter extends PagerAdapter {
    List<vw_SlideDetail> datalist = new ArrayList<>();
    Context context;
    public SlideAdapter(List<vw_SlideDetail> datalist, Context context){
        this.datalist.addAll(datalist);
        this.context = context;
    }
    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((View) object);

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view =layoutInflater.inflate(R.layout.item_layout,container,false);
        ImageView imageView ;
        imageView = view.findViewById(R.id.img_slide);
        Picasso.with(context)
                .load(Gobal.IDImage + datalist.get(position).getImage())
                .fit()
                .centerCrop()
                .error(R.drawable.imageerro)
                .into(imageView);
        container.addView(view,0);

        return view;
    }
}

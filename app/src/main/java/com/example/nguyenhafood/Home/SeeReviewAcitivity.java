package com.example.nguyenhafood.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenhafood.API.APIClient;
import com.example.nguyenhafood.Apdater.Home.SeeReviewAdapter;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.Model.Review.DataReviewList;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.Home.ReViewProductRequest;
import com.example.nguyenhafood.Response.Home.ReViewProductResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeeReviewAcitivity extends AppCompatActivity {
    TextView review_total,review_total_star;
    RatingBar star_total;
    ProgressBar review_value1,review_value2,review_value3,review_value4,review_value5;
    TextView amount_5,amount_4,amount_3,amount_2,amount_1;
    SeeReviewAdapter seeReviewAdapter;
    ImageView backaccount;
    List<DataReviewList> dataReviewLists = new ArrayList<>();
    RecyclerView rcl_review_product;
    Intent intent ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = this.getIntent();
        setContentView(R.layout.acitivity_seereview_product);
        review_total = findViewById(R.id.review_total);
        backaccount=findViewById(R.id.backaccount);
        backaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        review_total_star = findViewById(R.id.review_total_star);
        star_total = findViewById(R.id.star_total);
        rcl_review_product=findViewById(R.id.rcl_review_product);
        review_value1=findViewById(R.id.review_value1);
        review_value2=findViewById(R.id.review_value2);
        review_value3=findViewById(R.id.review_value3);
        review_value4=findViewById(R.id.review_value4);
        review_value5=findViewById(R.id.review_value5);
        amount_5=findViewById(R.id.amount_5);
        amount_4=findViewById(R.id.amount_4);
        amount_3=findViewById(R.id.amount_3);
        amount_2=findViewById(R.id.amount_2);
        amount_1=findViewById(R.id.amount_1);
        ReViewProductRequest reViewProductRequest = new ReViewProductRequest();
        reViewProductRequest.setProductID(intent.getStringExtra("ProductID"));
        GetReViewProduct(reViewProductRequest);

    }
    public void GetReViewProduct(ReViewProductRequest reViewProductRequest){
        Call<ReViewProductResponse> reViewProductResponseCall = APIClient.reViewProductService().GetReviewList(Gobal.GuiID,reViewProductRequest);
        reViewProductResponseCall.enqueue(new Callback<ReViewProductResponse>() {
            @Override
            public void onResponse(Call<ReViewProductResponse> call, Response<ReViewProductResponse> response) {
                if (response.isSuccessful()){
                    ReViewProductResponse reViewProductResponse = response.body();
                    if (reViewProductResponse.getStatusID()==1){
                        if (reViewProductResponse.getReviewToTal()!=null){
                        }
                        if (reViewProductResponse.getReviewList().size()!=0) {
                            try {
                                star_total.setRating(Float.parseFloat(intent.getStringExtra("ReViewCount")));
                                review_total_star.setText(intent.getStringExtra("ReViewCount"));
                                review_total.setText(reViewProductResponse.getReviewList().size() + "  Nhận Xét");
                                seeReviewAdapter = new SeeReviewAdapter(reViewProductResponse.getReviewList(), getApplication());
                                rcl_review_product.setAdapter(seeReviewAdapter);
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
                                rcl_review_product.setLayoutManager(linearLayoutManager);
                                Model.ChangeValueCount1(reViewProductResponse.getReviewList().size(), review_value5, review_value4, review_value3, review_value2, review_value1, reViewProductResponse.getReviewToTal());
                                Model.changeValueStar1(reViewProductResponse.getReviewToTal(),amount_5,amount_4,amount_3,amount_2,amount_1);
                            }
                            catch (Exception exception){
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ReViewProductResponse> call, Throwable t) {
                Model.Dialog(getApplicationContext());
            }
        });
    }
}

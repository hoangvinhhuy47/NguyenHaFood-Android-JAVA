package com.example.nguyenhafood.Home;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nguyenhafood.API.APIClient;
import com.example.nguyenhafood.Database.DBHelper;
import com.example.nguyenhafood.Gobal.Gobal;

import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.Model.Review.DataWriteReview;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.Home.WriteReviewRequest;
import com.example.nguyenhafood.Response.Home.WriteReviewReponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WriteReviewActivity extends AppCompatActivity {
    RelativeLayout back_vietdanhgia;
    Button btn_guidanhgia;
    RatingBar danhgiangoisao;
    EditText text_content_writereview;
    TextView text_title_writereview;
    DBHelper db;
    TextView camera;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vietdanhgia);
        db = new DBHelper(getApplication());
        camera = findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                int REQUEST_ID_IMAGE_CAPTURE = 100;
// Start Activity chụp hình, và chờ đợi kết quả trả về.
                startActivity(intent);
            }
        });
        text_content_writereview = findViewById(R.id.text_content_writereview);

        text_title_writereview = findViewById(R.id.text_title_writereview);

        danhgiangoisao = findViewById(R.id.danhgiangoisao);
        danhgiangoisao.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (ratingBar.getRating() == 1){
                    text_title_writereview.setText("Rất Không Hài Lòng");
                }
                if (ratingBar.getRating()==2){
                    text_title_writereview.setText("Không Hài Lòng");
                }
                if (ratingBar.getRating()==3){
                    text_title_writereview.setText("Bình Thường");
                }
                if (ratingBar.getRating()==4){
                    text_title_writereview.setText("Hài Lòng");
                }
                if (ratingBar.getRating()==5){
                    text_title_writereview.setText("Rất Hài Lòng");
                }
            }
        });
        btn_guidanhgia = findViewById(R.id.btn_guidanhgia);
        Intent intent = this.getIntent();
        btn_guidanhgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text_content_writereview.getText().toString().equals("") || text_title_writereview.getText().toString().equals("")) {
                    text_content_writereview.setError("Vui lòng nhập đầy đủ");
                } else {
                    DataWriteReview dataWriteReview = new DataWriteReview();
                    dataWriteReview.setItemID(intent.getStringExtra("ItemID"));
                    dataWriteReview.setCustomerID(db.GetDL());
                    dataWriteReview.setReviewTitle(text_title_writereview.getText().toString());
                    dataWriteReview.setReviewContent(text_content_writereview.getText().toString());
                    dataWriteReview.setReviewStatus("1");
                    dataWriteReview.setReviewValue(String.valueOf(danhgiangoisao.getRating()));
                    WriteReviewRequest writeReviewRequest = new WriteReviewRequest();
                    writeReviewRequest.setReview(dataWriteReview);
                    LoadWriteReview(writeReviewRequest);
                }
            }
        });
        back_vietdanhgia = findViewById(R.id.back_vietdanhgia);
        back_vietdanhgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void LoadWriteReview(WriteReviewRequest writeReviewRequest) {
        Call<WriteReviewReponse> writeReviewReponseCall = APIClient.getWriteReviewService().WriteReview(Gobal.GuiID, writeReviewRequest);
        writeReviewReponseCall.enqueue(new Callback<WriteReviewReponse>() {
            @Override
            public void onResponse(Call<WriteReviewReponse> call, Response<WriteReviewReponse> response) {
                if (response.isSuccessful()) {
                    WriteReviewReponse writeReviewReponse = response.body();
                    if (writeReviewReponse.getStatusID() == 1) {
                        finish();
                    }
                } else {
                    Toast.makeText(getApplication(), "lỗi", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<WriteReviewReponse> call, Throwable t) {
                Model.Dialog(getApplicationContext());
            }
        });
    }
}
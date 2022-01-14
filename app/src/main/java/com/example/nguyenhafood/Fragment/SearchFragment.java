package com.example.nguyenhafood.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyenhafood.API.APIClient;


import com.example.nguyenhafood.Activity.ViewCartsAcivity;
import com.example.nguyenhafood.Apdater.Search.ProductlistSearchAdapter;
import com.example.nguyenhafood.Database.DBHelper;
import com.example.nguyenhafood.Gobal.Gobal;
import com.example.nguyenhafood.Interface.UpdateSizeCart;
import com.example.nguyenhafood.Model.Home.vw_WebItemWebsite;

import com.example.nguyenhafood.Untils.Model;
import com.example.nguyenhafood.R;
import com.example.nguyenhafood.Request.Home.SearchRequest;
import com.example.nguyenhafood.Response.Home.SearchResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends AppCompatActivity implements UpdateSizeCart {
    private EditText searchview;

    private RelativeLayout cart_see;
    private ProgressBar progressBar1;
    private ImageView btm_search;
    int pageindex = 1;
    Boolean isScrolling = false;
    String searchstring;
    RecyclerView rcv_productsearch;
    DBHelper dbHelper;
    ProductlistSearchAdapter productlistSearchAdapter;
    List<vw_WebItemWebsite> searchlist = new ArrayList<>();
    TextView cart_badge;
    GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplication(), 2, RecyclerView.VERTICAL, false);
    int qualtitifind=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search);

        rcv_productsearch = findViewById(R.id.rcv_productsearch);
        cart_badge = findViewById(R.id.cart_badge);
        cart_badge.setText(String.valueOf(Gobal.SizeProduct));
        progressBar1 = findViewById(R.id.progressbar1);
        dbHelper = new DBHelper(getApplication());
        cart_see = findViewById(R.id.cart_see);
        searchview = findViewById(R.id.searchview);
        searchview.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (searchview.getText().toString().equals("") ==false) {
                    progressBar1.setVisibility(View.VISIBLE);
                    pageindex = 1;
                    searchlist.removeAll(searchlist);
                    SearchRequest searchRequest = new SearchRequest();
                    searchRequest.setSearchString(searchview.getText().toString());
                    searchRequest.setPageIndex(String.valueOf(pageindex));
                    searchRequest.setUserID(dbHelper.GetDL());
                    LoadSearchDTO(searchRequest);
                    if (searchRequest.getSearchString().equals("") == false) {
                        rcv_productsearch.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                                super.onScrollStateChanged(recyclerView, newState);
                                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                                    if (!isScrolling) {
                                        if (gridLayoutManager != null && gridLayoutManager.findLastVisibleItemPosition() == searchlist.size() - 1) {
                                            pageindex = pageindex + 1;
                                            progressBar1.setVisibility(View.VISIBLE);
                                            CountDownTimer countDownTimer = new CountDownTimer(800, 400) {
                                                @Override
                                                public void onTick(long millisUntilFinished) {
                                                    loadMore(searchRequest.getSearchString(), pageindex);

                                                }

                                                @Override
                                                public void onFinish() {
                                                    progressBar1.setVisibility(View.GONE);
                                                }
                                            }.start();

                                        }
                                    } else {
                                        isScrolling = true;
                                    }
                                }
                            }
                        });
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
//                Model.hideKeyboardFrom(SearchFragment.this, searchview);
            }
        });


        cart_see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), ViewCartsAcivity.class);
                startActivity(intent);
            }
        });

        searchstring = searchview.getText().toString();
        btm_search = findViewById(R.id.btm_search);

        btm_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchview.getText().toString().equals("") == false) {
                    pageindex = 1;
                    searchlist.removeAll(searchlist);
                    SearchRequest searchRequest = new SearchRequest();
                    searchRequest.setSearchString(searchview.getText().toString());
                    searchRequest.setPageIndex(String.valueOf(pageindex));
                    searchRequest.setUserID(dbHelper.GetDL());
                    LoadSearchDTO(searchRequest);
                    if (searchRequest.getSearchString().equals("") == false) {
                        rcv_productsearch.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                                super.onScrollStateChanged(recyclerView, newState);
                                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                                    if (!isScrolling) {
                                        if (gridLayoutManager != null && gridLayoutManager.findLastVisibleItemPosition() == searchlist.size() - 1) {
                                            pageindex = pageindex + 1;
                                            recyclerView.setVisibility(View.GONE);
                                            progressBar1.setVisibility(View.VISIBLE);
                                            CountDownTimer countDownTimer = new CountDownTimer(800, 400) {
                                                @Override
                                                public void onTick(long millisUntilFinished) {
                                                    loadMore(searchRequest.getSearchString(), pageindex);
                                                }

                                                @Override
                                                public void onFinish() {
                                                    recyclerView.setVisibility(View.VISIBLE);
                                                    progressBar1.setVisibility(View.GONE);
                                                }
                                            }.start();

                                        }
                                    } else {
                                        isScrolling = true;
                                    }
                                }
                            }
                        });

                    }
                }
                else {
                    searchview.setError("Vui lòng nhập từ khóa tìm kiếm");
                }
            }
        });


    }

    public void loadMore(String searchstring, int pageindex) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setPageIndex(String.valueOf(pageindex));
        searchRequest.setSearchString(searchstring);
        searchRequest.setUserID(dbHelper.GetDL());
        LoadSearchMoreDTO(searchRequest);
    }

    public void LoadSearchDTO(SearchRequest searchRequest) {
        Call<SearchResponse> searchResponseCall = APIClient.getSearchDTO().SearchDTO(Gobal.GuiID, searchRequest);
        searchResponseCall.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.isSuccessful()) {
                    SearchResponse searchResponse = response.body();
                    if (searchResponse.getStatusID() == 1) {
                        qualtitifind=0;
                        for (int i = 0; i < searchResponse.getHomeData().getProductList().size(); i++) {
                            searchlist.addAll(searchResponse.getHomeData().getProductList());
                            rcv_productsearch.setVisibility(View.VISIBLE);
                            break;
                        }
                        qualtitifind=searchResponse.getHomeData().getProductList().size();
                        productlistSearchAdapter = new ProductlistSearchAdapter(getApplication(), searchlist, SearchFragment.this);
                        rcv_productsearch.setAdapter(productlistSearchAdapter);
                        rcv_productsearch.setLayoutManager(gridLayoutManager);

                        progressBar1.setVisibility(View.GONE);
                    } else {
                        progressBar1.setVisibility(View.GONE);
                    }
                }

            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Model.Dialog(getApplicationContext());
            }
        });
    }

    public void LoadSearchMoreDTO(SearchRequest searchRequest) {
        progressBar1.setVisibility(View.VISIBLE);
        Call<SearchResponse> searchResponseCall = APIClient.getSearchDTO().SearchDTO(Gobal.GuiID, searchRequest);
        searchResponseCall.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.isSuccessful()) {
                    SearchResponse searchResponse = response.body();
                    if (searchResponse.getStatusID() == 1) {
                        if (searchResponse.getHomeData().getProductList().size() != 0) {
                            qualtitifind = qualtitifind+searchResponse.getHomeData().getProductList().size();

                            productlistSearchAdapter.LoadMore(searchResponse.getHomeData().getProductList());
                            searchlist.addAll(searchResponse.getHomeData().getProductList());
                            progressBar1.setVisibility(View.GONE);
                        } else {
                            isScrolling = true;
                            progressBar1.setVisibility(View.GONE);
                        }
                    }

                }

            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Model.Dialog(getApplicationContext());
            }
        });
    }

    @Override
    public void UpdateSize(int position1) {
        productlistSearchAdapter = new ProductlistSearchAdapter(getApplication(), searchlist, this);
        productlistSearchAdapter.notifyDataSetChanged();
        cart_badge.setText(String.valueOf(Gobal.SizeProduct));
    }

    @Override
    public void onRefresh() {

    }
}
